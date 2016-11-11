package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductActivity;
import com.example.posstrsoftware.posstrsoftware.model.PojoDisCount;
import com.example.posstrsoftware.posstrsoftware.util.InputFilterMinMax;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class DiscountMainFragment extends Fragment implements View.OnClickListener {

    RadioGroup rgRadiogroup;
    ButtonRectangle btn_save;
    RadioButton radiobutton_percent;
    ButtonRectangle btn_back;
    EditText editText_DiscountCost;
    EditText editText_DiscountPercent;
    CheckBox checkbox_discount;
    RadioButton radiobutton_cost;


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

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

        saveradio();
    }

    private void saveradio() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("1", radiobutton_cost.isChecked());
        editor.putBoolean("2", radiobutton_cost.isEnabled());
        editor.putBoolean("3", radiobutton_percent.isChecked());
        editor.putBoolean("4", radiobutton_percent.isEnabled());
        editor.putBoolean("5", checkbox_discount.isChecked());
        editor.putBoolean("6", editText_DiscountCost.isEnabled());
        editor.putBoolean("7", editText_DiscountPercent.isEnabled());

        editor.commit();
    }

    private void loadradio() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        radiobutton_cost.setChecked(sharedPreferences.getBoolean("1", false));
        radiobutton_cost.setEnabled(sharedPreferences.getBoolean("2", false));
        radiobutton_percent.setChecked(sharedPreferences.getBoolean("3", false));
        radiobutton_percent.setEnabled(sharedPreferences.getBoolean("4", false));
        checkbox_discount.setChecked(sharedPreferences.getBoolean("5", false));
        editText_DiscountCost.setEnabled(sharedPreferences.getBoolean("6", false));
        editText_DiscountPercent.setEnabled(sharedPreferences.getBoolean("7", false));

    }


    @Override
    public void onResume() {
        super.onResume();
        loadradio();

        rgRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (rgRadiogroup.getCheckedRadioButtonId()) {
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
        });
        checkbox_discount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkbox_discount.isChecked() == true) {

                    for (int i = 0; i < rgRadiogroup.getChildCount(); i++) {
                        (rgRadiogroup.getChildAt(i)).setEnabled(isChecked);
                        switch (rgRadiogroup.getCheckedRadioButtonId()) {
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
                }else  if (checkbox_discount.isChecked() == false) {
                   radiobutton_cost.setEnabled(false);
                    radiobutton_percent.setEnabled(false);
                    editText_DiscountCost.setEnabled(false);
                    editText_DiscountPercent.setEnabled(false);
                }

            }

        });
    }


    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        radiobutton_cost = (RadioButton) rootView.findViewById(R.id.radiobutton_cost);
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        checkbox_discount = (CheckBox) rootView.findViewById(R.id.checkbox_discount);
        radiobutton_percent = (RadioButton) rootView.findViewById(R.id.radiobutton_percent);
        rgRadiogroup = (RadioGroup) rootView.findViewById(R.id.radiogroup_discount);
        editText_DiscountCost = (EditText) rootView.findViewById(R.id.editText_DiscountCost);
        editText_DiscountPercent = (EditText) rootView.findViewById(R.id.editText_DiscountPercent);
        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_back.setRippleSpeed(40);


        //// DisCount % ////
        editText_DiscountPercent.setInputType(InputType.TYPE_CLASS_NUMBER);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(3);
        editText_DiscountPercent.setFilters(FilterArray);
        editText_DiscountPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String added_number = editText_DiscountPercent.getText().toString();
                if (added_number.length() != 0) {
                    int number = Integer.parseInt(added_number);

                    if (number > 100) {
                        Toast.makeText(getActivity(), "กำหนดอยู่ในช่วง 0 - 100 (%)", Toast.LENGTH_SHORT).show();
                        editText_DiscountPercent.setText("");
                    }

                }

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

        if (btn_back == v) {
            getActivity().finish();

        } else if (btn_save == v) {
            ArrayList<PojoDisCount> pojoDisCountArrayList = new ArrayList<>();
            PojoDisCount pojoDisCount = new PojoDisCount();
            pojoDisCount.setDiscountcost(editText_DiscountCost.getText().toString());
            pojoDisCount.setDiscountpercent(editText_DiscountPercent.getText().toString());
            pojoDisCountArrayList.clear();
            pojoDisCountArrayList.add(pojoDisCount);
            Log.d("Cost", editText_DiscountCost.getText().toString());
            Log.d("percent", editText_DiscountPercent.getText().toString());

            Toast.makeText(getActivity(), pojoDisCountArrayList.toString(), Toast.LENGTH_SHORT).show();
        }


    }


}
