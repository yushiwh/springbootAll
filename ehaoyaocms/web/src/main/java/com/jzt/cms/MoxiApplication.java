package com.jzt.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.jztey.framework.boot.ApplicationDruid;
import com.jztey.framework.boot.ApplicationDubbo;

@SpringBootApplication
@Import({ ApplicationDruid.class, ApplicationDubbo.class })
public class MoxiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoxiApplication.class, args);
	}
}
