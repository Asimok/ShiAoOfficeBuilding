package ShiAoOfficeBuilding.RoomList;

public class roomlistAdapterInfo {

    //封装roomlist 获取到的数据
    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getUsetypename() {
        return usetypename;
    }

    public void setUsetypename(String usetypename) {
        this.usetypename = usetypename;
    }

    private String RoomNumber,statusname,usetypename;
}
