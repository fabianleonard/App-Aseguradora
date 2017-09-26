package com.example.fabianleonard.prueba1;

import android.app.Activity;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Fabian Leonard on 19-09-2017.
 */

public class Activity2 extends AppCompatActivity {

    TextView año_1, patente, marca, modelo, valor_uf, antiguedad, estado, valor_seguro;

    String patente1, marca1, modelo1;

    int año_2, uf;

    ImageView asegurable;
    ImageView imagenMarca;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        patente = (TextView) findViewById(R.id.ingreso_patente);
        marca = (TextView) findViewById(R.id.ingreso_marca);
        modelo = (TextView) findViewById(R.id.ingreso_modelo);
        año_1 = (TextView) findViewById(R.id.ingreso_año);
        valor_uf = (TextView) findViewById(R.id.ingreso_uf);

        antiguedad = (TextView) findViewById(R.id.ingreso_antiguedad);
        estado = (TextView) findViewById(R.id.resultado_estado);
        valor_seguro = (TextView) findViewById(R.id.ingreso_ValorSeguro);

        asegurable=(ImageView) findViewById(R.id.img_seguro);
        imagenMarca=(ImageView) findViewById(R.id.img_marca);

        Bundle bundle = this.getIntent().getExtras();
        patente1 = bundle.getString("patente");
        marca1 = bundle.getString("marca");
        modelo1 = bundle.getString("modelo");
        año_2 = bundle.getInt("año_1");
        uf = bundle.getInt("valoruf");

        patente.setText(patente1);
        marca.setText(marca1);
        modelo.setText(modelo1);
        año_1.setText(String.valueOf(año_2));
        valor_uf.setText(String.valueOf(uf));

        String AntiguedadDelVehiculo= Metodo_antiguedad(año_2);
        antiguedad.setText(AntiguedadDelVehiculo);

        String Valido=Metodo_Valido(año_2);
        estado.setText(Valido);

        String Valor= Valor_Seguro(uf,año_2);
        valor_seguro.setText(Valor);

        Marca(marca1);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Metodo_antiguedad(int año_ingreso) {
        String antiguo;
        Calendar actual = new GregorianCalendar();
        int año_actual=actual.get(Calendar.YEAR);

        int antiguedad = año_actual - año_ingreso;

        if (año_ingreso>año_actual){
            antiguo= "Año ingresado es invalido.";
        }
        else if (año_ingreso == 2017) {
            antiguo = "El vehículo es del año";
        }else{
            antiguo = "El vehículo tiene " + antiguedad + " años";
        }
        return antiguo;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Metodo_Valido(int ingreso){
        String valido;
        Calendar actual = new GregorianCalendar();
        int año_actual=actual.get(Calendar.YEAR);
        int antiguedad = año_actual - ingreso;

        if (antiguedad > 10 || ingreso>año_actual){
            valido="El vehículo NO es Asegurable";
            asegurable.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.no));

        }else{
            valido="El vehículo es Asegurable";
            asegurable.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.si));
        }
        return valido;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Valor_Seguro(int valor_uf, int año){

        Calendar actual = new GregorianCalendar();
        int año_actual=actual.get(Calendar.YEAR);
        String valor;

        int cero= año_actual - año;
        int valor_total= (int) ((valor_uf *(0.1 * cero)));

        if(cero > 10 || año>año_actual){
            valor = "Valor 0 pesos";
        }else if(año==año_actual){
            valor_total= (int) (valor_uf*0.1);
            valor= "El valor del seguro es "+valor_total+" pesos.";
        }
        else {
            valor= "El valor del seguro es "+valor_total+" pesos.";
        }
        return valor;
    }
    public String Marca(String marca1){

        if(marca1.equals("KIA")){
            imagenMarca.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ccc));
        }
        if(marca1.equals("MAZDA")){
            imagenMarca.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.mazda));
        }
        if(marca1.equals("PEUGEOT")){
            imagenMarca.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.peugeot));
        }
        if(marca1.equals("PORSCHE")){
            imagenMarca.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.porsche));
        }
        if(marca1.equals("TOYOTA")){
            imagenMarca.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.toyota));
        }
        return marca1;
    }
}