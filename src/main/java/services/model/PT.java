package services.model;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class PT {
    private String name;
    private String note;

    public PT() {
    }

    public PT(String name, String note) {
        this.name = name;
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

    @Override
    public String toString() {
        return "PT{" +
                "name='" + name + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
