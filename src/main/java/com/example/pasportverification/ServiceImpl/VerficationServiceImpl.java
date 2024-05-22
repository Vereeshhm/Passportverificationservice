package com.example.pasportverification.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.pasportverification.Entity.Verification;
import com.example.pasportverification.Entity.pasportdto;

import com.example.pasportverification.Service.VerificationService;
import com.example.pasportverification.exception.EmptyDobException;
import com.example.pasportverification.exception.EmptyNameException;
import com.example.pasportverification.exception.EmptyfileNumberrexception;
import com.example.pasportverification.exception.FilenumberRequiredException;
import com.example.pasportverification.exception.InvalidDobException;
import com.example.pasportverification.exception.InvalidFilenumberException;
import com.example.pasportverification.exception.InvalidNameException;
import com.example.pasportverification.exception.filenumbernotfoundexception;
import com.example.pasportverification.exception1.BadRequestException;
import com.example.pasportverification.exception1.Emptydobexception;
import com.example.pasportverification.exception1.Emptyfilenumberexception;
import com.example.pasportverification.exception1.Filenumberrequiredexception;
import com.example.pasportverification.exception1.InvalidDOBexception;
import com.example.pasportverification.exception1.filenotfoundexception;
import com.example.pasportverification.utils.PropertiesConfig;

@Service
public class VerficationServiceImpl implements VerificationService {

	private final RestTemplate restTemplate;

	private final String apiKey;

	private static final Logger logger = LoggerFactory.getLogger(VerficationServiceImpl.class);

	@Autowired
	PropertiesConfig config;

	@Autowired
	public VerficationServiceImpl(RestTemplate restTemplate, @Value("${api.key}") String apiKey) {
		this.restTemplate = restTemplate;
		this.apiKey = apiKey;
	}

	@Override
	public String getVerify(Verification dto) {

		String APIURL = config.getVerificationApiURl();

		dto.getFileNumber();
		dto.getDob();
		dto.getName();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", apiKey); // Include API key directly without "Bearer" prefix

		HttpEntity<String> request = new HttpEntity(dto, headers);

//		Object response = restTemplate.postForObject(APIURL, request, Object.class);
//		return response;

		try {
			String response = restTemplate.postForObject(APIURL, request, String.class);
			return response;
		} catch (HttpClientErrorException.BadRequest e) {
			String errorMessage = e.getResponseBodyAsString();
			// System.out.println("Error Response: " + errorMessage);
			logger.error("Error Response: {}", errorMessage);
			if (errorMessage.contains("fileNumber is not allowed to be empty string")) {
				throw new EmptyfileNumberrexception("fileNumber is not allowed to be empty string");
			} else if (errorMessage.contains("dob is not allowed to be empty string")) {
				throw new EmptyDobException("dob is not allowed to be empty string");
			} else if (errorMessage.contains("Invalid File Number")) {
				throw new InvalidFilenumberException("Invalid File Number");
			} else if (errorMessage.contains("fileNumber is required")) {
				throw new FilenumberRequiredException("fileNumber is required");
			} else if (errorMessage.contains("name is not allowed to be empty string")) {
				throw new EmptyNameException("name is not allowed to be empty string");
			} else if (errorMessage.contains("Invalid Name")) {
				throw new InvalidNameException("Invalid Name");
			} else if (errorMessage.contains("Invalid dob")) {
				throw new InvalidDobException("Invalid dob");
			}

			else {
				throw e;
			}
		} catch (HttpClientErrorException.NotFound e) {
			String errorMessage = e.getResponseBodyAsString();
			logger.error("Error Response : {}", errorMessage);
			if (errorMessage.contains("fileNumber is not found")) {
				throw new filenumbernotfoundexception("fileNumber is not found");
			} else {
				throw e;
			}
		}

	}

	@Override
	public String getDetails(pasportdto pasportdto) {
		String APIURL = config.getPportApiURl();

		pasportdto.getFileNumber();
		pasportdto.getDob();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", apiKey); // Include API key directly without "Bearer" prefix

		HttpEntity<String> request = new HttpEntity(pasportdto, headers);

//		String response1 = restTemplate.postForObject(APIURL, request, String.class);
//		return response1;

		try {
			String response1 = restTemplate.postForObject(APIURL, request, String.class);
			return response1;
		} catch (HttpClientErrorException.BadRequest e) {
			String errorMessage = e.getResponseBodyAsString();
			logger.error("Error Response:{}", errorMessage);

			if (errorMessage.contains("fileNumber is not allowed to be empty string")) {
				throw new Emptyfilenumberexception("fileNumber is not allowed to be empty string");
			} else if (errorMessage.contains("dob is not allowed to be empty string")) {

				throw new Emptydobexception("dob is not allowed to be empty string");
			} else if (errorMessage.contains("Invalid File Number")) {
				throw new Filenumberrequiredexception("Invalid File Number");
			} else if (errorMessage.contains("Invalid dob")) {
				throw new InvalidDOBexception("Invalid dob");
			} else if (pasportdto.getFileNumber() == null || pasportdto.getFileNumber().isEmpty()) {
				throw new BadRequestException("fileNumber is required");
			} else {
				throw e;
			}

		} catch (HttpClientErrorException.NotFound e) {
			String errorMessage = e.getResponseBodyAsString();
			logger.error("Error Response : {}", errorMessage);
			if (errorMessage.contains("fileNumber is not found")) {
				throw new filenotfoundexception("fileNumber is not found");
			} else {
				throw e;
			}
		}

	}

}
