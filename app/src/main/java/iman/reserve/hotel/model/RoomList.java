
package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RoomList {
    @SerializedName("roomList")
    private ArrayList<Room> roomList;

    public ArrayList<Room> getRoomArrayList() {
        return roomList;
    }

    public void setRoomArrayList(ArrayList<Room> roomArrayList) {
        this.roomList = roomArrayList;
    }
}
