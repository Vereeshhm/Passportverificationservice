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

		ApiLog apiLog = new ApiLog();
		String response1 = null;
		try {
			String APIURL = config.getVerificationApiURl();

			String requestUrl = request.getRequestURI().toString();

			dto.getFileNumber();
			dto.getDob();
			dto.getName();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", apiKey);

			Gson gson = new Gson();

			String requestBodyJson = gson.toJson(dto);

			HttpEntity<String> request1 = new HttpEntity(requestBodyJson, headers);

			apiLog.setUrl(requestUrl);
			apiLog.setRequestBody(requestBodyJson);

			response1 = restTemplate.postForObject(APIURL, request1, String.class);
			apiLog.setResponseBody(response1);
			apiLog.setStatusCode(HttpStatus.OK.value());
			return response1;
		} catch (HttpClientErrorException.TooManyRequests e) {
			// Handling Too Many Requests Exception specifically
			apiLog.setStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());

			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + "Response");
			apiLog.setResponseBodyAsJson("API rate limit exceeded");
		} catch (HttpClientErrorException.Unauthorized e) {
			// Handling Unauthorized Exception specifically
			apiLog.setStatusCode(HttpStatus.UNAUTHORIZED.value());

			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + "Response");
			apiLog.setResponseBodyAsJson("No API key found in request");

		}

		catch (HttpClientErrorException e) {
			apiLog.setStatusCode(e.getStatusCode().value());

			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + " Response ");
			apiLog.setResponseBody(response1);
		}

		catch (Exception e) {
			apiLog.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

			response1 = e.getMessage();
			apiLog.setResponseBody(response1);
		} finally {
			apiLogRepository.save(apiLog);
		}
		return response1;

	}

	@Override
	public String getDetails(pasportdto pasportdto, HttpServletRequest request, HttpServletResponse response) {

		ApiLogentity apiLogentity = new ApiLogentity();
		String response1 = null;
		try {
			String APIURL = config.getPportApiURl();

			String requestUrl = request.getRequestURI().toString();

			pasportdto.getFileNumber();
			pasportdto.getDob();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", apiKey);

			Gson gson = new Gson();

			String requestBodyJson = gson.toJson(pasportdto);

			HttpEntity<String> request1 = new HttpEntity(requestBodyJson, headers);

			apiLogentity.setUrl(requestUrl);
			apiLogentity.setRequestBody(requestBodyJson);

			response1 = restTemplate.postForObject(APIURL, request1, String.class);
			apiLogentity.setResponseBody(response1);
			apiLogentity.setStatusCode(HttpStatus.OK.value());
			return response1;
		} catch (HttpClientErrorException.TooManyRequests e) {
			// Handling Too Many Requests Exception specifically
			apiLogentity.setStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());

			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + "Response");
			apiLogentity.setResponseBodyAsJson("API rate limit exceeded");
		} catch (HttpClientErrorException.Unauthorized e) {
			// Handling Unauthorized Exception specifically
			apiLogentity.setStatusCode(HttpStatus.UNAUTHORIZED.value());

			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + "Response");
			apiLogentity.setResponseBodyAsJson("No API key found in request");

		}

		catch (HttpClientErrorException e) {
			apiLogentity.setStatusCode(e.getStatusCode().value());

			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + " Response ");
			apiLogentity.setResponseBody(response1);
		}

		catch (Exception e) {
			apiLogentity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

			response1 = e.getMessage();
			apiLogentity.setResponseBody(response1);
		}

		finally {

			apiLogRepository1.save(apiLogentity);
		}
		return response1;

	}

}
