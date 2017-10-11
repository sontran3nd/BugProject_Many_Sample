package com.example.admin.bugproject.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/31/2017.
 */

public class FUser {

    @SerializedName("id")
    @Expose
    public String id = "";

    @SerializedName("name")
    @Expose
    public String name = "";

    @SerializedName("email")
    @Expose
    public String email = "";

    @SerializedName("tel")
    @Expose
    public String tel = "";

    @SerializedName("code")
    @Expose
    public String code = "";

    @SerializedName("first_address")
    @Expose
    public String first_address = "";

    @SerializedName("last_address")
    @Expose
    public String last_address = "";

    @SerializedName("station_id")
    @Expose
    public Integer station_id = 0;

    @SerializedName("service_token")
    @Expose
    public String service_token = "";

    @SerializedName("token")
    @Expose
    public String token = "";

    @SerializedName("status")
    @Expose
    public String status = "";

    @SerializedName("shop_token")
    @Expose
    public String shop_token = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirst_address() {
        return first_address;
    }

    public void setFirst_address(String first_address) {
        this.first_address = first_address;
    }

    public String getLast_address() {
        return last_address;
    }

    public void setLast_address(String last_address) {
        this.last_address = last_address;
    }

    public Integer getStation_id() {
        return station_id;
    }

    public void setStation_id(Integer station_id) {
        this.station_id = station_id;
    }

    public String getService_token() {
        return service_token;
    }

    public void setService_token(String service_token) {
        this.service_token = service_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShop_token() {
        return shop_token;
    }

    public void setShop_token(String shop_token) {
        this.shop_token = shop_token;
    }
}
