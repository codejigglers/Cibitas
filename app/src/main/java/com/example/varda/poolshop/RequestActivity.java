package com.example.varda.poolshop;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import retrofit2.Call;
import studios.codelight.smartloginlibrary.LoginType;
import studios.codelight.smartloginlibrary.SmartLoginFactory;


public final class RequestActivity extends AppCompatActivity implements AddDriverDialogFragment.AddDriverDialogListener {

    private static final Level LOGGING_LEVEL = Level.OFF;

    private static final String PREF_ACCOUNT_NAME = "vardan";

    static final String TAG = "CalendarSampleActivity";

    private static final String BASE_URL = "http://vardan.pythonanywhere.com/";



    final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    RequestDeliveryModel model = new RequestDeliveryModel();

    RequestsAdapter adapter;

    public FragmentTransaction ft;
    FrameLayout fm;
    public Fragment loadingFragment;
    protected DrawerLayout drawerLayout;
    protected ImageView profile;
    private View navHeader;
    private NavigationView navigationView;
    private ArrayList<RequestDeliveryModel> data = new ArrayList<>();
    private LinearLayout noAppointmentLayout;
    private SwipeRefreshLayout appointmentLayout;
    private RecyclerView appointmentRv;
    FloatingActionButton f1;
    FloatingActionButton f2;
    TextView navName;
    Call<List<RequestDeliveryModel>> call;
    public boolean isConnected = false;

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_activity);

        noAppointmentLayout = findViewById(R.id.no_appointment_view);
        appointmentLayout = findViewById(R.id.appointment_view);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        navName = navHeader.findViewById(R.id.navName);
        setTitle("View All Riders");

        appointmentLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                appointmentLayout.setRefreshing(false);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent = new Intent(RequestActivity.this, RequestActivity.class);
                        startActivityForResult(intent, 22);
                        return true;
                    case R.id.nav_sign_out:
                        SmartLoginFactory.build(LoginType.CustomLogin).logout(RequestActivity.this);
                        Intent intent1 = new Intent(RequestActivity.this, MainActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.view_pickup_requests:
                        SmartLoginFactory.build(LoginType.CustomLogin).logout(RequestActivity.this);
                        Intent intent2 = new Intent(RequestActivity.this, PickupReqestsActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.view_requests:
                        SmartLoginFactory.build(LoginType.CustomLogin).logout(RequestActivity.this);
                        Intent intent3 = new Intent(RequestActivity.this, AcceptPickupRequest.class);
                        startActivity(intent3);
                        return true;
                }
                return true;
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        fm = findViewById(R.id.loader);
        f1 = findViewById(R.id.fab);
        f2 = findViewById(R.id.fab2);
        appointmentRv = findViewById(R.id.appointment_recyclerview);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });
        Endpoints service = RetrofitInstance.getRetrofitInstance().create(Endpoints.class);
        call = service.getAllProfs();
//        call.enqueue(new Callback<List<RequestDeliveryModel>>() {
//
//            @Override
//            public void onResponse(Call<List<RequestDeliveryModel>> call, Response<List<RequestDeliveryModel>> response) {
//                Log.e("Sam", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<RequestDeliveryModel>> call, Throwable t) {
//                Log.e("Sam", "here2");
//
//                Toast.makeText(RequestActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
        MyProperties hj = new MyProperties();
        data = MyProperties.getInstance().data;

        adapter = new RequestsAdapter(data, this);
        appointmentRv.setLayoutManager(new LinearLayoutManager(this));
        appointmentRv.setAdapter(adapter);


    }


    private void showEditDialog() {
        AddDriverDialogFragment addDriverFragment = new AddDriverDialogFragment();
        addDriverFragment.show(getSupportFragmentManager(),"example dialog");
    }



    public void removeLoader() {
        getSupportFragmentManager().beginTransaction().remove(loadingFragment).commitAllowingStateLoss();
        //drawerLayout.getBackground().setAlpha(0);
        fm.setVisibility(View.GONE);
    }

    public void showLoader() {
        //drawerLayout.getBackground().setAlpha(127);
        if (loadingFragment.isAdded()) {
            return;
        }
        fm.setVisibility(View.VISIBLE);
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.loader, loadingFragment, "MyLoader").commit();
    }

    @Override
    public void applyTexts(String name, String address, String date, String time) {
        RequestDeliveryModel r = new RequestDeliveryModel();
        r.setTime(time);
        r.setName("Vardan");
        r.setLocation(address);
        r.setDate(date);
        data.add(r);
        adapter.notifyDataSetChanged();
    }
}