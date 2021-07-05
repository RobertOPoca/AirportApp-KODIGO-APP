package com.kodigo.airportapp.contract;

import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.item.ItemCity;
import com.kodigo.airportapp.item.ItemCountry;
import com.kodigo.airportapp.item.ItemFlight;
import com.kodigo.airportapp.listener.OnAirlineListenerRead;
import com.kodigo.airportapp.listener.OnAirplaneListenerRead;
import com.kodigo.airportapp.listener.OnCityListenerRead;
import com.kodigo.airportapp.listener.OnCountryListenerRead;
import com.kodigo.airportapp.listener.OnFlightListenerRead;
import com.kodigo.airportapp.listener.OnFlightListenerWrite;
import com.kodigo.airportapp.listener.OnReportListenerRead;
import com.kodigo.airportapp.listener.OnReportListenerWrite;
import com.kodigo.airportapp.model.Flight;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public interface FlightContract {

  interface View {

    void showFlights(ResponseApi<List<ItemFlight>> flights);

    void onGetAllFlightsFailure(String message);

    void loadFlightsByExcel();

    void onLoadExcelSuccess(String message);

    void onLoadExcelFailure(String message);


    void reportByDate(String date);

    void onReportByDateSuccess(String message);

    void onReportByDateFailure(String message);
  }

  interface ViewForm {

    void getFlightById(Integer id);

    void onGetFlightFailure(String message);

    void airlineList(ResponseApi<List<ItemAirline>> airlines);

    void onGetAirlineListFailure(String message);

    void airplaneList(ResponseApi<List<ItemAirplane>> airplane);

    void onGetAirplaneListFailure(String message);

    void countryList(ResponseApi<List<ItemCountry>> countries);

    void onGetCountryListFailure(String message);

    void cityList(ResponseApi<List<ItemCity>> cities);

    void onGetCityListFailure(String message);

    void saveFlight(Flight flight);

    void updateFlight(Flight flight);

    void onGetFlightSuccess(ResponseApi<ItemFlight> flightResponseApi);

    void onSaveSuccess(String message);

    void onSaveFailure(String message);

    void onUpdateSuccess(String message);

    void onUpdateFailure(String message);


    void reportById(Integer id);

    void onReportByIdSuccess(String message);

    void onReportByIdFailure(String message);
  }

  interface Presenter {

    void showAll();

    void showAirlineList();

    void showAirplaneList();

    void showCountryList();

    void showCityList();

    void loadFlightsByExcel();

    void reportByDate(String date);

    void reportById(Integer id);

    void getFlight(Integer id);


    void createFlight(Flight flight);

    void updateFlight(Flight flight);
  }

  interface Model {

    void getAllFlights();

    void loadFlightsExcel();

    void getAirplaneList();

    void getAirlineList();

    void getCountryList();

    void getCityList();

    void getFlightById(Integer id);


    void createFlight(Flight flight);

    void updateFlight(Flight flight);

    void reportByDate(String date);

    void reportById(Integer id);
  }

  interface OnFlightListener extends OnAirlineListenerRead, OnAirplaneListenerRead,
      OnFlightListenerRead, OnFlightListenerWrite, OnCountryListenerRead,
      OnCityListenerRead, OnReportListenerRead, OnReportListenerWrite {


  }

}