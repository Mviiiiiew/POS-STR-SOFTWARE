package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.CompanyMainFragment;

public class CompanyMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main);


        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerCompany);
        if(fragment instanceof CompanyMainFragment == false )
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainerCompany, CompanyMainFragment.newInstance())
                .commit();
    }
}
