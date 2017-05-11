package com.example.sairameshg.prci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onB1Click(View v)
    {
        // Button B1=(Button)findViewById(R.id.B1);

        Intent intentbegin=new Intent(getApplicationContext(),begin.class);
        startActivity(intentbegin);
    }

    public void onB2Click(View v)
    {
        // Button B1=(Button)findViewById(R.id.B1);

        Intent intentbegin=new Intent(getApplicationContext(),guidelines_1.class);
        startActivity(intentbegin);
    }





}
