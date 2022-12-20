package com.demirturk.springcloudgatewaycontext.filter.post;

import com.demirturk.springcloudgatewaycontext.context.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ContextPostFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            var response = exchange.getResponse();

            System.out.println("PostFilter " + ThreadLocalHolder.getValue());

            exchange.mutate().response(response).build();
        }));
    }
}
