package com.example.u2tema1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Datos extends AppCompatActivity {
    TextView txtmensaje,txtsexo,txtcelular,txtdomicilio;
    String titulo,sexo,celular,domicilio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datoscliente);

        Bundle extras = getIntent().getExtras();
        titulo = extras.getString("Nombre");
        sexo=extras.getString("sexo");
        celular=extras.getString("celular");
        domicilio=extras.getString("Domicilio");

        txtmensaje=findViewById(R.id.titulo);
        txtsexo=findViewById(R.id.sexo);
        txtcelular=findViewById(R.id.celular);
        txtdomicilio=findViewById(R.id.domicilio);

        txtmensaje.setText(titulo);
        txtsexo.setText(sexo);
        txtcelular.setText(celular);
        txtdomicilio.setText(domicilio);
    }


}
