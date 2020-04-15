package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {
    private EditText etAnio;
    String dataError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        etAnio = (EditText) findViewById(R.id.etAnio);
        dataError=getResources().getString(R.string.dberror);
    }

    public static String getAnimal(int anio){
        String animal = "";
        int resto = anio % 12;
        switch (resto) {
            case 0: animal = "Mono"; break;
            case 1: animal = "Gallo"; break;
            case 2: animal = "Perro"; break;
            case 3: animal = "Cerdo"; break;
            case 4: animal = "Rata"; break;
            case 5: animal = "Buey"; break;
            case 6: animal = "Tigre"; break;
            case 7: animal = "Conejo"; break;
            case 8: animal = "Dragon"; break;
            case 9: animal = "Serpiente"; break;
            case 10: animal = "Caballo"; break;
            case 11: animal = "Cabra"; break;
        }
        return animal;
    }
    public void Consulta(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity5.this, "registro",null,1);
        SQLiteDatabase DataBase = admin.getWritableDatabase();
        Cursor fila = DataBase.rawQuery("SELECT anio FROM formulario", null);
        if(fila.moveToFirst())
        {
            String varAnio = fila.getString(0);
            DataBase.close();

            int anio = Integer.parseInt(varAnio);
            etAnio.setText(getAnimal(anio));

        }
        else
        {
            Toast.makeText(MainActivity5.this, dataError, Toast.LENGTH_SHORT).show();
            DataBase.close();
        }
    }

    public void Regresar(View view)
    {
        Intent vuelve = new Intent(MainActivity5.this, MainActivity2.class);
        startActivity(vuelve);
    }
}
