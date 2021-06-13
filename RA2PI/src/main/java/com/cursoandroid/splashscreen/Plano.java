package com.cursoandroid.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Plano extends AppCompatActivity {

    boolean estadoBoton;
    Button boton;
    TextView TFeito;
    boolean estadoFeito;
    TextView cajadetexto;
    ImageView ver_imagen;

    private DadosApp dadosApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dadosApp = new DadosApp();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        estadoBoton=true;
        boton=findViewById(R.id.Button);
        TFeito = findViewById(R.id.textView4);
        cajadetexto= findViewById(R.id.textView);

        cajadetexto.setText(dadosApp.getTextoPassoReceita());
        TFeito.setText("Por fazer");


    }

    //Metodos
    @Override
    public boolean dispatchKeyEvent( KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:

                if (action == KeyEvent.ACTION_DOWN) {


                    dadosApp.avancar();
                   // dadosApp.marcarFeito();
                    cajadetexto.setText(dadosApp.getTextoPassoReceita());

                    estadoBoton= false;

                    if(dadosApp.getPosicao() == dadosApp.getSizeListaPassos()){
                        TFeito.setText("Feito");
                     }else{
                        TFeito.setText("Por fazer");
                    }
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {

                    dadosApp.retroceder();
                    cajadetexto.setText(dadosApp.getTextoPassoReceita());

                    estadoBoton= false;

                }

                return true;
            case KeyEvent.KEYCODE_ENTER:
                if (action == KeyEvent.ACTION_DOWN) {
                    Intent inicio = new Intent(this,MainActivity.class);
                    startActivity(inicio);
                }

                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }




}