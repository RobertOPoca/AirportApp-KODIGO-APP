package com.kodigo.airportapp.interactor;

import com.kodigo.airportapp.contract.AirlineContract;
import com.kodigo.airportapp.service.AirlineHttpService;
import com.kodigo.airportapp.service.ApiAirport;


public class AirlineInteractor implements AirlineContract.Model {

  private AirlineContract.OnAirlineListener onAirlineListener;

  public AirlineInteractor(AirlineContract.OnAirlineListener onAirlineListener) {
    this.onAirlineListener = onAirlineListener;
  }

  @Override
  public void getAllAirlines() {
    AirlineHttpService airlineHttpService = new AirlineHttpService(new ApiAirport());
    airlineHttpService.getAllAirlines(onAirlineListener);
  }

}
