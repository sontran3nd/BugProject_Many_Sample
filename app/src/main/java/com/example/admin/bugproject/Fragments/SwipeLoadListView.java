package com.example.admin.bugproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.bugproject.R;
import com.example.admin.bugproject.Views.popover.WaveSwipe.WaveSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Created by Admin on 9/22/2017.
 */

public class SwipeLoadListView extends BaseFragment{

    @Override
    public int getResourceLayout() {
        return R.layout.fragment_swipe_load_listview;
    }

    private ListView listSample;
    private WaveSwipeRefreshLayout waveSwipe;

    @Override
    public void setupViews(View view)
    {
        listSample = view.findViewById(R.id.fragment_swipe_load_listview);
        waveSwipe = view.findViewById(R.id.main_swipe);
        waveSwipe.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(5000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    waveSwipe.setRefreshing(false);
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        ArrayList<String> sample = new ArrayList<>();
        sample.add("Sample 1");
        sample.add("Sample 2");
        sample.add("Sample 3");
        sample.add("Sample 4");
        sample.add("Sample 5");
        sample.add("Sample 6");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, sample);
        listSample.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
}
