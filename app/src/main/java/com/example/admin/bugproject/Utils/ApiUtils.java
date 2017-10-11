package com.example.admin.bugproject.Utils;

import com.example.admin.bugproject.Interfaces.SOService;
import com.example.admin.bugproject.Services.RestClient;

/**
 * Created by Admin on 8/31/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://khachhang.giaohangtietkiem.vn";

    public static SOService getSOService() {
        return RestClient.getClient(BASE_URL).create(SOService.class);
    }

    public static  SOService getAnotherService(){
        return RestClient.getStringClient(BASE_URL).create(SOService.class);
    }

}
