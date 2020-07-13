package com.sbs.vc;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sbs.vc.file.FileStorageProperties;
@EnableScheduling
@SpringBootApplication
@EntityScan(basePackageClasses = {
		AuroraApplication.class,
		Jsr310JpaConverters.class
})
@EnableConfigurationProperties({FileStorageProperties.class})
public class AuroraApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(AuroraApplication.class, args);
	}
}
