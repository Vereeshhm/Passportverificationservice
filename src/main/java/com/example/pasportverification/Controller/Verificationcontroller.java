package com.example.pasportverification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pasportverification.Entity.Verification;
import com.example.pasportverification.Entity.pasportdto;

import com.example.pasportverification.Service.VerificationService;


@RestController
public class Verificationcontroller {

	@Autowired
	VerificationService service;
	
	@PostMapping("api/passport/verify")
	 public String Verify(@RequestBody Verification dto)
	 {
		
		 return service.getVerify(dto);
	 }
	
	
	@PostMapping("api/passport/fetch")
	public String details(@RequestBody pasportdto pasportdto){
		
		
		
		return service.getDetails(pasportdto);
	}
	
	
}
