package com.example.ravit.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{


    private TextView appName;
    private Button addButton, manageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appName = (TextView)findViewById(R.id.appName);
        addButton = (Button)findViewById(R.id.addButton);
        manageButton = (Button)findViewById(R.id.manageButton);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MainActivity.this,AddList.class);
                startActivity(i1);

            }
        });

        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2= new Intent(MainActivity.this,ManageList.class);
                startActivity(i2);

            }
        });

    }
}
