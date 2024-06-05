package com.example.pasportverification.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.pasportverification.Entity.Verification;
import com.example.pasportverification.Entity.pasportdto;
import com.example.pasportverification.Logentities.ApiLog;
import com.example.pasportverification.Logentities.ApiLogentity;
import com.example.pasportverification.Repository.ApiLogRepository;
import com.example.pasportverification.Repository.ApiLogRepository1;
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
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class VerficationServiceImpl implements VerificationService {

	private final RestTemplate restTemplate;

	private final String apiKey;

	@Autowired
	ApiLogRepository apiLogRepository;

	@Autowired
	ApiLogRepository1 apiLogRepository1;

	private static final Logger logger = LoggerFactory.getLogger(VerficationServiceImpl.class);

	@Autowired
	PropertiesConfig config;

	@Autowired
	public VerficationServiceImpl(RestTemplate restTemplate, @Value("${api.key}") String apiKey) {
		this.restTemplate = restTemplate;
		this.apiKey = apiKey;
	}

	@Override
	public String getVerify(Verification dto, HttpServletRequest request, HttpServletResponse response) {

		String APIURL = config.getVerificationApiURl();

		String requestUrl = request.getRequestURI().toString();

		int statusCode = response.getStatus();

		dto.getFileNumber();
		dto.getDob();
		dto.getName();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", apiKey); // Include API key directly without "Bearer" prefix
		Gson gson = new Gson();

		String requestBodyJson = gson.toJson(dto);

		HttpEntity<String> request1 = new HttpEntity(requestBodyJson, headers);

		ApiLog apiLog = new ApiLog();
		apiLog.setUrl(requestUrl);
		apiLog.setRequestBody(requestBodyJson);

		try {
			String response1 = restTemplate.postForObject(APIURL, request1, String.class);
			apiLog.setResponseBody(response1);
			apiLog.setStatusCode(HttpStatus.OK.value());
			return response1;
		} catch (HttpClientErrorException.BadRequest e) {
			String errorMessage = e.getResponseBodyAsString();

			apiLog.setResponseBody(errorMessage);
			apiLog.setStatusCode(e.getStatusCode().value());
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

			apiLog.setResponseBody(errorMessage);
			apiLog.setStatusCode(e.getStatusCode().value());
			logger.error("Error Response : {}", errorMessage);
			if (errorMessage.contains("fileNumber is not found")) {
				throw new filenumbernotfoundexception("fileNumber is not found");
			} else {
				throw e;
			}
		} finally {
			apiLogRepository.save(apiLog);
		}

	}

	@Override
	public String getDetails(pasportdto pasportdto, HttpServletRequest request, HttpServletResponse response) {
		String APIURL = config.getPportApiURl();

		String requestUrl = request.getRequestURI().toString();

		int statusCode = response.getStatus();

		pasportdto.getFileNumber();
		pasportdto.getDob();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", apiKey);

		Gson gson = new Gson();

		String requestBodyJson = gson.toJson(pasportdto);

		HttpEntity<String> request1 = new HttpEntity(requestBodyJson, headers);

		ApiLogentity apiLogentity = new ApiLogentity();
		apiLogentity.setUrl(requestUrl);
		apiLogentity.setRequestBody(requestBodyJson);

		try {
			String response1 = restTemplate.postForObject(APIURL, request1, String.class);
			apiLogentity.setResponseBody(response1);
			apiLogentity.setStatusCode(HttpStatus.OK.value());
			return response1;
		} catch (HttpClientErrorException.BadRequest e) {
			String errorMessage = e.getResponseBodyAsString();

			apiLogentity.setResponseBody(errorMessage);
			apiLogentity.setStatusCode(e.getStatusCode().value());
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
			apiLogentity.setResponseBody(errorMessage);
			apiLogentity.setStatusCode(e.getStatusCode().value());
			logger.error("Error Response : {}", errorMessage);
			if (errorMessage.contains("fileNumber is not found")) {
				throw new filenotfoundexception("fileNumber is not found");
			} else {
				throw e;
			}
		} finally {

			apiLogRepository1.save(apiLogentity);
		}

	}

}
