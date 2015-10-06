package com.emythmakers.ashik.icare.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.emythmakers.ashik.icare.R;
import com.emythmakers.ashik.icare.adapter.ContactAdapter;
import com.emythmakers.ashik.icare.database.ContactModel;
import com.emythmakers.ashik.icare.database.DataSource;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    ListView listView;
    ContactAdapter contactAdapter;
    DataSource dataSource;
    ArrayList<ContactModel> UserNameList;
    Fragment fragment;
    ContactModel contactModel;
    TextView sMassage;
    Button btnCreate;
    boolean status;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list, container, false);
        listView=(ListView)view.findViewById(R.id.UserList);
        sMassage=(TextView)view.findViewById(R.id.sMassage);
        btnCreate=(Button)view.findViewById(R.id.btnInsert);
        dataSource=new DataSource(getActivity());
        status=dataSource.CheckRow();

        if(status) {
            btnCreate.setVisibility(View.GONE);
            sMassage.setVisibility(View.GONE);
            UserNameList= new ArrayList<>();
            UserNameList=dataSource.getAllContact();
            contactAdapter = new ContactAdapter(getActivity(), UserNameList);
            listView.setAdapter(contactAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position,
                                        long id) {

                    contactModel = UserNameList.get(position);
                    String positionID = contactModel.getId();
                    ContactModel.setPositionID(positionID);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment = new userMenuFragment();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.loginFragment, fragment);
                    fragmentTransaction.commit();
                    Toast.makeText(getActivity(), "Details for " + positionID, Toast.LENGTH_SHORT).show();

                }
            });

        }else{
            listView.setVisibility(View.GONE);
            btnCreate.setVisibility(View.VISIBLE);
            sMassage.setVisibility(View.VISIBLE);
            sMassage.setText("No Profile Found Please create new one +");
        }



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment = new InsertFragment();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.loginFragment, fragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Going to Blank from", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
