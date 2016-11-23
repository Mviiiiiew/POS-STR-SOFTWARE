package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.ConcludeFragment;

public class ConcludeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclude);
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerConclude);
        if (fragment instanceof ConcludeFragment == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerConclude,ConcludeFragment.newInstance())
                    .commit();
        }
    }


    }

