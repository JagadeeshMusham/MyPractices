package com.musham.mySpringProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
/**
 * We can add this code in Main Class also i.e., MySpringProjectApplication,
 * but it is good practice to add in separate configuration file
 *
 * Below annotation will enable transactional support i.e., if we define a method with
 * @Transactional annotation, on failures the roll-back will be taken care by SpringBoot framework
 */
@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory trManager) {
        return new MongoTransactionManager(trManager);
    }
}
