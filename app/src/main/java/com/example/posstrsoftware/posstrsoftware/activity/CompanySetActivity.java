package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.CompanySetFragment;

public class CompanySetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_set);


        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerCompany);
        if(fragment instanceof CompanySetFragment == false )
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerSetCompany, CompanySetFragment.newInstance())
                    .commit();
    }
}
