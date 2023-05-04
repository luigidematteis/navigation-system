package com.wemakesoftware.navigationsystem;

import jakarta.servlet.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.*;
import org.springframework.boot.web.servlet.*;
import org.springframework.boot.web.servlet.support.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class NavigationsystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NavigationsystemApplication.class, args);
	}

}


