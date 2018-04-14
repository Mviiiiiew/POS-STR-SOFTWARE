package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.PayMainActivity;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.util.CostPercent;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Locale;

import static com.example.posstrsoftware.posstrsoftware.util.VAT.VATRATE;


/**
 * Created by nuuneoi on 11/16/2014.
 */

/**
 * หน้าจอส่วนลดสินค้าในการขายแต่ละครั้ง
 * */
public class DiscountMainFragment extends Fragment implements View.OnClickListener {

    RadioGroup rgRadiogroup;
    ButtonRectangle btn_save;
    RadioButton radiobutton_percent;
    ButtonRectangle btn_back;
    EditText editText_DiscountCost;
    EditText editText_DiscountPercent;
    CheckBox checkbox_discount;
    //CheckBox checkbox_AutoPay;
    RadioButton radiobutton_cost;
    String x;
    String discountcost;
    String discountpercent;
    TextView txt_NameTotal;
    Double C = 0.0;
    Double totalall = 0.0;
    Double A = 0.0;
    Double B = 0.0;
    Double ValueVat;
    Double TotalSumVat;
    int processmanual;
    int processbarcode;


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
        Intent intent = getActivity().getIntent();
        ValueVat = intent.getDoubleExtra("ValueVat", 0.0);
        Log.d("ValueVat", ValueVat + "");
        x = intent.getStringExtra("total");
        txt_NameTotal.setText(x.toString());
        processmanual = intent.getIntExtra("processmanual", 0);
        Log.d("processm", processmanual + "");
        processbarcode = intent.getIntExtra("processbarcode", 0);
        Log.d("processb", processbarcode + "");

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
        //editor.putBoolean("8", checkbox_AutoPay.isChecked());
        editor.putString("discountpercent", (editText_DiscountPercent.getText().toString()));
        editor.putString("discountcost", (editText_DiscountCost.getText().toString()));


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
        //checkbox_AutoPay.setChecked(sharedPreferences.getBoolean("8", false));
        editText_DiscountPercent.setText(sharedPreferences.getString("discountpercent", editText_DiscountPercent.getText().toString()));
        editText_DiscountCost.setText(sharedPreferences.getString("discountcost", editText_DiscountCost.getText().toString()));


    }


    @Override
    public void onResume() {
        super.onResume();
        loadradio();
        if (checkbox_discount.isChecked() == true) {
            if (radiobutton_cost.isChecked() == true) {
                editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscOK));
                editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
            } else if (radiobutton_percent.isChecked() == true) {
                editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscOK));
            } else {
                editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
            }
        }
        rgRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (rgRadiogroup.getCheckedRadioButtonId()) {
                    case R.id.radiobutton_cost:
                        editText_DiscountCost.setEnabled(true);
                        editText_DiscountPercent.setEnabled(false);
                        editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscOK));
                        editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                        break;
                    case R.id.radiobutton_percent:
                        editText_DiscountCost.setEnabled(false);
                        editText_DiscountPercent.setEnabled(true);
                        editText_DiscountPercent.setBackgroundColor(Color.GRAY);
                        editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                        editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscOK));
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
                                editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscOK));
                                editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                                break;
                            case R.id.radiobutton_percent:

                                editText_DiscountCost.setEnabled(false);
                                editText_DiscountPercent.setEnabled(true);
                                editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                                editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscOK));
                                break;
                        }

                    }
                } else if (checkbox_discount.isChecked() == false) {

                    radiobutton_cost.setEnabled(false);
                    radiobutton_percent.setEnabled(false);
                    editText_DiscountCost.setEnabled(false);
                    editText_DiscountPercent.setEnabled(false);
                    editText_DiscountPercent.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                    editText_DiscountCost.setBackgroundColor(getResources().getColor(R.color.colorDiscNO));
                }

            }

        });
    }


    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        //checkbox_AutoPay = (CheckBox) rootView.findViewById(R.id.checkbox_AutoPay);
        txt_NameTotal = (TextView) rootView.findViewById(R.id.txt_NameTotal);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        radiobutton_cost = (RadioButton) rootView.findViewById(R.id.radiobutton_cost);
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        checkbox_discount = (CheckBox) rootView.findViewById(R.id.checkbox_discount);
        radiobutton_percent = (RadioButton) rootView.findViewById(R.id.radiobutton_percent);
        rgRadiogroup = (RadioGroup) rootView.findViewById(R.id.radiogroup_discount);
        editText_DiscountCost = (EditText) rootView.findViewById(R.id.editText_DiscountCost);
        editText_DiscountPercent = (EditText) rootView.findViewById(R.id.editText_DiscountPercent);
        editText_DiscountPercent.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText_DiscountCost.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText_DiscountPercent.clearFocus();
        editText_DiscountCost.clearFocus();
        editText_DiscountCost.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText_DiscountCost.setText("");
                return false;
            }
        });
        editText_DiscountPercent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText_DiscountPercent.setText("");
                return false;
            }
        });


        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_back.setRippleSpeed(40);
        btn_save.setRippleSpeed(40);
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
                    double number = Double.valueOf(added_number);

                    if (number > 100.0) {
                        Toast.makeText(getActivity(), R.string.discPercent, Toast.LENGTH_SHORT).show();
                        editText_DiscountPercent.setText("");
                    }
                }

            }
        });

        editText_DiscountCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                editText_DiscountCost.removeTextChangedListener(this);

                try {
                    String originalString = String.valueOf(editText_DiscountCost.getText());

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###.##");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    editText_DiscountCost.setText(formattedString);
                    editText_DiscountCost.setSelection(editText_DiscountCost.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                editText_DiscountCost.addTextChangedListener(this);
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


            if (checkbox_discount.isChecked() == true) {
                if (radiobutton_cost.isChecked() == true) {
                    if ((editText_DiscountCost.getText().toString().replaceAll("", "").replaceAll("\\.", "").matches(""))) {
                        Toast.makeText(getActivity(), R.string.txt_please_enter_qty, Toast.LENGTH_SHORT).show();
                    } else {
                        PayCost();
                    }

                    //     Toast.makeText(getActivity(), totalall.toString(), Toast.LENGTH_SHORT).show();


                } else if (radiobutton_percent.isChecked() == true) {
                    if (editText_DiscountPercent.getText().toString().replaceAll("", "").matches("")) {
                        Toast.makeText(getActivity(), R.string.txt_please_enter_qty, Toast.LENGTH_SHORT).show();
                    } else {

                        PayPercent();


                        //   Toast.makeText(getActivity(), totalall.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (checkbox_discount.isChecked() == false) {
                pay();
                //Toast.makeText(getActivity(), totalall.toString(), Toast.LENGTH_SHORT).show();

            }
        }

    }



    private void pay() {
        totalall = Double.valueOf(txt_NameTotal.getText().toString().replace(",", ""));
        TotalSumVat = totalall;
        Log.d("VAT", TotalSumVat + "");
        String u = "0.0";
        discountpercent = String.valueOf(editText_DiscountPercent.getText() + " %");
        Intent intent = new Intent(getActivity(), PayMainActivity.class);
        intent.putExtra("totalx", txt_NameTotal.getText().toString().replace(",", ""));
        intent.putExtra("discountcost", u);
        intent.putExtra("totalall", TotalSumVat.toString());
        intent.putExtra("discountpercent", u);
        intent.putExtra("discount", u);
        intent.putExtra("symbol", "0.00");
        intent.putExtra("processmanual", processmanual);
        intent.putExtra("processbarcode", processbarcode);
        intent.putExtra("ValueVat", ValueVat);
        startActivity(intent);
    }

    private void PayPercent() {

        A = Double.valueOf(txt_NameTotal.getText().toString().replace(",", ""));
        B = Double.valueOf(editText_DiscountPercent.getText().toString());
        C = CostPercent.costpercent(A, B);
        Log.d("valA=", A + "");
        Log.d("valC=", C + "");
        totalall = (A - C);
        Log.d("totalall", totalall.toString());
        discountpercent = String.valueOf(editText_DiscountPercent.getText() + " %");
        Intent intent = new Intent(getActivity(), PayMainActivity.class);
        intent.putExtra("totalx", txt_NameTotal.getText().toString().replace(",", ""));
        intent.putExtra("totalall", totalall.toString());
        intent.putExtra("discountpercent", discountpercent);
        intent.putExtra("discount", C.toString());
        intent.putExtra("symbol", discountpercent);
        intent.putExtra("processmanual", processmanual);
        intent.putExtra("processbarcode", processbarcode);
        intent.putExtra("ValueVat", ValueVat);
        startActivity(intent);
    }


    private void PayCost() {
        A = Double.valueOf(txt_NameTotal.getText().toString().replace(",", ""));
        B = Double.valueOf(editText_DiscountCost.getText().toString().replace(",", ""));
        totalall = A - B;
        discountcost = String.valueOf(editText_DiscountCost.getText());
        String totalcost = String.valueOf(totalall);
        Intent intent = new Intent(getActivity(), PayMainActivity.class);
        intent.putExtra("totalx", txt_NameTotal.getText().toString().replace(",", ""));
        intent.putExtra("discountcost", editText_DiscountCost.getText().toString().replace(",", ""));
        intent.putExtra("totalall", totalcost);
        intent.putExtra("discount", B.toString());
        intent.putExtra("symbol", "Cost");
        intent.putExtra("processmanual", processmanual);
        intent.putExtra("processbarcode", processbarcode);
        intent.putExtra("ValueVat", ValueVat);
        startActivity(intent);
    }


    private void PayAutoPercent() {
        A = Double.valueOf(txt_NameTotal.getText().toString().replace(",", ""));
        B = Double.valueOf(editText_DiscountPercent.getText().toString());
        C = CostPercent.costpercent(A, B);
        Log.d("valA=", A + "");
        Log.d("valC=", C + "");
        totalall = CostPercent.parserFormat(A - C);
        Log.d("totalall", totalall.toString());
        discountpercent = String.valueOf(editText_DiscountPercent.getText() + " %");
        Intent intent = new Intent(getActivity(), PayMainActivity.class);
        intent.putExtra("totalx", txt_NameTotal.getText().toString().replace(",", ""));
        intent.putExtra("totalall", totalall.toString());
        intent.putExtra("discountpercent", discountpercent);
        intent.putExtra("discount", C.toString());
        intent.putExtra("symbol", discountpercent);
        intent.putExtra("processmanual", processmanual);
        intent.putExtra("processbarcode", processbarcode);
        intent.putExtra("ValueVat", ValueVat);

        startActivity(intent);
    }

    private void PayAutoCost() {
        A = Double.valueOf(txt_NameTotal.getText().toString().replace(",", ""));
        B = Double.valueOf(editText_DiscountCost.getText().toString().replace(",", ""));
        totalall = A - B;
        discountcost = String.valueOf(editText_DiscountCost.getText());

        String totalcost = String.valueOf(CostPercent.parserFormat(totalall));
        Intent intent = new Intent(getActivity(), PayMainActivity.class);
        intent.putExtra("totalx", txt_NameTotal.getText().toString().replace(",", ""));
        intent.putExtra("discountcost", editText_DiscountCost.getText().toString().replace(",", ""));
        intent.putExtra("totalall", totalcost);
        intent.putExtra("discount", B.toString());
        intent.putExtra("symbol", "Cost");
        intent.putExtra("processmanual", processmanual);
        intent.putExtra("processbarcode", processbarcode);
        intent.putExtra("ValueVat", ValueVat);
        startActivity(intent);
    }
    private void payAuto() {
        totalall = CostPercent.parserFormat(Double.valueOf(txt_NameTotal.getText().toString().replace(",", "")));
        TotalSumVat = totalall;
        Log.d("VAT", TotalSumVat + "");
        String u = "0.0";
        discountpercent = String.valueOf(editText_DiscountPercent.getText() + " %");
        Intent intent = new Intent(getActivity(), PayMainActivity.class);
        intent.putExtra("totalx", txt_NameTotal.getText().toString().replace(",", ""));
        intent.putExtra("discountcost", u);
        intent.putExtra("totalall", TotalSumVat.toString());
        intent.putExtra("discountpercent", u);
        intent.putExtra("discount", u);
        intent.putExtra("symbol", "0.00");
        intent.putExtra("processmanual", processmanual);
        intent.putExtra("processbarcode", processbarcode);
        intent.putExtra("ValueVat", ValueVat);


        startActivity(intent);

    }


}
