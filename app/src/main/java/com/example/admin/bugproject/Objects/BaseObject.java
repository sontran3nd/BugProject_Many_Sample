package com.example.admin.bugproject.Objects;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by Admin on 8/30/2017.
 */

public abstract class BaseObject {

    protected JSONObject jsonObject;

    public BaseObject(JSONObject object){
        this.jsonObject = object;
    }

    protected String getStringValue(String key){
        String result = "";
        try{
            if (jsonObject.has(key) && !jsonObject.isNull(key))
            {
                result = jsonObject.getString(key);
            }
        }
        catch (Exception e){
            System.out.println(key + ": Error "+e.getMessage());
        }
        return result;
    }

    protected int getIntegerValue(String key){
        int result = 0;
        try{
            if (jsonObject.has(key) && !jsonObject.isNull(key))
            {
                result = jsonObject.getInt(key);
            }
        }
        catch (Exception e){
            System.out.println(key + ": Error "+e.getMessage());
        }
        return result;
    }

    protected boolean getBooleanValue(String key){
        boolean result = false;
        try{
            if (jsonObject.has(key) && !jsonObject.isNull(key))
            {
                result = jsonObject.getBoolean(key);
            }
        }
        catch (Exception e){
            System.out.println(key + ": Error "+e.getMessage());
        }
        return result;
    }
}
