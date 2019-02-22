package com.example.cristiangarcia.chat;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class Login extends AsyncTask<String, HashMap<String, String>, String> {
    String adrecaURL;
    HashMap<String, String> parametres;

    protected Login(String adrecaURL, HashMap<String, String> parametres){
        this.adrecaURL = adrecaURL;
        this.parametres = parametres;
    }


    @Override
    protected String doInBackground(String... strings) {
        String resultat = "";
        try {
            URL url = new URL(adrecaURL);
            Log.i("ResConnectUtils", "Connectant: " + adrecaURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setReadTimeout(15000);
            httpConn.setConnectTimeout(25000);
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            OutputStream os = httpConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(montaParametres(parametres));
            writer.flush();
            writer.close();
            os.close();
            int resposta = httpConn.getResponseCode();
            if (resposta == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(httpConn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    resultat += line;
                }
                Log.i("ResConnectUtils", resultat);
            } else {
                resultat = "";
                Log.i("ResConnectUtils", "Errors:" + resposta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultat;
    }
    private static String montaParametres(HashMap<String, String> params) throws
            UnsupportedEncodingException {
        // A partir d'un hashmap clau-valor cream
        // clau1=valor1&clau2=valor2&...
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
