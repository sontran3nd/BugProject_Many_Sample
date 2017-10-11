package com.example.admin.bugproject.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.bugproject.Fragments.DrawImageFragment;
import com.example.admin.bugproject.Fragments.DrawableViewShow;
import com.example.admin.bugproject.Fragments.SharedPreferencesFragment;
import com.example.admin.bugproject.Fragments.SwipeListFragment;
import com.example.admin.bugproject.Fragments.SwipeLoadListView;
import com.example.admin.bugproject.Fragments.ViewUpFragment;
import com.example.admin.bugproject.R;
import com.example.admin.bugproject.Services.ChatHeadService;

public class DetailActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    private String[] mPlanetTitles = {"Main", "List", "View", "Draw Image", "Swipe view", "Draw View"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupViews();
    }

    private void setupViews() {
        frameLayout = (FrameLayout) findViewById(R.id.activity_detail_frame);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        DrawableViewShow initFragmentView = new DrawableViewShow();
        ft.replace(R.id.activity_detail_frame, initFragmentView);
        ft.commit();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                menuItemClicked(i);
            }
        });
    }

    private void menuItemClicked(int Position)
    {
        Fragment frag = null;
        switch (Position)
        {
            case 0:{
                mDrawerLayout.closeDrawers();
                frag = new SharedPreferencesFragment();
                break;
            }
            case 1:{
                mDrawerLayout.closeDrawers();
                frag = new SwipeListFragment();
                break;
            }
            case 2:{
                mDrawerLayout.closeDrawers();
                frag = new ViewUpFragment();
                break;
            }
            case 3:{
                mDrawerLayout.closeDrawers();
                frag = DrawImageFragment.instance(getParent());
                break;
            }
            case 4:{
                mDrawerLayout.closeDrawers();
                frag = new SwipeLoadListView();
                break;
            }
            case 5:{
                mDrawerLayout.closeDrawers();
                frag = new DrawableViewShow();
                break;
            }
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_detail_frame, frag);
        ft.commit();
    }
}
