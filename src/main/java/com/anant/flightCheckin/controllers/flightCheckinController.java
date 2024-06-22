package com.anant.flightCheckin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.anant.flightCheckin.dtos.Reservation;
import com.anant.flightCheckin.dtos.ReservationCheckInRequest;
import com.anant.flightCheckin.integration.ReservationClient;


@Controller
public class flightCheckinController {

	@Autowired
	private ReservationClient reservationClient;
	
	@GetMapping("/checkin")
	public String showCheckInPage() {
		return "checkin";
	}
	
	@GetMapping("/checkinWithFlightInfo")
	public String showCheckInPageWithFlighInfo(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) {
		Reservation reservation = reservationClient.fetchReservation(reservationId);
		System.out.println("reservation is very good: "+ reservation);
		modelMap.addAttribute("reservation", reservation);
		return "checkinPageWithFlightInfo";
	}
	
	@GetMapping("/processCheckin")
	public String processCheckIn(@RequestParam("numberOfBags") int numberOfBags, @RequestParam("reservationId") Long reservationId, ModelMap modelMap) {
		ReservationCheckInRequest request = new ReservationCheckInRequest();
		request.setId(reservationId);
		request.setNumberOfBags(numberOfBags);
		request.setCheckedIn(true);
		
		reservationClient.updateReservation(request);
		modelMap.addAttribute("msg", "checked in successfully");
		return "index";
	}
	
	
}
