package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.ReportToPrintFragment;

public class ReportToPrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_to_print);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainerPrint, ReportToPrintFragment.newInstance())
                .commit();
    }
}
