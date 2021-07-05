package com.kodigo.airportapp.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemIncident {

  Integer idIncident;
  String description;
  String flight;
  String date;
  String time;
}
