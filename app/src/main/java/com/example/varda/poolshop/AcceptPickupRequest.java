package com.example.varda.poolshop;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import de.hdodenhof.circleimageview.CircleImageView;

public class AcceptPickupRequest extends AppCompatActivity {


    CircleImageView c;
    TextView b;
    TextView m;
    Button book;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){

            case 200:

                boolean locationAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_pickup_request);
        c = findViewById(R.id.profile);
        b = findViewById(R.id.call);
        m = findViewById(R.id.message);
        book = findViewById(R.id.book);

        String[] perms = new String[]{"android.permission.CALL_PHONE", "android.permission.SEND_SMS"};

        int permsRequestCode = 200;
        requestPermissions(perms, permsRequestCode);

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "6503099157";  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceptPickupRequest.this, ItemsList.class);
                startActivity(intent);
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "6503099157"));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        Glide.with(this).load("https://media.licdn.com/dms/image/C5603AQGyzGrWu1ekbw/profile-displayphoto-shrink_800_800/0?e=1560988800&v=beta&t=SahHNvNwJyRdoUunXKz7dR5TUuDr2ktBgKt7Y388Ulg")
                .centerCrop()
                .crossFade()
                .override(150, 150)
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(c);
    }
}
