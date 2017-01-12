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
        editText_Cost = (EditText) rootView.findViewById(R.id.editText_Cost);
        checkbox_vat = (CheckBox) rootView.findViewById(R.id.checkbox_vat);
        btn_back = (ButtonRectangle) rootView.findViewById(R.id.btn_back);
        btn_save = (ButtonRectangle) rootView.findViewById(R.id.btn_save);
        editText_Price = (EditText) rootView.findViewById(R.id.editText_Price);
        editText_Barcode = (EditText) rootView.findViewById(R.id.editText_Barcode);
        spinner_unit = (Spinner) rootView.findViewById(R.id.spinner_unit);
        spinner_group = (Spinner) rootView.findViewById(R.id.spinner_group);
        editText_Price.addTextChangedListener(this);
        editText_Price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText_Price.setText("");
                }
            }
        });

        editText_Cost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText_Cost.setText("");
                }
            }
        });
        editText_Cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editText_Cost.removeTextChangedListener(this);

                try {
                    String originalString = String.valueOf(editText_Cost.getText());

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###.##");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    editText_Cost.setText(formattedString);
                    editText_Cost.setSelection(editText_Cost.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                editText_Cost.addTextChangedListener(this);

            }


        });
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
        double price = 0.0;
        if (v == btn_save) {
            if (editText_Product.getText().toString().trim().replaceAll("", "").replaceAll("\\.", "").matches("")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle("กรุณาใส่ชื่อสินค้า ?");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();


            } else if (editText_Price.getText().toString().matches("") || editText_Price.getText().toString().matches("\\.")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle("กรุณาใส่ราคาสินค้า ?");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else if (editText_Cost.getText().toString().matches("") || editText_Cost.getText().toString().matches("\\.")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle("กรุณาใส่ราคาต้นทุน หรือ 0 ?");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else {

                ProductList productList = new ProductList();
                productList.setProductText(Util_String.getGennerlateString(editText_Product.getText().toString()));
                productList.setBarcode(editText_Barcode.getText().toString());
                productList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                productList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                productList.setProductprice(Double.valueOf(editText_Price.getText().toString().replaceAll(",", "")));
                productList.setCost(Double.valueOf(editText_Cost.getText().toString().replaceAll(",", "")));

                if (checkbox_vat.isChecked() == true) {
                    productList.setCheckvat("N");
                    productList.setSymbolVat("ไม่มีภาษี");

                } else {
                    productList.setCheckvat("Y");
                    productList.setSymbolVat("มีภาษี");

                }
                ProductDAO productDAO = new ProductDAO(getActivity());
                if (editText_Barcode.getText().toString().matches("")) {
                    productDAO.open();
                    ex = productDAO.addNobarcode(productList);
                    productDAO.close();


                     if (ex == 0) {
                        AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                        alertDialogder.setTitle("มีข้อมูล 'ชื่อสินค้า' ซ้ำในระบบ");
                        alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alertDialogder.show();
                    }else {
                         getActivity().finish();
                     }

                } else {
                    productDAO.open();
                    ex = productDAO.add(productList);
                    productDAO.close();
                    if (ex == 2) {
                        AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                        alertDialogder.setTitle("มีข้อมูล 'Barcode' และ 'ชื่อสินค้า' ซ้ำในระบบ");
                        alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alertDialogder.show();

                    } else if (ex == 3) {
                        AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                        alertDialogder.setTitle("มีข้อมูล 'Barcode' ซ้ำในระบบ");
                        alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alertDialogder.show();
                    } else if (ex == 0) {
                        AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                        alertDialogder.setTitle("มีข้อมูล 'ชื่อสินค้า' ซ้ำในระบบ");
                        alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
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
            formatter.applyPattern("#,###.##");
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
