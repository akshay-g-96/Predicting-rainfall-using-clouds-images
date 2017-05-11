package com.example.sairameshg.prci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class begin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
    }

    public void onB3Click(View v)
    {
        // Button B1=(Button)findViewById(R.id.B1);

        Intent intentcapture=new Intent(getApplicationContext(),capture.class);
        startActivity(intentcapture);
    }
    public void onB4Click(View v)
    {
        // Button B1=(Button)findViewById(R.id.B1);

        Intent intentinformation=new Intent(getApplicationContext(),information_1.class);
        startActivity(intentinformation);
    }




}
