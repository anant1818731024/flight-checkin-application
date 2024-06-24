package com.anant.flightCheckin.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.anant.flightCheckin.dtos.Reservation;
import com.anant.flightCheckin.dtos.ReservationCheckInRequest;

@Component
public class ReservationClient {
	
	
	@Value("${com.anant.flightCheckin.reservation_rest_url}")
	private String RESERVATION_REST_URL;

	public Reservation fetchReservation(long id) {
		RestTemplate template = new RestTemplate();
		Reservation reservation = template.getForObject(RESERVATION_REST_URL+id, Reservation.class);
		return reservation;
	}
	
	public Reservation updateReservation(ReservationCheckInRequest request) {
		RestTemplate template = new RestTemplate();
		
		return template.postForObject(RESERVATION_REST_URL + "checkin", request, Reservation.class);
	}
}
