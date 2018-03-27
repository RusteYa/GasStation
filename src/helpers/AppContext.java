package helpers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Rustem.
 */
public class AppContext {
    private static ApplicationContext ac;

    public static ApplicationContext getApplicationContext() {
        if (ac == null) {
            ac = new ClassPathXmlApplicationContext("spring-config.xml");
        }
        return ac;
    }
}
