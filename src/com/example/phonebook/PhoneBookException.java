package com.example.phonebook;

public class PhoneBookException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PhoneBookException(String message) {
		super(message);
	}

	public PhoneBookException(Throwable throwable) {
		super(throwable);
	}
}
