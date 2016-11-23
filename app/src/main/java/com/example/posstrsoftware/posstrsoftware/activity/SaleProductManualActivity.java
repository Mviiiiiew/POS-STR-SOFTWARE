package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.SaleProductManualFragment;

public class SaleProductManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_product_manual);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainerSaleProductManual, SaleProductManualFragment.newInstance())
                    .commit();


    }
}
