package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.adapter.spinnerGroupAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.spinnerUnitAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.dao.GroupDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.UnitDAO;
import com.example.posstrsoftware.posstrsoftware.model.CompanyList;
import com.example.posstrsoftware.posstrsoftware.model.GroupList;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.model.UnitList;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.example.posstrsoftware.posstrsoftware.util.VAT;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class AddProductFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, TextWatcher {
    EditText editText_Product;
    ButtonRectangle btn_back;
    ButtonRectangle btn_save;
    EditText editText_Price;
    EditText editText_Barcode;
    CheckBox checkbox_vat;
    Spinner spinner_unit;
    Spinner spinner_group;
    EditText editText_Cost;
    private spinnerGroupAdapter mSpinnerGroupAdapter;
    private GroupList mSelectedGroup;
    private spinnerUnitAdapter mSpinnerUnitAdapter;
    private UnitList mSelectedUnit;

    public AddProductFragment() {
        super();
    }

    public static AddProductFragment newInstance() {
        AddProductFragment fragment = new AddProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        initInstances(rootView);
        final UnitDAO mUnitDAO = new UnitDAO(getActivity());
        mUnitDAO.open();
        final ArrayList<UnitList> unitList = mUnitDAO.getAllUnitList();
        mUnitDAO.close();


        mSpinnerUnitAdapter = new spinnerUnitAdapter(getActivity(), unitList);
        spinner_unit.setAdapter(mSpinnerUnitAdapter);
        final GroupDAO mGroupDAO = new GroupDAO(getActivity());
        mGroupDAO.open();
        final ArrayList<GroupList> groupList = mGroupDAO.getAllGroupList();
        mGroupDAO.close();

        mSpinnerGroupAdapter = new spinnerGroupAdapter(getActivity(), groupList);
        spinner_group.setAdapter(mSpinnerGroupAdapter);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        editText_Product = (EditText) rootView.findViewById(R.id.editText_Product);
        editText_Cost = (EditText)rootView.findViewById(R.id.editText_Cost);
        checkbox_vat = (CheckBox) rootView.findViewById(R.id.checkbox_vat);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        editText_Price = (EditText) rootView.findViewById(R.id.editText_Price);
        editText_Barcode = (EditText) rootView.findViewById(R.id.editText_Barcode);
        spinner_unit = (Spinner) rootView.findViewById(R.id.spinner_unit);
        spinner_group = (Spinner) rootView.findViewById(R.id.spinner_group);
        editText_Price.addTextChangedListener(this);
        spinner_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedGroup = (GroupList) mSpinnerGroupAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_save.setRippleSpeed(15);
        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        spinner_unit.setOnItemSelectedListener(this);
        btn_back.setRippleSpeed(40);


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
        int ex = 0;
        double price=0.0;
        if (v == btn_save) {
            if (editText_Product.getText().toString().trim().replaceAll("", "").replaceAll("\\.", "").matches("")) {
                Toast.makeText(getActivity(), "< Please input Product >", Toast.LENGTH_SHORT).show();
            } else if (editText_Price.getText().toString().matches("")) {
                Toast.makeText(getActivity(), "< Please input Price >", Toast.LENGTH_SHORT).show();
            } else {

                ProductList productList = new ProductList();
                productList.setProductText(Util_String.getGennerlateString(editText_Product.getText().toString()));
                productList.setBarcode(editText_Barcode.getText().toString());
                productList.setUnitList(new UnitList(mSelectedUnit.getId(), ""));
                productList.setGroupList(new GroupList(mSelectedGroup.getId(), ""));
                productList.setProductprice(Double.valueOf(editText_Price.getText().toString().replaceAll(",","")));
                productList.setCost(Double.valueOf(editText_Cost.getText().toString()));

                if(checkbox_vat.isChecked() == true){
                    productList.setCheckvat("Y");
                    Toast.makeText(getActivity(),"Y",Toast.LENGTH_LONG).show();
                }else{
                    productList.setCheckvat("N");
                    Toast.makeText(getActivity(),"N",Toast.LENGTH_LONG).show();
                }
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                ex = productDAO.add(productList);
                productDAO.close();
                if (ex == 0) {
                    AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                    alertDialogder.setMessage("< Please change nameproduct because repeat >");
                    alertDialogder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialogder.show();
                } else {
                    getActivity().finish();
                }


            }
        } else if (btn_back == v) {
            getActivity().finish();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        mSelectedUnit = (UnitList) mSpinnerUnitAdapter.getItem(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        editText_Price.removeTextChangedListener(this);

        try {
            String originalString = String.valueOf(editText_Price.getText());

            Long longval;
            if (originalString.contains(",")) {
                originalString = originalString.replaceAll(",", "");
            }
            longval = Long.parseLong(originalString);

            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###,###,###.##");
            String formattedString = formatter.format(longval);

            //setting text after format to EditText
            editText_Price.setText(formattedString);
            editText_Price.setSelection(editText_Price.getText().length());

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }


        editText_Price.addTextChangedListener(this);

    }
}
