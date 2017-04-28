package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.activity.DiscountMainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.MainActivity;
import com.example.posstrsoftware.posstrsoftware.activity.SaleProductDeleteActivity;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductListManualAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleAdapter;
import com.example.posstrsoftware.posstrsoftware.adapter.ProductSaleManualAdapter;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ProductSaleDAO;
import com.example.posstrsoftware.posstrsoftware.dao.ReportDAO;
import com.example.posstrsoftware.posstrsoftware.model.CompanyList;
import com.example.posstrsoftware.posstrsoftware.model.ProductList;
import com.example.posstrsoftware.posstrsoftware.model.ProductSaleList;
import com.example.posstrsoftware.posstrsoftware.model.ReportList;
import com.example.posstrsoftware.posstrsoftware.util.formatAmount;
import com.gc.materialdesign.views.ButtonRectangle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SaleProductManualFragment extends Fragment implements View.OnClickListener {
    ListView listview_salemanual;
    ListView listView_SaleProductmanual;
    Button btn_clear;
    ButtonRectangle btn_backz;
    SearchView searchViewProduct;
    TextView txt_cost;
    ImageButton btn_back;
    ButtonRectangle btn_Pay;
    EditText edit_Amount;
    int amount = 1;
    int VarAmount = 0;
    String mProduct;
    Double ValueVat = 0.0;



    public SaleProductManualFragment() {
        super();
    }

    public static SaleProductManualFragment newInstance() {
        SaleProductManualFragment fragment = new SaleProductManualFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saleproduct_manual, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView_SaleProductmanual = (ListView) rootView.findViewById(R.id.listView_SaleProductmanual);
        listview_salemanual = (ListView) rootView.findViewById(R.id.listview_salemanual);
        searchViewProduct = (SearchView) rootView.findViewById(R.id.searchViewProduct);
        searchViewProduct.setQueryHint("Search..");
        btn_backz = (ButtonRectangle) rootView.findViewById(R.id.btn_backz);
        btn_clear = (Button) rootView.findViewById(R.id.btn_clear);
        btn_Pay = (ButtonRectangle) rootView.findViewById(R.id.btn_Pay);
        btn_back = (ImageButton) rootView.findViewById(R.id.btn_back);
        txt_cost = (TextView) rootView.findViewById(R.id.txt_cost);
        edit_Amount = (EditText) rootView.findViewById(R.id.edit_Amount);

        edit_Amount.setImeOptions(EditorInfo.IME_ACTION_DONE);
       /* edit_searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewProduct.setIconified(false);
            }
        });*/
        edit_Amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_Amount.setText("");


            }
        });

        btn_Pay.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_backz.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_Pay.setRippleSpeed(40);
        btn_backz.setRippleSpeed(40);


    }

    @Override
    public void onPause() {
        super.onPause();

    }


    private String Date() {
        java.text.DateFormat df = new java.text.SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(java.util.Calendar.getInstance().getTime());
        return date;
    }


    @Override
    public void onResume() {
        super.onResume();

        edit_Amount.setText("1");
        searchViewProduct.setIconifiedByDefault(false);
        searchViewProduct.setIconified(false);
        searchViewProduct.clearFocus();



        List<String> list = new ArrayList<>();
        list.add(Date());

        Log.d("date", String.valueOf(list));


        final ProductDAO productDAO = new ProductDAO(getActivity());
        productDAO.open();
        final ArrayList<ProductList> myProductList = productDAO.getAllProductList();
        productDAO.close();
        final ProductListManualAdapter objAdapter = new ProductListManualAdapter(getActivity(), myProductList);
        listview_salemanual.setAdapter(objAdapter);
        ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
        productSaleDAO.open();
        ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
        Double x = 0.0;
        Double y = 0.0;
        DecimalFormat money_format = new DecimalFormat("###,###,##0.00");
        for (ProductSaleList bean : productSaleLists) {
            x += Double.valueOf(bean.getPrice());
            y += bean.getValueVat();

        }
        ValueVat = y;

        txt_cost.setText(money_format.format((x)));

        ProductSaleManualAdapter adapter = new ProductSaleManualAdapter(getActivity(), productSaleLists);
        listView_SaleProductmanual.setAdapter(adapter);


        listView_SaleProductmanual.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
                productSaleDAO.open();
                ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                ProductSaleManualAdapter adapter = new ProductSaleManualAdapter(getActivity(), productSaleLists);
                AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
                alertDialogder.setMessage(getString(R.string.txt_deletemanual) + ((ProductSaleList) adapter.getItem(position)).getProductSale());
                mProduct = ((ProductSaleList) adapter.getItem(position)).getProductSale();
                Log.d("mProduct", mProduct);
                alertDialogder.setTitle(R.string.title_deleteproduct);
                alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), SaleProductDeleteActivity.class);
                        intent.putExtra("mProduct", mProduct);

                        startActivity(intent);
                    }
                });
                alertDialogder.show();

                return false;
            }
        });

        listview_salemanual.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                       @Override
                                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                           if (edit_Amount.getText().toString().matches("")) {
                                                               edit_Amount.setText("1");
                                                           } else {
                                                               VarAmount = Integer.parseInt(edit_Amount.getText().toString());
                                                           }
                                                           if (VarAmount > 100) {
                                                               Toast.makeText(getActivity(), R.string.txt_please_enter_qtysale, Toast.LENGTH_LONG).show();

                                                               edit_Amount.setText("1");
                                                           } else {
                                                               ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
                                                               productSaleDAO.open();
                                                               Double total = 0.0;
                                                               ValueVat = 0.0;

                                                               if (edit_Amount.getText().toString().matches("") || edit_Amount.getText().toString().matches("0")) {
                                                                   amount = 1;
                                                               } else {
                                                                   amount = Integer.parseInt(edit_Amount.getText().toString().trim());
                                                               }


                                                               for (int i = 0; i < amount; i++) {
                                                                   ProductSaleList productSaleList = new ProductSaleList();
                                                                   productSaleList.setPrice(Double.valueOf(((ProductList) objAdapter.getItem(position)).getProductpricesumvat()));
                                                                   productSaleList.setProductSale(((ProductList) objAdapter.getItem(position)).getProductText());
                                                                   productSaleList.setProductid(((ProductList) objAdapter.getItem(position)).getId());
                                                                   productSaleList.setUnitid(((ProductList) objAdapter.getItem(position)).getUnitList().getId());
                                                                   productSaleList.setUnitname(((ProductList) objAdapter.getItem(position)).getUnitList().getUnitText());
                                                                   productSaleList.setGroupid(((ProductList) objAdapter.getItem(position)).getGroupList().getId());
                                                                   productSaleList.setGroupname(((ProductList) objAdapter.getItem(position)).getGroupList().getGroupText());
                                                                   productSaleList.setProduct_price(((ProductList) objAdapter.getItem(position)).getProductprice());
                                                                   productSaleList.setProduct_cost(((ProductList) objAdapter.getItem(position)).getCost());
                                                                   productSaleList.setVat_flag(((ProductList) objAdapter.getItem(position)).getCheckvat());
                                                                   productSaleList.setSymbolVat(((ProductList) objAdapter.getItem(position)).getSymbolVat());
                                                                   productSaleList.setValueVat(((ProductList) objAdapter.getItem(position)).getValueVat());
                                                                   productSaleDAO.add(productSaleList);
                                                                   ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                                                                   ProductSaleManualAdapter adapter = new ProductSaleManualAdapter(getActivity(), productSaleLists);
                                                                   adapter.notifyDataSetChanged();
                                                                   listView_SaleProductmanual.setAdapter(adapter);

                                                               }


                                                               edit_Amount.setText("1");

                                                               ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                                                               for (ProductSaleList bean : productSaleLists) {
                                                                   total += Double.valueOf(bean.getPrice());
                                                                   ValueVat += Double.valueOf(bean.getValueVat());
                                                                   Log.d("ValueVat", ValueVat + "");
                                                                   txt_cost.setText(formatAmount.formatAmountDouble(total));


                                                               }

                                                               productSaleDAO.close();
                                                           }
                                                       }
                                                   }

        );
        searchViewProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener()

                                                 {
                                                     @Override
                                                     public boolean onQueryTextSubmit(String query) {
                                                         return true;
                                                     }

                                                     @Override
                                                     public boolean onQueryTextChange(String query) {
                                                         objAdapter.getFilter().filter(query);
                                                         return false;
                                                     }
                                                 }

        );

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        if (btn_clear == v) {
            AlertDialog.Builder alertDialogder = new AlertDialog.Builder(getActivity());
            alertDialogder.setMessage(R.string.txt_beginconfirm);
            alertDialogder.setTitle(R.string.title_begin);
            alertDialogder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProductSaleList productSaleList = new ProductSaleList();
                    ProductSaleDAO productSaleDAO = new ProductSaleDAO(getActivity());
                    productSaleDAO.open();
                    productSaleDAO.clear(productSaleList);
                    ArrayList<ProductSaleList> productSaleLists = productSaleDAO.getAllProductSaleList();
                    final ProductSaleAdapter adapter = new ProductSaleAdapter(getActivity(), productSaleLists);
                    listView_SaleProductmanual.setAdapter(adapter);
                    productSaleDAO.close();
                    ValueVat = 0.0;
                    txt_cost.setText("0.00");
                }
            });
            alertDialogder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialogder.show();

        } else if (btn_backz == v || btn_back == v) {

            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().finishAffinity();
            startActivity(intent);


        } else if (btn_Pay == v) {


            Intent intent = new Intent(getActivity(), DiscountMainActivity.class);
            intent.putExtra("total", txt_cost.getText());
            intent.putExtra("ValueVat", ValueVat);
            intent.putExtra("processmanual", 1);
            intent.putExtra("processbarcode", 0);
            startActivity(intent);


        }
    }
}
