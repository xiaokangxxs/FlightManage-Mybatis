package com.xiaokang.pojo;

import java.util.Date;

import com.xiaokang.common.time.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
	private String flightno;
	private String departureCity;
	private String arrivalCity;
	private Date departureTime;
	private Date arrivalTime;

	@Override
	public String toString() {
		return flightno + "," + departureCity + " - " + arrivalCity + "," + DateUtils.parseDateToString(departureTime)
				+ " - " + DateUtils.parseDateToString(arrivalTime);
	}
}
