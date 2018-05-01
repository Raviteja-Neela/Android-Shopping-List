package com.example.ravit.shoppinglist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by ravit on 4/20/2018.
 */

public class ManageList extends AppCompatActivity {

    private ListView list1;
    public static final String DATABASE_NAME = "shopping.db";
    private static final int DATABASEVERSION = 1;
    ArrayList<TaskInfo> alist;
    TaskInfo ts;
    MyArrayAdapter mad;
    private Button totalcost,favorite;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managelist);

        totalcost=(Button)findViewById(R.id.totalcost);
        favorite=(Button)findViewById(R.id.filter);
        list1=(ListView)findViewById(R.id.list1);

        alist = new ArrayList<TaskInfo>();
        Log.i("Before 999",""+alist);
        ts = new TaskInfo();

        final MyDataBase mdb = new MyDataBase(getApplicationContext(), DATABASE_NAME, null, DATABASEVERSION);

        alist = mdb.retrieveData();
        mad = new MyArrayAdapter();
        Log.i("After 999",""+alist);

        if (alist.size()!=0)
        {
            Log.i("Inside if: Managelist",""+alist.size());
            list1.setAdapter(mad);

        }
        else
        {

            Toast.makeText(getApplicationContext(),"No items have been added to the shopping list" ,Toast.LENGTH_LONG).show();
        }

        totalcost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdb.itemSum();

                Toast.makeText(getApplicationContext(),"Total sum of Products is"+ mdb.itemSum(),Toast.LENGTH_LONG).show();
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alist = mdb.filterFavorite();

                if (alist.size()!=0)
                {
                    Log.i("Inside if: Managelist",""+alist.size());
                    list1.setAdapter(mad);

                }
                else
                {

                    Toast.makeText(getApplicationContext(),"No items have been added to the shopping list" ,Toast.LENGTH_LONG).show();
                }
            }
        });

      /*  list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

                String row_id = alist.get(position).getId();
                mdb.findStatusFavorite(row_id);

                AlertDialog.Builder dialog = new AlertDialog.Builder(ManageList.this);
                dialog.setTitle("Favorite Alert");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String row_id = alist.get(position).getId();
                        mdb.delete(row_id);
                        alist.remove(position);
                        mad.updateData();
                        mdb.retrieveData();
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.create();
                dialog.show();


            }
        });*/



        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(ManageList.this);
                dialog.setTitle("Delete Alert");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String row_id = alist.get(position).getId();
                        mdb.delete(row_id);
                        alist.remove(position);
                        mad.updateData();
                        mdb.retrieveData();
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.create();
                dialog.show();

                return false;
            }
        });




        }

    class MyArrayAdapter extends BaseAdapter {
        TextView name, price, quantity;

        public int getCount() {

            return alist.size();
        }

        public void updateData() {

            notifyDataSetChanged();

        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View v, ViewGroup arg2) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View v1 = inflater.inflate(R.layout.custom, null);
            name = (TextView) v1.findViewById(R.id.itemName1);
            price = (TextView) v1.findViewById(R.id.itemPrice1);
            quantity = (TextView) v1.findViewById(R.id.itemSize1);

            String s11 = alist.get(position).getName();
            String s12 = alist.get(position).getPrice();
            String s13 = alist.get(position).getQuantity();
            String s14= alist.get(position).getFavorite();

            int quantity1 = Integer.parseInt(s13);
            int price1 = Integer.parseInt(s12);
            int updated_price1 = price1 * quantity1;

            name.setText("Product name: " + s11);
            Log.i("Product name:",s11);
            price.setText("Price:" + updated_price1 );
            quantity.setText("Quantity:"+s13);

            return v1;
        }
    }
}
