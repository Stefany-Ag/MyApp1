package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String Nombre = "nombre";
    public static final String apellido1 = "apellidoPaterno";
    public static final String apellido2 = "apellidoMaterno";
    public static final String Dia = "dia";
    public static final String Mes = "mes";
    public static final String Anio = "anio";

    EditText etFecha, etNombre, etApPat,etApMat;
    TextView tvDia, tvMes, tvAnio;
    MediaPlayer mp;
    Button btnListo;
    String adv, error, exito, borrado, dberror,fechaError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.always);
        mp.start();
        etFecha = (EditText) findViewById(R.id.etFecha);
        etFecha.setOnClickListener(this);
        etNombre = (EditText) findViewById(R.id.etDia);
        etApPat = (EditText)findViewById(R.id.etApellidoPaterno);
        etApMat = (EditText)findViewById(R.id.etApellidoMaterno);
        tvDia = (TextView)findViewById(R.id.tvDia);
        tvMes = (TextView)findViewById(R.id.tvMes);
        tvAnio = (TextView)findViewById(R.id.tvAnio);
        btnListo = findViewById(R.id.btnListo);

        adv = getResources().getString(R.string.advertencia);
        error = getResources().getString(R.string.error);
        fechaError=getResources().getString(R.string.fechaError);
        exito = getResources().getString(R.string.exitoso);
        borrado=getResources().getString(R.string.borrado);
        dberror=getResources().getString(R.string.dberror);
        btnListo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                        (MainActivity.this, "registro", null, 1);
                SQLiteDatabase Database = admin.getWritableDatabase();

                String nombre = etNombre.getText().toString();
                String apellidoPaterno = etApPat.getText().toString();
                String apellidoMaterno = etApMat.getText().toString();
                String fecha = etFecha.getText().toString();
                showDatePickerDialog();
                String dia = tvDia.getText().toString();
                String mes = tvMes.getText().toString();
                String anio = tvAnio.getText().toString();
                if(!nombre.isEmpty() && !apellidoPaterno.isEmpty() && !apellidoMaterno.isEmpty() && !fecha.isEmpty())
                {
                    Calendar hoy=Calendar.getInstance();
                    if(hoy.get(Calendar.YEAR)<Integer.parseInt(anio)||hoy.get(Calendar.MONTH)<Integer.parseInt(mes)||hoy.get(Calendar.DATE)<Integer.parseInt(dia)) {
                        Toast.makeText(MainActivity.this, fechaError, Toast.LENGTH_LONG).show();
                        etFecha.setError(error);
                    }else {
                        ContentValues registros = new ContentValues();
                        registros.put(Nombre, nombre);
                        registros.put(apellido1, apellidoPaterno);
                        registros.put(apellido2, apellidoMaterno);
                        registros.put(Dia, dia);
                        registros.put(Mes, mes);
                        registros.put(Anio, anio);
                        Database.insert("formulario", null, registros);
                        Database.close();
                        Toast.makeText(MainActivity.this, exito, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                    if(etNombre.getText().length()==0) {
                        etNombre.setError(error);
                    }
                    if (etApPat.getText().length() == 0) {
                        etApPat.setError(error);
                    }
                    if (etApMat.getText().length() == 0) {
                        etApMat.setError(error);
                    }
                    if (etFecha.getText().length() == 0) {
                        etFecha.setError(error);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.etFecha) {
            showDatePickerDialog();
        }
    }

    private void showDatePickerDialog() {
        escondeTeclado(this);
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {

                final String fechaSeleccionada = dia + " / " + (mes + 1) + " / " + anio;
                etFecha.setText(fechaSeleccionada);
                String a = Integer.toString(anio);
                String m = Integer.toString(mes);
                String d = Integer.toString(dia);
                tvDia.setText(String.valueOf(datePicker.getDayOfMonth()));
                tvMes.setText(String.valueOf(datePicker.getMonth() + 1));
                tvAnio.setText(String.valueOf(datePicker.getYear()));
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static void escondeTeclado(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mp.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this,"registro",null,1);
        SQLiteDatabase Database = admin.getWritableDatabase();
        if(Database!= null)
        {
            Database.execSQL("DELETE FROM formulario");
            Database.close();
            Toast.makeText(MainActivity.this, borrado, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, dberror, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
}