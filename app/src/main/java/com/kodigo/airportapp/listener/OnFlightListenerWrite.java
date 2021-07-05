package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnFlightListenerWrite {

  void onCreateFlightSuccess(ResponseApi<?> responseApi);

  void onCreateFlightFailure(String message);


  void onUpdateFlightSuccess(ResponseApi<?> responseApi);

  void onUpdateFlightFailure(String message);
}
