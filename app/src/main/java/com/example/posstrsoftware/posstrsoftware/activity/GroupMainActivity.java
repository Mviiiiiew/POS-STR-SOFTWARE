package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.GroupMainFragment;

public class GroupMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerGroup);
        if(fragment instanceof GroupMainFragment == false ) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerGroup, GroupMainFragment.newInstance())
                    .commit();
        }
    }
}


