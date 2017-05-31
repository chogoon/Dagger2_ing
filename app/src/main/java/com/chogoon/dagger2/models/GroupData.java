package com.chogoon.dagger2.models;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by chogoon on 2017-05-31.
 */

public class GroupData {



    @SerializedName("ad_group_no")
    public int group_no;

    @SerializedName("ad_group_name")
    public String group_name;

    public List<ItemData> items;

    

}
