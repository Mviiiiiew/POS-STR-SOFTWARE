package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by nuuneoi on 11/16/2014.
 */


/**
 *  แก้ไขสินค้า
 * */
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
    String CheckVAT;
    String BarcodeBefore;

    EditText edit_price;
    Double price;
    Double priceCost;
    EditText edit_priceCost;
    EditText editText_Barcode;
    CheckBox checkbox_vat;
    int Unit;
    int Group;


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
    public void onResume() {
        super.onResume();
        UnitDAO mUnitDAO = new UnitDAO(getActivity());
        mUnitDAO.open();
        ArrayList<UnitList> unitList = mUnitDAO.getAllFixUnitList(Unit);
        mUnitDAO.close();
        mSpinnerUnitAdapter = new spinnerUnitAdapter(getActivity(), unitList);
        spinner_unit.setAdapter(mSpinnerUnitAdapter);

        GroupDAO mGroupDAO = new GroupDAO(getActivity());
        mGroupDAO.open();
        ArrayList<GroupList> groupList = mGroupDAO.getAllFixGroupList(Group);
        mGroupDAO.close();
        mSpinnerGroupAdapter = new spinnerGroupAdapter(getActivity(), groupList);
        spinner_group.setAdapter(mSpinnerGroupAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fix_product, container, false);
        initInstances(rootView);
        ProductList editProductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
        NameProductBefore = editProductList.getProductText();
        BarcodeBefore = editProductList.getBarcode();
        barcode = editProductList.getBarcode();
        price = editProductList.getProductprice();
        priceCost = editProductList.getCost();
        Vat = editProductList.getCheckvat();
        Unit = editProductList.getUnitList().getId();
        Group = editProductList.getGroupList().getId();
        edit_price.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edit_priceCost.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText_Barcode.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText_product.setImeOptions(EditorInfo.IME_ACTION_DONE);


        editText_product.setText(editProductList.getProductText());
        edit_price.setText(formatAmount.DecimalFormatDouble(price).toString());
        Log.d(price + "", "price");
        edit_priceCost.setText(formatAmount.DecimalFormatDouble(priceCost).toString());
        Log.d(priceCost + "", "cost");
        editText_Barcode.setText(barcode);
        if (Vat.matches("N")) {
            checkbox_vat.setChecked(true);
        } else {
            checkbox_vat.setChecked(false);
        }
        editNumberPrice();
        editNumberCost();
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
        edit_price = (EditText) rootView.findViewById(R.id.edit_price);
        edit_priceCost = (EditText) rootView.findViewById(R.id.edit_priceCost);
        editText_Barcode = (EditText) rootView.findViewById(R.id.editText_Barcode);
        checkbox_vat = (CheckBox) rootView.findViewById(R.id.checkbox_vat);
        btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_edit_product.setOnClickListener(this);
        spinner_unit.setOnItemSelectedListener(this);
        edit_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edit_price.setText("");
                }

            }
        });
        edit_priceCost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edit_priceCost.setText("");
                }
            }
        });

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
        if (checkbox_vat.isChecked() == true) {
            CheckVAT = "No";

        } else if (checkbox_vat.isChecked() == false) {
            CheckVAT = "Yes";

        }
        ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
        int ex = 0;
        if (v == btn_edit_product) {
            int GroupId = spinner_group.getSelectedItemPosition();
            int UnitId = spinner_unit.getSelectedItemPosition();
            if (GroupId == -1 && UnitId == -1) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(R.string.txt_pls_setup_group_and_unit);
                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();


            } else if (GroupId == -1) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(R.string.txt_pls_setup_group);
                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else if (UnitId == -1) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(R.string.txt_pls_setup_unit);
                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else if (editText_product.getText().toString().trim().replaceAll("", "").replaceAll("\\.", "").matches("")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(R.string.txt_pls_enter_product_name);
                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else if (edit_price.getText().toString().equals("") || edit_price.getText().toString().matches("\\.")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(R.string.txt_pls_enter_price);
                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else if (edit_priceCost.getText().toString().matches("") || edit_priceCost.getText().toString().matches("\\.")) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setTitle(R.string.txt_pls_enter_cost);
                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.show();

            } else if (NameProductBefore.equals(editText_product.getText().toString())) {

                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setBarcode(editText_Barcode.getText().toString());
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                ex = productDAO.checkNoName(eProductList);
                productDAO.close();

                if (ex == 1) {
                    //Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                    NameMatchesEx1(alertDialogder);
                } else if (editText_Barcode.getText().toString().matches("")) {
                  //  Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                    NameMatchesBarcodeEmpty(alertDialogder);
                } else if (BarcodeBefore.matches(editText_Barcode.getText().toString())) {
                 //   Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                    NameMatchesBarcodematches(alertDialogder);

                } else {
                    alertDialogder.setTitle(R.string.txt_duplicate_barcode);
                    alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialogder.show();
                }

            } else {

                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setBarcode(editText_Barcode.getText().toString());
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                ex = productDAO.check(eProductList);
                productDAO.close();
                if (ex == 1) {
                    //Success
                    // Toast.makeText(getActivity(), "ex1", Toast.LENGTH_SHORT).show();
                    Ex1Default(alertDialogder);

                } else if (ex == 2) {
                    //  Toast.makeText(getActivity(), "ex2", Toast.LENGTH_SHORT).show();
                    //ex2 = NameProduct  duplicate or NameProduct and Barcode duplicate

                    if (editText_Barcode.getText().toString().matches("")) {
                        //  ชื่อสินค้าซ้ำในระบบ  " + "->" + "  ชื่อสินค้า :
                        alertDialogder.setTitle(R.string.txt_duplicate_product_name + Util_String.getGennerlateString(editText_product.getText().toString()));
                        alertDialogder.setNegativeButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alertDialogder.show();
                    } else {
                    //"มีข้อมูล 'Barcode' และ 'ชื่อสินค้า' ซ้ำในระบบ"
                        alertDialogder.setTitle(R.string.txt_duplicate_barcode_and_name);
                        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialogder.show();

                    }
                } else if (ex == 0) {
                    //Toast.makeText(getActivity(), "ex0", Toast.LENGTH_SHORT).show();
                    //ชื่อสินค้าซ้ำในระบบ  " + "->" + "  ชื่อสินค้า :
                    alertDialogder.setTitle(R.string.txt_duplicate_product_name + Util_String.getGennerlateString(editText_product.getText().toString()));
                    alertDialogder.setNegativeButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialogder.show();

                } else if (ex == 3) {

                    //ex3 = Barcode matches BarcodeBefore

                    if (BarcodeBefore.matches(editText_Barcode.getText().toString())) {
                        // Toast.makeText(getActivity(), "ex3-1", Toast.LENGTH_SHORT).show();
                        Ex3DefaultBarcodeBeforematches(alertDialogder);
                    } else if (editText_Barcode.getText().toString().matches("")) {
                        //Toast.makeText(getActivity(), "ex3-2", Toast.LENGTH_SHORT).show();
                        Ex3DefaultBarcodeEmpty(alertDialogder);
                    } else {
                        //มีข้อมูล 'Barcode' ซ้ำในระบบ
                        alertDialogder.setTitle(R.string.txt_duplicate_barcode);
                        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialogder.show();
                    }
                }

            }

        } else if (btn_back == v) {
            getActivity().finish();
        } else if (btn_delete == v) {
            //ลบสินค้า
            AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
            alertDialogder.setTitle(getString(R.string.txt_pls_confrim_del_product) + NameProductBefore );
            alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
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
            alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialogder.show();


        }
    }

    private void Ex3DefaultBarcodeEmpty(AlertDialog.Builder alertDialogder) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fix_product, null);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
        TextView txt_unit = (TextView) view.findViewById(R.id.txt_unit);
        TextView txt_group = (TextView) view.findViewById(R.id.txt_group);
        TextView txt_price = (TextView) view.findViewById(R.id.txt_price);
        TextView txt_cost = (TextView) view.findViewById(R.id.txt_cost);
        TextView txt_vat = (TextView) view.findViewById(R.id.txt_vat);
        TextView txt_barcode = (TextView) view.findViewById(R.id.txt_barcode);


        txt_name.setText(Util_String.getGennerlateString(editText_product.getText().toString()));
        txt_unit.setText(mSelectedUnit.getUnitText().toString());
        txt_group.setText(mSelectedGroup.getGroupText().toString());
        txt_price.setText(edit_price.getText().toString());
        txt_cost.setText(edit_priceCost.getText().toString());
        txt_vat.setText(CheckVAT.toString());
        txt_barcode.setText(editText_Barcode.getText().toString());

        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductprice(Double.valueOf(edit_price.getText().toString().replaceAll(",", "")));
                eProductList.setCost(Double.valueOf(edit_priceCost.getText().toString().replaceAll(",", "")));
                eProductList.setBarcode(editText_Barcode.getText().toString());
                if (checkbox_vat.isChecked() == true) {
                    eProductList.setCheckvat("N");
                    eProductList.setSymbolVat("No");

                } else {
                    eProductList.setCheckvat("Y");
                    eProductList.setSymbolVat("Yes");

                }
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                productDAO.updatereplace(eProductList);
                productDAO.close();
                getActivity().finish();
            }
        });
        alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogder.setView(view);
        alertDialogder.show();
    }

    private void Ex3DefaultBarcodeBeforematches(AlertDialog.Builder alertDialogder) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fix_product, null);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
        TextView txt_unit = (TextView) view.findViewById(R.id.txt_unit);
        TextView txt_group = (TextView) view.findViewById(R.id.txt_group);
        TextView txt_price = (TextView) view.findViewById(R.id.txt_price);
        TextView txt_cost = (TextView) view.findViewById(R.id.txt_cost);
        TextView txt_vat = (TextView) view.findViewById(R.id.txt_vat);
        TextView txt_barcode = (TextView) view.findViewById(R.id.txt_barcode);


        txt_name.setText(Util_String.getGennerlateString(editText_product.getText().toString()));
        txt_unit.setText(mSelectedUnit.getUnitText().toString());
        txt_group.setText(mSelectedGroup.getGroupText().toString());
        txt_price.setText(edit_price.getText().toString());
        txt_cost.setText(edit_priceCost.getText().toString());
        txt_vat.setText(CheckVAT.toString());
        txt_barcode.setText(editText_Barcode.getText().toString());

        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductprice(Double.valueOf(edit_price.getText().toString().replaceAll(",", "")));
                eProductList.setCost(Double.valueOf(edit_priceCost.getText().toString().replaceAll(",", "")));
                eProductList.setBarcode(editText_Barcode.getText().toString());
                if (checkbox_vat.isChecked() == true) {
                    eProductList.setCheckvat("N");
                    eProductList.setSymbolVat("No");

                } else {

                    eProductList.setCheckvat("Y");
                    eProductList.setSymbolVat("Yes");

                }
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                productDAO.updatereplace(eProductList);
                productDAO.close();
                getActivity().finish();
            }
        });
        alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogder.setView(view);
        alertDialogder.show();
    }

    private void Ex1Default(AlertDialog.Builder alertDialogder) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fix_product, null);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
        TextView txt_unit = (TextView) view.findViewById(R.id.txt_unit);
        TextView txt_group = (TextView) view.findViewById(R.id.txt_group);
        TextView txt_price = (TextView) view.findViewById(R.id.txt_price);
        TextView txt_cost = (TextView) view.findViewById(R.id.txt_cost);
        TextView txt_vat = (TextView) view.findViewById(R.id.txt_vat);
        TextView txt_barcode = (TextView) view.findViewById(R.id.txt_barcode);


        txt_name.setText(Util_String.getGennerlateString(editText_product.getText().toString()));
        txt_unit.setText(mSelectedUnit.getUnitText().toString());
        txt_group.setText(mSelectedGroup.getGroupText().toString());
        txt_price.setText(edit_price.getText().toString());
        txt_cost.setText(edit_priceCost.getText().toString());
        txt_vat.setText(CheckVAT.toString());
        txt_barcode.setText(editText_Barcode.getText().toString());
        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductprice(Double.valueOf(edit_price.getText().toString().replaceAll(",", "")));
                eProductList.setCost(Double.valueOf(edit_priceCost.getText().toString().replaceAll(",", "")));
                eProductList.setBarcode(editText_Barcode.getText().toString());
                if (checkbox_vat.isChecked() == true) {
                    eProductList.setCheckvat("N");
                    eProductList.setSymbolVat("No");
                    //  Toast.makeText(getActivity(), "N", Toast.LENGTH_LONG).show();
                } else {
                    eProductList.setCheckvat("Y");
                    eProductList.setSymbolVat("Yes");
                    // Toast.makeText(getActivity(), "Y", Toast.LENGTH_LONG).show();
                }
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                productDAO.updatereplace(eProductList);
                productDAO.close();
                getActivity().finish();
            }
        });
        alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogder.setView(view);
        alertDialogder.show();
    }

    private void NameMatchesBarcodeEmpty(AlertDialog.Builder alertDialogder) {
        //  Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fix_product, null);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
        TextView txt_unit = (TextView) view.findViewById(R.id.txt_unit);
        TextView txt_group = (TextView) view.findViewById(R.id.txt_group);
        TextView txt_price = (TextView) view.findViewById(R.id.txt_price);
        TextView txt_cost = (TextView) view.findViewById(R.id.txt_cost);
        TextView txt_vat = (TextView) view.findViewById(R.id.txt_vat);
        TextView txt_barcode = (TextView) view.findViewById(R.id.txt_barcode);


        txt_name.setText(Util_String.getGennerlateString(editText_product.getText().toString()));
        txt_unit.setText(mSelectedUnit.getUnitText().toString());
        txt_group.setText(mSelectedGroup.getGroupText().toString());
        txt_price.setText(edit_price.getText().toString());
        txt_cost.setText(edit_priceCost.getText().toString());
        txt_vat.setText(CheckVAT.toString());
        txt_barcode.setText(editText_Barcode.getText().toString());

        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductprice(Double.valueOf(edit_price.getText().toString().replaceAll(",", "")));
                eProductList.setCost(Double.valueOf(edit_priceCost.getText().toString().replaceAll(",", "")));
                eProductList.setBarcode(editText_Barcode.getText().toString());

                if (checkbox_vat.isChecked() == true) {
                    eProductList.setCheckvat("N");
                    eProductList.setSymbolVat("No");
                    //  Toast.makeText(getActivity(), "N", Toast.LENGTH_LONG).show();
                } else {
                    eProductList.setCheckvat("Y");
                    eProductList.setSymbolVat("Yes");
                    //  Toast.makeText(getActivity(), "Y", Toast.LENGTH_LONG).show();
                }

                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                productDAO.updatereplace(eProductList);
                productDAO.close();
                getActivity().finish();
            }
        });
        alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogder.setView(view);
        alertDialogder.show();

    }

    private void NameMatchesEx1(AlertDialog.Builder alertDialogder) {
        // Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fix_product, null);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
        TextView txt_unit = (TextView) view.findViewById(R.id.txt_unit);
        TextView txt_group = (TextView) view.findViewById(R.id.txt_group);
        TextView txt_price = (TextView) view.findViewById(R.id.txt_price);
        TextView txt_cost = (TextView) view.findViewById(R.id.txt_cost);
        TextView txt_vat = (TextView) view.findViewById(R.id.txt_vat);
        TextView txt_barcode = (TextView) view.findViewById(R.id.txt_barcode);


        txt_name.setText(Util_String.getGennerlateString(editText_product.getText().toString()));
        txt_unit.setText(mSelectedUnit.getUnitText().toString());
        txt_group.setText(mSelectedGroup.getGroupText().toString());
        txt_price.setText(edit_price.getText().toString());
        txt_cost.setText(edit_priceCost.getText().toString());
        txt_vat.setText(CheckVAT.toString());
        txt_barcode.setText(editText_Barcode.getText().toString());

        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductprice(Double.valueOf(edit_price.getText().toString().replaceAll(",", "")));
                eProductList.setCost(Double.valueOf(edit_priceCost.getText().toString().replaceAll(",", "")));
                eProductList.setBarcode(editText_Barcode.getText().toString());

                if (checkbox_vat.isChecked() == true) {
                    eProductList.setCheckvat("N");
                    eProductList.setSymbolVat("No");

                } else {
                    eProductList.setCheckvat("Y");
                    eProductList.setSymbolVat("Yse");
                }
                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                productDAO.updatereplace(eProductList);
                productDAO.close();
                getActivity().finish();
            }
        });
        alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogder.setView(view);
        alertDialogder.show();

    }

    private void NameMatchesBarcodematches(AlertDialog.Builder alertDialogder) {
        //    Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fix_product, null);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
        TextView txt_unit = (TextView) view.findViewById(R.id.txt_unit);
        TextView txt_group = (TextView) view.findViewById(R.id.txt_group);
        TextView txt_price = (TextView) view.findViewById(R.id.txt_price);
        TextView txt_cost = (TextView) view.findViewById(R.id.txt_cost);
        TextView txt_vat = (TextView) view.findViewById(R.id.txt_vat);
        TextView txt_barcode = (TextView) view.findViewById(R.id.txt_barcode);


        txt_name.setText(Util_String.getGennerlateString(editText_product.getText().toString()));
        txt_unit.setText(mSelectedUnit.getUnitText().toString());
        txt_group.setText(mSelectedGroup.getGroupText().toString());
        txt_price.setText(edit_price.getText().toString());
        txt_cost.setText(edit_priceCost.getText().toString());
        txt_vat.setText(CheckVAT.toString());
        txt_barcode.setText(editText_Barcode.getText().toString());


        alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductList editproductList = (ProductList) getActivity().getIntent().getSerializableExtra("editProduct");
                ProductList eProductList = new ProductList();
                eProductList.setId(editproductList.getId());
                eProductList.setUnitList(new UnitList(mSelectedUnit.getId(), mSelectedUnit.getUnitText()));
                eProductList.setGroupList(new GroupList(mSelectedGroup.getId(), mSelectedGroup.getGroupText()));
                eProductList.setProductText(Util_String.getGennerlateString(editText_product.getText().toString()));
                eProductList.setProductprice(Double.valueOf(edit_price.getText().toString().replaceAll(",", "")));
                eProductList.setCost(Double.valueOf(edit_priceCost.getText().toString().replaceAll(",", "")));
                eProductList.setBarcode(editText_Barcode.getText().toString());

                if (checkbox_vat.isChecked() == true) {
                    eProductList.setCheckvat("N");
                    eProductList.setSymbolVat("No");
                    //   Toast.makeText(getActivity(), "N", Toast.LENGTH_LONG).show();
                } else {
                    eProductList.setCheckvat("Y");
                    eProductList.setSymbolVat("Yes");
                    // Toast.makeText(getActivity(), "Y", Toast.LENGTH_LONG).show();
                }

                ProductDAO productDAO = new ProductDAO(getActivity());
                productDAO.open();
                productDAO.updatereplace(eProductList);
                productDAO.close();
                getActivity().finish();
            }
        });
        alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogder.setView(view);
        alertDialogder.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        mSelectedUnit = (UnitList) mSpinnerUnitAdapter.getItem(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void editNumberCost() {
        edit_priceCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edit_priceCost.removeTextChangedListener(this);

                try {
                    String originalString = String.valueOf(edit_priceCost.getText());

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###.##");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    edit_priceCost.setText(formattedString);
                    edit_priceCost.setSelection(edit_priceCost.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                edit_priceCost.addTextChangedListener(this);


            }
        });
    }

    private void editNumberPrice() {
        edit_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edit_price.removeTextChangedListener(this);

                try {
                    String originalString = String.valueOf(edit_price.getText());

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###.##");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    edit_price.setText(formattedString);
                    edit_price.setSelection(edit_price.getText().length());

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                edit_price.addTextChangedListener(this);

            }
        });
    }
}
