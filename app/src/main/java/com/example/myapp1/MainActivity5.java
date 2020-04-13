package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {

    String anio = getIntent().getStringExtra("año");
    TextView tvChino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        int anioN = Integer.parseInt(anio);
        final String horoscopo = getAnimal(anioN);
        tvChino.setText(horoscopo);
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
}
