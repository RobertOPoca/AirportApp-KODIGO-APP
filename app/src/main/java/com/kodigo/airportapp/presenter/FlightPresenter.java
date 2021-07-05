package com.kodigo.airportapp.presenter;

import com.kodigo.airportapp.contract.FlightContract;
import com.kodigo.airportapp.interactor.FlightInteractor;
import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.item.ItemCity;
import com.kodigo.airportapp.item.ItemCountry;
import com.kodigo.airportapp.item.ItemFlight;
import com.kodigo.airportapp.model.Flight;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public class FlightPresenter implements FlightContract.Presenter, FlightContract.OnFlightListener {

  private FlightContract.View mFlightView;
  private FlightContract.Model mFlightInteractor;
  private FlightContract.ViewForm mFlightViewForm;

  public FlightPresenter(FlightContract.ViewForm mFlightViewForm) {
    this.mFlightViewForm = mFlightViewForm;
    this.mFlightInteractor = new FlightInteractor(this);
    showAirlineList();
    showAirplaneList();
    showCountryList();
    showCityList();
  }

  public FlightPresenter(FlightContract.View mFlightView) {
    this.mFlightView = mFlightView;
    this.mFlightInteractor = new FlightInteractor(this);
    showAll();
  }

  @Override
  public void showAll() {
    this.mFlightInteractor.getAllFlights();
  }

  @Override
  public void showAirlineList() {
    this.mFlightInteractor.getAirlineList();
  }

  @Override
  public void showAirplaneList() {
    this.mFlightInteractor.getAirplaneList();
  }

  @Override
  public void showCountryList() {
    this.mFlightInteractor.getCountryList();
  }

  @Override
  public void showCityList() {
    this.mFlightInteractor.getCityList();
  }

  @Override
  public void loadFlightsByExcel() {
    this.mFlightInteractor.loadFlightsExcel();
  }

  @Override
  public void reportByDate(String date) {
    this.mFlightInteractor.reportByDate(date);
  }

  @Override
  public void reportById(Integer id) {
    this.mFlightInteractor.reportById(id);
  }

  @Override
  public void getFlight(Integer id) {
    this.mFlightInteractor.getFlightById(id);
  }


  @Override
  public void createFlight(Flight flight) {
    this.mFlightInteractor.createFlight(flight);
  }

  @Override
  public void updateFlight(Flight flight) {
    this.mFlightInteractor.updateFlight(flight);

  }

  @Override
  public void onCreateFlightSuccess(ResponseApi<?> responseApi) {

    this.mFlightViewForm.onSaveSuccess(responseApi.getMessage());
  }

  @Override
  public void onCreateFlightFailure(String message) {
    this.mFlightViewForm.onSaveFailure(message);
  }

  @Override
  public void onUpdateFlightSuccess(ResponseApi<?> responseApi) {
    this.mFlightViewForm.onUpdateSuccess(responseApi.getMessage());
  }

  @Override
  public void onUpdateFlightFailure(String message) {
    this.mFlightViewForm.onUpdateFailure(message);
  }

  @Override
  public void onGetAllFlightsSuccess(ResponseApi<?> responseApi) {
    this.mFlightView.showFlights((ResponseApi<List<ItemFlight>>) responseApi);
  }

  @Override
  public void onGetAllFlightsFailure(String message) {
    this.mFlightView.onGetAllFlightsFailure(message);
  }

  @Override
  public void onGetFlightSuccess(ResponseApi<?> responseApi) {
    this.mFlightViewForm.onGetFlightSuccess((ResponseApi<ItemFlight>) responseApi);
  }

  @Override
  public void onGetFlightFailure(String message) {

    this.mFlightViewForm.onGetFlightFailure(message);
  }


  @Override
  public void onLoadExcelSuccess(ResponseApi<?> responseApi) {
    this.mFlightView.onLoadExcelSuccess(responseApi.getMessage());
  }

  @Override
  public void onLoadExcelFailure(String message) {
    this.mFlightView.onLoadExcelFailure(message);
  }

  @Override
  public void onReportByDateSuccess(ResponseApi<?> responseApi) {
    this.mFlightView.onReportByDateSuccess(responseApi.getMessage());
  }

  @Override
  public void onReportByDateFailure(String message) {
    this.mFlightView.onReportByDateFailure(message);
  }

  @Override
  public void onReportByIdSuccess(ResponseApi<?> responseApi) {
    this.mFlightViewForm.onReportByIdSuccess(responseApi.getMessage());
  }

  @Override
  public void onReportByIdFailure(String message) {
    this.mFlightViewForm.onReportByIdFailure(message);
  }

  @Override
  public void onGetAllAirlineSuccess(ResponseApi<?> responseApi) {

    this.mFlightViewForm.airlineList((ResponseApi<List<ItemAirline>>) responseApi);
  }

  @Override
  public void onGetAllAirlineFailure(String message) {
    this.mFlightViewForm.onGetAirlineListFailure(message);
  }

  @Override
  public void onGetAllAirplaneSuccess(ResponseApi<?> responseApi) {

    this.mFlightViewForm.airplaneList((ResponseApi<List<ItemAirplane>>) responseApi);
  }

  @Override
  public void onGetAllAirplaneFailure(String message) {
    this.mFlightViewForm.onGetAirplaneListFailure(message);
  }


  @Override
  public void onGetAllCitiesSuccess(ResponseApi<?> responseApi) {

    this.mFlightViewForm.cityList((ResponseApi<List<ItemCity>>) responseApi);
  }

  @Override
  public void onGetAllCitiesFailure(String message) {
    this.mFlightViewForm.onGetCityListFailure(message);
  }

  @Override
  public void onGetAllCountriesSuccess(ResponseApi<?> responseApi) {

    this.mFlightViewForm.countryList((ResponseApi<List<ItemCountry>>) responseApi);
  }

  @Override
  public void onGetAllCountriesFailure(String message) {

    this.mFlightViewForm.onGetCountryListFailure(message);
  }
}
