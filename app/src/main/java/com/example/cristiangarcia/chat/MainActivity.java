package com.example.cristiangarcia.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText nombre, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.login_btn);
        nombre = findViewById(R.id.userInput);
        password = findViewById(R.id.passInput);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("nom", nombre.getText().toString());
                hashMap.put("password", password.getText().toString());
                Login l = new Login(hashMap, getBaseContext());
                l.execute();
            }
        });
    }


}
