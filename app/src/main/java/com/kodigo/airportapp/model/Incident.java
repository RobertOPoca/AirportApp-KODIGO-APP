package com.kodigo.airportapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Incident {

  private Integer idIncident;
  private String description;
  private Integer idFlight;
  private String dateTime;
}
