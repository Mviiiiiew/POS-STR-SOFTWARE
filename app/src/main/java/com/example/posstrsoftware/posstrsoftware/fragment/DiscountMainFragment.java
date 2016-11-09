package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductActivity;
import com.example.posstrsoftware.posstrsoftware.util.InputFilterMinMax;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class DiscountMainFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup rgRadiogroup;
    ButtonRectangle btn_save;
    ButtonRectangle btn_back;
    EditText editText_DiscountCost;
    EditText editText_DiscountPercent;

    public DiscountMainFragment() {
        super();
    }

    public static DiscountMainFragment newInstance() {
        DiscountMainFragment fragment = new DiscountMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_discount, container, false);
        initInstances(rootView);
        editText_DiscountPercent.setEnabled(false);



        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ButtonRectangle)rootView.findViewById(R.id.btn_back);
        btn_save = (ButtonRectangle)rootView.findViewById(R.id.btn_save);
        rgRadiogroup = (RadioGroup)rootView.findViewById(R.id.radiogroup_discount);
        editText_DiscountCost = (EditText)rootView.findViewById(R.id.editText_DiscountCost);
        editText_DiscountPercent = (EditText)rootView.findViewById(R.id.editText_DiscountPercent);
        rgRadiogroup.setOnCheckedChangeListener(this);
        btn_back.setOnClickListener(this);
        btn_back.setRippleSpeed(40);
        editText_DiscountPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onClick(View v) {
        if(btn_back == v){
       getActivity().finish();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       switch (rgRadiogroup.getCheckedRadioButtonId()){
           case R.id.radiobutton_cost:
               editText_DiscountCost.setEnabled(true);
               editText_DiscountPercent.setEnabled(false);
               break;
           case R.id.radiobutton_percent:
               editText_DiscountCost.setEnabled(false);
               editText_DiscountPercent.setEnabled(true);
               break;
       }



    }
}
