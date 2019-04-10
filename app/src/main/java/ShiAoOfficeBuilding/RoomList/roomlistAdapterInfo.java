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



    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getUseobjguid() {
        return useobjguid;
    }

    public void setUseobjguid(String useobjguid) {
        this.useobjguid = useobjguid;
    }
    private String RoomNumber;
    private String statusname;
    private String usetypename;
    private String companyname;
    private String companytype;
    private String useobjguid;
}
