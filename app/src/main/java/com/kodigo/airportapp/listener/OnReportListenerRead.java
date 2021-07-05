package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnReportListenerRead {

  void onLoadExcelSuccess(ResponseApi<?> responseApi);

  void onLoadExcelFailure(String message);
}
