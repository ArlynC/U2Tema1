package com.example.u2tema1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Datos extends AppCompatActivity {
    TextView txtcod,txtmensaje,txtape,txtsexo,txtcelular,txtdomicilio;
    String cod,titulo,ape,sexo,celular,domicilio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datoscliente);

        Bundle extras = getIntent().getExtras();
        cod=extras.getString("codigo");
        titulo = extras.getString("nombre");
        ape = extras.getString("ape");
        sexo=extras.getString("sexo");
        celular=extras.getString("celular");
        domicilio=extras.getString("Domicilio");

        txtcod=findViewById(R.id.codigo);
        txtmensaje=findViewById(R.id.titulo);
        txtape=findViewById(R.id.ape);
        txtsexo=findViewById(R.id.sexo);
        txtcelular=findViewById(R.id.celular);
        txtdomicilio=findViewById(R.id.domicilio);

        txtcod.setText(cod);
        txtmensaje.setText(titulo);
        txtape.setText(ape);
        txtsexo.setText(sexo);
        txtcelular.setText(celular);
        txtdomicilio.setText(domicilio);
    }


}
