package com.example.ravit.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by ravit on 4/20/2018.
 */

public class AddList extends Activity  {

    //MyDataBase mdb;
    private EditText itemName, itemPrice;
    private Spinner itemSize, itemFav;
    private Button save;

    private TextView favTv;
    //String name,price1;
    //private int price=0;
    public String option,name;
    public String quantity,price;

    private static final String DATABASENAME ="Shopping.db";
    private static final int DATABASEVERSION = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);


        itemName = (EditText)findViewById(R.id.itemName);
        itemPrice = (EditText)findViewById(R.id.itemPrice);
        itemSize = (Spinner)findViewById(R.id.itemSize);
        itemFav = (Spinner)findViewById(R.id.itemFav);
        save = (Button)findViewById(R.id.save);
        favTv = (TextView)findViewById(R.id.favTv);

        final MyDataBase mdb=new MyDataBase(getApplicationContext(), DATABASENAME, null, DATABASEVERSION);
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_size, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        itemSize.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.fav_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        itemFav.setAdapter(adapter1);
        */


      //  price = Integer.parseInt(itemPrice.getText().toString());
       // price1 = price +"";


        save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if(itemName.getText().toString() != null)
                {
                    name= itemName.getText().toString();
                }
                Log.i("300",name);
                //String price1;
                price = itemPrice.getText().toString();
                //price1 = ""+price;

                // Toast.makeText(AddList.this, name + price + quantity + option,Toast.LENGTH_LONG).show();

                boolean isInserted = mdb.insertData(name,price,quantity,option);

                if (isInserted == true)
                {
                    Toast.makeText(AddList.this, "Data inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(AddList.this, "Data is not inserted, Please enter correctly",Toast.LENGTH_LONG).show();
                }



            }
        });


        itemSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                TextView tv=(TextView)view;
                quantity=tv.getText().toString();
                Log.i("100","Reached here");
                //Toast.makeText(AddList.this,quantity,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        itemFav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Log.i("200","Reached here");
                TextView tv1=(TextView)view;
                option=tv1.getText().toString();
                //Toast.makeText(AddList.this,option,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


}
