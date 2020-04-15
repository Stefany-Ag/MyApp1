package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity3 extends AppCompatActivity {

    private EditText etNombre,etEdad;
    String dataError, fechaError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEdad = (EditText)findViewById(R.id.etEdad);
        dataError=getResources().getString(R.string.dataError);
        fechaError=getResources().getString(R.string.fechaError);

    }

    public static String primerosCuatroCaracteresRFC(String nombre, String apellidoPaterno, String apellidoMaterno){
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

    public int edadReal(int anio, int mes, int dia){

        Calendar hoy=Calendar.getInstance();
        int edad = hoy.get(Calendar.YEAR) - anio;
        if(hoy.get(Calendar.YEAR)<anio||hoy.get(Calendar.MONTH)<mes||hoy.get(Calendar.DATE)<dia){
            Toast.makeText(MainActivity3.this, fechaError, Toast.LENGTH_LONG).show();
            return 0;
        }else{
            if (hoy.get(Calendar.MONTH) > mes){
                return edad;
            }
            if (hoy.get(Calendar.MONTH) < mes){
                return edad - 1;
            }
            else{
                if(hoy.get(Calendar.DATE) > dia)
                {
                    return edad;
                }
                else
                {
                    return edad - 1;
                }
            }
        }
    }

    public void Consulta(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity3.this, "registro",null,1);
        SQLiteDatabase DataBase = admin.getWritableDatabase();
        Cursor fila = DataBase.rawQuery("SELECT nombre, apellidoPaterno, apellidoMaterno, dia, mes, anio FROM formulario", null);
        if(fila.moveToFirst())
        {
            String varNombre = fila.getString(0);
            String varApellidoPaterno = fila.getString(1);
            String varApellidoMaterno = fila.getString(2);
            String varDia = fila.getString(3);
            String varMes = fila.getString(4);
            String varAnio = fila.getString(5);
            DataBase.close();

            String letras = primerosCuatroCaracteresRFC(varNombre,varApellidoPaterno,varApellidoMaterno);
            String numeros = varAnio.substring(2);
            int anio = Integer.parseInt(varAnio);
            int mes = Integer.parseInt(varMes);
            int dia = Integer.parseInt(varDia);
            String edad =Integer.toString(edadReal(anio,mes,dia));
            etEdad.setText(edad);
            if(dia>=10){
                if(mes>=10) {
                    String rfc = letras + numeros + varMes + varDia;
                    etNombre.setText(rfc);
                }else{
                    String rfc = letras + numeros + "0" + varMes + varDia;
                    etNombre.setText(rfc);
                }
            }else{
                if(mes>=10) {
                    String rfc = letras + numeros + varMes + "0" + varDia;
                    etNombre.setText(rfc);
                }else{
                    String rfc = letras + numeros + "0" + varMes + "0" + varDia;
                    etNombre.setText(rfc);
                }
            }
        }
        else
        {
            Toast.makeText(MainActivity3.this, dataError, Toast.LENGTH_SHORT).show();
            DataBase.close();
        }
    }
    //Metodo para regresar
    public void Regresar(View view)
    {
        Intent vuelve = new Intent(MainActivity3.this, MainActivity2.class);
        startActivity(vuelve);
    }
}