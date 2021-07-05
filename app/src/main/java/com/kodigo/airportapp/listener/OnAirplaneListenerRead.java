package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnAirplaneListenerRead {

  void onGetAllAirplaneSuccess(ResponseApi<?> responseApi);

  void onGetAllAirplaneFailure(String message);
}
