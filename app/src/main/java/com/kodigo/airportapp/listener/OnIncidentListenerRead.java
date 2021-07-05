package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnIncidentListenerRead {

  void onGetAllIncidentSuccess(ResponseApi<?> responseApi);

  void onGetAllIncidentFailure(String message);
}
