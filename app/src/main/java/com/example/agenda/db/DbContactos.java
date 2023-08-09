package com.example.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbContactos extends DbHelper{
    Context context; /*Declaramos un contexto que vamos a usar*/

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertaContacto(String nombre, String telefono, String correo_electronico){
        long id =0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase(); /* para escribir el bd*/
            ContentValues values = new ContentValues(); /*contenedor de valores*/
            values.put("nombre", nombre);/*guardado por pareja clave-valor*/
            values.put("telefono", telefono);/*guardado por pareja clave-valor*/
            values.put("correo_electronico", correo_electronico);
            id = db.insert(TABLE_CONTACTO, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return  id;
    }
}
