package com.cursoandroid.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().getInsetsController().hide(WindowInsets.Type.statusBars());

        setContentView(R.layout.activity_main);


        //Agregar animaciones
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_hacia_arriba);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_hacia_abajo);


        TextView byTextView = findViewById(R.id.byTextView);
        TextView tomasnecrisTextView = findViewById(R.id.tomasnecrisTextView);
        ImageView logoImageView = findViewById(R.id.logoImageView);

        byTextView.setAnimation(animation2);
        tomasnecrisTextView.setAnimation(animation2);
        logoImageView.setAnimation(animation1);


        new Handler().postDelayed(() -> {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
            if(user != null && account != null){
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(logoImageView, "logoImageTrans");
                pairs[1] = new Pair<View, String>(tomasnecrisTextView, "textTrans");

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }else{
                    startActivity(intent);
                    finish();
                }
            }

        },4000);

    }
}