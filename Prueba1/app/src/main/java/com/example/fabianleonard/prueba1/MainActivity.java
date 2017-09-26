
package com.example.fabianleonard.prueba1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Fabian Leonard on 19-09-2017.
 */


public class MainActivity extends AppCompatActivity {

    Button enviar_datos;
    EditText año_ingreso;
    EditText patente;
    Spinner marca;
    EditText valoruf;
    EditText modelo;
    ArrayAdapter<String>aaMarca;
    String [] opcMarca=new String[]{"KIA","MAZDA","PEUGEOT","PORSCHE","TOYOTA"};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        año_ingreso = (EditText) findViewById(R.id.txt_año);
        patente = (EditText) findViewById(R.id.txt_patente);
        marca = (Spinner) findViewById(R.id.txt_marca);
        modelo = (EditText) findViewById(R.id.txt_modelo);
        valoruf = (EditText) findViewById(R.id.txt_uf);
        enviar_datos = (Button) findViewById(R.id.btn_enviar);
        final TextView resultado = (TextView) findViewById(R.id.resultado);

        aaMarca= new ArrayAdapter<>(this,android.R.layout.simple_list_item_activated_1,opcMarca);
        marca.setAdapter(aaMarca);

        enviar_datos.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(patente.getText().toString().isEmpty() || modelo.getText().toString().isEmpty() || año_ingreso.getText().toString().isEmpty()
                        || valoruf.getText().toString().isEmpty()){
                    resultado.setText("Llene los campos correctamente!");

                }else {
                    EnviarDatos();
                }
            }
            });
    }
    private void EnviarDatos() {
        String patente_e, marca_e, modelo_e;
        int año, uf;
        patente_e = patente.getText().toString();
        marca_e = marca.getSelectedItem().toString();
        modelo_e = modelo.getText().toString();
        año = Integer.parseInt(año_ingreso.getText().toString());
        uf = Integer.parseInt(valoruf.getText().toString());

        Intent envio = new Intent(MainActivity.this, Activity2.class);
        envio.putExtra("patente", patente_e);
        envio.putExtra("marca", marca_e);
        envio.putExtra("modelo", modelo_e);
        envio.putExtra("año_1", año);
        envio.putExtra("valoruf", uf);
        startActivity(envio);

    }

}