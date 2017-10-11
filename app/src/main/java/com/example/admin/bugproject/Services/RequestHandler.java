package com.example.admin.bugproject.Services;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Admin on 8/30/2017.
 */

public interface RequestHandler {
    void onRequestSuccess();
    void onRequestSuccess(JSONObject obj);
    void onRequestSuccess(JSONArray obj);
    void onRequestFailure(int status);
    void onRequestCancel();
    void onRequestFinish();
    void onRequestFailure(String failureMessage);
}
