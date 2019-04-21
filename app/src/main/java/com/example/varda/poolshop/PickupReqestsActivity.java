package com.example.varda.poolshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PickupReqestsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<RequestDeliveryModel> data = new ArrayList<>();
    PickupReuqestsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_reqests);
        setTitle("My Pickup Requests");

        recyclerView = findViewById(R.id.pickupRequestElementRecyclerView);

        data = MyProperties.getInstance().myData;

        adapter = new PickupReuqestsAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
