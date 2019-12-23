package services.control;

import services.model.GT;
import services.model.PT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class PCGT {
    private List<GT> gts = new ArrayList<>();
    private List<PT> pts = new ArrayList<>();

    private LinkedList<GT> gtsLink = new LinkedList<>();
    private LinkedList<PT> ptsLink = new LinkedList<>();

    public static void main(String[] args) {

    }

    public List<GT> getGts() {
        return gts;
    }

    public void setGts(List<GT> gts) {
        this.gts = gts;
    }

    public List<PT> getPts() {
        return pts;
    }

    public void setPts(List<PT> pts) {
        this.pts = pts;
    }

    public LinkedList<GT> getGtsLink() {
        return gtsLink;
    }

    public void setGtsLink(LinkedList<GT> gtsLink) {
        this.gtsLink = gtsLink;
    }

    public LinkedList<PT> getPtsLink() {
        return ptsLink;
    }

    public void setPtsLink(LinkedList<PT> ptsLink) {
        this.ptsLink = ptsLink;
    }
}
