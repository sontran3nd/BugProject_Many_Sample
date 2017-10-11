package com.example.admin.bugproject.Services;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Admin on 8/30/2017.
 */

public class BaseRequestHandle implements RequestHandler{
    @Override
    public void onRequestSuccess() {

    }

    @Override
    public void onRequestSuccess(JSONObject obj) {

    }

    @Override
    public void onRequestSuccess(JSONArray obj) {

    }

    @Override
    public void onRequestFailure(int status) {

    }

    @Override
    public void onRequestCancel() {

    }

    @Override
    public void onRequestFinish() {

    }

    @Override
    public void onRequestFailure(String failureMessage) {

    }
}
