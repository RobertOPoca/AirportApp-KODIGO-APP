package com.kodigo.airportapp.interactor;

import com.kodigo.airportapp.contract.AirplaneContract;
import com.kodigo.airportapp.service.AirplaneHttpService;
import com.kodigo.airportapp.service.ApiAirport;

public class AirplaneInteractor implements AirplaneContract.Model {

  private AirplaneContract.OnAirplaneListener onAirplaneListener;

  public AirplaneInteractor(AirplaneContract.OnAirplaneListener onAirplaneListener) {
    this.onAirplaneListener = onAirplaneListener;
  }

  @Override
  public void getAllAirplanes() {
    AirplaneHttpService airplaneHttpService = new AirplaneHttpService(new ApiAirport());
    airplaneHttpService.getAllAirplanes(onAirplaneListener);
  }
}
