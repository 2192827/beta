package com.cursoandroid.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private ArrayList <Respuestas> respuestas;
    private TextToSpeech leer;
    private Object TextView;
    TextView emailTextView;
    MaterialButton logoutButton;
    private DadosApp dadosApp;
    private int positionTarefa;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        inicializar();
        emailTextView = findViewById(R.id.emailTextView);
        logoutButton = findViewById(R.id.logOutButton);
        Intent hablar = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        hablar.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX" );
        startActivityForResult( hablar, RECONOCEDOR_VOZ );
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
            prepararRespuesta( escuchado );
        }
    }
    private void prepararRespuesta ( String escuchado ) {
        String normalizar = Normalizer.normalize( escuchado, Normalizer.Form.NFD );
        String sintilde = normalizar.replaceAll( "[^\\p{ASCII}]", "" );

        //    int resultado;
        //  String respuesta = respuest.get(0).getRespuestas();
        for (int i = 0; i < respuestas.size(); i++) {
            int resultado = sintilde.toLowerCase().indexOf( respuestas.get( i ).getCuestion() );
            if (resultado != -1) {
                //         respuesta = respuestas.get(i);
                responder( respuestas.get( i ) );
                return;
            }
        }

    }
    private void responder ( Respuestas respuesta ) {
        startActivity( respuesta.getIntent() );

        // respuesta.setText(respuestita);
  /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null, null);
        }else {
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null);
        }*/
    }


    private void inicializar () {
        escuchando = ( TextView ) findViewById( R.id.textView );

        respuesta = ( TextView ) findViewById( R.id.tvRespuesta );
        respuestas = proveerDatos();

        leer = new TextToSpeech( this, this );
    }

    private ArrayList <Respuestas> proveerDatos () {
        ArrayList <Respuestas> respuestas = new ArrayList <>();
        respuestas.add( new Respuestas( "tarifas", "  ",new Intent(this,activa_reconocimientodevoz.class)));

        respuestas.add( new Respuestas( "scanner", "",new Intent(this,Q_R_CODE.class)));

        return respuestas;
    }


    public void hablar ( View v ) {
        Intent hablar = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        hablar.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX" );
        startActivityForResult( hablar, RECONOCEDOR_VOZ );
    }


    @Override
    public void onInit ( int status ) {

    }




    @Override
    public boolean dispatchKeyEvent( KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO


    //                    Intent intent = new Intent(this, LoginActivity.class);
       //                 startActivity(intent);

                }
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
            //        Intent anterior = new Intent(this, MainActivity.class);
              //      startActivity(anterior);
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent anterior = new Intent(this, Q_R_CODE.class);
                    startActivity(anterior);
                }
                return true;
            case KeyEvent.ACTION_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent hablar = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
                    hablar.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX" );
                    startActivityForResult( hablar, RECONOCEDOR_VOZ );







                }
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent anterior = new Intent(this, MainActivity.class);
                    startActivity(anterior);
                }
                return true;
  /*          case KeyEvent.KEYCODE_ENTER:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    Intent anterior = new Intent(this, activa_reconocimientodevoz.class);
                    startActivity(anterior);
                }*/
            case KeyEvent.KEYCODE_ENTER:
                if (action == KeyEvent.ACTION_DOWN) {
                    setContentView(R.layout.activity_listview);
                    listviewTarefas();

                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }

    }


    public boolean listviewTarefas(){

        listView = findViewById(R.id.listview);

        String[] values = new String[] {
                "1. Receita de bolo " , "2. Plantação de trigo", "3. Voltar para a Main"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,values);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    positionTarefa = 0;
                    dadosApp = new DadosApp();
                 /*   Intent Tarefa1 = new Intent(view.getContext(),
                            Plano.class);
                    startActivity(Tarefa1);*/
                }

                if (position == 1) {
                    positionTarefa = 1;
                    dadosApp = new DadosApp();
                 /*   Intent Tarefa2 = new Intent(view.getContext(),
                            Plano.class);
                    startActivity(Tarefa2);*/


                }

                if(position == 2){
                    positionTarefa = 2;
                /*    Intent inicio = new Intent(view.getContext(),MainActivity.class);
                    startActivity(inicio);*/
                }
            }
        });
        return true;
    }

}













