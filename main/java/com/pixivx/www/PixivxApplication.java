package com.pixivx.www;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.pixivx.www.mapper")
@EntityScan("com.pixivx.www.entity")
@ComponentScan("com.pixivx.www")
public class PixivxApplication extends ServletInitializer{
    protected static final Logger logger = LoggerFactory.getLogger(PixivxApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(PixivxApplication.class);
    }
    public static void main(String[] args) {
        logger.info("============= SpringBoot Start Success =============");
        SpringApplication.run(PixivxApplication.class, args);
        logger.info("============= SpringBoot end =============");
    }
}
