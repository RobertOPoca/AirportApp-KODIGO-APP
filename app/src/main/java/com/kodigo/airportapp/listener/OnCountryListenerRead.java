package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnCountryListenerRead {

  void onGetAllCountriesSuccess(ResponseApi<?> responseApi);

  void onGetAllCountriesFailure(String message);
}
