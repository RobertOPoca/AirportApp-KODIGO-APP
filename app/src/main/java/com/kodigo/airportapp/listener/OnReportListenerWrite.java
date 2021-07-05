package com.kodigo.airportapp.listener;

import com.kodigo.airportapp.response.ResponseApi;

public interface OnReportListenerWrite {

  void onReportByDateSuccess(ResponseApi<?> responseApi);

  void onReportByDateFailure(String message);

  void onReportByIdSuccess(ResponseApi<?> responseApi);

  void onReportByIdFailure(String message);
}
