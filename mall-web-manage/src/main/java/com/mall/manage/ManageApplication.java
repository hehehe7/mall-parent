package com.mall.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@MapperScan(value= "com.mall.dao.mapper")
@ComponentScan(basePackages={"com.mall"})
@EntityScan(basePackages = "com.mall.dao.entity")
@EnableJpaRepositories(basePackages = { "com.mall.dao.repository" })
public class ManageApplication {

	public static void main(String[] args) {
        System.out.println("SpringBoot      ==>开始启动");
	    SpringApplication.run(ManageApplication.class, args);
        System.out.println("SpringBoot      ==>启动成功");
	}

}
