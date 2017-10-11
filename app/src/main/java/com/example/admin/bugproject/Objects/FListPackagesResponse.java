package com.example.admin.bugproject.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 9/1/2017.
 */

public class FListPackagesResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("pkgs")
    @Expose
    private List<FPackage> pkgs;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FPackage> getPkgs() {
        return pkgs;
    }

    public void setPkgs(List<FPackage> pkgs) {
        this.pkgs = pkgs;
    }
}
