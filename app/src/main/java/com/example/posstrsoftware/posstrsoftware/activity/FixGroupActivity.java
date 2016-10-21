package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.FixGroupFragment;

public class FixGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_group);


        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerFixGroup);
        if(fragment instanceof FixGroupFragment == false ) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.contentContainerFixGroup, FixGroupFragment.newInstance())
                    .commit();
        }
    }
}
