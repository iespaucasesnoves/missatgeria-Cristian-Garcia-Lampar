package com.example.cristiangarcia.chat;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chat extends AppCompatActivity {

    ListView lv;
    EditText escribir;
    Button enviar;
    ListAdapter adapter;
    Mensajes m;
    Enviar e;
    HashMap<String, String> hashMap;

    @Override
    protected void onStart(){
        super.onStart();
        handler.removeCallbacks(getResponceAfterInterval);
        handler.post(getResponceAfterInterval);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        lv = findViewById(R.id.lista);
        escribir = findViewById(R.id.texto_enviar);
        enviar = findViewById(R.id.btn_enviar);
        m = new Mensajes(this);
        m.execute();

        mostraMensajes();

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Chat.this, "" + escribir.getText(), Toast.LENGTH_SHORT).show();
                hashMap = new HashMap<>();
                hashMap.put("msg", escribir.getText().toString());
                hashMap.put("codiusuari", "19");
                e = new Enviar(hashMap, getBaseContext());
                e.execute();

                mostraMensajes();
            }
        });


    }
    public void mostraMensajes() {
        // Obrim la base de dades
        DataSourceMensajes bd;
        bd = new DataSourceMensajes(this);
        bd.open();
        // Obtenim tots els vins
        List<Mensaje> listaMensajes = bd.getAllMensaje();
        ArrayList<HashMap<String, String>> llista = new ArrayList<>();
        for (int i = 0; i < listaMensajes.size(); i++) {
            HashMap<String, String> map = new HashMap<>();
            Mensaje mensaje = listaMensajes.get(i);
            //map.put("codigo", String.valueOf(mensaje.getCodigo()));
            map.put("mensaje", mensaje.getMensaje());
            map.put("fechahora", mensaje.getFechaHora());
            map.put("nom", mensaje.getNombre());
            //map.put("codigousuario", mensaje.getFKCodiUsuario());
            //map.put("pendiente", mensaje.getPendiente());
            llista.add(map);
        }
        //Tanquem la BD
        bd.close();
        //Assignar a la listview
        MsgAdapter adapter = new MsgAdapter(this, R.layout.activity_main, listaMensajes);
        lv.setAdapter(adapter);
        adapter.addAll(listaMensajes);
        adapter.notifyDataSetChanged();
    }

    private final Handler handler = new Handler();
    private Runnable getResponceAfterInterval = new Runnable() {
        public void run() {
            try
            {
                mostraMensajes(); //Descarrega els missatges els guarda dins la
//base de dades i els mostra a la llista.
            } catch (Exception e) {
            }
            handler.postDelayed(this, 1000*60);
        }
    };

}
