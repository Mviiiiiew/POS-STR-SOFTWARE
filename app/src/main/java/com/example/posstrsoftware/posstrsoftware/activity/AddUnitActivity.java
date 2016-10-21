package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.AddUnitFragment;

public class AddUnitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);


        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerAddUnit);
        if(fragment instanceof AddUnitFragment == false ) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerAddUnit, AddUnitFragment.newInstance())
                    .commit();
        }
    }
}
