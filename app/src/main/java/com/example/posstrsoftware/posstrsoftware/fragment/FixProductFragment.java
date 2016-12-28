package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import com.example.posstrsoftware.posstrsoftware.activity.ProductMainActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.spinnerGroupAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.spinnerUnitAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.GroupDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.UnitDAO;
import com.example.posstrsoftware.posstrsoftware.model.GroupList;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.model.UnitList;
import com.example.posstrsoftware.posstrsoftware.util.Util_String;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FixProductFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ImageButton btn_back;
    EditText editText_product;
    ButtonRectangle btn_edit_product;
    ButtonRectangle btn_delete;
    Spinner spinner_group;

    private spinnerGroupAdapter mSpinnerGroupAdapter;
    private GroupList mSelectedGroup;
    Spinner spinner_unit;

    private spinnerUnitAdapter mSpinnerUnitAdapter;
    private UnitList mSelectedUnit;
    String NameProductBefore;
    String barcode;
    String Vat;

    EditText edit_price;
    Double price;
    Double priceCost;
    EditText edit_priceCost;
    EditText editText_Barcode;
    CheckBox checkbox_vat;


    public FixProductFragment() {
        super();
    }

    public static FixProductFragment newInstance() {
        FixProductFragment fragment = new FixProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fix_product, container, false);
        initInstances(rootView);
        ProductList editProductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
        NameProductBefore = editProductList.getProductText();
        barcode = editProductList.getBarcode();
        price = editProductList.getProductprice();
        priceCost = editProductList.getCost();
        Vat = editProductList.getSymbolVat();
        Log.d("VAT", Vat);

        Log.d(NameProductBefore, "UnitBefore");

        editText_product.setText(editProductList.getProductText());
        edit_price.setText(price.toString());
        Log.d(price+"", "price");
        edit_priceCost.setText(priceCost.toString());
        Log.d(priceCost+"", "cost");
        editText_Barcode.setText(barcode);
        if(Vat.matches("มีภาษี")){
            checkbox_vat.setChecked(true);
        }



        final GroupDAO mGroupDAO = new GroupDAO(getActivity());
        mGroupDAO.open();
        final ArrayList<GroupList> groupList = mGroupDAO.getAllGroupList();
        mGroupDAO.close();

        mSpinnerGroupAdapter = new spinnerGroupAdapter(getActivity(), groupList);
        spinner_group.setAdapter(mSpinnerGroupAdapter);

        final UnitDAO mUnitDAO = new UnitDAO(getActivity());
        mUnitDAO.open();
        final ArrayList<UnitList> unitList = mUnitDAO.getAllUnitList();
        mUnitDAO.close();


        mSpinnerUnitAdapter = new spinnerUnitAdapter(getActivity(), unitList);
        spinner_unit.setAdapter(mSpinnerUnitAdapter);


        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        spinner_group = (Spinner) rootView.findViewById(R.id.spinner_group);
        spinner_unit = (Spinner) rootView.findViewById(R.id.spinner_unit);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        editText_product = (EditText) rootView.findViewById(R.id.editText_product);
        btn_edit_product = (ButtonRectangle) rootView.findViewById(R.id.btn_edit_product);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);
        edit_price = (EditText)rootView.findViewById(R.id.edit_price);
        edit_priceCost = (EditText)rootView.findViewById(R.id.edit_priceCost);
        editText_Barcode = (EditText)rootView.findViewById(R.id.editText_Barcode);
        checkbox_vat = (CheckBox)rootView.findViewById(R.id.checkbox_vat);

        btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_edit_product.setOnClickListener(this);
        spinner_unit.setOnItemSelectedListener(this);
        btn_delete.setRippleSpeed(15);
        btn_edit_product.setRippleSpeed(15);

        spinner_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedGroup = (GroupList) mSpinnerGroupAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
        int ex = 0;
        if (v == btn_edit_product) {
            if (editText_product.getText().toString().trim().replaceAll("", "").matches("")) {
                Toast.makeText(getActivity(), " กรุณาใส่ชื่อสินค้า ", Toast.LENGTH_LONG).show();
            } else if (NameProductBefore.matches(editText_product.getText().toString())) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle("กรุณาตรวจสอบข้อมูลก่อนบันทึก");
                alertDialogder.setMessage("ชื่อสินค้า : " + editText_product.getText().toString() + "\n" + "ชื่อหน่วย : " + mSelectedUnit.getUnitText().toString() + "\n" + "ชื่อกลุ่ม   : " + mSelectedGroup.getGroupText().toString() + "\n");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                        ProductList eProductList = new ProductList();
                        eProductList.setId(editproductList.getId());
                        eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                        eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                        eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                        eProductList.setProductprice(Double.valueOf(edit_price.getText().toString()));
                        eProductList.setCost(Double.valueOf(edit_priceCost.getText().toString()));
                        eProductList.setBarcode(editText_Barcode.getText().toString());
                        ProductDAO productDAO = new ProductDAO(getActivity());
                        productDAO.open();
                        productDAO.updatereplace(eProductList);
                        productDAO.close();
                        getActivity().finish();
                    }
                });
                alertDialogder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogder.show();

            } else {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle("กรุณาตรวจสอบข้อมูลก่อนบันทึก");
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                ex = productDAO.check(eProductList);
                productDAO.close();
                if (ex == 1) {
                    alertDialogder.setMessage("ชื่อสินค้า : " + editText_product.getText().toString() + "\n" + "ชื่อหน่วย : " + mSelectedUnit.getUnitText().toString() + "\n" + "ชื่อกลุ่ม   : " + mSelectedGroup.getGroupText().toString() + "\n");
                    alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                            ProductList eProductList = new ProductList();
                            eProductList.setId(editproductList.getId());
                            eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                            eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                            eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                            ProductDAO productDAO = new ProductDAO(getActivity());
                            productDAO.open();
                            productDAO.updatereplace(eProductList);
                            productDAO.close();
                            getActivity().finish();
                        }
                    });
                    alertDialogder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialogder.show();

                } else {
                    alertDialogder.setMessage("ซื่อสินค้าซ้ำในระบบต้องการบันทึกซ้ำหรือไม่ ?\n" + "ชื่อสินค้า : " + editText_product.getText().toString() + "\n" + "ชื่อหน่วย : " + mSelectedUnit.getUnitText().toString() + "\n" + "ชื่อกลุ่ม   : " + mSelectedGroup.getGroupText().toString() + "\n");
                    alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                            ProductList eProductList = new ProductList();
                            eProductList.setId(editproductList.getId());
                            eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                            eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                            eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                            ProductDAO productDAO = new ProductDAO(getActivity());
                            productDAO.open();
                            productDAO.updatereplace(eProductList);
                            productDAO.close();
                            getActivity().finish();
                        }
                    });
                    alertDialogder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialogder.show();
                }

            }

        } else if (btn_back == v) {
            getActivity().finish();
        } else if (btn_delete == v) {

            AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
            alertDialogder.setTitle("คุณต้องการที่จะลบสินค้า : " + NameProductBefore + "  หรือไม่ ?");
            alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                    ProductDAO productDAODel = new ProductDAO(getActivity());
                    productDAODel.open();
                    productDAODel.delete(editproductList);
                    productDAODel.close();
                    getActivity().finish();
                }
            });
            alertDialogder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialogder.show();


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        mSelectedUnit = (UnitList) mSpinnerUnitAdapter.getItem(position);





    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
