package iman.reserve.hotel.model;

import com.google.gson.annotations.SerializedName;

public class MyReserve {
    @SerializedName("r_id")
    private String r_id;
    @SerializedName("r_chkindt")
    private String r_chkindt;
    @SerializedName("r_chkoutdt")
    private String r_chkoutdt;
    @SerializedName("r_rooms")
    private String r_rooms;
    @SerializedName("r_type")
    private String r_type;
    @SerializedName("hotel")
    private String hotelID;
    @SerializedName("h_id")
    private String h_id;
    @SerializedName("city")
    private String city;
    @SerializedName("name")
    private String name;
    @SerializedName("numOfStars")
    private String numOfStars;
    @SerializedName("desc")
    private String desc;
    @SerializedName("room_number")
    private String room_number;


    public MyReserve(String r_id, String r_chkindt, String r_chkoutdt, String r_rooms, String r_type, String name, String room_number) {
        this.r_id = r_id;
        this.r_chkindt = r_chkindt;
        this.r_chkoutdt = r_chkoutdt;
        this.r_rooms = r_rooms;
        this.r_type = r_type;
        this.name = name;
        this.room_number = room_number;
    }


    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }


    public String getR_chkindt() {
        return r_chkindt;
    }

    public void setR_chkindt(String r_chkindt ) {
        this.r_chkindt = r_chkindt;
    }


    public String getR_chkoutdt() {
        return r_chkoutdt;
    }

    public void setR_chkoutdt(String r_chkoutdt) {
        this.r_chkoutdt = r_chkoutdt;
    }


    public String getR_rooms() {
        return r_rooms;
    }

    public void setR_rooms(String  r_rooms) { this.r_rooms = r_rooms; }


    public String getR_type() {
        return r_type;
    }

    public void setR_type(String  r_type) { this.r_type = r_type; }


    public String getName() {
        return name;
    }

    public void setName(String  name) { this.name = name; }


    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String  room_number) { this.room_number = room_number; }

}
