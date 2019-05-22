package com.cts.news.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.news.entity.SearchedWords;
import com.cts.news.entity.Users;

@Repository
public interface SearchRepository extends JpaRepository<SearchedWords, Long> {

//	boolean existsBySearchWordNameAndUserId(String searchedWord, Users user);

//	List<SearchedWords> findByUserId(int userId);

	boolean existsBySearchWordName(String searchWordName);

//	SearchedWords findBySearchWordNameAndUserMailId(String searchWordName, String mailId);
	List<SearchedWords> findByUser(Users user);

	void deleteBySearchWordId(long searchWordId);

}
