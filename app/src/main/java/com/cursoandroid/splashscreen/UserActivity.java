package com.cursoandroid.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.Normalizer;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 2;
    private static final int RECONOCEDOR_VOZ = 7;
    private TextView escuchando;
    private TextView respuesta;

    private TextToSpeech leer;
    private Object TextView;
    TextView emailTextView;
    MaterialButton logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        emailTextView = findViewById(R.id.emailTextView);
        logoutButton = findViewById(R.id.logOutButton);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            emailTextView.setText(user.getEmail());
        }


    }



    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode == RESULT_OK && requestCode == RECONOCEDOR_VOZ) {
            ArrayList <String> reconocido =
                    data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );
            String escuchado = reconocido.get( 0 );
            escuchando.setText( escuchado );

        }
    }





    @Override
    public boolean dispatchKeyEvent( KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO


                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);

                }
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent anterior = new Intent(this, MainActivity.class);
                    startActivity(anterior);
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent anterior = new Intent(this, Q_R_CODE.class);
                    startActivity(anterior);
                }



            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent anterior = new Intent(this, Arreglarmaquinaestragadanoarranca.class);
                    startActivity(anterior);
                }

            case KeyEvent.KEYCODE_ENTER:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent anterior = new Intent(this, activa_reconocimientodevoz.class);
                    startActivity(anterior);
                }

            default:
                return super.dispatchKeyEvent(event);
        }
    }


    @Override
    public void onInit(int status) {

    }
}













