package com.kpfu.itis.gasstation.service.social;

import com.kpfu.itis.gasstation.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Rustem.
 */
@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
    private final UserDetailsService userDetailService;

    @Autowired
    public SocialUserDetailsServiceImpl(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userName) throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = userDetailService.loadUserByUsername(userName);
        return (AppUser) userDetails;
    }
}