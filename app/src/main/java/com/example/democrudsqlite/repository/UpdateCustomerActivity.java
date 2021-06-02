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

public class UpdateCustomerActivity extends AppCompatActivity {
    //Declare variable
    private Button btnUpdate;
    private EditText editFullName;
    private EditText editCode;
    private EditText editUrl;
    private DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);
        // Call id in Layout
        btnUpdate = findViewById(R.id.btnSave);
        editFullName = findViewById(R.id.editText_note_title);
        editCode = findViewById(R.id.editText_note_content);
        editUrl = findViewById(R.id.editText_note_image);

        // Get data from intent
        if (intent != null) {
            editFullName.setText(intent.getStringExtra("full_name"));
            editCode.setText(intent.getStringExtra("code"));
            editUrl.setText(intent.getStringExtra("link_image"));
        }

        databaseHandler = new DatabaseHandler(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String full_name = editFullName.getText().toString();
                String code = editCode.getText().toString();
                String url = editUrl.getText().toString();

                // Update database
                databaseHandler.UpdateCustomer(new Customer(full_name, code, url), id);

                Intent intent = new Intent(UpdateCustomerActivity.this, CustomerActivity.class);

                startActivity(intent);
                Toast.makeText(UpdateCustomerActivity.this, "Update successfully !!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
