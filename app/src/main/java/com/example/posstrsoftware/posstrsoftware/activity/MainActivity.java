package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainer);

        if(fragment instanceof MainFragment == false ) {

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, MainFragment.newInstance())
                        .commit();

            }
        }
    }

}
