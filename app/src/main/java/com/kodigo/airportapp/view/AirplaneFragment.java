package com.kodigo.airportapp.view;

import android.os.Bundle;

import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kodigo.airportapp.R;
import com.kodigo.airportapp.adapter.AirplaneAdapter;
import com.kodigo.airportapp.contract.AirplaneContract;
import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.presenter.AirplanePresenter;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public class AirplaneFragment extends Fragment implements AirplaneContract.View,
    AirplaneAdapter.SelectedItem {


  private AirplaneContract.Presenter airplanePresenter;
  private AirplaneAdapter airplaneAdapter;
  private RecyclerView airplaneRecycler;
  private View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    this.view = inflater.inflate(R.layout.fragment_airplane, container, false);

    this.airplanePresenter = new AirplanePresenter(this);
    this.airplaneRecycler = view.findViewById(R.id.airplaneRecycler);
    return this.view;
  }

  private void setAirplaneRecycler(List<ItemAirplane> itemAirplaneList) {

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
        RecyclerView.VERTICAL, false);
    airplaneRecycler.setLayoutManager(layoutManager);
    airplaneAdapter = new AirplaneAdapter(getContext(), itemAirplaneList, this);
    airplaneRecycler.setAdapter(airplaneAdapter);

  }

  @Override
  public void selectedItem(ItemAirplane itemAirplane) {
    Toast.makeText(getActivity(), itemAirplane.getModel(), Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showAirplanes(ResponseApi<List<ItemAirplane>> airplanes) {
    setAirplaneRecycler(airplanes.getData());
  }

  @Override
  public void onGetAllAirplanesFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }
}