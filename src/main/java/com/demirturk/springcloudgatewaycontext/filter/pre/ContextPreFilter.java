package com.demirturk.springcloudgatewaycontext.filter.pre;

import com.demirturk.springcloudgatewaycontext.context.ThreadLocalContext;
import com.demirturk.springcloudgatewaycontext.context.ThreadLocalHolder;
import io.micrometer.context.ContextRegistry;
import io.micrometer.context.ContextSnapshot;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ContextPreFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ContextRegistry registry = new ContextRegistry();
        registry.registerThreadLocalAccessor(new ThreadLocalContext());

        ThreadLocalHolder.setValue("Hello  ");
        ContextSnapshot snapshot = ContextSnapshot.captureAllUsing(key -> true, registry);

        ThreadLocalHolder.setValue("hola");
        try {
            try (ContextSnapshot.Scope scope = snapshot.setThreadLocals()) {
                System.out.println("First TRY  " + ThreadLocalHolder.getValue());
            }
            System.out.println("Second  TRY  " + ThreadLocalHolder.getValue());
        }
        finally {
            System.out.println("finally");
        }


        return chain.filter(exchange);
    }
}
