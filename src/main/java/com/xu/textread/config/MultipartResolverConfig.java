package com.xu.textread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author aniki
 * @CreteDate 2023/2/8 19:09
 **/
@Configuration
public class MultipartResolverConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }
}
