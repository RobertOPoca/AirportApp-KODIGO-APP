package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnCityListenerRead {

  void onGetAllCitiesSuccess(ResponseApi<?> responseApi);

  void onGetAllCitiesFailure(String message);
}
