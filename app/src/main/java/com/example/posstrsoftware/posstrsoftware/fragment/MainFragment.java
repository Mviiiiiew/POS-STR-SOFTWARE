package com.example.posstrsoftware.posstrsoftware.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.posstrsoftware.posstrsoftware.R;
import com.example.posstrsoftware.posstrsoftware.dao.CompanyDAO;
import com.example.posstrsoftware.posstrsoftware.model.CompanyList;
import com.gc.materialdesign.views.ButtonRectangle;
import com.inthecheesefactory.thecheeselibrary.view.SlidingTabLayout;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {
    ViewPager viewPager;
    SlidingTabLayout slidingTabLayout;
    TextView Company;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        CompanyDAO companyDAO = new CompanyDAO(getActivity());
        companyDAO.open();
        Company.setText(companyDAO.InvoiceMaster().getCompanyName().toString());


        companyDAO.close();
    }

    private void initInstances(View rootView) {
        CompanyList companyList = new CompanyList();

        // Init 'View' instance(s) with rootView.findViewById here
        Company  =  (TextView)rootView.findViewById(R.id.Company);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);


        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return SaleMainFragment.newInstance();
                    case 1:
                        return ProductSetMainFragment.newInstance();
                    case 2:
                        return CompanyMainFragment.newInstance();
                    case 3:
                        return ReportMainFragment.newInstance();
                    case 4:
                        return ContactMainFragment.newInstance();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "     ขายสินค้า       ";
                    case 1:
                        return "     ตั้งค่าสินค้า       ";
                    case 2:
                        return "     ตั้งค่าบริษัท       ";
                    case 3:
                        return "      รายงาน        ";
                    case 4:
                        return "      ติดต่อ       ";
                    default:
                        return "";
                }
            }
        });

        slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setViewPager(viewPager);

        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(getActivity(),R.color.colorOrange));





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
}
