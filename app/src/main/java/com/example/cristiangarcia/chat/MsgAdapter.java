package com.example.cristiangarcia.chat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

class MsgAdapter extends ArrayAdapter<Mensaje> {
    private Context context;
    private List<Mensaje> mensajes;

    //constructor
    public MsgAdapter(Context context, int resource, List<Mensaje>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.mensajes = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //agafam l'esdeveniment per posició
        Mensaje m = mensajes.get(position);
        //agafam "l'inflater" per "inflar" el layout per a cada item
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mensaje, null);
        //instanciam cada element del layout a utilitzar
        TextView nombre = view.findViewById(R.id.nombreUsuario);
        TextView mensaje = view.findViewById(R.id.mensaje);
        TextView fecha = view.findViewById(R.id.fecha);
        //omplim les dades
        nombre.setText(m.getNombre());
        mensaje.setText(m.getMensaje());
        fecha.setText(m.getFechaHora());

        return view;
    }
}
