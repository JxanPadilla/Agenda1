package com.example.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.agenda.entidades.Contactos;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public ArrayList<Contactos> mostrarContactos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); /*Para escribir en la bd*/

        ArrayList<Contactos> listContactos = new ArrayList<>();/*Creamos un array de tipo Contactos*/
        Contactos contacto = null; /*Creamos una variable de tipo contacto y la inicializamos*/
        Cursor cursorContactos = null;
        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTO, null);

        if (cursorContactos.moveToFirst()){/*nos vemos al primer registro que trae la consulta*/
            do{
                contacto = new Contactos(); /*Instanciamos el objeto contacto desde la clase contactos para poder trabajar los metodos set y get*/
                contacto.setId(cursorContactos.getInt(0)); /*Aqui pasamos la informacion de cada registro y cada campo, se trabaja a apartir de 0*/
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
                /*una vez enviados los parametros que necesitamos, trabajamos con listaContactos*/
                listContactos.add(contacto); /*le pasamos la informacion que trae contactos el cual viene de las asignaciones anteriores*/
            } while (cursorContactos.moveToNext());
        }
        cursorContactos.close(); /*cierre del cursor*/
        return listContactos; /*retomamos el array listaContactos que armamos*/
    }
}
