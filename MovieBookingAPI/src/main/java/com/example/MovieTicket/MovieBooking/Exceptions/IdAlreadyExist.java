package com.example.MovieTicket.MovieBooking.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdAlreadyExist extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public IdAlreadyExist(String message) {
        super(message);
    }
}
