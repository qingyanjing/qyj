package org.wisdom.pms.bp.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="org.wisdom.pms.bp")
@MapperScan(basePackages = {"org.wisdom.pms.bp.dao.*"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
