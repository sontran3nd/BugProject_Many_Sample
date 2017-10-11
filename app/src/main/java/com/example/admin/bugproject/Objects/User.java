package com.example.admin.bugproject.Objects;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by Admin on 8/30/2017.
 */

public class User extends BaseObject{
    public String id = "";
    public String name = "";
    public String email = "";
    public String tel = "";
    public String code = "";
    public String first_address = "";
    public String last_address = "";
    public int station_id = 0;
    public String service_token = "";
    public String token = "";
    public String status = "";
    public String shop_token = "";

    public User(JSONObject object) {
        super(object);

        id = getStringValue("id");
        name = getStringValue("name");
        email = getStringValue("email");
        tel = getStringValue("tel");
        code = getStringValue("code");
        first_address = getStringValue("first_address");
        last_address = getStringValue("last_address");
        station_id = getIntegerValue("station_id");
        service_token = getStringValue("service_token");
        token = getStringValue("token");
        status = getStringValue("status");
        shop_token = getStringValue("shop_token");
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", code='" + code + '\'' +
                ", first_address='" + first_address + '\'' +
                ", last_address='" + last_address + '\'' +
                ", station_id=" + station_id +
                ", service_token='" + service_token + '\'' +
                ", token='" + token + '\'' +
                ", status='" + status + '\'' +
                ", shop_token='" + shop_token + '\'' +
                '}';
    }
}
