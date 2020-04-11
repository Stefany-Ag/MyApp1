package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etFecha, etNombre, etApPat, etApMat;
    MediaPlayer mp;
    Button btnListo;
    String adv = getResources().getString(R.string.alertaDatos);
    String error = getResources().getString(R.string.reqDatos);
    String exito = getResources().getString(R.string.exitoso);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.always);
        mp.start();
        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(this);
        etNombre = findViewById(R.id.etNombre);
        etApPat = findViewById(R.id.etApPat);
        etApMat = findViewById(R.id.etApMat);
        btnListo = findViewById(R.id.btnListo);
        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellidoPaterno = etApPat.getText().toString();
                String apellidoMaterno = etApMat.getText().toString();
                if (etFecha.getText().length() !=0 && etNombre.getText().length() !=0 && etApPat.getText().length()!=0){
                    Bundle bundle = new Bundle();
                    bundle.putString("nombre", nombre);
                    bundle.putString("apellidoPaterno", apellidoPaterno);
                    bundle.putString("apellidoMaterno", apellidoMaterno);
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    Toast.makeText(MainActivity.this, exito, Toast.LENGTH_LONG).show();
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, adv, Toast.LENGTH_LONG).show();
                    etFecha.setError(error);
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
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                final String fechaSeleccionada = dia + " / " + (mes+1) + " / " + anio;
                etFecha.setText(fechaSeleccionada);
                String a= Integer.toString(anio);
                String m= Integer.toString(mes);
                String d= Integer.toString(dia);
                Bundle bundle = new Bundle();
                bundle.putString("año", a);
                bundle.putString("mes", m);
                bundle.putString("dia", d);
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtras(bundle);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onPause(){
        super.onPause();
        mp.pause();
        Toast.makeText(MainActivity.this, "onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mp.start();
        Toast.makeText(MainActivity.this, "onRestart",Toast.LENGTH_SHORT).show();
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