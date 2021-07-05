package com.kodigo.airportapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kodigo.airportapp.R;
import com.kodigo.airportapp.item.ItemFlight;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

  Context context;
  List<ItemFlight> itemFlightList;
  private FlightAdapter.SelectedItem selectedItem;


  public FlightAdapter(Context context, List<ItemFlight> itemFlightList,
      FlightAdapter.SelectedItem selectedItem) {
    this.context = context;
    this.itemFlightList = itemFlightList;
    this.selectedItem = selectedItem;
  }

  @NonNull
  @Override
  public FlightAdapter.FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.flight_item, parent, false);
    return new FlightAdapter.FlightViewHolder(view);
  }

  @Override
  public void onBindViewHolder(FlightAdapter.FlightViewHolder holder, final int position) {

    holder.flight.setText(String.valueOf(itemFlightList.get(position).getIdFlight()));
    holder.departureLocation.setText(itemFlightList.get(position).getDepartureCity() + ", " +
        itemFlightList.get(position).getDepartureCountry());
    holder.airplane.setText(itemFlightList.get(position).getModel() + ", " +
        itemFlightList.get(position).getAirline());
    holder.departureTime.setText(itemFlightList.get(position).getDepartureDate() + " " +
        itemFlightList.get(position).getDepartureTime());
    holder.arrivalLocation.setText(itemFlightList.get(position).getDestinationCity() + ", " +
        itemFlightList.get(position).getArrivalCountry());

    holder.arrivalTime.setText(itemFlightList.get(position).getArrivalDate() + " " +
        itemFlightList.get(position).getArrivalTime());
    holder.status.setText(itemFlightList.get(position).getStatus());
  }

  @Override
  public int getItemCount() {
    return itemFlightList.size();
  }


  public interface SelectedItem {

    void selectedItem(ItemFlight itemFlight);
  }

  public class FlightViewHolder extends RecyclerView.ViewHolder {


    TextView flight;
    TextView departureCity;
    TextView departureCountry;
    TextView airplane;
    TextView departureLocation;
    TextView departureTime;
    TextView arrivalLocation;
    TextView arrivalTime;
    TextView status;

    public FlightViewHolder(@NonNull View itemView) {
      super(itemView);

      flight = itemView.findViewById(R.id.flightNumber);
      departureLocation = itemView.findViewById(R.id.departureLocation);
      departureTime = itemView.findViewById(R.id.departureTime);
      airplane = itemView.findViewById(R.id.txvAirplane);
      arrivalLocation = itemView.findViewById(R.id.arrivalLocation);
      arrivalTime = itemView.findViewById(R.id.arrivalTime);
      status = itemView.findViewById(R.id.statusFlight);
      itemView.setOnClickListener(
          v -> selectedItem.selectedItem(itemFlightList.get(getAdapterPosition())));

    }
  }
}
