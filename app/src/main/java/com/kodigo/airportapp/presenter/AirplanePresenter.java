package com.kodigo.airportapp.presenter;

import com.kodigo.airportapp.contract.AirplaneContract;
import com.kodigo.airportapp.interactor.AirplaneInteractor;
import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public class AirplanePresenter implements AirplaneContract.Presenter,
    AirplaneContract.OnAirplaneListener {

  private AirplaneContract.Model mAirplaneInteractor;
  private AirplaneContract.View mAirplaneView;

  public AirplanePresenter(AirplaneContract.View view) {
    this.mAirplaneView = view;
    this.mAirplaneInteractor = new AirplaneInteractor(this);
    showAirplanes();
  }

  @Override
  public void showAirplanes() {
    this.mAirplaneInteractor.getAllAirplanes();
  }


  @Override
  public void onGetAllAirplaneSuccess(ResponseApi<?> responseApi) {

    this.mAirplaneView.showAirplanes((ResponseApi<List<ItemAirplane>>) responseApi);
  }

  @Override
  public void onGetAllAirplaneFailure(String message) {
    this.mAirplaneView.onGetAllAirplanesFailure(message);
  }
}
