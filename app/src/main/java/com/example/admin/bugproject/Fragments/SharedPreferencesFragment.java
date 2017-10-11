package com.example.admin.bugproject.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.bugproject.Objects.FAddress;
import com.example.admin.bugproject.R;
import com.example.admin.bugproject.Services.BaseRequestHandle;
import com.example.admin.bugproject.Services.BaseService;
import com.example.admin.bugproject.Utils.ApiUtils;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 9/11/2017.
 */

public class SharedPreferencesFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sharedpreferences, container, false);
        setupViews(rootView);
        return rootView;
    }

    private EditText edt_id;

    private void setupViews(View view) {
        Button btnGetData = view.findViewById(R.id.fragment_sharedpreferences_btn_getdata);
        btnGetData.setOnClickListener(dataGet);

        Button btnGet = view.findViewById(R.id.fragment_sharedpreferences_btn_get);
        btnGet.setOnClickListener(datPreferences);

        edt_id = view.findViewById(R.id.fragment_sharedpreferences_edt_idaddress);
    }

    private View.OnClickListener datPreferences = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String id = edt_id.getText().toString().trim();
            if (id != null && id.length() > 0) {
                SharedPreferences editor = getActivity().getSharedPreferences("com.example.admin.bugproject", getContext().MODE_PRIVATE);
                String region = editor.getString("ad"+id, null);
                Toast.makeText(getContext(), "Region: " + region, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener dataGet = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences prefs = getActivity().getSharedPreferences("com.example.admin.bugproject", getContext().MODE_PRIVATE);
            String token = prefs.getString("token_user", null);

            if (token != null) {
                ApiUtils.getAnotherService().getListAddress("PHPSESSID=" + token, "lab2.giaohangtietkiem.vn", "/", "0").enqueue(new Callback<List<FAddress>>() {
                    @Override
                    public void onResponse(Call<List<FAddress>> call, Response<List<FAddress>> response) {
                        List<FAddress> listAddress = response.body();
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("com.example.admin.bugproject", getContext().MODE_PRIVATE).edit();
                        for (FAddress address : listAddress) {
                            editor.putString("ad" + address.getId(), address.getRegion() + " - " + address.getName());
                        }
                        editor.apply();
                        Toast.makeText(getContext(), "Save Data Success", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor edt = getActivity().getSharedPreferences("com.example.admin.bugproject", getContext().MODE_PRIVATE).edit();
                        edt.putString("ad126", "TP. HCM - CT");
                        edt.apply();
                    }

                    @Override
                    public void onFailure(Call<List<FAddress>> call, Throwable t) {
                        System.out.println("Error: " + call.toString());
                    }
                });
            }
//            getListAddress();
        }
    };

    private void getListAddress() {
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.admin.bugproject", getContext().MODE_PRIVATE);
        String service_token = prefs.getString("token_service", null);
        String user_token = prefs.getString("token_user", null);

        BaseService client = new BaseService();
        client.addHeader("isMobileApp", "1");

        BasicClientCookie newCookie = new BasicClientCookie("PHPSESSID", service_token);
        newCookie.setDomain("lab2.giaohangtietkiem.vn");
        newCookie.setPath("/");
        newCookie.setValue(user_token);
        PersistentCookieStore cookieStore = new PersistentCookieStore(getContext());
        cookieStore.clear();
        cookieStore.addCookie(newCookie);

        client.setCookieStore(cookieStore);

        RequestParams params = new RequestParams();
        params.put("page", "1");

        client.sendRequest("POST", "https://lab2.giaohangtietkiem.vn/khach-hang/danh-sach-don-hang", params, new BaseRequestHandle() {

            public void onRequestSuccess(JSONObject response) {
                try {
                    System.out.println("Data Feedback: " + response);
                } catch (Exception e) {
                }

            }

            public void onRequestFailure(int status) {
                System.out.println("Error get: " + status);
            }

            public void onRequestFinish() {
            }
        });
    }
}
