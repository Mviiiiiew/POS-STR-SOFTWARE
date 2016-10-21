package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.ProductMainFragment;

public class ProductMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerProduct);
        if(fragment instanceof ProductMainFragment == false ) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerProduct, ProductMainFragment.newInstance())
                    .commit();
        }

    }


}
