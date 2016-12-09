package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.ReportToCSVDayFragment;

public class ReportToCSVDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_to_csvday);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainerReportToCSVDay, ReportToCSVDayFragment.newInstance())
                .commit();
    }
}
