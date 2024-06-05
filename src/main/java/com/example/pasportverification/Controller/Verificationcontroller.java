package com.example.pasportverification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pasportverification.Entity.Verification;
import com.example.pasportverification.Entity.pasportdto;
import com.example.pasportverification.Service.VerificationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class Verificationcontroller {

	@Autowired
	VerificationService service;

	@PostMapping("api/passport/verify")
	public String Verify(@RequestBody Verification dto, HttpServletRequest request, HttpServletResponse response) {

		return service.getVerify(dto, request, response);
	}

	@PostMapping("api/passport/fetch")
	public String details(@RequestBody pasportdto pasportdto, HttpServletRequest request,
			HttpServletResponse response) {

		return service.getDetails(pasportdto, request, response);
	}

}
