package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.DiscountMainFragment;

public class DiscountMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_main);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerdiscount);
        if (fragment instanceof DiscountMainFragment == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerdiscount, DiscountMainFragment.newInstance())
                    .commit();
        }
    }
}
