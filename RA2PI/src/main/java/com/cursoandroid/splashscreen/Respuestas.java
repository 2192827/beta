package com.cursoandroid.splashscreen;

import android.content.Intent;

public class Respuestas {


    private String cuestion;
    private String respuestas;
    private Intent intent;

    public Respuestas ( String cuestion, String respuestas, Intent intent ) {
        this.cuestion = cuestion;
        this.respuestas = respuestas;
        this.intent = intent;

    }


    public String getCuestion () {
        return cuestion;
    }

    public String getRespuestas () {
        return respuestas;
    }

    public Intent getIntent () {
        return intent;
    }

}
