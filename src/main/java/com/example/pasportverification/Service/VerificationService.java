package com.example.pasportverification.Service;

import com.example.pasportverification.Entity.Verification;
import com.example.pasportverification.Entity.pasportdto;



public interface VerificationService {

   public String getVerify(Verification dto);
   
   public String getDetails(pasportdto pasportdto);

}
