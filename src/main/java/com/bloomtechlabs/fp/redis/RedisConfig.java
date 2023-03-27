package com.bloomtechlabs.fp.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;


/*
------- DOCUMENTATION -------
The RedisConfig class is a Spring configuration class that provides configuration for Redis caching.
It uses Spring's @Configuration annotation to indicate that it is a configuration class and @EnableCaching to enable caching for the application.

The @AutoConfigureAfter annotation is used to specify that the Redis configuration should be done after the RedisAutoConfiguration class is configured.
This ensures that the Redis configuration is done after Redis is initialized.

The RedisConfig class has two methods: redisCacheTemplate() and cacheManager().
The first method creates a RedisTemplate for caching and the second method creates a CacheManager for caching.

Configuration Properties:
The Redis host and port are read from the Spring configuration properties using the @Value annotation.
These properties are injected into the RedisConfig class and used to configure the Redis connection factory.

By A.M., Feb 25 2023
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
public class RedisConfig {

//    @Autowired
//    private CacheManager cacheManager;

//    @Value("${spring.redis.host}")
//    private String redisHost;
//
//    @Value("${spring.redis.port}")
//    private int redisPort;

    /*
    Redis Cache Template
    The redisCacheTemplate() method creates a RedisTemplate bean with a LettuceConnectionFactory.
    The RedisTemplate is a generic class that takes two parameters: the key and value types. In this case, the key type is String and the value type is Serializable.
    The template.setKeySerializer(new StringRedisSerializer()) line sets the key serializer to the StringRedisSerializer, which serializes the keys as strings.
    The template.setValueSerializer(new GenericJackson2JsonRedisSerializer()) line sets the value serializer to the GenericJackson2JsonRedisSerializer,
    which serializes the values using Jackson's JSON serializer.
     */

    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /*
    Cache Manager
    The cacheManager() method creates a CacheManager bean with a RedisConnectionFactory.
    The RedisCacheConfiguration is used to configure the caching behavior, and RedisCacheManager is used to manage the cache..
     */

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration redisCacheConfiguration = config
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration)
                .build();
        return redisCacheManager;
    }

}

