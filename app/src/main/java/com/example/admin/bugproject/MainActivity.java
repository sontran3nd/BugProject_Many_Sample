package com.example.admin.bugproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.bugproject.Activities.DetailActivity;
import com.example.admin.bugproject.Fragments.PreviewPictureDialogFragment;
import com.example.admin.bugproject.Fragments.TakePictureDialogFragment;
import com.example.admin.bugproject.Interfaces.SOService;
import com.example.admin.bugproject.Objects.FListPackagesResponse;
import com.example.admin.bugproject.Objects.FPackage;
import com.example.admin.bugproject.Objects.FUser;
import com.example.admin.bugproject.Objects.User;
import com.example.admin.bugproject.Services.BaseRequestHandle;
import com.example.admin.bugproject.Services.BaseService;
import com.example.admin.bugproject.Services.ChatHeadService;
import com.example.admin.bugproject.Services.LockScreenService;
import com.example.admin.bugproject.Services.RestClient;
import com.example.admin.bugproject.Utils.ApiUtils;
import com.example.admin.bugproject.Views.popover.PopupItem;
import com.example.admin.bugproject.Views.popover.PopupOver;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up our Lockscreen
        makeFullScreen();
        startService(new Intent(this,LockScreenService.class));

        setContentView(R.layout.activity_main);

        Button btnTouch = (Button) findViewById(R.id.activity_main_btn_touch);
        btnTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupOver popupOver = new PopupOver(getApplicationContext());
                popupOver.setOnActionItemClickListener(new PopupOver.OnActionItemClickListener() {
                    @Override
                    public void onItemClick(PopupOver source, int pos, int actionId) {
                        PopupItem popupItem = popupOver.getActionItem(pos);
                        switch (actionId) {
                            case 0: {
                                startService(new Intent(getApplicationContext(), ChatHeadService.class));
                                break;
                            }
                            case 1: {
                                break;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                break;
                            }
                            case 4: {
                                break;
                            }
                        }
                    }
                });

                popupOver.addPopupItem(new PopupItem(0, "Tast 1", null));
                popupOver.addPopupItem(new PopupItem(1, "Tast 2", null));
                popupOver.addPopupItem(new PopupItem(2, "Tast 3", null));
                popupOver.addPopupItem(new PopupItem(3, "Tast 4", null));
                popupOver.addPopupItem(new PopupItem(4, "Tast 5", null));

                popupOver.show(view);
            }
        });

        Button log = (Button) findViewById(R.id.activity_main_btn_log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        Button send = (Button) findViewById(R.id.activity_main_btn_send);
        send.setOnClickListener(sendHandler);

        Button Rlog = (Button) findViewById(R.id.activity_main_btn_rlog);
        Rlog.setOnClickListener(rlogClick);

        Button rSend = (Button) findViewById(R.id.activity_main_btn_rget);
        rSend.setOnClickListener(rSendClick);

        Button btnPic = (Button) findViewById(R.id.activity_main_btn_pic);
        btnPic.setOnClickListener(takePic);

        Button btnPre = (Button) findViewById(R.id.activity_main_btn_preview);
        btnPre.setOnClickListener(preClick);

        Button newScreen = (Button) findViewById(R.id.main_activity_btn_new);
        newScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

        Button btnUn = (Button) findViewById(R.id.activity_main_un);
        btnUn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    private View.OnClickListener preClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PreviewPictureDialogFragment previewPictureDialogFragment = PreviewPictureDialogFragment.newInstance();
            previewPictureDialogFragment.show(getSupportFragmentManager(), "");
        }
    };

    private View.OnClickListener takePic = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TakePictureDialogFragment takePictureDialogFragment = TakePictureDialogFragment.newInstance();
            takePictureDialogFragment.show(getSupportFragmentManager(), "");
        }
    };

    private List<FPackage> listPkgs;
    private View.OnClickListener rSendClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ApiUtils.getAnotherService().getListPackage("PHPSESSID="+fUser.token, "lab2.giaohangtietkiem.vn", "/" ,"1").enqueue(new Callback<FListPackagesResponse>() {
                @Override
                public void onResponse(Call<FListPackagesResponse> call, Response<FListPackagesResponse> response) {
                    FListPackagesResponse flistPkgs = response.body();
                    listPkgs = flistPkgs.getPkgs();
                    System.out.println("====== Package =============");
                    for (FPackage pkg : listPkgs){
                        System.out.println("Pkg "+pkg.alias+": "+pkg.customer_fullname);
                    }
                }

                @Override
                public void onFailure(Call<FListPackagesResponse> call, Throwable t) {
                    System.out.println("Error: "+call.toString());
                }
            });
//            ApiUtils.getSOService().getListPackage("PHPSESSID="+fUser.token, "lab2.giaohangtietkiem.vn", "/" ,"1")
//                    .enqueue(new Callback<String>() {
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            System.out.println("Response: "+response.message());
//                            if (response.isSuccessful()) {
//                                System.out.println(response.body());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//                            System.out.println("Error: "+call.request().toString());
//                        }
//                    });
        }
    };

    private SOService mService;
    private FUser fUser;

    private View.OnClickListener rlogClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ApiUtils.getSOService()
                    .doLogin("nghiant@ghtk.vn", "ecom")
//                    .doLogin("quanph@giaohangtietkiem.vn", "babybaby1504")
                    .enqueue(new Callback<FUser>() {
                        @Override
                        public void onResponse(Call<FUser> call, Response<FUser> response) {
                            if (response.isSuccessful()) {
                                fUser = response.body();

                                SharedPreferences.Editor editor = getSharedPreferences("com.example.admin.bugproject", MODE_PRIVATE).edit();
                                editor.putString("token_service", fUser.service_token);
                                editor.putString("token_user", fUser.token);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                                Log.d("MainActivity", "posts loaded from API");
                            } else {
                                int statusCode = response.code();
                                // handle request errors depending on status code
                            }
                        }

                        @Override
                        public void onFailure(Call<FUser> call, Throwable t) {
                            Log.d("MainActivity", "error loading from API");

                        }
                    });
        }
    };

    private View.OnClickListener sendHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (user != null) {
                BaseService client = new BaseService();
                client.addHeader("isMobileApp", "1");

                BasicClientCookie newCookie = new BasicClientCookie("PHPSESSID", user.service_token);
                newCookie.setDomain("lab2.giaohangtietkiem.vn");
                newCookie.setPath("/");
                newCookie.setValue(user.token);
                PersistentCookieStore cookieStore = new PersistentCookieStore(getApplicationContext());
                cookieStore.clear();
                cookieStore.addCookie(newCookie);

                client.setCookieStore(cookieStore);

                RequestParams params = new RequestParams();
                params.put("page", "1");

                client.sendRequest("POST", "https://lab2.giaohangtietkiem.vn/khach-hang/danh-sach-don-hang", params, new BaseRequestHandle() {

                    public void onRequestSuccess(JSONObject response) {
                        try {
                            System.out.println("Data Feedback: " + response);
                            //loginCount = 0;
//                    if (response != null && response.has(EComConstant.key_response_status) && response.getString("status").equals("OK")) {
//
//                        EComUser.getInstance().setEcomUserInfo(username_, password_, response.getString(EComConstant.key_response_code), response.getString(EComConstant.key_response_email), response.getString(EComConstant.key_response_first_address), response.getString(EComConstant.key_response_last_address), response.getString(EComConstant.key_response_id), response.getString(EComConstant.key_response_tel), response.getString(EComConstant.key_response_service_token), response.getString(EComConstant.key_response_shop_token), response.getString(EComConstant.key_response_token), response.getString(EComConstant.key_response_name), true);
//
//                        if (handler != null) {
//                            handler.onRequestSuccess();
//                        }
//                    } else {
//                        if (handler != null) {
//                            handler.onRequestFailure();
//                        }
//                    }
                        } catch (Exception e) {
//                    Log.e(TAG, e.getMessage());
//                    Log.e(TAG, response.toString());
//                    if (handler != null) {
//                        handler.onRequestFailure();
//                    }
                        }

                    }

                    public void onRequestFailure(int status) {
                        System.out.println("Error get: " + status);
//                onHttpFailure(status, ECOM_LOGIN_PATH);
//                if (handler != null) {
//                    handler.onRequestFailure();
//                }
                    }

                    public void onRequestFinish() {
//                loginClient = null;
                    }
                });
            }
        }
    };

    private User user;

    private void sendRequest() {
        BaseService client = new BaseService();
        client.addHeader("isMobileApp", "1");
        client.setEnableRedirects(false);

        RequestParams params = new RequestParams();
        params.put("data[Shop][email]", "nghiant@ghtk.vn");
        params.put("data[Shop][password]", "ecom");

        client.sendRequest("POST", "https://lab2.giaohangtietkiem.vn/khach-hang/dang-nhap", params, new BaseRequestHandle() {

            public void onRequestSuccess(JSONObject response) {
                try {
                    user = new User(response);
                    System.out.println(user.toString());
                    //loginCount = 0;
//                    if (response != null && response.has(EComConstant.key_response_status) && response.getString("status").equals("OK")) {
//
//                        EComUser.getInstance().setEcomUserInfo(username_, password_, response.getString(EComConstant.key_response_code), response.getString(EComConstant.key_response_email), response.getString(EComConstant.key_response_first_address), response.getString(EComConstant.key_response_last_address), response.getString(EComConstant.key_response_id), response.getString(EComConstant.key_response_tel), response.getString(EComConstant.key_response_service_token), response.getString(EComConstant.key_response_shop_token), response.getString(EComConstant.key_response_token), response.getString(EComConstant.key_response_name), true);
//
//                        if (handler != null) {
//                            handler.onRequestSuccess();
//                        }
//                    } else {
//                        if (handler != null) {
//                            handler.onRequestFailure();
//                        }
//                    }
                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage());
//                    Log.e(TAG, response.toString());
//                    if (handler != null) {
//                        handler.onRequestFailure();
//                    }
                }

            }

            public void onRequestFailure(int status) {
//                onHttpFailure(status, ECOM_LOGIN_PATH);
//                if (handler != null) {
//                    handler.onRequestFailure();
//                }
            }

            public void onRequestFinish() {
//                loginClient = null;
            }
        });
    }

}

