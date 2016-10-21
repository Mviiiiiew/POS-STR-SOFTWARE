package com.example.posstrsoftware.posstrsoftware.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.fragment.AddGroupFragment;

public class AddGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainerAddGroup);
        if(fragment instanceof AddGroupFragment == false ) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainerAddGroup, AddGroupFragment.newInstance())
                    .commit();
        }
    }
}
