package com.kodigo.airportapp.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Flight {

  private Integer idFlight;
  private String model;
  private Integer idAirline;
  private Integer idDepartureCity;
  private Integer idArrivalCity;
  private String status;
  private String departureTime;
  private String arrivalTime;
}
