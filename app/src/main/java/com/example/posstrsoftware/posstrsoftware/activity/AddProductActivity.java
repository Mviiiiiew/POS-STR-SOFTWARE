package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.AddProductFragment;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerAddProduct);
        if(fragment instanceof AddProductFragment == false ) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerAddProduct, AddProductFragment.newInstance())
                    .commit();
        }
    }
}
