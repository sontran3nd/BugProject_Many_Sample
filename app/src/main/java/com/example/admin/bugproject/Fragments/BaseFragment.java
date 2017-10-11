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
 * Created by Admin on 9/22/2017.
 */

public abstract class BaseFragment extends Fragment {

    public abstract int getResourceLayout();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getResourceLayout(), container, false);
        setupViews(rootView);
        return rootView;
    }

    public abstract void setupViews(View view);

}
