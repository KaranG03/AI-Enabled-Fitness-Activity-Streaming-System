package com.fitness.gateway.user;



import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    // ye use kiya kyuki ham user service ki api ko call krenege jo ki activity service ke liye external api hai
    // aur ham usse direct naam se call kr skte hai
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("http://user-service") //userservice ki yml file me jo naam likha hai vhi capital letters me yaha likha jata hai
                .build();
    }
}