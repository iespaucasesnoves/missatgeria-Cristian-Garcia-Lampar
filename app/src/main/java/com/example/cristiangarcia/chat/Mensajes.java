package com.example.cristiangarcia.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;


public class Mensajes extends AsyncTask<String, HashMap<String, String>, String> {
    Context context;
    String adrecaURL = "http://iesmantpc.000webhostapp.com/public/provamissatge/19";
    HashMap<String, String> parametres;
    SharedPreferences.Editor editor;
    DataSourceMensajes dsm = new DataSourceMensajes(context);

    protected Mensajes(Context context){
        this.context = context;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        String resultat = "";
        try {
            URL url = new URL(adrecaURL);
            Log.i("ResConnectUtils", "Connectant: " + adrecaURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            int resposta = httpConn.getResponseCode();
            if (resposta == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(httpConn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    resultat += line;
                    JSONObject item = new JSONObject(resultat);
                    JSONArray array = item.getJSONArray("dades");
                    DataSourceMensajes bd = new DataSourceMensajes(context);
                    bd.open();
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        String codigo = object.getString("codi");
                        String mensaje = object.getString("msg");
                        String fecha = object.getString("datahora");
                        String nomUsuari = object.getString("nom");
                        String codiUsuari = object.getString("codiusuari");
                        Mensaje m = new Mensaje(Long.parseLong(codigo), mensaje, fecha, codiUsuari, nomUsuari);
                        bd.createMensaje(m);

                    }
                    bd.close();
                }
                Log.i("Resultado", resultat);
            } else {
                resultat = "";
                Log.i("ResConnectUtils", "Errors:" + resposta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultat;
    }
}
