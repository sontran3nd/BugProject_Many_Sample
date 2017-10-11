package com.example.admin.bugproject.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/1/2017.
 */

public class FPackage {

    @SerializedName("id")
    @Expose
    public String id = "";

    @SerializedName("shop_id")
    @Expose
    public String shop_id = "";

    @SerializedName("shop_code")
    @Expose
    public String shop_code = "";

    @SerializedName("client_id")
    @Expose
    public String client_id = "";

    @SerializedName("client_package_no")
    @Expose
    public String client_package_no = "";

    @SerializedName("client_is_cancel")
    @Expose
    public String client_is_cancel = "";

    @SerializedName("courier_id")
    @Expose
    public String courier_id = "";

    @SerializedName("red_bill_code")
    @Expose
    public String red_bill_code = "";

    @SerializedName("alias")
    @Expose
    public String alias = "";

    @SerializedName("package_status_id")
    @Expose
    public String package_status_id = "";

    @SerializedName("pick_fullname")
    @Expose
    public String pick_fullname = "";

    @SerializedName("created_username")
    @Expose
    public String created_username = "";

    @SerializedName("customer_tel")
    @Expose
    public String customer_tel = "";

    @SerializedName("customer_first_address")
    @Expose
    public String customer_first_address ="";

    @SerializedName("customer_last_address")
    @Expose
    public String customer_last_address = "";

    @SerializedName("order")
    @Expose
    public String order = "";

    @SerializedName("desc")
    @Expose
    public String desc = "";

    @SerializedName("message")
    @Expose
    public String message = "";

    @SerializedName("is_express")
    @Expose
    public String is_express = "";

    @SerializedName("is_freeship")
    @Expose
    public String is_freeship = "0";

    @SerializedName("is_cancel")
    @Expose
    public String is_cancel = "";

    @SerializedName("is_exported")
    @Expose
    public String is_exported = "";

    @SerializedName("is_refunded")
    @Expose
    public String is_refunded = "";

    @SerializedName("deliver_cart_alias")
    @Expose
    public String deliver_cart_alias = "";

    @SerializedName("is_evicted")
    @Expose
    public String is_evicted = "";

    @SerializedName("is_distributed")
    @Expose
    public String is_distributed = "";

    @SerializedName("area_relative")
    @Expose
    public String area_relative = "";

    @SerializedName("return_status")
    @Expose
    public String return_status = "";

    @SerializedName("courier_status")
    @Expose
    public String courier_status = "";

    @SerializedName("transfer_status")
    @Expose
    public String transfer_status = "";

    @SerializedName("is_valid")
    @Expose
    public String is_valid = "";

    @SerializedName("need_confirm")
    @Expose
    public String need_confirm = "";

    @SerializedName("customer_code")
    @Expose
    public String customer_code = "";

    @SerializedName("customer_fullname")
    @Expose
    public String customer_fullname = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_package_no() {
        return client_package_no;
    }

    public void setClient_package_no(String client_package_no) {
        this.client_package_no = client_package_no;
    }

    public String getClient_is_cancel() {
        return client_is_cancel;
    }

    public void setClient_is_cancel(String client_is_cancel) {
        this.client_is_cancel = client_is_cancel;
    }

    public String getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id = courier_id;
    }

    public String getRed_bill_code() {
        return red_bill_code;
    }

    public void setRed_bill_code(String red_bill_code) {
        this.red_bill_code = red_bill_code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPackage_status_id() {
        return package_status_id;
    }

    public void setPackage_status_id(String package_status_id) {
        this.package_status_id = package_status_id;
    }

    public String getIs_express() {
        return is_express;
    }

    public void setIs_express(String is_express) {
        this.is_express = is_express;
    }

    public String getIs_freeship() {
        return is_freeship;
    }

    public void setIs_freeship(String is_freeship) {
        this.is_freeship = is_freeship;
    }

    public String getIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(String is_cancel) {
        this.is_cancel = is_cancel;
    }

    public String getIs_exported() {
        return is_exported;
    }

    public void setIs_exported(String is_exported) {
        this.is_exported = is_exported;
    }

    public String getIs_refunded() {
        return is_refunded;
    }

    public void setIs_refunded(String is_refunded) {
        this.is_refunded = is_refunded;
    }

    public String getIs_evicted() {
        return is_evicted;
    }

    public void setIs_evicted(String is_evicted) {
        this.is_evicted = is_evicted;
    }

    public String getIs_distributed() {
        return is_distributed;
    }

    public void setIs_distributed(String is_distributed) {
        this.is_distributed = is_distributed;
    }

    public String getArea_relative() {
        return area_relative;
    }

    public void setArea_relative(String area_relative) {
        this.area_relative = area_relative;
    }

    public String getReturn_status() {
        return return_status;
    }

    public void setReturn_status(String return_status) {
        this.return_status = return_status;
    }

    public String getCourier_status() {
        return courier_status;
    }

    public void setCourier_status(String courier_status) {
        this.courier_status = courier_status;
    }

    public String getTransfer_status() {
        return transfer_status;
    }

    public void setTransfer_status(String transfer_status) {
        this.transfer_status = transfer_status;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getNeed_confirm() {
        return need_confirm;
    }

    public void setNeed_confirm(String need_confirm) {
        this.need_confirm = need_confirm;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_fullname() {
        return customer_fullname;
    }

    public void setCustomer_fullname(String customer_fullname) {
        this.customer_fullname = customer_fullname;
    }
}
