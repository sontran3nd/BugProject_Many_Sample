package com.example.admin.bugproject.Interfaces;

import com.example.admin.bugproject.Objects.FAddress;
import com.example.admin.bugproject.Objects.FListPackagesResponse;
import com.example.admin.bugproject.Objects.FPackage;
import com.example.admin.bugproject.Objects.FUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Admin on 8/31/2017.
 */

public interface SOService {
    @Headers("isMobileApp: 1")
    @FormUrlEncoded
    @POST("/khach-hang/dang-nhap")
    Call<FUser> doLogin(
            @Field("data[Shop][email]") String email,
            @Field("data[Shop][password]") String password
    );


    @Headers("isMobileApp: 1")
    @FormUrlEncoded
    @POST("/khach-hang/danh-sach-don-hang")

    Call<FListPackagesResponse> getListPackage(
            @Header("Cookie") String cookie,
            @Header("domain") String domain,
            @Header("path") String path,
            @Field("page") String page
    );


    @Headers("isMobileApp: 1")
    @GET("/khach-hang/services/list-dia-chi")
    Call<List<FAddress>> getListAddress(
            @Header("Cookie") String cookie,
            @Header("domain") String domain,
            @Header("path") String path,
            @Header("parentId") String pId
    );
}
