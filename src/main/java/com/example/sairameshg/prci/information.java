package com.example.sairameshg.prci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    public void onB09Click(View v)
    {
        // Button B1=(Button)findViewById(R.id.B1);
        Intent intentSignUP=new Intent(getApplicationContext(),information_3.class);
        startActivity(intentSignUP);
    }

}
