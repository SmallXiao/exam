package com.alvis.exam;

import com.alvis.exam.configuration.property.SystemConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author alvis
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.alvis.exam.repository")
@EnableConfigurationProperties(value = { SystemConfig.class})
@ServletComponentScan
@EnableCaching
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }
}
