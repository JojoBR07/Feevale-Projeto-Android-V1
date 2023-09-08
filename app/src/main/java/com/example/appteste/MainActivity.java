package com.example.appteste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textDisplay;
    String operadorandoA = "";
    String operadorandoB = "";
    String operador = "";
    String resultado = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDisplay = (TextView) findViewById(R.id.textDisplay);
        textDisplay.setText("");
    }

    public void defineOperando(View v) {
        Button bt = (Button) v;
        String valor = bt.getText().toString();

        if (operador != "") {
            operadorandoB = operadorandoB.concat(valor);
            textDisplay.setText(this.operadorandoB);
        } else if((operadorandoA == "") || (operadorandoB == "")){
            operadorandoA = operadorandoA.concat(valor);
            textDisplay.setText(this.operadorandoA);
        } else {
            Log.d("Número clicado", "Não entrou no IF :(");
        }

        Log.d("Número clicado", valor);
        Log.d("Operando A", this.operadorandoA);
        Log.d("Operando B", this.operadorandoB);
    }

    public void defineOperador(View v) {
        Button bt = (Button) v;
        String valor = bt.getText().toString();

        this.operador = valor;
        Log.d("Operador", this.operador);
    }

    public void calcular(View v) {
        double resultado = Double.parseDouble(operadorandoA);

        switch(this.operador) {
            case "+":
                resultado = Double.parseDouble(operadorandoA) + Double.parseDouble(operadorandoB);
                break;
            case "-":
                resultado = Double.parseDouble(operadorandoA) - Double.parseDouble(operadorandoB);
                break;
            case "*":
                resultado = Double.parseDouble(operadorandoA) * Double.parseDouble(operadorandoB);
                break;
            case "/":
                resultado = Double.parseDouble(operadorandoA) / Double.parseDouble(operadorandoB);
                break;
            default:
                if(this.operador == "")
                    Log.d("Alerta", "Operador não definido");
        }

        this.resultado = String.valueOf(resultado);
        textDisplay.setText(this.resultado);

        this.operadorandoA = this.resultado;
        this.operadorandoB = "";
        this.operador = "";
    }

    public void limpar(View v) {
        this.operadorandoA = "";
        this.operadorandoB = "";
        this.operador = "";
        this.resultado = "";
        textDisplay.setText("");
    }

    //    public void numeroClick(View v){
//        Button bt = (Button) v;
//        this.numero = Double.parseDouble(bt.getText().toString());
//
//        Log.d("Número clicado", String.valueOf(this.numero));
//        textDisplay.setText(this.numero);
//    }
//
//    public void somar(View v){
//        Button bt = (Button) v;
//
//        resultado = resultado + this.numero;
//        textDisplay.setText(String.valueOf(resultado));
//    }
//
//    public void subtrair(View v){
//        Button bt = (Button) v;
//
//        resultado = resultado - this.numero;
//        textDisplay.setText(String.valueOf(resultado));
//    }
//
//    public void dividir(View v){
//        Button bt = (Button) v;
//
//        resultado = resultado / this.numero;
//        textDisplay.setText(String.valueOf(resultado));
//    }
//
//    public void multiplicar(View v){
//        Button bt = (Button) v;
//
//        resultado = resultado * this.numero;
//        textDisplay.setText(String.valueOf(resultado));
//    }
//
//    public void calcular(View v){
//
//    }
}