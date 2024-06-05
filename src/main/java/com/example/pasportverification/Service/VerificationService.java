package com.example.pasportverification.Service;

import com.example.pasportverification.Entity.Verification;
import com.example.pasportverification.Entity.pasportdto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public interface VerificationService {

   public String getVerify(Verification dto, HttpServletRequest request, HttpServletResponse response);
   
   public String getDetails(pasportdto pasportdto, HttpServletRequest request, HttpServletResponse response);

}
