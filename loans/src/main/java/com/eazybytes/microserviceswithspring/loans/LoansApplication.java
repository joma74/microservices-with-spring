package com.eazybytes.microserviceswithspring.loans;

import com.eazybytes.microserviceswithspring.loans.config.LoansServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.eazybytes.microserviceswithspring.loans.repository")
@EntityScan("com.eazybytes.microserviceswithspring.loans.model")
@EnableConfigurationProperties(LoansServiceConfig.class)
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }


}
