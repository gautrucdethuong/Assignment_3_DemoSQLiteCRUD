package com.example.democrudsqlite.repository;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.democrudsqlite.R;
import com.example.democrudsqlite.model.Customer;
import com.example.democrudsqlite.repository.database.DatabaseHandler;
import com.example.democrudsqlite.repository.layout_activity.CustomerActivity;


/**
 * Author by HUYNH NHAT MINH (ミン).
 * Email: minhhnce140197@fpt.edu.vn.
 * Date on 6/1/2021.
 * Company: FPT大学.
 */

public class AddCustomerActivity extends AppCompatActivity{

    //Declare variable
    private Button btnSave;
    private EditText txtFullName;
    private EditText txtUrl;
    private EditText txtCodeNumber;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        // Call id in Layout
        btnSave = (Button) findViewById(R.id.btnSave);
        txtFullName = (EditText) findViewById(R.id.editText_note_title);
        txtCodeNumber = (EditText) findViewById(R.id.editText_note_content);
        txtUrl = (EditText) findViewById(R.id.editText_note_image);

        // object initialization
        databaseHandler = new DatabaseHandler(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName = txtFullName.getText().toString();
                final String codeNumber = txtCodeNumber.getText().toString();
                final String url = txtUrl.getText().toString();
                    // Add data to the database
                    databaseHandler.AddCustomer(new Customer(fullName, codeNumber, url));

                    Intent intent = new Intent(AddCustomerActivity.this, CustomerActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddCustomerActivity.this, "Add successfully !!!", Toast.LENGTH_LONG).show();
                }
        });

    }
}
