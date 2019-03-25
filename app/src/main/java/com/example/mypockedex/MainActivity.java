package com.example.mypockedex;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mypockedex.view.PokemonApiList;

public class MainActivity extends AppCompatActivity {
    private static int timer = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.first, R.anim.end);
            }
        }, timer);
    }

}
