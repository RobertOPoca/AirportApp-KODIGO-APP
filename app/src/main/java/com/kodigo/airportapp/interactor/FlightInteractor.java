package com.kodigo.airportapp.interactor;

import com.kodigo.airportapp.contract.FlightContract;
import com.kodigo.airportapp.model.Flight;
import com.kodigo.airportapp.service.AirlineHttpService;
import com.kodigo.airportapp.service.AirplaneHttpService;
import com.kodigo.airportapp.service.ApiAirport;
import com.kodigo.airportapp.service.CityHttpService;
import com.kodigo.airportapp.service.CountryHttpService;
import com.kodigo.airportapp.service.FlightHttpService;
import com.kodigo.airportapp.service.IncidentHttpService;
import com.kodigo.airportapp.service.ReportHttpService;

public class FlightInteractor implements FlightContract.Model {

  private FlightContract.OnFlightListener onFlightListener;

  public FlightInteractor(FlightContract.OnFlightListener onFlightListener) {
    this.onFlightListener = onFlightListener;
  }

  @Override
  public void getAllFlights() {
    FlightHttpService flightHttpService = new FlightHttpService(new ApiAirport());
    flightHttpService.getAllFlights(this.onFlightListener);
  }

  @Override
  public void loadFlightsExcel() {

    ReportHttpService reportHttpService = new ReportHttpService(new ApiAirport());
    reportHttpService.loadFlightsByExcel(this.onFlightListener);
  }

  @Override
  public void getAirplaneList() {
    AirplaneHttpService airplaneHttpService = new AirplaneHttpService(new ApiAirport());
    airplaneHttpService.getAllAirplanes(this.onFlightListener);
  }

  @Override
  public void getAirlineList() {
    AirlineHttpService airlineHttpService = new AirlineHttpService(new ApiAirport());
    airlineHttpService.getAllAirlines(onFlightListener);
  }

  @Override
  public void getCountryList() {

    CountryHttpService countryHttpService = new CountryHttpService(new ApiAirport());
    countryHttpService.getAllCountries(onFlightListener);
  }

  @Override
  public void getCityList() {
    CityHttpService cityHttpService = new CityHttpService(new ApiAirport());
    cityHttpService.getAllCities(onFlightListener);

  }

  @Override
  public void getFlightById(Integer id) {

    FlightHttpService flightHttpService = new FlightHttpService(new ApiAirport());
    flightHttpService.getFlightById(onFlightListener, id);
  }


  @Override
  public void createFlight(Flight flight) {

    FlightHttpService flightHttpService = new FlightHttpService(new ApiAirport());
    flightHttpService.createFlight(onFlightListener, flight);
  }

  @Override
  public void updateFlight(Flight flight) {

    FlightHttpService flightHttpService = new FlightHttpService(new ApiAirport());
    flightHttpService.updateFlight(onFlightListener, flight);
  }

  @Override
  public void reportByDate(String date) {

    ReportHttpService reportHttpService = new ReportHttpService(new ApiAirport());
    reportHttpService.reportByDate(onFlightListener, date);
  }

  @Override
  public void reportById(Integer id) {

    ReportHttpService reportHttpService = new ReportHttpService(new ApiAirport());
    reportHttpService.reportById(onFlightListener, id);
  }
}
