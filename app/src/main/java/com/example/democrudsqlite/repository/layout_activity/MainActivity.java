package com.example.democrudsqlite.repository.layout_activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import com.example.democrudsqlite.R;


public class MainActivity extends AppCompatActivity {

    //Declare variable
    private Button btnCustomer, btnAuthor, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCustomer = findViewById(R.id.buttonSubjects);
        btnExit = findViewById(R.id.buttonExit);
        btnAuthor = findViewById(R.id.ButtonAuthor);

        // config library Picasso bitmap connect Internet
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Set event when user click button choose
        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAuthor();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogExit();
            }
        });
        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    //Dialog Author
    private void DialogAuthor() {
        //Tạo đối tượng cửa sổ dialog
        Dialog dialog = new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialog_login_author);
        dialog.show();
    }

    //Dialog Exit
    private void DialogExit() {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog = new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialog_exit);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khởi tạo lại activity main
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                // Tạo sự kiện kết thúc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
            }
        });
        //Nếu no thì đóng dialog
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //show dialog lên activity
        dialog.show();
    }

}