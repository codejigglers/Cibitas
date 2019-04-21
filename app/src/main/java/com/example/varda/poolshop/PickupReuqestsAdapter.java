package com.example.varda.poolshop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PickupReuqestsAdapter extends RecyclerView.Adapter<PickupReuqestsAdapter.ViewHolder> {

    private ArrayList<RequestDeliveryModel> data;
    //private AppointmentClickListener appCompatActivity;

    public PickupReuqestsAdapter(ArrayList<RequestDeliveryModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PickupReuqestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.pickup_requests, viewGroup, false);
        PickupReuqestsAdapter.ViewHolder viewHolder = new PickupReuqestsAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PickupReuqestsAdapter.ViewHolder viewHolder, int i) {
        final RequestDeliveryModel element = data.get(i);
        viewHolder.dropLocation.setText(element.date);
        viewHolder.time.setText(element.time);
        viewHolder.location.setText("Safeway, Santa Clara");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView location;
        TextView dropLocation;
        TextView time;
        TextView delete;
        TextView accept;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.location);
            dropLocation = itemView.findViewById(R.id.dropLocation);
            time = itemView.findViewById(R.id.dropTime);
            delete = itemView.findViewById(R.id.deleteButton);
            accept = itemView.findViewById(R.id.accept);
        }

    }
}
