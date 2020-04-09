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

    EditText etFecha, etNombre, etApellido;
    MediaPlayer mp;
    Button btnListo;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.always);
        mp.start();
        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(this);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        btnListo = findViewById(R.id.btnListo);
        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etFecha.getText().length()==0){
                    Toast.makeText(MainActivity.this, "@string/alertaDatos", Toast.LENGTH_LONG).show();
                    etFecha.setError("@string/reqDatos");
                }
                else{
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "@string/exitoso", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.etFecha:
                showDatePickerDialog();
            case R.id.btnListo:
                if(validarFormulario()){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usuario", usuario);
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "@string/alertaDatos", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                final String fechaSeleccionada = dia + " / " + (mes+1) + " / " + anio;
                etFecha.setText(fechaSeleccionada);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private boolean validarFormulario(){
        if(etNombre.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "@string/alertaDatos", Toast.LENGTH_LONG).show();
            return false;
        }else{
            if (etApellido.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "@string/alertaDatos", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onPause(){
        super.onPause();
        mp.pause();
        Toast.makeText(MainActivity.this, "@string/onPausa",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mp.start();
        Toast.makeText(MainActivity.this, "@string/onRest",Toast.LENGTH_SHORT).show();
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