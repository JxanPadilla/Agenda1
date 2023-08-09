package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.db.DbContactos;

public class activity_nuevo extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btnGuarda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreo);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(activity_nuevo.this);
                long id = dbContactos.insertaContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString());

                if(id>0){
                    Toast.makeText(activity_nuevo.this, "REGISTRO AGREGADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else{
                    Toast.makeText(activity_nuevo.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private  void  limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}