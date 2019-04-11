package ShiAoOfficeBuilding.importancePeople;

public class ImportantPointInfo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalType() {
        return PersonalType;
    }

    public void setPersonalType(String personalType) {
        PersonalType = personalType;
    }

    public String getClocktime() {
        return clocktime;
    }

    public void setClocktime(String clocktime) {
        this.clocktime = clocktime;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    private String PersonalType;
    private String clocktime;
    private String checktime;

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    private String idnum;
}
