package com.cn.hotel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.cn.hotel.exceptions.HotelNotFoundExceptions;
import com.cn.hotel.model.Hotel;

@Service
public class HotelService {

	List<Hotel> hotelList = new ArrayList<>();
	Map<String,Hotel> hotelMap= new HashMap<>();

	public void createHotel(Hotel hotel) {
		hotelList.add(hotel);
		hotelMap.put(hotel.getId(), hotel);
	}


	public Hotel getHotelById(String id) {
		if(ObjectUtils.isEmpty(hotelMap.get(id))) {
			throw new HotelNotFoundExceptions( "Hotel not found for id"+id);
		}
		return hotelMap.get(id);
	}


	public List<Hotel> getAllHotels() {
		
		return hotelList;
	}

	public void deleteHotelById(String id) {
		Hotel hotel = getHotelById(id);
		hotelList.remove(hotel);
		hotelMap.remove(id);
	}

	public void updateHotel(Hotel updatedhotel) {
		Hotel existingHotel=getHotelById(updatedhotel.getId());
		hotelList.remove(existingHotel);
		hotelList.add(updatedhotel);
		
		hotelMap.put(updatedhotel.getId(),updatedhotel);
		
		
	}

}
