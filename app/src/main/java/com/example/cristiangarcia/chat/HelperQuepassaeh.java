package com.example.cristiangarcia.chat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HelperQuepassaeh extends SQLiteOpenHelper {
    public static final String TABLE_MISSATGE = "missatge";
    public static final String COLUMN_CODI = "codi";
    public static final String COLUMN_MSG = "msg";
    public static final String COLUMN_DATAHORA = "datahora";
    public static final String COLUMN_FKCODIUSUARI = "codiusuari";
    public static final String COLUMN_PENDENT = "pendent";
    public static final String COLUMN_NOM = "nom";
    private static final String DATABASE_NAME = "quepassaeh.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_CREATE_MISSATGE = "create table "
            + TABLE_MISSATGE + "(" + COLUMN_CODI
            + " integer primary key, "
            + COLUMN_MSG + " text not null,"
            + COLUMN_DATAHORA + " text not null,"
            + COLUMN_FKCODIUSUARI + " integer not null,"
            + COLUMN_PENDENT + " integer default 0 not null,"
            + COLUMN_NOM + " text not null)";

    public HelperQuepassaeh(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i("QuePassaEh", DATABASE_CREATE_MISSATGE);
        database.execSQL(DATABASE_CREATE_MISSATGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(HelperQuepassaeh.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MISSATGE);
        onCreate(db);
    }
}
