package com.xiaokang.service;

import java.util.List;

import com.xiaokang.pojo.Plane;

public interface FlightService {

	String deleteFlight(String delFlightNo);

	List<Plane> selectAllFlight();

	List<Plane> queryFlight(String departureCity, String arrivalCity);

	String exportToTxt(List<Plane> flights);

	String addFight(String flightNo, String departureCity, String departureTime, String arrivalCity,
			String arrivalTime);

	void closeSqlSession();

}
