package com.kodigo.airportapp.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kodigo.airportapp.R;
import com.kodigo.airportapp.contract.FlightContract;
import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.item.ItemCity;
import com.kodigo.airportapp.item.ItemCountry;
import com.kodigo.airportapp.item.ItemFlight;
import com.kodigo.airportapp.model.Flight;
import com.kodigo.airportapp.presenter.FlightPresenter;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightFormFragment extends Fragment implements FlightContract.ViewForm,
    View.OnClickListener {

  @BindView(R.id.txtAirline)
  AutoCompleteTextView txtAirline;
  @BindView(R.id.txtAirplane)
  AutoCompleteTextView txtAirplane;
  @BindView(R.id.txvStatus)
  AutoCompleteTextView txvStatus;
  @BindView(R.id.txvDepartCountry)
  AutoCompleteTextView txvDepartCountry;
  @BindView(R.id.txvDepartCity)
  AutoCompleteTextView txvDepartCity;
  @BindView(R.id.txvArrivalCountry)
  AutoCompleteTextView txvArrCountry;
  @BindView(R.id.txvArrivalCity)
  AutoCompleteTextView txvArrCity;
  @BindView(R.id.btnSaveFlight)
  Button btnSaveFlight;
  @BindView(R.id.btnCreateIncident)
  Button btnCreateIncient;
  @BindView(R.id.btnReportById)
  Button btnReportById;
  @BindView(R.id.btnDepDate)
  Button btnDeptDate;
  @BindView(R.id.btnDepTime)
  Button btnDeptTime;
  @BindView(R.id.btnArrDate)
  Button btnArrDate;
  @BindView(R.id.btnArrTime)
  Button btnArrTime;
  @BindView(R.id.txtDeptDate)
  EditText txtDeptDate;
  @BindView(R.id.txtDeptTime)
  EditText txtDeptTime;
  @BindView(R.id.txtArrDate)
  EditText txtArrDate;
  @BindView(R.id.txtArrTime)
  EditText txtArrTime;

  private FlightContract.Presenter presenter;
  private Map<Integer, Integer> airlineMap;
  private List<ItemAirline> itemAirlineList;
  private List<ItemCity> itemCityList;
  private List<ItemCountry> itemCountryList;
  private Integer idCountryFlag;
  private ItemFlight itemFlight;
  private String[] optionStatus;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View viewF = inflater.inflate(R.layout.fragment_flight_form, container, false);
    ButterKnife.bind(this, viewF);
    this.presenter = new FlightPresenter(this);
    this.btnCreateIncient.setVisibility(View.INVISIBLE);
    this.btnReportById.setVisibility(View.INVISIBLE);
    this.airlineMap = new HashMap<>();
    Bundle bundle = getArguments();
    if (bundle != null) {
      getFlightById(bundle.getInt("idFlight"));

      this.btnCreateIncient.setVisibility(View.VISIBLE);
      this.btnReportById.setVisibility(View.VISIBLE);
    }
    optionStatus = new String[]{"ONTIME", "DELAYED", "CANCELLED", "LANDED"};
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.menu_status_item,
        optionStatus);
    this.txvStatus.setText(arrayAdapter.getItem(0), false);
    this.txvStatus.setAdapter(arrayAdapter);
    this.btnSaveFlight.setOnClickListener(this);
    this.btnCreateIncient.setOnClickListener(this);
    this.btnDeptDate.setOnClickListener(this);
    this.btnDeptTime.setOnClickListener(this);
    this.btnArrDate.setOnClickListener(this);
    this.btnArrTime.setOnClickListener(this);
    this.btnReportById.setOnClickListener(this);
    this.txvDepartCountry.setOnItemClickListener(
        (adapterView, view, i, l) -> doListCity(itemCityList, txvDepartCity,
            itemCountryList.get(i).getIdCountry(), R.layout.menu_city_item));
    this.txvArrCountry.setOnItemClickListener(
        (adapterView, view, i, l) -> doListCity(itemCityList, txvArrCity,
            itemCountryList.get(i).getIdCountry(), R.layout.menu_arrival_city_item));
    return viewF;
  }

  private void save() {
    Flight flight = new Flight();
    flight.setModel(txtAirplane.getText().toString());
    flight.setIdAirline(searchAirlineByName(txtAirline.getText().toString()));
    flight.setIdDepartureCity(searchCityByName(txvDepartCity.getText().toString()));
    flight.setIdArrivalCity(searchCityByName(txvArrCity.getText().toString()));
    flight.setStatus(txvStatus.getText().toString());
    flight.setDepartureTime(
        txtDeptDate.getText().toString() + " " + txtDeptTime.getText().toString());
    flight.setArrivalTime(txtArrDate.getText().toString() + " " + txtArrTime.getText().toString());
    if (itemFlight != null) {
      flight.setIdFlight(itemFlight.getIdFlight());
      updateFlight(flight);
    } else {
      saveFlight(flight);
    }
  }

  private void createIncident() {
    Bundle bundle = new Bundle();
    bundle.putInt("idFlight", itemFlight.getIdFlight());
    IncidentFragment fr = new IncidentFragment();
    fr.setArguments(bundle);
    getActivity().getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, fr)
        .addToBackStack(null)
        .commit();
  }

  private Integer searchCityByName(String name) {
    Integer result = -1;
    for (ItemCity itemCity : itemCityList) {
      if (itemCity.getCityName().equals(name)) {
        result = itemCity.getIdCity();
        break;
      }
    }
    return result;
  }

  private Integer searchAirlineByName(String name) {
    Integer result = -1;
    for (ItemAirline itemAirline : itemAirlineList) {
      if (itemAirline.getAirlineName().equals(name)) {
        result = itemAirline.getIdAirline();
        break;
      }
    }
    return result;
  }

  private void showDate(EditText txt) {
    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);

    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
        (view, year, monthOfYear, dayOfMonth) -> txt
            .setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth), mYear, mMonth, mDay);
    datePickerDialog.show();
  }

  private void showTime(EditText txt) {
    final Calendar c = Calendar.getInstance();
    int mHour = c.get(Calendar.HOUR_OF_DAY);
    int mMinute = c.get(Calendar.MINUTE);

    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
        (view, hourOfDay, minute) -> txt.setText(hourOfDay + ":" + minute + ":00"), mHour, mMinute,
        false);
    timePickerDialog.show();

  }


  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnDepDate:
        showDate(this.txtDeptDate);
        break;
      case R.id.btnDepTime:
        showTime(this.txtDeptTime);
        break;
      case R.id.btnArrDate:
        showDate(this.txtArrDate);
        break;
      case R.id.btnArrTime:
        showTime(this.txtArrTime);
        break;
      case R.id.btnSaveFlight:
        save();
        break;
      case R.id.btnCreateIncident:
        createIncident();
        break;
      case R.id.btnReportById:
        reportById(itemFlight.getIdFlight());
        break;
      default:
        break;
    }


  }


  @Override
  public void getFlightById(Integer id) {
    this.presenter.getFlight(id);
  }

  @Override
  public void onGetFlightFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }


  @Override
  public void airlineList(ResponseApi<List<ItemAirline>> airlines) {
    List<String> nameAirlineList = new ArrayList<>();
    this.itemAirlineList = airlines.getData();
    int position = 0;
    for (ItemAirline airline : airlines.getData()) {
      nameAirlineList.add(airline.getAirlineName());
      this.airlineMap.put(position, airline.getIdAirline());
    }
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
        R.layout.menu_airline_item,
        nameAirlineList);
    this.txtAirline.setText(arrayAdapter.getItem(0), false);
    this.txtAirline.setAdapter(arrayAdapter);
  }

  @Override
  public void onGetAirlineListFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void airplaneList(ResponseApi<List<ItemAirplane>> airplane) {

    List<String> airplaneList = new ArrayList<>();
    for (ItemAirplane plane : airplane.getData()) {
      airplaneList.add(plane.getModel());
    }
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
        R.layout.menu_airplane_item,
        airplaneList);
    this.txtAirplane.setText(arrayAdapter.getItem(0), false);
    this.txtAirplane.setAdapter(arrayAdapter);
  }

  @Override
  public void onGetAirplaneListFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void countryList(ResponseApi<List<ItemCountry>> countries) {
    this.itemCountryList = countries.getData();
    List<String> countryList = new ArrayList<>();
    idCountryFlag = countries.getData().get(0).getIdCountry();
    for (ItemCountry country : countries.getData()) {
      countryList.add(country.getCountryName());
    }
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
        R.layout.menu_country_item,
        countryList);
    this.txvDepartCountry.setText(arrayAdapter.getItem(0), false);
    this.txvDepartCountry.setAdapter(arrayAdapter);

    ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(getActivity(),
        R.layout.menu_arrival_country_item,
        countryList);
    this.txvArrCountry.setText(arrayAdapter2.getItem(0), false);
    this.txvArrCountry.setAdapter(arrayAdapter2);
    if (itemFlight != null) {
      this.txvDepartCountry.setText(itemFlight.getDepartureCountry());
      this.txvDepartCountry.setText(itemFlight.getArrivalCountry());

    }


  }

  @Override
  public void onGetCountryListFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void cityList(ResponseApi<List<ItemCity>> cities) {
    this.itemCityList = cities.getData();

    doListCity(cities.getData(), txvDepartCity, itemFlight != null ? Integer.
        parseInt(itemFlight.getIdDepartureCountry()) : idCountryFlag, R.layout.menu_city_item);
    doListCity(cities.getData(), txvArrCity, itemFlight != null ? Integer.
            parseInt(itemFlight.getIdArrivalCountry()) : idCountryFlag,
        R.layout.menu_arrival_city_item);
    if (itemFlight != null) {
      this.txvDepartCity.setText(itemFlight.getDepartureCity());
      this.txvArrCity.setText(itemFlight.getDestinationCity());

    }
  }

  @Override
  public void onGetCityListFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  private void doListCity(List<ItemCity> cities, AutoCompleteTextView txt, Integer idCountry,
      Integer id) {

    List<String> cityList = new ArrayList<>();
    for (ItemCity city : cities) {
      if (city.getIdCountry().equals(idCountry)) {
        cityList.add(city.getCityName());
      }
    }
    if (cityList.isEmpty()) {
      cityList.add("N/A");
    }
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), id, cityList);
    txt.setText(arrayAdapter.getItem(0), false);
    txt.setAdapter(arrayAdapter);
  }


  @Override
  public void saveFlight(Flight flight) {
    this.presenter.createFlight(flight);
  }

  @Override
  public void updateFlight(Flight flight) {
    this.presenter.updateFlight(flight);
  }

  @Override
  public void onGetFlightSuccess(ResponseApi<ItemFlight> flightResponseApi) {
    this.itemFlight = flightResponseApi.getData();
    this.txtDeptDate.setText(flightResponseApi.getData().getDepartureDate());
    this.txtDeptTime.setText(flightResponseApi.getData().getDepartureTime());
    this.txtArrTime.setText(flightResponseApi.getData().getArrivalTime());
    this.txtArrDate.setText(flightResponseApi.getData().getArrivalDate());
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.menu_status_item,
        optionStatus);
    this.txvStatus.setText(flightResponseApi.getData().getStatus());
    this.txvStatus.setAdapter(arrayAdapter);


  }

  @Override
  public void onSaveSuccess(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    FlightFragment fr = new FlightFragment();
    getActivity().getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, fr)
        .addToBackStack(null)
        .commit();
  }

  @Override
  public void onSaveFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onUpdateSuccess(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    createIncident();
  }

  @Override
  public void onUpdateFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void reportById(Integer id) {
    this.presenter.reportById(id);
  }


  @Override
  public void onReportByIdSuccess(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onReportByIdFailure(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }


}