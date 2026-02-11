package com.example.inventario;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    private TextView tv;
    private CheckBox tec;
    private CheckBox mou;
    private CheckBox pan;
    private CheckBox mic;
    private EditText nt;
    private EditText nm;
    private EditText np;
    private EditText nmic;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv=(TextView) findViewById(R.id.textView);
        tec=(CheckBox) findViewById(R.id.ch_tec);
        mou=(CheckBox) findViewById(R.id.ch_mou);
        pan=(CheckBox) findViewById(R.id.ch_pan);
        mic=(CheckBox) findViewById(R.id.ch_mic);
        nt=(EditText) findViewById(R.id.n_tec);
        nm=(EditText) findViewById(R.id.n_mou);
        np=(EditText) findViewById(R.id.n_pan);
        nmic=(EditText) findViewById(R.id.n_mic);
        nt.setKeyListener(DigitsKeyListener.getInstance("-0123456789"));
        nm.setKeyListener(DigitsKeyListener.getInstance("-0123456789"));
        np.setKeyListener(DigitsKeyListener.getInstance("-0123456789"));
        nmic.setKeyListener(DigitsKeyListener.getInstance("-0123456789"));

    }

    public void almacenar(View view)
    {
        if(tec.isChecked()==true)
        {
            String nombre="Teclado";
            String valor=nt.getText().toString();
            int cani=Integer.parseInt(valor);
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            String canto=preferencias.getString(nombre,"0");
            int cant=Integer.parseInt(canto);
            cant=cant+cani;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se registro el producto",Toast.LENGTH_SHORT).show();

        }

        if(mou.isChecked()==true)
        {
            String nombre="Mouse";
            String valor=nm.getText().toString();
            int cani=Integer.parseInt(valor);
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            String canto=preferencias.getString(nombre,"0");
            int cant=Integer.parseInt(canto);
            cant=cant+cani;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se registro el producto",Toast.LENGTH_SHORT).show();

        }

        if(pan.isChecked()==true)
        {
            String nombre="Pantalla";
            String valor=np.getText().toString();
            int cani=Integer.parseInt(valor);
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            String canto=preferencias.getString(nombre,"0");
            int cant=Integer.parseInt(canto);
            cant=cant+cani;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se registro el producto",Toast.LENGTH_SHORT).show();
        }

        if(mic.isChecked()==true)
        {
            String nombre="Microfono";
            String valor=nmic.getText().toString();
            int cani=Integer.parseInt(valor);
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            String canto=preferencias.getString(nombre,"0");
            int cant=Integer.parseInt(canto);
            cant=cant+cani;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se registro el producto",Toast.LENGTH_SHORT).show();
        }

    }



    public void mostrar(View view)
    {
        String resultado="";
        if(tec.isChecked()==true)
        {
            String nombre="Teclado";
            SharedPreferences preferencias= getSharedPreferences("inventario",Context.MODE_PRIVATE);
            String datos=preferencias.getString(nombre,"");
            resultado="Hay "+datos+" unidades del articulo ("+nombre+")\n";
            if(datos.length()==0)
            {
                Toast.makeText(this,"No existe en el inventario",Toast.LENGTH_SHORT).show();
            }

        }

        if(mou.isChecked()==true)
        {
            String nombre="Mouse";
            SharedPreferences preferencias= getSharedPreferences("inventario",Context.MODE_PRIVATE);
            String datos=preferencias.getString(nombre,"");
            resultado= resultado+"Hay "+datos+" unidades del articulo ("+nombre+")\n";
            if(datos.length()==0)
            {
                Toast.makeText(this,"No existe en el inventario",Toast.LENGTH_SHORT).show();
            }

        }

        if(pan.isChecked()==true)
        {
            String nombre="Pantalla";
            SharedPreferences preferencias= getSharedPreferences("inventario",Context.MODE_PRIVATE);
            String datos=preferencias.getString(nombre,"");
            resultado=resultado+"Hay "+datos+" unidades del articulo ("+nombre+")\n";
            if(datos.length()==0)
            {
                Toast.makeText(this,"No existe en el inventario",Toast.LENGTH_SHORT).show();
            }

        }

        if(mic.isChecked()==true)
        {
            String nombre="Microfono";
            SharedPreferences preferencias= getSharedPreferences("inventario",Context.MODE_PRIVATE);
            String datos=preferencias.getString(nombre,"");
            resultado=resultado+"Hay "+datos+" unidades del articulo ("+nombre+")\n";
            if(datos.length()==0)
            {
                Toast.makeText(this,"No existe en el inventario",Toast.LENGTH_SHORT).show();
            }
        }
        tv.setText(resultado);

    }

    public void reset(View view)
    {
        if(tec.isChecked()==true)
        {
            String nombre="Teclado";
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            int cant= 0;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se reinicio el inventario",Toast.LENGTH_SHORT).show();

        }

        if(mou.isChecked()==true)
        {
            String nombre="Mouse";
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            int cant= 0;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se reinicio el inventario",Toast.LENGTH_SHORT).show();

        }

        if(pan.isChecked()==true)
        {
            String nombre="Pantalla";
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            int cant= 0;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se reinicio el inventario",Toast.LENGTH_SHORT).show();
        }

        if(mic.isChecked()==true)
        {
            String nombre="Microfono";
            SharedPreferences preferencias= getSharedPreferences("inventario", Context.MODE_PRIVATE);
            int cant= 0;
            String cantidad=String.valueOf(cant);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString(nombre,cantidad);
            editor.commit();
            Toast.makeText(this,"Se reinicio el inventario",Toast.LENGTH_SHORT).show();
        }

    }








}