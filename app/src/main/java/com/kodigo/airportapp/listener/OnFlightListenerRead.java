package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnFlightListenerRead {

  void onGetAllFlightsSuccess(ResponseApi<?> responseApi);

  void onGetAllFlightsFailure(String message);

  void onGetFlightSuccess(ResponseApi<?> responseApi);

  void onGetFlightFailure(String message);
}
