package com.cts.news.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cts.news.entity.JwtResponse;
import com.cts.news.entity.SearchedWords;
import com.cts.news.entity.SignUpStatus;
import com.cts.news.entity.Users;
import com.cts.news.repository.UserRepository;
import com.cts.news.security.JwtGenerator;
import com.cts.news.service.UsersService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class UserController extends ExceptionHandlingController {

	@Autowired
	UsersService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SignUpStatus signUpStatus;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtGenerator jwtGenerator;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/SignUp")
	public ResponseEntity<SignUpStatus> register(@RequestBody Users user) {
		LOGGER.info("register() execution started!");
		System.out.println(user);
		user.setUserPassword(encoder.encode(user.getUserPassword()));
		SignUpStatus signUpStatus = userService.register(user);
		if (signUpStatus.isStatus() == true) {
			LOGGER.info("registration successful");
			return new ResponseEntity<SignUpStatus>(signUpStatus, HttpStatus.OK);
			

		} else {
			LOGGER.error("registration not successful");
			return new ResponseEntity<SignUpStatus>(signUpStatus, HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/login")
	public JwtResponse login(@RequestBody Users users) {

		LOGGER.info("login() execution started!");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(users.getUserMailId(), users.getUserPassword()));
		if (users.getUserAvailabilityStatus() == 1)
			SecurityContextHolder.getContext().setAuthentication(authentication);
		else{
			throw new RuntimeException("User doesnot exist");
		}
			
		User user = (User) authentication.getPrincipal();
		String jwt = jwtGenerator.generateToken(authentication);
		LOGGER.info("login() execution completed!");
		return new JwtResponse(jwt,userService.getUserIdbyMaildId(user.getUsername()),userService.getUserNamebyMailId(user.getUsername()), user.getUsername(), user.getAuthorities());

	}

	@Secured("ROLE_NEWSANALYST")
	@PostMapping("/StoreSearchedWord")
	public ResponseEntity<Void> search(@RequestBody SearchedWords searchedWords) {
		LOGGER.info("search() execution started!->{}",searchedWords);
//		userService.storeSearch(searchedWords);
//		return new ResponseEntity<Void>(HttpStatus.OK);
	if (userService.storeSearch(searchedWords)) {
		LOGGER.debug("search() execution completed!");
		userService.storeSearch(searchedWords);
		return new ResponseEntity<Void>(HttpStatus.OK);
		}

	else {
			LOGGER.info("search() execution completed!");
			LOGGER.error("Could not store the word");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@Secured("ROLE_NEWSANALYST")
	@GetMapping("/SearchedWordsList/{userId}")
	public ResponseEntity<List<SearchedWords>> searchedWordsList(@PathVariable long userId) {
		LOGGER.info("searchwordslist() execution started!");
		List<SearchedWords> list = userService.searchedWordsList(userId);
		if (list != null) {
			LOGGER.info("searchwordslist() execution completed!");
			return new ResponseEntity<List<SearchedWords>>(list, HttpStatus.OK);
		} else {
			LOGGER.info("searchwordslist() execution completed!");
			LOGGER.error("search word not found");
			return new ResponseEntity<List<SearchedWords>>(HttpStatus.NOT_FOUND);

		}
	}

	@Secured("ROLE_NEWSANALYST")
	@DeleteMapping("/DeleteSearchedWord/{searchWordId}")
	public ResponseEntity<Void> deleteSearchedWord(@PathVariable("searchWordId") long searchWordId) {
		LOGGER.info("deleteSearchedWord() execution started!");
		LOGGER.debug("search id :",searchWordId);
		userService.deleteSearchedWord(searchWordId);
		LOGGER.info("deleteSearchedWord() execution completed!");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/searchNewsAnalyst/{userMailId}")
	public ResponseEntity<Users> searchAnalyst(@PathVariable("userMailId") String mailId) {
		LOGGER.info("searchAnalyst() execution started!");
		LOGGER.debug(mailId);
		Users list = userService.searchAnalyst(mailId);
		LOGGER.info("searchAnalyst() execution completed!");
		return new ResponseEntity<Users>(list, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/DeleteNewsAnalyst/{userMailId}")
	public ResponseEntity<Void> deleteNewsAnalyst(@PathVariable("userMailId") String mailId) {
		LOGGER.info("deleteNewsAnalyst() execution started!");
		LOGGER.debug(mailId);
		userService.deleteAnalyst(mailId);
		LOGGER.info("deleteNewsAnalyst() execution completed!");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/reactivateNewsAnalyst/{userMailId}")
	public ResponseEntity<Void> reactivateNewsAnalyst(@PathVariable("userMailId") String mailId) {
		LOGGER.info("reactivateNewsAnalyst() execution started!");
		LOGGER.debug(mailId);
		userService.reactivateAnalyst(mailId);
		LOGGER.info("reactivateNewsAnalyst() execution completed!");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/getAllNewsAnalyst")
	public ResponseEntity<Set<Users>> getAllNewsAnalyst() {
		LOGGER.info("getAllNewsAnalyst() execution started!");
		Set<Users> list = userService.getAllUsers();
		LOGGER.info("getAllNewsAnalyst() execution completed!");
		return new ResponseEntity<Set<Users>>(list, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/getAllBlockedNewsAnalyst")
	public ResponseEntity<Set<Users>> getAllBlockedNewsAnalyst() {
		LOGGER.info("getAllBlockedNewsAnalyst() execution started!");
		Set<Users> list = userService.getAllBlockedUsers();
		LOGGER.info("getAllBlockedNewsAnalyst() execution completed!");
		return new ResponseEntity<Set<Users>>(list, HttpStatus.OK);

	}

}
