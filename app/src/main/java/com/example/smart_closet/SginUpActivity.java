package com.example.smart_closet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SginUpActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText email, edit_password, edit_con_password, edit_name;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sgin_up);

        db = new DatabaseHelper(this);

        email = (EditText) findViewById(R.id.email);
        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_con_password = (EditText) findViewById(R.id.edit_con_password);
        edit_name = (EditText) findViewById(R.id.edit_name);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString();
                String sepass = edit_password.getText().toString();
                String secpass = edit_con_password.getText().toString();
                String sename = edit_name.getText().toString();

                if(semail.equals("") || sepass.equals("") || secpass.equals("") || sename.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (sepass.equals(secpass)) {
                        Boolean chkemail = db.chkemail(semail);
                        if (chkemail == true) {
                            Boolean insert = db.insert(semail, sepass, sename);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}

