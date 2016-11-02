package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.PayMainFragment;

public class PayMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_main);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerPay);
        if (fragment instanceof PayMainFragment == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerPay, PayMainFragment.newInstance())

                    .commit();
        }
    }
}
