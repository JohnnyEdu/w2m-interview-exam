package org.w2m.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Configuration
public class RedisConfiguration {
    @Value("${redis.all-super-hero-cache-ttl}")
    public int ALL_SUPER_HERO_CACHE_TTL = 30;

    @Value("${redis.super-hero-cache-ttl}")
    public int SUPER_HERO_CACHE_TTL = 20;

    @Value("${redis.all-super-power-cache-ttl}")
    public int ALL_SUPER_POWER_CACHE_TTL = 60;//this shouldn't change frequently

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("allSuperHeroCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(ALL_SUPER_HERO_CACHE_TTL)))
                .withCacheConfiguration("superHeroCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(SUPER_HERO_CACHE_TTL)))
                .withCacheConfiguration("allSuperPowerCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(ALL_SUPER_POWER_CACHE_TTL)));
    }
}
