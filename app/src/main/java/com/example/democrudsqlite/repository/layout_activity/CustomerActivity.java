package com.example.democrudsqlite.repository.layout_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.democrudsqlite.R;
import com.example.democrudsqlite.model.Customer;
import com.example.democrudsqlite.repository.AddCustomerActivity;
import com.example.democrudsqlite.repository.UpdateCustomerActivity;
import com.example.democrudsqlite.repository.database.DatabaseHandler;
import com.example.democrudsqlite.service.AdapterCustomer;

import java.util.ArrayList;

/**
 * Author by HUYNH NHAT MINH (ミン).
 * Email: minhhnce140197@fpt.edu.vn.
 * Date on 6/1/2021.
 * Company: FPT大学.
 */

public class CustomerActivity extends AppCompatActivity {
    //Declare variable
    private Toolbar toolbarCustomer;
    private ListView listViewCustomer;
    private ArrayList<Customer> customerArrayList;
    private DatabaseHandler database;
    private AdapterCustomer adapterCustomer;
    private int counter = 0;
    private ImageView imageUrlView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listview);

        toolbarCustomer = findViewById(R.id.toolbarCustomer);
        listViewCustomer = findViewById(R.id.listviewCustomer);
        imageUrlView = findViewById(R.id.imageViewUrl);


        setSupportActionBar(toolbarCustomer);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // object initialization
        database = new DatabaseHandler(this);
        customerArrayList = new ArrayList<>();

        database.createDefaultNotesIfNeed();

        // Initialization pointer, pointing to the result of the query
        Cursor cursor = database.getAllListCustomer();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String full_name = cursor.getString(1);
            String code_number = cursor.getString(2);
            String link_image = cursor.getString(3);

            customerArrayList.add(new Customer(id, full_name, code_number, link_image));
        }
        adapterCustomer = new AdapterCustomer(CustomerActivity.this, customerArrayList);
        listViewCustomer.setAdapter(adapterCustomer);
        // close connect
        cursor.moveToFirst();
        cursor.close();

    }

    //Load an add menu into the actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd, menu);
        return true;
    }


    //Catch event when click Add
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                Intent intent = new Intent(CustomerActivity.this, AddCustomerActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    // Method to delete
    public void delete(final int position) {
        // Show dialog confirm request
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Delete data in SQL
                        database.DeleteCustomer(position);

                        Intent intent = new Intent(CustomerActivity.this, CustomerActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    // Method to update
    public void update(final int position) {
        // Initialization pointer, pointing to the result of the query
        Cursor cursor = database.getAllListCustomer();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == position) {
                Intent intent = new Intent(CustomerActivity.this, UpdateCustomerActivity.class);
                intent.putExtra("id", position);

                String full_name = cursor.getString(1);
                String code = cursor.getString(2);
                String link_image = cursor.getString(3);
                // get value data from intent
                intent.putExtra("full_name", full_name);
                intent.putExtra("code", code);
                intent.putExtra("link_image", link_image);

                startActivity(intent);
            }
        }

    }

}
