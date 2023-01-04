package com.HogwartsForum.util;

import com.HogwartsForum.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    String databaseAccessType;
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptFactory() {
        return new BCryptPasswordEncoder();
    }

    // TODO uncomment it when you are ready to test it
//    @PostConstruct
//    public void createDefaultAdmin() {
//        if (databaseAccessType.equals("create")) {
//            // TODO add role when roles created
//            HogwartsUser defaultAdmin = new HogwartsUser("admin", "admin", "gryffindor", "owl");
//            userService.addUser(defaultAdmin);
//        }
//    }
}
