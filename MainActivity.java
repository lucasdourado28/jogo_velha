package com.example.dourado_dtona.jogo_velha;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PvP(View v){
        Intent intent = new Intent(this, PvP.class);
        startActivity(intent);
    }

    public void PvC(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("AVISO");
        alert.setMessage("Opção indisponível no momento!");
        alert.setNeutralButton("Voltar para o menu", null);
        alert.show();

    }
}
