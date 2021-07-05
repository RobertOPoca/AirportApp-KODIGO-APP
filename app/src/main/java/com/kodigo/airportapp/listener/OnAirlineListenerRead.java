package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnAirlineListenerRead {

  void onGetAllAirlineSuccess(ResponseApi<?> responseApi);

  void onGetAllAirlineFailure(String message);
}
