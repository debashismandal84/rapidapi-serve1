package com.example.microserv.rapidapiservone.config;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
@RestController
@Component
public class GatewayConfig {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EurekaClient eurekaClient;

    WebClient webclient = WebClient.builder().build();
    @Autowired
    @Value("${app.other.dbserv}")
    private String dbServPath;

    @Bean
    @LoadBalanced
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        System.out.println("----dbServPath----"+dbServPath);
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://httpbin.org:80"))
             //   .route(p -> p.path("/hello/*").uri("lb://micro-dbserv/hello/first/"))
                .route(p -> p.path("/hello/*").uri("http://google.com"))
                .build();
    }

    @RequestMapping("/dbdtl")
    public String getDBValue() {
        System.out.println("startign------");
        String str = eurekaClient.getApplication("micro-dbserv").getInstances().get(0).getHomePageUrl();
        System.out.println("-----quoteResponse---1111---------"+str);

        ResponseEntity<String> quoteResponse = restTemplate.exchange("http://micro-dbserv/hello/first/", HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                });
        System.out.println("-----quoteResponse---1111---------"+str);
        System.out.println("-----quoteResponse------------"+quoteResponse);

        return quoteResponse.getBody();

    }



    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
