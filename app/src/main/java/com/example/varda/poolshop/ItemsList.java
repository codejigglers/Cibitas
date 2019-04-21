package com.example.varda.poolshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemsList extends AppCompatActivity implements AddItemDialogFragment.AddItemDialogListener {
    ArrayList<Items> items;
    RecyclerView recyclerView;
    Button addItem, submit;
    ItemsAdapter adapter;


    private static final String TAG = ItemsList.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);
        setTitle("Item List");
        recyclerView = findViewById(R.id.recyler_view);
        addItem = findViewById(R.id.add_item);
        submit = findViewById(R.id.submit_list);
        items = new ArrayList<>();
        adapter = new ItemsAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //add item button shows the custom dialog
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });

        //submit button submits the request and directs to another activity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestDeliveryModel my = MyProperties.getInstance().data.get(0);
                MyProperties.getInstance().myData.add(my);
                MyProperties.getInstance().data.remove(0);
                Intent intent = new Intent(ItemsList.this,RequestActivity.class);
                startActivity(intent);
            }
        });


    }

    private void showEditDialog() {
        AddItemDialogFragment addItemFragment = new AddItemDialogFragment();
        addItemFragment.show(getSupportFragmentManager(),"example dialog");
    }

    @Override
    public void applyTexts(String item, String quantity) {
        items.add(new Items(item,quantity));
        adapter.notifyDataSetChanged();
        Toast.makeText(this,"item added",Toast.LENGTH_SHORT).show();
    }
}
