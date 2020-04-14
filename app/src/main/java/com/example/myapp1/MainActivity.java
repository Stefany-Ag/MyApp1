package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String Nombre = "NOMBRE";
    public static final String apellido1 = "APELLIDOP";
    public static final String apellido2 = "APELLIDOM";
    public static final String Dia = "DIA";
    public static final String Mes = "MES";
    public static final String Anio = "AÑO";
    EditText etFecha, etNombre, etApPat,etApMat;
    MediaPlayer mp;
    Button btnListo;
    String adv, error, exito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SampleFragment sampleFragment = new SampleFragment();

        mp = MediaPlayer.create(this, R.raw.always);
        mp.start();
        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(this);
        etNombre = findViewById(R.id.etNombre);
        etApPat = findViewById(R.id.etApellidoPaterno);
        etApMat = findViewById(R.id.etApellidoMaterno);
        btnListo = findViewById(R.id.btnListo);
        adv = getResources().getString(R.string.advertencia);
        error = getResources().getString(R.string.error);
        exito = getResources().getString(R.string.exitoso);
        btnListo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellidoPaterno = etApMat.getText().toString();
                String apellidoMaterno = etApMat.getText().toString();
                if(etFecha.getText().length()!=0 && etNombre.getText().length() !=0 && etApPat.getText().length() !=0 && etApMat.getText().length() !=0){
                    Bundle bundle = new Bundle();
                    bundle.putString(Nombre, nombre);
                    bundle.putString(apellido1, apellidoPaterno);
                    bundle.putString(apellido2, apellidoMaterno);
                    sampleFragment.setArguments(bundle);
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    Toast.makeText(MainActivity.this, exito, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, adv, Toast.LENGTH_LONG).show();
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
                Bundle bundle = new Bundle();
                bundle.putString(Anio, a);
                bundle.putString(Mes, m);
                bundle.putString(Dia, d);
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtras(bundle);
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
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
}