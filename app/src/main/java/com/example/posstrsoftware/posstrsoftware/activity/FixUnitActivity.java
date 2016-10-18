package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.FixUnitFragment;

public class FixUnitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_unit);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentContainerFixUnit, FixUnitFragment.newInstance())
                .commit();
    }
}
