package com.chogoon.dagger2.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chogoon on 2017-05-31.
 */

public class ItemData  implements Comparable<ItemData>{

    @SerializedName("ano")
    public int id;

    @SerializedName("imgpath")
    public String imagePath;

    @SerializedName("aname")
    public String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemData itemData = (ItemData) o;
        return id == itemData.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(@NonNull ItemData o) {
        if(id < o.id){
            return 1;
        }else if (id > o.id) return -1;
        return 0;
    }
}
