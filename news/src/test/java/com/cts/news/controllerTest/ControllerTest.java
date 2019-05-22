package com.cts.news.controllerTest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.cts.news.entity.SearchedWords;
import com.cts.news.entity.SignUpStatus;
import com.cts.news.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTest {
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	String adminEmail = "admin@" + new Random().nextInt(1000) + ".com";
	String adminName = "admin" + new Random().nextInt(1000);
	String analystEmail = "analyst@" + new Random().nextInt(1000) + ".com";
	String analystName = "analyst" + new Random().nextInt(1000);
	String searchWord = "word" + new Random().nextInt(1000);
	static String searchId = "";

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerTest.class);

	@Test
	public void testForSuccessfulSignupOfAnalyst() throws Exception {
		LOGGER.info("Controller Test started for successful registration for analyst");

		Users user = new Users(analystName, analystEmail, "123456", 2, 1, "ROLE_NEWSANALYST", "others");
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/SignUp", user, SignUpStatus.class);
		LOGGER.info("response", response.getBody().getError());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		LOGGER.info("Controller Test completed for success registration for analyst");

	}

	@Test
	public void testForSuccessfulSignupOfAdmin() throws Exception {
		LOGGER.info("Controller Test started for successful registration for admin");
		Users user = new Users(adminName, adminEmail, "123456", 1, 1, "ROLE_ADMIN", "others");
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/SignUp", user, SignUpStatus.class);
		LOGGER.info("response", response.getBody().getError());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		LOGGER.info("Controller Test completed for successful registration for admin");

	}

	@Test
	public void testForSuccessfulStorageOfWords() throws Exception// analyst1
																	// :user
	{
		LOGGER.info("Controller Test started for successful storage of words");
		SearchedWords search = new SearchedWords(searchWord, "murthy@gmail.com");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmFseXN0MUBnbWFpbC5jb20iLCJpYXQiOjE1NTEyNzY1NzQsImV4cCI6NDcwNDg3NjU3NH0.0UyHoxwh1fvjx5ClRx782QrwjMJD3f-A_zZI5-c3pq_NvY0hM29Fwdxq827buF2xyI4DzuXBZ39PJtbRh94cGg";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Object>(search, headers);
		ResponseEntity<Void> response = restTemplate.exchange("/StoreSearchedWord", HttpMethod.POST, entity,
				Void.class);
		LOGGER.info("Response->{}", response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		LOGGER.info("Controller Test completed for successful storage of words");
	}

	@Test
	public void testForSuccessfulSearchOfWords() throws Exception// analyst1
																	// :user
	{

		LOGGER.info("Controller Test started for successful search of words");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmFseXN0MUBnbWFpbC5jb20iLCJpYXQiOjE1NTEyNzY1NzQsImV4cCI6NDcwNDg3NjU3NH0.0UyHoxwh1fvjx5ClRx782QrwjMJD3f-A_zZI5-c3pq_NvY0hM29Fwdxq827buF2xyI4DzuXBZ39PJtbRh94cGg";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Object>(null, headers);
		ResponseEntity<List> response = restTemplate.exchange("/SearchedWordsList/{userMailId}", HttpMethod.GET, entity,
				List.class, "analyst1@gmail.com");
		searchId = String.valueOf(((LinkedHashMap<String, String>) response.getBody().get(0)).get("searchWordId"));
		LOGGER.info("Test->{}", searchId);
		LOGGER.info("Response->{}", response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		LOGGER.info("Controller Test completed for successful search of words");
	}

	@Test
	public void testForWsuccessfulDeletionOfWords() throws Exception// analyst1
																	// :user
	{

		LOGGER.info("Controller Test started for successful deletion of words", searchId);
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmFseXN0MUBnbWFpbC5jb20iLCJpYXQiOjE1NTEyNzY1NzQsImV4cCI6NDcwNDg3NjU3NH0.0UyHoxwh1fvjx5ClRx782QrwjMJD3f-A_zZI5-c3pq_NvY0hM29Fwdxq827buF2xyI4DzuXBZ39PJtbRh94cGg";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Void>(null, headers);
		ResponseEntity<Void> response = restTemplate.exchange("/DeleteSearchedWord/{searchWordId}", HttpMethod.DELETE,
				entity, Void.class, searchId);
		LOGGER.info("Response->{}", response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
		LOGGER.info("Controller Test completed for successful deletion of words", searchId);
	}

	@Test
	public void testForSuccessfulSearchOfAnalyst() throws Exception// admin1@gmail.com:user
	{

		LOGGER.info("Controller Test started for successful search of analysts");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjFAZ21haWwuY29tIiwiaWF0IjoxNTUxMjc2MjI2LCJleHAiOjQ3MDQ4NzYyMjZ9.4KCaDQKsysTagMJZlddsdyneweA5b5pKL2HBi2mT_ycBgTOBhb5rmLBAHobOJnoB_7PgSw7SMKP0ajCbROxxaA";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Users>(null, headers);
		ResponseEntity<Users> response = restTemplate.exchange("/searchNewsAnalyst/{userMailId}", HttpMethod.GET,
				entity, Users.class, "analyst1@gmail.com");
		LOGGER.info("Response->{}", response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		LOGGER.info("Controller Test completed for successful search of analysts");

	}

	@Test
	public void testForSuccessfulDeletionOfAnalyst() throws Exception// admin1@gmail.com:user
	{

		LOGGER.info("Controller Test started for successful deletion of analyst");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjFAZ21haWwuY29tIiwiaWF0IjoxNTUxMjc2MjI2LCJleHAiOjQ3MDQ4NzYyMjZ9.4KCaDQKsysTagMJZlddsdyneweA5b5pKL2HBi2mT_ycBgTOBhb5rmLBAHobOJnoB_7PgSw7SMKP0ajCbROxxaA";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Void>(null, headers);
		ResponseEntity<Void> response = restTemplate.exchange("/DeleteNewsAnalyst/{userMailId}", HttpMethod.DELETE,
				entity, Void.class, "analyst@gmail.com");// TODO: DeleteNewsAnalyst/{userMailId}"
		LOGGER.info("Response->{}", response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		LOGGER.info("Controller Test completed for successful deletion of analyst");

	}

	@Test
	public void testForSuccessfulReactivationOfAnalyst() throws Exception// admin1@gmail.com:user
	{

		LOGGER.info("Controller Test started for successful reactivation of analyst");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjFAZ21haWwuY29tIiwiaWF0IjoxNTUxMjc2MjI2LCJleHAiOjQ3MDQ4NzYyMjZ9.4KCaDQKsysTagMJZlddsdyneweA5b5pKL2HBi2mT_ycBgTOBhb5rmLBAHobOJnoB_7PgSw7SMKP0ajCbROxxaA";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<?> entity = new HttpEntity<Void>(null, headers);
		ResponseEntity<Void> response = restTemplate.exchange("/reactivateNewsAnalyst/{userMailId}", HttpMethod.DELETE,
				entity, Void.class, "analyst@gmail.com");
		LOGGER.info("Response->{}", response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		LOGGER.info("Controller Test completed for successful reactivation of analyst");

	}

}
