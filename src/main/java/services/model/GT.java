package services.model;

import java.util.ArrayList;
import java.util.List;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class GT {
    private int cardIndex;
    private String name;
    private String dob;
    private String workplace;
    private String note;

    private List<String> gts = new ArrayList<>();
    private List<String> pts = new ArrayList<>();


    public GT(){}

    public GT(int cardIndex, String name, String dob, String workplace, String note) {
        this.cardIndex = cardIndex;
        this.name = name;
        this.dob = dob;
        this.workplace = workplace;
        this.note = note;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWorkplace() {
        return workplace;
    }

    public List<String> getGts() {
        return gts;
    }

    public void setGts(List<String> gts) {
        this.gts = gts;
    }

    public List<String> getPts() {
        return pts;
    }

    public void setPts(List<String> pts) {
        this.pts = pts;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void addGt(String name) {
        this.gts.add(name);
    }

    public boolean containsGt(String name) {
        return this.gts.contains(name);
    }

    public void addPts(String name){
        this.pts.add(name);
    }

    public boolean containsPt(String name){
        return this.pts.contains(name);
    }

    @Override
    public String toString() {
        return "GT{" +
                "cardIndex=" + cardIndex +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", workplace='" + workplace + '\'' +
                ", note='" + note + '\'' +
                ", gts=" + gts +
                ", pts=" + pts +
                '}';
    }
}
