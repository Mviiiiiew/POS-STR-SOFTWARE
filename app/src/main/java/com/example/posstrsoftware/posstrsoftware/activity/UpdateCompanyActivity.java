package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.UpdateCompanyFragment;

public class UpdateCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_company);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerUpdateCompany);
        if (fragment instanceof UpdateCompanyFragment == false) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerUpdateCompany, UpdateCompanyFragment.newInstance())
                    .commit();
        }
    }
}
