package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnIncidentListenerWrite {

  void onCreateIncidentSuccess(ResponseApi<?> responseApi);

  void onCreateIncidentFailure(String message);
}
