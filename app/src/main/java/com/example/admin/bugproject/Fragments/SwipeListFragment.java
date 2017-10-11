package com.example.admin.bugproject.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bugproject.Activities.DetailActivity;
import com.example.admin.bugproject.Adapters.SwipeListAdapter;
import com.example.admin.bugproject.R;
import com.example.admin.bugproject.Services.ChatHeadService;

import java.util.ArrayList;

/**
 * Created by Admin on 9/11/2017.
 */

public class SwipeListFragment extends Fragment {


    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    private SwipeListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swipe_list, container, false);
        setupViews(rootView);
        return rootView;
    }

    private ListView list;
    private ArrayList<String> dataList = new ArrayList<>();

    float mPreviousX = 0;
    float mPreviousY = 0;
    boolean mIsDown = false;
    boolean isHandling = false;

    private void setupViews(View view) {
        list = view.findViewById(R.id.fragment_swipe_list_list);
        createDataAdapter();
        adapter = new SwipeListAdapter(getContext(), dataList);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        list.setItemsCanFocus(false);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtContent = view.findViewById(R.id.item_swipe_list_txt_title);
                txtContent.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        float x = motionEvent.getX();
                        float y = motionEvent.getY();

                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                mIsDown = true;
                                break;
                            case MotionEvent.ACTION_MOVE:

                                float dx = x - mPreviousX;
                                float dy = y - mPreviousY;

                                if (dx < 0) {
                                    Toast.makeText(getContext(), "To left", Toast.LENGTH_SHORT).show();
                                }else if(dx >0){
                                    Toast.makeText(getContext(), "To right", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case MotionEvent.ACTION_UP:
                                mIsDown = false;
                                break;

                            case MotionEvent.ACTION_CANCEL:
                                System.out.println("Outer finish");
                        }

                        mPreviousX = x;
                        mPreviousY = y;
                        return true;
                    }
                });
            }
        });
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView txt = view.findViewById(R.id.item_swipe_list_txt_title);
//                Toast.makeText(getContext(), "T: "+txt.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
        Button btnMsg = view.findViewById(R.id.fragment_swipe_list_btn_msg);
        btnMsg.setOnClickListener(msgShow);
    }

    private View.OnClickListener msgShow = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getContext())) {
                //If the draw over permission is not available open the settings screen
                //to grant the permission.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getContext().getPackageName()));
                startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
            } else {
                initializeView();
            }
        }
    };

    private void createDataAdapter() {
        String sample = "Sample";
        for (int i = 0; i < 10; i++) {
            dataList.add(sample + i);
        }
    }

    private void initializeView() {
        getContext().startService(new Intent(getContext(), ChatHeadService.class));
        getActivity().finish();
    }
}
