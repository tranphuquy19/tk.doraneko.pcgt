package services.model;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class PT {
    private String name;
    private String address;
    private String note;

    public PT() {
    }

    public PT(String name, String address, String note) {
        this.name = name;
        this.address = address;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PT{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
