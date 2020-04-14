package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity4 extends AppCompatActivity {

    String mes = getIntent().getStringExtra("mes");
    String dia = getIntent().getStringExtra("dia");
    EditText etTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        int diaN = Integer.parseInt(dia);
        int mesN = Integer.parseInt(mes);
        String signo = getSigno(mesN,diaN);
        etTexto.setText(signo);
    }

    public static String getSigno(int mes, int dia){
        String signo = "";
        switch (mes) {
            case 1:
                if (dia > 21) {
                    signo = "ACUARIO (Ene 20 - Feb 18)";
                } else {
                    signo = "CAPRICORNIO (Dic 22 - Ene 19)";
                }
                break;
            case 2:
                if (dia > 20) {
                    signo = "PISCIS (Feb 19 - Mar 20)";
                } else {
                    signo = "ACUARIO (Ene 20 - Feb 18)";
                }
                break;
            case 3:
                if (dia > 22) {
                    signo = "ARIES (Mar 21 - Abr 19)";
                } else {
                    signo = "PISCIS (Feb 19 - Mar 20)";
                }
                break;
            case 4:
                if (dia > 21) {
                    signo = "TAURO (Abr 20 - May 20)";
                } else {
                    signo = "ARIES (Mar 21 - Abr 19)";
                }
                break;
            case 5:
                if (dia > 22) {
                    signo = "GEMINIS (May 21 - Jun 20)";
                } else {
                    signo = "TAURO (Abr 20 - May 20)";
                }
                break;
            case 6:
                if (dia > 22) {
                    signo = "CANCER (Jun 21 - Jul 22)";
                } else {
                    signo = "GEMINIS (May 21 - Jun 20)";
                }
                break;
            case 7:
                if (dia > 24) {
                    signo = "LEO (Jul 23 - Ago 22)";
                } else {
                    signo = "CANCER (Jun 21 - Jul 22)";
                }
                break;
            case 8:
                if (dia > 24) {
                    signo = "VIRGO (Ago 23 - Sep 22)";
                } else {
                    signo = "LEO (Jul 23 - Ago 22)";
                }
                break;
            case 9:
                if (dia > 24) {
                    signo = "LIBRA (Sep 23 - Oct 22)";
                } else {
                    signo = "VIRGO (Ago 23 - Sep 22)";
                }
                break;
            case 10:
                if (dia > 24) {
                    signo = "ESCORPION (Oct 23 - Nov 21)";
                } else {
                    signo = "LIBRA (Sep 23 - Oct 22)";
                }
                break;
            case 11:
                if (dia > 23) {
                    signo = "SAGITARIO (Nov 22 - Dic 21)";
                } else {
                    signo = "ESCORPION (Oct 23 - Nov 21)";
                }
                break;
            case 12:
                if (dia > 23) {
                    signo = "CAPRICORNIO (Dic 22 - Ene 19)";
                } else {
                    signo = "SAGITARIO (Nov 22 - Dic 21)";
                }
                break;
        }
        return signo;
    }
}