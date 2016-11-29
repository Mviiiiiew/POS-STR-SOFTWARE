package com.example.posstrsoftware.posstrsoftware.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.ReportDayFragment;

public class ReportDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_day);
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerReportDay);
        if (fragment instanceof ReportDayFragment == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerReportDay, ReportDayFragment.newInstance())
                    .commit();
        }
    }
}
