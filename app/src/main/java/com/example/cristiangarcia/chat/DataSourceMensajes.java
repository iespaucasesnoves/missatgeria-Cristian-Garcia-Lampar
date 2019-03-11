package com.example.cristiangarcia.chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataSourceMensajes {
    private SQLiteDatabase database;
    private HelperQuepassaeh dbAjuda; //CLASSE AJUDA

    private String[] allColumnsMensajes = {HelperQuepassaeh.COLUMN_CODI, HelperQuepassaeh.COLUMN_MSG, HelperQuepassaeh.COLUMN_DATAHORA,
            HelperQuepassaeh.COLUMN_FKCODIUSUARI , HelperQuepassaeh.COLUMN_PENDENT};



    public DataSourceMensajes(Context context) { //CONSTRUCTOR
        dbAjuda = new HelperQuepassaeh(context);
    }
    public void open() throws SQLException {
        database = dbAjuda.getWritableDatabase();
    }

    public void close() {
        dbAjuda.close();
    }

    public Mensaje createMensaje(Mensaje Mensaje) {
        // insert d'un nou Mensaje
        ContentValues values = new ContentValues();
        values.put(HelperQuepassaeh.COLUMN_CODI, Mensaje.getCodigo());
        values.put(HelperQuepassaeh.COLUMN_MSG, Mensaje.getMensaje());
        values.put(HelperQuepassaeh.COLUMN_DATAHORA, Mensaje.getFechaHora());
        values.put(HelperQuepassaeh.COLUMN_FKCODIUSUARI, Mensaje.getFKCodiUsuario());
        //values.put(HelperQuepassaeh.COLUMN_PENDENT, Mensaje.getPendiente());

        long insertId = database.insert(HelperQuepassaeh.TABLE_MISSATGE, null, values);
        Mensaje.setCodigo(insertId);
        return Mensaje;
    }
    public List<Mensaje> getAllMensaje() {
        List<Mensaje> mensajes = new ArrayList();
        Cursor cursor = database.query(HelperQuepassaeh.TABLE_MISSATGE, allColumnsMensajes,
                null, null, null, null,
                HelperQuepassaeh.COLUMN_DATAHORA);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mensaje Mensaje = cursorToMensaje(cursor);
            mensajes.add(Mensaje);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return mensajes;
    }
    private Mensaje cursorToMensaje(Cursor cursor) {
        Mensaje v = new Mensaje();
        v.setCodigo(cursor.getLong(0));
        v.setMensaje(cursor.getString(1));
        v.setFechaHora(cursor.getString(2));
        v.setFKCodiUsuario(cursor.getString(3));
        v.setPendiente(cursor.getString(4));
        return v;
    }
}
