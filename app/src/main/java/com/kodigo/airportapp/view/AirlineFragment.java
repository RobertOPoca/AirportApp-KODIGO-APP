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
import com.kodigo.airportapp.adapter.AirlineAdapter;
import com.kodigo.airportapp.contract.AirlineContract;
import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.presenter.AirlinePresenter;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineFragment extends Fragment implements AirlineContract.View,
    AirlineAdapter.SelectedItem {

  @BindView(R.id.airlineRecycler)
  RecyclerView airlineRecycler;
  private View view;
  private AirlineContract.Presenter airlinePresenter;
  private AirlineAdapter airlineAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_airline, container, false);
    ButterKnife.bind(this, view);
    this.airlinePresenter = new AirlinePresenter(this);
    return view;
  }

  private void setAirlineRecycler(List<ItemAirline> itemAirlineList) {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
        RecyclerView.VERTICAL, false);
    airlineRecycler.setLayoutManager(layoutManager);
    airlineAdapter = new AirlineAdapter(getContext(), itemAirlineList, this);
    airlineRecycler.setAdapter(airlineAdapter);
  }

  @Override
  public void showAirline(ResponseApi<List<ItemAirline>> airlines) {
    setAirlineRecycler(airlines.getData());
  }

  @Override
  public void onGetAllAirlineFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void selectedItem(ItemAirline itemAirline) {
    Toast.makeText(getActivity(), itemAirline.getAirlineName(), Toast.LENGTH_SHORT).show();
  }
}