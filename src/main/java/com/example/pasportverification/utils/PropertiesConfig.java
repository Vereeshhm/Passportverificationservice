package com.example.pasportverification.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:src/main/resources/application.properties")
public class PropertiesConfig {

	
	@Value("${verification.ApiURl}")
	private String verificationApiURl;

	public String getVerificationApiURl() {
		return verificationApiURl;
	}

	public void setVerificationApiURl(String verificationApiURl) {
		this.verificationApiURl = verificationApiURl;
	}
	
	@Value("${pport.ApiURl}")                      
	private String pportApiURl;

	public String getPportApiURl() {
		return pportApiURl;
	}

	public void setPportApiURl(String pportApiURl) {
		this.pportApiURl = pportApiURl;
	}
	
}
