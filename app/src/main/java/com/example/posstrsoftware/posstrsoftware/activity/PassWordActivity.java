package com.example.posstrsoftware.posstrsoftware.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.model.CompanyList;

public class PassWordActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_Ok;
    EditText edit_pass;
    Button btn_cancel;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int ps = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        initInstants();
        CompanyDAO companyDAO = new CompanyDAO(this);
        companyDAO.open();
        ps = companyDAO.check();
        companyDAO.close();
        if (ps == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            this.finishAffinity();
            startActivity(intent);
        }


    }

    private void initInstants() {
        btn_Ok = (Button) findViewById(R.id.btn_Ok);
        btn_cancel = (Button) findViewById(R.id.btn_Cancel);
        edit_pass = (EditText) findViewById(R.id.edit_pass);
        btn_Ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        edit_pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edit_pass.setText("");
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        int ex = 0;
        if (btn_Ok == v) {
            if (edit_pass.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Input Password Please", Toast.LENGTH_SHORT).show();
            } else {
            CompanyList companyList = new CompanyList();
            companyList.setPassWord(Integer.parseInt(edit_pass.getText().toString()));
            CompanyDAO companyDAO = new CompanyDAO(this);
            companyDAO.open();
            ex = companyDAO.checkPass(companyList);

                if (ex == 0) {
                    companyDAO.Updatepass();
                    Intent intent = new Intent(this, MainActivity.class);
                    this.finishAffinity();
                    startActivity(intent);
                    companyDAO.close();

                } else {
                    Toast.makeText(this, "Password Fail!!", Toast.LENGTH_SHORT).show();
                }
            }

           /* Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);*/
        } else if (btn_cancel == v) {
            edit_pass.setText("");
        }

    }
}
