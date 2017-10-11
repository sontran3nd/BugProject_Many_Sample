package com.example.admin.bugproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.bugproject.Fragments.Dialog.ShowSelectionDialogFragment;
import com.example.admin.bugproject.R;

/**
 * Created by Admin on 9/13/2017.
 */

public class ViewUpFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_up, container, false);
        setupViews(rootView);
        return rootView;
    }

    private void setupViews(View view)
    {
        Button btnShow = view.findViewById(R.id.btnshow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowSelectionDialogFragment showSelectionDialogFragment = new ShowSelectionDialogFragment();
                showSelectionDialogFragment.show(getFragmentManager(), "");
            }
        });
    }

}
