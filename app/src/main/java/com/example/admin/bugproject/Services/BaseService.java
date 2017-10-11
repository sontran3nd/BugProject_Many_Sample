package com.example.admin.bugproject.Services;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Admin on 8/29/2017.
 */

public class BaseService extends AsyncHttpClient {

    public void sendRequest(String method, String url, RequestParams params, final BaseRequestHandle handler){
        if (method == "POST") {
            post(url, params, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    if (response != null){
                        handler.onRequestSuccess(response);
                    }
                    else {
                        handler.onRequestFailure(500);
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    JSONObject object = new JSONObject();
                    if (response != null)
                    {
                        try {
                            object.put("data",response);
                            handler.onRequestSuccess(response);
                        }
                        catch (Exception e){
//                        Utils.error(e.getMessage());
//                        result = new EComRequestResult(null);
                        }
                    }
                    else{
                        handler.onRequestFailure(500);
                    }
//                    callBack.onFinish(result);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                    EComRequestResult result = new EComRequestResult(null);
//                    result.msg = Utils.getStringValue(responseString);
//                    callBack.onFinish(result);
                    handler.onRequestFailure(500);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                    if (!processError(statusCode,throwable,errorResponse)){
//                        //do smt
//                        if(callBack != null) callBack.onFailure();
//                    }
                    handler.onRequestFailure(statusCode);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                    if (!processError(statusCode,throwable,responseString)){
//                        //do smt
//                        if(callBack != null) callBack.onFailure();
//                    }
                    handler.onRequestFailure(statusCode);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                    if (!processError(statusCode,throwable,errorResponse)){
//                        //do smt
//                        if(callBack != null) callBack.onFailure();
//                    }
                    if (errorResponse != null) {
                        Log.i("Base Service", errorResponse.toString());
                    }
                    handler.onRequestFailure(statusCode);
                }

                @Override
                public void onCancel() {
                    handler.onRequestCancel();
                }

                @Override
                public void onFinish() {
                    handler.onRequestFinish();

                }
            });
        }
        else if (method == "GET"){
            get(url, params, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    if (response != null){
//                        Utils.info(response.toString());
                    }
//                    callBack.onFinish(new EComRequestResult(response));
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    JSONObject object = new JSONObject();
//                    EComRequestResult result;
                    try {
                        object.put("data",response);
//                        result = new EComRequestResult(object);
                    }
                    catch (Exception e){
//                        Utils.error(e.getMessage());
//                        result = new EComRequestResult(null);
                    }
//                    callBack.onFinish(result);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                    EComRequestResult result = new EComRequestResult(null);
//                    result.msg = Utils.getStringValue(responseString);
//                    callBack.onFinish(result);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                    if (!processError(statusCode,throwable,errorResponse)){
//                        //do smt
//                        if(callBack != null) callBack.onFailure();
//                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                    if (!processError(statusCode,throwable,responseString)){
//                        //do smt
//                        if(callBack != null) callBack.onFailure();
//                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                    if (!processError(statusCode,throwable,errorResponse)){
//                        //do smt
//                        if(callBack != null) callBack.onFailure();
//                    }
                }
            });
        }
    }
}
