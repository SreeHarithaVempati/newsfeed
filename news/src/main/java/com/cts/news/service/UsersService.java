package com.cts.news.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.news.repository.RoleRepository;
import com.cts.news.repository.SearchRepository;
import com.cts.news.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import com.cts.news.entity.Role;
import com.cts.news.entity.SearchedWords;
import com.cts.news.entity.Users;
import com.cts.news.entity.SignUpStatus;

@Service // perform service tasks
public class UsersService implements UserDetailsService {
	@Autowired UserRepository userRepository;
	@Autowired RoleRepository roleRepository;
	@Autowired SearchRepository searchRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

	public SignUpStatus register(Users user) {
		LOGGER.info("register() in service has started");
		Users list = userRepository.findByUserMailId(user.getUserMailId());
		SignUpStatus signUpStatus = new SignUpStatus();
		if (list != null) {
			signUpStatus.setStatus(false);
			signUpStatus.setError("Email already exists !");
			LOGGER.error("email already exists");
			LOGGER.info("register() in service has completed");
			return signUpStatus;
		} else {
			signUpStatus.setStatus(true);
			userRepository.save(user);
			LOGGER.info("register() in service has completed");
			return signUpStatus;
		}

	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mailId) throws UsernameNotFoundException {
		LOGGER.info("loadUserByUsername() in service has started");
		Users user1 = userRepository.findByUserMailId(mailId);

		if (user1.getUserAvailabilityStatus() == 1) {
			Role roles = roleRepository.findByRoleId(user1.getUserRoleId());
			List<GrantedAuthority> roles1 = new ArrayList<GrantedAuthority>();
			roles1.add((new SimpleGrantedAuthority(roles.getRoleName())));

			User user = new User(user1.getUserMailId(), user1.getUserPassword(), roles1);
			LOGGER.info("loadUserByUsername() in service has completed");
			return user;
		} else {
			LOGGER.info("loadUserByUsername() in service has completed");
			throw new RuntimeException("user blocked");
		}

	}

	public boolean storeSearch(SearchedWords searchedWords) {
		LOGGER.info("storeSearch() in service has started");


		if(userRepository.existsByUserMailId(searchedWords.getUser().getUserMailId()))
		{
				
			searchedWords.setUser(userRepository.findByUserMailId(searchedWords.getUser().getUserMailId()));
			searchRepository.save(searchedWords);
			
			return true;
		}  
		else 
			{
			
			return false;
			}
		

	}

	public List<SearchedWords> searchedWordsList(long userId) {
		boolean  userstatus = userRepository.existsByUserId(userId); 
		Users users=userRepository.findByUserId(userId);
		
		if(userstatus){
			
		List<SearchedWords> searchList= searchRepository.findByUser(users);
		System.out.println(searchList);
		return searchList;
		}
		else return null;

	}

	public void deleteSearchedWord(long searchWordId) {
		LOGGER.info("deleteSearchedWord() in service has started");
		searchRepository.deleteById(searchWordId);
		LOGGER.info("deleteSearchedWord() in service has completed");

	}

	public Users searchAnalyst(String mailId) {
		LOGGER.info("searchAnalyst() in service has started");

		Users list = userRepository.findByUserMailId(mailId);
		LOGGER.info("searchAnalyst() in service has completed");
		return list;

	}

	public void deleteAnalyst(String mailId) {
		LOGGER.info("deleteAnalyst() in service has started", mailId);
		Users user = new Users();

		if (userRepository.existsByUserMailId(mailId) == true) {
			user = userRepository.findByUserMailId(mailId);
			user.setUserAvailabilityStatus(0);
			userRepository.save(user);

		}
		LOGGER.info("deleteAnalyst() in service has completed");

	}

	public Set<Users> getAllUsers() {
		LOGGER.info("getAllUsers() in service has started");
		Set<Users> list = userRepository.findByUserRoleIdAndUserAvailabilityStatus(2, 1);
		LOGGER.info("getAllUsers() in service has completed");
		return list;
	}

	public Set<Users> getAllBlockedUsers() {
		LOGGER.info("getAllBlockedUsers() in service has started");
		Set<Users> list = userRepository.findByUserRoleIdAndUserAvailabilityStatus(2, 0);
		LOGGER.info("getAllBlockedUsers() in service has completed");
		return list;
	}

	public void reactivateAnalyst(String mailId) {
		LOGGER.info("reactivateAnalyst() in service has started");
		Users user = new Users();
		user = userRepository.findByUserMailId(mailId);
		if (user.getUserAvailabilityStatus() == 0) {

			user.setUserAvailabilityStatus(1);
			userRepository.save(user);

		}
		LOGGER.info("reactivateAnalyst() in service has completed", user.getUserAvailabilityStatus());

	}

	public long getUserIdbyMaildId(String mailId) {
	Users user=userRepository.findByUserMailId(mailId);
	return user.getUserId();
	}

	public String getUserNamebyMailId(String mailId) {
		Users user=userRepository.findByUserMailId(mailId);
		return user.getUserName();
	}
}
