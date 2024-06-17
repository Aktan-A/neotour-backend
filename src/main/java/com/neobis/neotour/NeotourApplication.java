package com.neobis.neotour;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class NeotourApplication {

	@Value("${cloudinary.cloud_name}")
	private String cloudinary_cloud_name;

	@Value("${cloudinary.api_key}")
	private String cloudinary_api_key;

	@Value("${cloudinary.api_secret}")
	private String cloudinary_api_secret;

	public static void main(String[] args) {
		SpringApplication.run(NeotourApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Cloudinary cloudinary() {
		Cloudinary cloudinary = null;
		Map config = new HashMap();
		config.put("cloud_name", cloudinary_cloud_name);
		config.put("api_key", cloudinary_api_key);
		config.put("api_secret", cloudinary_api_secret);
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}

}
