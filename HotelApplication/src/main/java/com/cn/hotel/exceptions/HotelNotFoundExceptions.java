package com.cn.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundExceptions extends RuntimeException {
	
	public HotelNotFoundExceptions(String message) {
		super(message);
		
	}

	

}
