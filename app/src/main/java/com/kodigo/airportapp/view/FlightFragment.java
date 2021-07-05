package com.kodigo.airportapp.view;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.kodigo.airportapp.R;
import com.kodigo.airportapp.adapter.FlightAdapter;
import com.kodigo.airportapp.contract.FlightContract;
import com.kodigo.airportapp.item.ItemFlight;
import com.kodigo.airportapp.presenter.FlightPresenter;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FlightFragment extends Fragment implements FlightContract.View,
    FlightAdapter.SelectedItem,
    AdapterView.OnItemClickListener, View.OnClickListener {

  @BindView(R.id.autoTxvStatus)
  AutoCompleteTextView txvStatus;
  @BindView(R.id.btnCreateFlight)
  Button btnCreateFlight;
  @BindView(R.id.btnLoadByExcel)
  Button btnLoadByExcel;
  @BindView(R.id.btnReportByDay)
  Button btnReportByDay;
  @BindView(R.id.flightRecycler)
  RecyclerView flightRecycler;

  private FlightContract.Presenter presenter;
  private FlightAdapter flightAdapter;
  private List<ItemFlight> itemFlightList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_flight, container, false);
    ButterKnife.bind(this, view);
    this.presenter = new FlightPresenter(this);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
        RecyclerView.VERTICAL, false);
    flightRecycler.setLayoutManager(layoutManager);

    String[] optionStatus = {"ALL", "ONTIME", "DELAYED", "CANCELLED", "LANDED"};
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.menu_status_item,
        optionStatus);
    this.txvStatus.setText(arrayAdapter.getItem(0), false);
    this.txvStatus.setAdapter(arrayAdapter);
    this.btnCreateFlight.setOnClickListener(this);
    this.btnLoadByExcel.setOnClickListener(this);
    this.txvStatus.setOnItemClickListener(this);
    this.btnReportByDay.setOnClickListener(this);
    return view;
  }

  private void reportDate() {
    final Calendar c = Calendar.getInstance();
    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
        (view, year, monthOfYear, dayOfMonth) -> reportByDate(
            year + "-" + (monthOfYear + 1) + "-" + dayOfMonth), c.get(Calendar.YEAR),
        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    datePickerDialog.show();
  }

  private void createFlight() {
    FlightFormFragment fr = new FlightFormFragment();
    getActivity().getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, fr).addToBackStack(null).commit();
  }

  private void setFlightRecycler(List<ItemFlight> itemFlightList) {
    this.itemFlightList = itemFlightList;
    flightAdapter = new FlightAdapter(getContext(), itemFlightList, this);
    flightRecycler.setAdapter(this.flightAdapter);
  }

  @Override
  public void selectedItem(ItemFlight itemFlight) {
    Bundle bundle = new Bundle();
    bundle.putInt("idFlight", itemFlight.getIdFlight());
    FlightFormFragment fr = new FlightFormFragment();
    fr.setArguments(bundle);
    getActivity().getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, fr)
        .addToBackStack(null)
        .commit();
  }

  @Override
  public void showFlights(ResponseApi<List<ItemFlight>> flights) {
    setFlightRecycler(flights.getData());
  }

  @Override
  public void onGetAllFlightsFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void loadFlightsByExcel() {
    this.presenter.loadFlightsByExcel();
  }

  @Override
  public void onLoadExcelSuccess(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    this.presenter.showAll();
  }

  @Override
  public void onLoadExcelFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void reportByDate(String date) {
    this.presenter.reportByDate(date);
  }

  @Override
  public void onReportByDateSuccess(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onReportByDateFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    List<ItemFlight> result = new ArrayList<>();
    if (i > 0) {
      for (ItemFlight itemFlight : itemFlightList) {
        if (itemFlight.getStatus().equals(adapterView.getItemAtPosition(i).toString())) {
          result.add(itemFlight);
        }
      }
    } else {
      result = itemFlightList;
    }
    flightAdapter = new FlightAdapter(getContext(), result, this);
    flightRecycler.setAdapter(this.flightAdapter);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnCreateFlight:
        createFlight();
        break;
      case R.id.btnLoadByExcel:
        loadFlightsByExcel();
        break;
      case R.id.btnReportByDay:
        reportDate();
        break;
      default:
        break;
    }
  }
}