package com.example.u2tema1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity  extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MiNuevoAdaptador adaptador;
    private ArrayList<Cliente> misdatos;
    public Vector<String> valor;
    private String res;
    HttpURLConnection conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        misdatos = new ArrayList<>();
       // misdatos.add(new Cliente("1", "Juanito", "Perez"));
       // misdatos.add(new Cliente("2", "Pablito", "Canto"));
        //adaptador = new MiNuevoAdaptador(this, misdatos);
        adaptador = new MiNuevoAdaptador(this,ListaClientes(conseguirstring()));
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        new Peticion().execute();
    }
    //Leyendo JSON
    private ArrayList<Cliente> ListaClientes(String string) {
        ArrayList<Cliente> Clientes = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                Clientes.add(new Cliente(objeto.getString("Cod_persona"), objeto.getString("Nombre"),objeto.getString("Apellidos"),
                        objeto.getString("Sexo"),objeto.getString("celular"),objeto.getString("Domicilio")));
            }
        } catch (JSONException e) {
            Log.i("mierror",e.toString());
            e.printStackTrace();
        }
        return Clientes;
    }
    public String conseguirstring() {
        try {
            String miurl= this.getString(R.string.dominio)+this.getString(R.string.vercliente);
            URL url=new URL(miurl);
            conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                res=linea;
            } else {
                Log.e("mierror", conexion.getResponseMessage());
            }
        } catch (Exception e) {
            return res="";
        } finally {
            if (conexion!=null) conexion.disconnect();
        }
        return res;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insertar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_insertar:
                startActivity(new Intent(this, InsertarCliente.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        adaptador.update(ListaClientes(conseguirstring()));
    }


    public static class Peticion extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
//Url del servicio,sin el endpoint
            final String url = "https://fugacious-bits.000webhostapp.com/";
//Creamos el objeto Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)//Indicamos la url del servicio
                    .addConverterFactory(GsonConverterFactory.create())//Agregue la fábrica del convertidor para la serialización
// y la deserialización de objetos.
                    .build();//Cree la instancia de Retrofit utilizando los valores configurados.
//https://square.github.io/retrofit/2.x/retrofit/retrofit2/Retrofit.Builder.html
            servicesRetrofit service = retrofit.create(servicesRetrofit.class);
//Recuerda que debemos colocar el modo en como obtenemos esa respuesta,en este caso es una lista de objetos
//pero puede ser simplemente un objeto.
            Call<List<Cliente>> response = service.getUsersGet();//indicamos el metodo que deseamos ejecutar
            try {
//Realizamos la peticion sincrona
//Recuerda que en el body se encuentra la respuesta,que en este caso es una lista de objetos
                for (Cliente user : response.execute().body())//realizamos un foreach para recorrer la lista
                    Log.e("Respuesta: ",user.getNombre()+ " "+user.getApellido()+" "+user.getSexo()+" "+user.getCelular());//mostamos en pantalla algunos de los datos del usuario
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
