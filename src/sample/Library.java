package sample;

public class Library {
    private int id;
    private String buildingName;
    private Number contact;

    public Library() {
    }

    public Library(int id, String buildingName, Number contact) {
        this.id = id;
        this.buildingName = buildingName;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Number getContact() {
        return contact;
    }

    public void setContact(Number contact) {
        this.contact = contact;
    }
}
