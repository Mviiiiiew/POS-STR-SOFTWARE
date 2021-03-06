package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.UnitMainFragment;

public class UnitMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_main);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerUnit);
        if(fragment instanceof UnitMainFragment == false ) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerUnit, UnitMainFragment.newInstance())
                    .commit();
        }
    }
}
