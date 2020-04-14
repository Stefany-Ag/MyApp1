package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity3 extends AppCompatActivity {
    /*String nombre = getIntent().getStringExtra(Nombre);
    String apellidoPaterno = getIntent().getStringExtra(MainActivity.apellido1);
    String apellidoMaterno = getIntent().getStringExtra(MainActivity.apellido2);
    String anio = getIntent().getStringExtra(MainActivity.Anio);
    String mes = getIntent().getStringExtra(MainActivity.Mes);
    String dia = getIntent().getStringExtra(MainActivity.Dia);*/
    TextView tvDatoRFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        /*String inicio = primerosCuatroCaracteresRFC(nombre, apellidoPaterno, apellidoMaterno);
        String datoRfc = inicio+anio+mes+dia;
        tvDatoRFC.setText(datoRfc);*/
    }

    public static String primerosCuatroCaracteresRFC(String nombre, String apellidoPaterno, String apellidoMaterno)
    {
        //Eliminar acentos y llevar a mayúsculas
        nombre = eliminarAcentosYSimbolos(nombre);
        apellidoPaterno = eliminarAcentosYSimbolos(apellidoPaterno);
        apellidoMaterno = eliminarAcentosYSimbolos(apellidoMaterno);

        //Nombre: Omitir palabras y compuestos que no se utilizan, y obtener las 2 primeras letras
        Pattern pattern = Pattern.compile("\\A(?:(?:MARIA|JOSE) )?+(?:(?:DEL?|L(?:AS?|OS)|M(?:AC|[CI])|V[AO]N|Y)\\b ?)*+([A-Z&]?)([A-Z&]?)");
        final Matcher matcherNom = pattern.matcher(nombre);
        matcherNom.find();

        //Apellido: Omitir palabras que no se utilizan, obtener la primera letra y la vocal interna (si el apellido tiene más de 2 letras)
        pattern = Pattern.compile("\\A(?:(?:DEL?|L(?:AS?|OS)|M(?:AC|[CI])|V[AO]N|Y)\\b ?)*+(([A-Z&]?)[B-DF-HJ-NP-TV-Z&]*([AEIOU]?)[A-Z&]?)");
        final Matcher matcherPat = pattern.matcher(apellidoPaterno);
        matcherPat.find();
        final Matcher matcherMat = pattern.matcher(apellidoMaterno);
        matcherMat.find();

        //LETRAS
        //Obtener vocal de apellido paterno y letra(s) del nombre
        String letraPat = matcherPat.group(2);
        String letraMat = matcherMat.group(2);
        String letraNom = matcherNom.group(1);
        String rfc;
        if (letraPat.isEmpty() || letraMat.isEmpty()) {
            //Si no tiene alguno de los apellidos (paterno o materno), se toma la primera y segunda letra del apellido que tiene y el 4to caracter será la segunda letra del nombre.
            rfc = (matcherPat.group(1) + matcherMat.group(1)).substring(0,2) + letraNom + matcherNom.group(2);
        }
        else if (matcherPat.group(1).length() > 2)
        {
            String vocal = matcherPat.group(3);
            //Cuando el apellido paterno no tiene vocales, se utiliza una X.
            if (vocal.isEmpty())
                vocal = "X";
            rfc = letraPat + vocal + letraMat + letraNom;
        }
        else
        {
            //Si el apellido paterno tiene 1 o 2 letras, no se toma la primera vocal, y el 4to caracter es la segunda letra del nombre.
            rfc = letraPat + letraMat + letraNom + matcherNom.group(2);
        }
        //Cuando las 4 letras resulten en una palabra inconveniente, se modifica la última letra a una X
        if (rfc.matches("BUE[IY]|C(?:A[CGK][AO]|O(?:GE|J[AEIO])|ULO)|FETO|GUEY|JOTO|K(?:A(?:[CG][AO]|KA)|O(?:GE|JO)|ULO)|M(?:AM[EO]|E(?:A[RS]|ON)|ION|OCO|ULA)|P(?:E(?:D[AO]|NE)|UT[AO])|QULO|R(?:ATA|UIN)"))
            return rfc.substring(0,3) + "X";
        else
            return rfc;
    }
    public static String eliminarAcentosYSimbolos(String s)
    {
        s = Normalizer.normalize(s.replaceAll("[Ññ]","&"), Normalizer.Form.NFD);
        s = s.replaceAll("[^&A-Za-z ]", "");
        return s.trim().toUpperCase();
    }
    //Metodo para regresar
    public void Regresar(View view)
    {
        Intent vuelve = new Intent(MainActivity3.this, MainActivity2.class);
        startActivity(vuelve);
    }
}