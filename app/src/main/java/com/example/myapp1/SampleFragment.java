package com.example.myapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class SampleFragment extends Fragment {
    private String apPater;
    public SampleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            apPater = getArguments().getString(MainActivity.apellido1, "Puto");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(),"El apellido es: "+apPater,Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }
}