package com.cts.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "SearchWords")
public class SearchedWords implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long searchWordId;
	private String searchWordName;
		
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;

	@CreationTimestamp
	private LocalDateTime creationDate;


	public SearchedWords() {
		super();
	}

	public SearchedWords(long searchWordId,String searchWordName, Users user) {
		super();
        this.searchWordId=searchWordId;
		this.searchWordName = searchWordName;
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	

	public long getSearchWordId() {
		return searchWordId;
	}

	public void setSearchWordId(long searchWordId) {
		this.searchWordId = searchWordId;
	}

	public String getSearchWordName() {
		return searchWordName;
	}

	public void setSearchWordName(String searchWordName) {
		this.searchWordName = searchWordName;
	}

	@Override
	public String toString() {
		return "SearchWords [searchWordId=" + searchWordId + ", searchWordName=" + searchWordName + ", user="
				+ user.getUserMailId() + "]";
	}

	
}
