package com.cts.news.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.concretepage.entity.CustomerDetails;
import com.cts.news.entity.SearchedWords;
import com.cts.news.entity.SignUpStatus;
import com.cts.news.entity.Users;
import com.cts.news.repository.RoleRepository;
import com.cts.news.repository.SearchRepository;
import com.cts.news.repository.UserRepository;
import com.cts.news.service.UsersService;

public class ServiceTest {
	
@Mock 
private UserRepository mockedUserRepo;
@Mock 
private SearchRepository mockedSearchRepo;
@Mock 
private RoleRepository mockedRoleRepo;
@InjectMocks
private UsersService userService;
private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);

@Before
public void setUp()
{
	MockitoAnnotations.initMocks(this);
}

@Test
public void testForSuccessfulSignup() throws Exception 
{
	LOGGER.info("Junit Testing started  for Successful Signup");
	SignUpStatus expectedStatus = new SignUpStatus();
	expectedStatus.setStatus(true);
	Users user=new Users("andy","andy@gmail.com","andypassword",1,1,"ROLE_ADMIN","68646941");
	SignUpStatus actualStatus = userService.register(user);
	Mockito.verify(mockedUserRepo).save(user);
	assertEquals(true,expectedStatus.equals(actualStatus) );
	LOGGER.info("Junit Testing completed for Successful Signup");

}

@Test
public void testForUnSuccessfulSignup() throws Exception 
{
	LOGGER.info("Junit Testing started  for UnSuccessful Signup");
	SignUpStatus expectedStatus = new SignUpStatus();
	expectedStatus.setStatus(true);
	expectedStatus.setError("Email already exists!");
	Users user=new Users("andy","andy@gmail.com","andypassword",1,1,"ROLE_ADMIN","68646941");
	when(mockedUserRepo.existsByUserMailId(user.getUserMailId())).thenReturn(true );
	SignUpStatus actualStatus = userService.register(user);
	assertEquals(true,expectedStatus.equals(actualStatus) );
	LOGGER.info("Junit Testing completed for UnSuccessful Signup");
	
}


@Test
public void testForSuccessfulStoringWords() throws Exception 
{
	
	LOGGER.info("Junit Testing started  for Successful storage of words");
	Boolean expectedStatus=true;
    SearchedWords searchedWords=new SearchedWords("hiiii","register2@gmail.com");
    boolean actualStatus=userService.storeSearch(searchedWords);
    Mockito.verify(mockedSearchRepo).save(searchedWords);
    assertEquals(true,expectedStatus.equals(actualStatus)); 
    LOGGER.info("Junit Testing completed for Successful storage of words");
  
}
@Test
public void testForUnSuccessfulStoringWords() throws Exception 
{
	
	LOGGER.info("Junit Testing started  for UnSuccessful storage of words");
	Boolean expectedStatus=false;
    SearchedWords searchedWords=new SearchedWords("hiiii","register2@gmail.com");
    when(mockedSearchRepo.existsBySearchWordNameAndUserMailId(searchedWords.getSearchWordName(),searchedWords.getUserMailId())).thenReturn(false);
    boolean actualStatus=userService.storeSearch(searchedWords);
    assertEquals(expectedStatus, actualStatus); 
    LOGGER.info("Junit Testing completed for UnSuccessful storage of words");
  
}
@Test
public void testForSuccessfulSearchOfWords()  throws Exception 
{
	
	LOGGER.info("Junit Testing started  for Successful search of words");
	SearchedWords search=new SearchedWords();
	search.setUserMailId("rama@gmail.com");
	userService.searchedWordsList(search.getUserMailId());
	Mockito.verify(mockedSearchRepo).findByUserMailId(search.getUserMailId());
    LOGGER.info("Junit Testing completed for Successful storage of words");
	
}


@Test
public void testForSuccessfulDeletionOfWords()  throws Exception 
{
	
	LOGGER.info("Junit Testing started  for Successful deletion of words");
	SearchedWords search=new SearchedWords();
	search.setSearchWordId(29);
	userService.deleteSearchedWord(search.getSearchWordId());
	Mockito.verify(mockedSearchRepo).deleteById(search.getSearchWordId());
    LOGGER.info("Junit Testing completed for Successful deletion of words");
	
}

@Test
public void testForSuccessfulSearchAnalyst()  throws Exception 
{
	
	LOGGER.info("Junit Testing started  for Successful search of analysts");
	Users user=new Users();
	user.setUserMailId("register2@gmail.com");
	userService.searchAnalyst(user.getUserMailId());
	Mockito.verify(mockedUserRepo).findByUserMailId(user.getUserMailId());
	 LOGGER.info("Junit Testing completed for Successful search of analysts");

  
}

@Test
public void testForSuccessfulDeletionOfAnalyst()  throws Exception 
{
	
	LOGGER.info("Junit Testing started  for Successful deletion of analysts");
	Users user=new Users();
	user.setUserMailId("register2@gmail.com");
	userService.deleteAnalyst(user.getUserMailId());
    Mockito.verify(mockedUserRepo).existsByUserMailId(user.getUserMailId());
	 LOGGER.info("Junit Testing completed for Successful deletion of analysts");

}

@Test
public void testForSuccessfulListingOfAllAnalysts()  throws Exception 
{
	
	LOGGER.info("Junit Testing started  for Successful listing of analysts");
	userService.getAllUsers();
    Mockito.verify(mockedUserRepo).findByUserRoleIdAndUserAvailabilityStatus(2,1);
    LOGGER.info("Junit Testing completed for Successful listing of analysts");

}














}
