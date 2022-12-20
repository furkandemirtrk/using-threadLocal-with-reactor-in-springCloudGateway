package com.demirturk.springcloudgatewaycontext.gateway;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class Gateway extends AbstractGatewayFilterFactory<Gateway.Config> {


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
                log.info("Pre GatewayFilter logging: ");
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> log.info("Post GatewayFilter logging: ")));
        };
    }

    public static class Config {
    }
}
