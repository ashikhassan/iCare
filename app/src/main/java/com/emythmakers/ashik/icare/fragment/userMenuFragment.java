package com.emythmakers.ashik.icare.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emythmakers.ashik.icare.R;
import com.emythmakers.ashik.icare.database.ContactModel;
import com.emythmakers.ashik.icare.database.DataSource;

/**
 * A simple {@link Fragment} subclass.
 */
public class userMenuFragment extends Fragment {
    DataSource dataSource;
    ContactModel contactModel;
    Fragment fragment;
    TextView sUserName;
    Button bntGeneralInfo;
    Button btnDietChart;
    Button btnDoctorList;
    Button btnVaccineList;
    String iAge;
    String iID;


    public userMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_menu, container, false);
        sUserName=(TextView)view.findViewById(R.id.sUserName);
        bntGeneralInfo=(Button)view.findViewById(R.id.bntGeneralInfo);
        btnDietChart=(Button)view.findViewById(R.id.btnDietChart);
        btnDoctorList=(Button)view.findViewById(R.id.btnDoctorList);
        btnVaccineList=(Button)view.findViewById(R.id.btnVaccineList);

        final String positionID= ContactModel.getPositionID();
        dataSource=new DataSource(getActivity());

        contactModel=new ContactModel(positionID);
        contactModel=dataSource.singleContact(positionID);

        sUserName.setText(contactModel.getName());
        iAge=contactModel.getAge();
        iID=contactModel.getId();


        /*bntGeneralInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactModel.setPositionID(iID);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment = new GeneralInfoFragment();
                fragmentTransaction.replace(R.id.loginFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Going for Edit", Toast.LENGTH_SHORT).show();

            }
        });*/


        // Inflate the layout for this fragment
        return view;
    }


}
