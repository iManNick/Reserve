
package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyReserveList {
    @SerializedName("myreserveList")
    private ArrayList<MyReserve> myreserveList;

    public ArrayList<MyReserve> getMyreserveArrayList() {
        return myreserveList;
    }

    public void setMyreserveArrayList(ArrayList<MyReserve> myreserveArrayList) {
        this.myreserveList = myreserveArrayList;
    }
}
