package com.cursoandroid.splashscreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DadosApp extends MainActivity {

    private List<Boolean> listaPassos;
    private int posicao;
    ArrayList<String> numberlist = new ArrayList<>();


    // singleton
    public DadosApp() {

        get_json();
        }


    public void get_json(){

        String json;
        try{
            InputStream is = getAssets().open("JsonTest.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);

                if (i == 0 && obj.getString("Receita de bolo").
                        equals("1")){
                    for(int b = 0; b < obj.getString("Receita de bolo").length(); i++){
                        listaPassos.add(obj.getString("Receita de bolo").equals(b));
                    }
                 /*   numberlist.add(obj.getString
                            ("1. Passo --> Preparação de ingredientes"));*/
                    posicao = 1;
                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    public String getTextoPassoReceita() {

        return listaPassos.get(posicao - 1).toString();
    }

    public void avancar() {
        if (posicao < listaPassos.size()){
            posicao++;
        }
    }

    public void retroceder() {
        if (posicao > 1)
            posicao--;
    }

    public int getPosicao()  {
        return posicao;
    }
    public int getSizeListaPassos()  {
        return listaPassos.size();
    }
/*
        public void marcarFeito() {
        listaPassos.get(posicao - 1).setFeito(true);
    }*/

}
