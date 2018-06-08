package com.kpfu.itis.gasstation.config;


import com.kpfu.itis.gasstation.service.entities.AppUserService;
import com.kpfu.itis.gasstation.service.social.ConnectionSignUpImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

/**
 * Created by Rustem.
 */
@Configuration
@EnableSocial
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {
    private boolean autoSignUp = false;

    private final DataSource dataSource;
    private final AppUserService appUserService;

    @Autowired
    public SocialConfig(@Qualifier("dataSource") DataSource dataSource, AppUserService appUserService) {
        this.dataSource = dataSource;
        this.appUserService = appUserService;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        try {
            this.autoSignUp = Boolean.parseBoolean(env.getProperty("social.auto-signup"));
        } catch (Exception e) {
            this.autoSignUp = false;
        }

        GoogleConnectionFactory gfactory = new GoogleConnectionFactory(//
                env.getProperty("google.client.id"), //
                env.getProperty("google.client.secret"));

        gfactory.setScope(env.getProperty("google.scope"));
        cfConfig.addConnectionFactory(gfactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText());

        if (autoSignUp) {
            ConnectionSignUp connectionSignUp = new ConnectionSignUpImpl(appUserService);
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        } else {
            usersConnectionRepository.setConnectionSignUp(null);
        }
        return usersConnectionRepository;
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
