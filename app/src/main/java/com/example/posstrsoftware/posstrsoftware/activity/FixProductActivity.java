package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.FixProductFragment;

public class FixProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_product);

        getSupportFragmentManager().beginTransaction()

                .add(R.id.contentContainerFixProduct, FixProductFragment.newInstance())
                .commit();
    }
}
