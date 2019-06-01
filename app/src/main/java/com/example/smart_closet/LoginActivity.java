package com.example.smart_closet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText email, password;
    Button subtn, sibtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        subtn = (Button) findViewById(R.id.email_sign_up_button);
        sibtn = (Button) findViewById(R.id.email_sign_in_button);

        sibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString();
                String sepass = password.getText().toString();
                Boolean chkemailpass = db.emailpassword(semail, sepass);
                if(chkemailpass == true) {
                    ArrayList<String> userData = db.getUserData(semail);
                    Toast.makeText(getApplicationContext(), semail + "님 반갑습니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("email", semail);
                    intent.putExtra("userData", userData);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        subtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SginUpActivity.class);
                startActivity(intent);
            }
        });

    }
}


