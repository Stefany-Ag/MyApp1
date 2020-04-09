package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button btnRFC, btnHoros, btnChino;

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
}
