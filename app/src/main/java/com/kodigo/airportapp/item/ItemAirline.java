package com.kodigo.airportapp.item;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemAirline implements Serializable {

  Integer idAirline;
  String airlineName;
}
