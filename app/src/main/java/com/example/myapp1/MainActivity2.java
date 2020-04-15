package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity2 extends  AppCompatActivity implements View.OnClickListener {

    Button btnRFC, btnHoros, btnChino;
    String borrado, dberror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnRFC = (Button) findViewById(R.id.btnRFC);
        btnRFC.setOnClickListener(this);
        btnHoros = (Button) findViewById(R.id.btnHoros);
        btnHoros.setOnClickListener(this);
        btnChino = (Button) findViewById(R.id.btnChino);
        btnChino.setOnClickListener(this);
        overridePendingTransition(R.anim.sacar,R.anim.mantener);
        borrado=getResources().getString(R.string.borrado);
        dberror=getResources().getString(R.string.dberror);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnRFC:
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
                break;
            case R.id.btnHoros:
                intent = new Intent(MainActivity2.this,MainActivity4.class);
                startActivity(intent);
                break;
            case R.id.btnChino:
                intent = new Intent(MainActivity2.this,MainActivity5.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.mantener,R.anim.sacar);
    }
    //Metodo para regresar
    public void Regresar(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity2.this,"registro",null,1);
        SQLiteDatabase Database = admin.getWritableDatabase();
        if(Database!= null)
        {
            Database.execSQL("DELETE FROM formulario");
            Toast.makeText(MainActivity2.this, borrado, Toast.LENGTH_SHORT).show();
            Intent vuelve = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(vuelve);
        }
        else
        {
            Toast.makeText(MainActivity2.this, dberror, Toast.LENGTH_SHORT).show();
        }
    }
}