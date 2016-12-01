package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.ReportProductFragment;

public class ReportProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_product);



        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerReportProduct);
        if (fragment instanceof ReportProductFragment == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerReportProduct, ReportProductFragment.newInstance())
                    .commit();
        }
    }
}

