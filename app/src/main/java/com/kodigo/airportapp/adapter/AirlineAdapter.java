package com.kodigo.airportapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kodigo.airportapp.R;
import com.kodigo.airportapp.item.ItemAirline;

import java.util.List;

public class AirlineAdapter extends RecyclerView.Adapter<AirlineAdapter.AirlineViewHolder> {

  Context context;
  List<ItemAirline> itemAirlineList;
  private SelectedItem selectedItem;

  public AirlineAdapter(Context context, List<ItemAirline> itemAirlineList,
      SelectedItem selectedItem) {
    this.context = context;
    this.itemAirlineList = itemAirlineList;
    this.selectedItem = selectedItem;
  }

  @NonNull
  @Override
  public AirlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.airline_item, parent, false);
    return new AirlineViewHolder(view);
  }

  @Override
  public void onBindViewHolder(AirlineViewHolder holder, final int position) {

    holder.name.setText(itemAirlineList.get(position).getAirlineName());

  }

  @Override
  public int getItemCount() {
    return itemAirlineList.size();
  }

  public interface SelectedItem {

    void selectedItem(ItemAirline itemAirline);
  }

  public class AirlineViewHolder extends RecyclerView.ViewHolder {

    TextView name;

    public AirlineViewHolder(@NonNull View itemView) {
      super(itemView);
      name = itemView.findViewById(R.id.name);
      itemView.setOnClickListener(
          v -> selectedItem.selectedItem(itemAirlineList.get(getAdapterPosition())));

    }
  }

}

