package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.DiscountMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.PayMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductDeleteActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.util.VAT;
import com.gc.materialdesign.views.ButtonRectangle;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener{

    Double total;
    ButtonRectangle btn_Pay;
    ListView listView_SaleProduct;
    ImageButton btn_back;
    EditText edit_Barcode;
    ButtonRectangle btn_clear;
    TextView txt_cost;
    ButtonRectangle btn_delete;
    ButtonRectangle btn_backz;
    EditText edit_Amount;
    int amount=1;


    public SaleProductFragment() {
        super();
    }

    public static SaleProductFragment newInstance() {
        SaleProductFragment fragment = new SaleProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saleproduct, container, false);
        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        listView_SaleProduct = (ListView) rootView.findViewById(R.id.listView_SaleProduct);
        edit_Barcode = (EditText) rootView.findViewById(R.id.edit_Barcode);
        btn_delete = (ButtonRectangle) rootView.findViewById(R.id.btn_delete);
        btn_Pay = (ButtonRectangle) rootView.findViewById(R.id.btn_Pay);
        txt_cost = (TextView) rootView.findViewById(R.id.txt_cost);
        btn_clear = (ButtonRectangle) rootView.findViewById(R.id.btn_clear);
        btn_backz = (ButtonRectangle) rootView.findViewById(R.id.btn_backz);
        edit_Amount = (EditText)rootView.findViewById(R.id.edit_Amount);
        edit_Amount.setImeOptions(EditorInfo.IME_ACTION_DONE);
        btn_backz.setOnClickListener(this);
        btn_Pay.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        edit_Barcode.setOnEditorActionListener(this);
        btn_Pay.setRippleSpeed(15);
        btn_clear.setRippleSpeed(15);
        btn_delete.setRippleSpeed(50);
        btn_backz.setRippleSpeed(40);
        edit_Barcode.setShowSoftInputOnFocus(false);


        edit_Amount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
          @Override
          public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
              if (actionId == EditorInfo.IME_ACTION_DONE) {
                Toast.makeText(getActivity(),"OK",Toast.LENGTH_SHORT).show();
               edit_Amount.clearFocus();
                  edit_Barcode.requestFocus();
                  edit_Barcode.setCursorVisible(true);
                  InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                  imm.hideSoftInputFromWindow(edit_Barcode.getWindowToken(),0);

                  return false;
              } else {
                  return true;
              }
          }
      });
        edit_Amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_Amount.setText("");
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        edit_Amount.setText("1");
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        final ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
        productSaleDAO.close();
        final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
        listView_SaleProduct.setAdapter(adapter);


        Double x = 0.0;
        DecimalFormat money_format = new DecimalFormat("###,###,##0.00");
        for (ProductSaleList bean : productSaleLists) {
            x += Double.valueOf(bean.getPrice());
        }
        txt_cost.setText(money_format.format((x)));


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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here


        }

    }

    @Override
    public void onClick(View v) {
        if (btn_back == v || btn_backz == v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("processmanual",0);
            intent.putExtra("processbarcode",1);
            startActivity(intent);
            this.getActivity().finish();


        } else if (btn_clear == v) {

            if (btn_clear == v) {
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setMessage("คุณแน่ใจว่าจะทำการเริ่มรายการใหม่");
                alertDialogder.setTitle("เริ่มรายการใหม่ ?");
                alertDialogder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProductSaleList productSaleList = new ProductSaleList();
                        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
                        productSaleDAO.open();
                        productSaleDAO.clear(productSaleList);
                        ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                        final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
                        listView_SaleProduct.setAdapter(adapter);
                        productSaleDAO.close();
                        txt_cost.setText("0.00");
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

        } else if (btn_Pay == v) {
            Intent intent = new Intent(getActivity(), DiscountMainActivity.class);
            intent.putExtra("total", txt_cost.getText());

            startActivity(intent);

        } else if (btn_delete == v) {
            Intent intent = new Intent(getActivity(), SaleProductDeleteActivity.class);
            startActivity(intent);
        }
    }




    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Double total = 0.0;
        ProductDAO productDAO = new ProductDAO(getActivity());
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());

        if (edit_Amount.getText().toString().matches("")||edit_Amount.getText().toString().matches("0")) {
            amount = 1;
        } else {
            amount = Integer.parseInt(edit_Amount.getText().toString().trim());
        }

            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                for (int i = 0; i < amount; i++) {
                productDAO.open();
                ProductSaleList productSaleList = new ProductSaleList();
                productSaleList.setProductSale(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getProductSale());
                productSaleList.setPrice(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getPrice());
                productSaleList.setProductid(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getProductid());
                productSaleList.setUnitid(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getUnitid());
                productSaleList.setUnitname(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getUnitname());
                productSaleList.setGroupid(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getGroupid());
                productSaleList.setVat_flag(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getVat_flag());
                productSaleList.setGroupname(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getGroupname());
                productSaleList.setProduct_cost(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getProduct_cost());
                productSaleList.setProduct_price(productDAO.SearchID(String.valueOf(edit_Barcode.getText())).getProduct_price());
                productDAO.close();


                productSaleDAO.open();
                productSaleDAO.add(productSaleList);
                ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
                listView_SaleProduct.setAdapter(adapter);
                adapter.notifyDataSetChanged();


        }
                edit_Amount.setText("1");
                ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
            DecimalFormat money_format = new DecimalFormat("###,###,##0.00");
            for (ProductSaleList bean : productSaleLists) {
                total += Double.valueOf(bean.getPrice());

            }

            txt_cost.setText(money_format.format((total)));
            productSaleDAO.close();
            edit_Barcode.setText("");


            return true;

        }
        return false;
    }



}


