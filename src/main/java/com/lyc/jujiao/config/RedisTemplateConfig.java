package com.lyc.jujiao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 *
 */
@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
//        redisTemplate.setValueSerializer(new RedisJsonSerializerImpl<>());
        return redisTemplate;
    }

    // @Bean
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    //     RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    //     redisTemplate.setConnectionFactory(connectionFactory);
    //     redisTemplate.setKeySerializer(new StringRedisSerializer());
    //     redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    //     return redisTemplate;
    // }

    // 这里才是设置session序列化的方法
//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//        // 因为我使用的是FastJson
//        // 如果使用别的请自行设置，就是返回时返回序列化类即可
//        return new FastJsonRedisSerializer(Object.class);
//    }
}
