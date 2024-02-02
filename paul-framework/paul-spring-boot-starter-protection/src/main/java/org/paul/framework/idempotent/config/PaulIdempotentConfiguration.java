package org.paul.framework.idempotent.config;

import org.paul.framework.idempotent.core.aop.IdempotentAspect;
import org.paul.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import org.paul.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import org.paul.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import org.paul.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.paul.framework.redis.config.PaulRedisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = PaulRedisAutoConfiguration.class)
public class PaulIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
