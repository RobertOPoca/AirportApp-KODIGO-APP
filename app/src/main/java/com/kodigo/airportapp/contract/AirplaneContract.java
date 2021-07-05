package com.kodigo.airportapp.contract;

import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.listener.OnAirplaneListenerRead;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public interface AirplaneContract {

  interface View {

    void showAirplanes(ResponseApi<List<ItemAirplane>> airplanes);

    void onGetAllAirplanesFailure(String message);

  }

  interface Presenter {

    void showAirplanes();
  }

  interface Model {

    void getAllAirplanes();
  }

  interface OnAirplaneListener extends OnAirplaneListenerRead {

  }
}
