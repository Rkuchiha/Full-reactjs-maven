package com.WithRkuchiha.Fullstackbackpart.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(Long id) {
		super("Could Not Found the User With Id "+id );
	}

}
