package sample;

public class Staff {
    private int id;
    private String staffName;

    public Staff(int id, String staffName) {
        this.id = id;
        this.staffName = staffName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
