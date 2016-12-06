package com.example.posstrsoftware.posstrsoftware.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.fragment.ConcludeFragment;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.gc.materialdesign.widgets.Dialog;

public class ConcludeActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclude);
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerConclude);
        if (fragment instanceof ConcludeFragment == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerConclude,ConcludeFragment.newInstance())
                    .commit();
        }
    }


    }

