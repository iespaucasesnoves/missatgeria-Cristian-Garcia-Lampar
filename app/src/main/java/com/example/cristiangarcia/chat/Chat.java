package com.example.cristiangarcia.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Chat extends AppCompatActivity {

    String url = "https://iesmantpc.000webhostapp.com/public/provamissatge/";
    //{"codi":"105","msg":"hola\n","datahora":"2019-02-19 16:51:56","codiusuari":"19","nom":"GARCIA"}

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        lv = findViewById(R.id.lista);
        

    }
}
