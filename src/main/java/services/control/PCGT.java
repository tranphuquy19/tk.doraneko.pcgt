package services.control;

import services.model.GT;
import services.model.PC;
import services.model.PT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class PCGT {
    private List<GT> gts = new ArrayList<>();
    private List<PT> pts = new ArrayList<>();
    private List<PC> pcs = new ArrayList<>();

    private LinkedList<GT> gtsLinked = new LinkedList<>();
    private LinkedList<PT> ptsLinked = new LinkedList<>();
    private LinkedList<GT> tempGtsLinked = new LinkedList<>();

    public <T> List<T> convertToLinkedList(List<T> list) {
        List<T> lL = new LinkedList<>(list);
        return lL;
    }

    public boolean checkQuantity() {
        return true;
    }

    private long giaiThua(int n) {
        long count = 1;
        for (int i = 2; i <= n; i++) {
            count *= i;
        }
        return count;
    }

    private long cKn(int k, int n) {
        return giaiThua(n) / (giaiThua(k) * giaiThua(n - k));
    }

    public long getLoopCount() {
        long value = cKn(2, gtsLinked.size());
        return value <= 0 ? Long.MAX_VALUE : value;
    }

    public static boolean checkPairGT(GT gt1, GT gt2) {
        if (gt1.getGts().contains(gt2.getName())) return false;
        if (gt1.getWorkplace().equals(gt2.getWorkplace())) return false;
        return true;
    }

    public void phanCong() {
        if (checkQuantity()) {
            long count = 1;
            gtsLinked = (LinkedList<GT>) convertToLinkedList(gts);
            ptsLinked = (LinkedList<PT>) convertToLinkedList(pts);
            Collections.shuffle(gtsLinked);
            long loops = getLoopCount();
            while (count <= loops) {
                System.out.println(count + " " + ptsLinked.size() + " " + gtsLinked.size());
                if (gtsLinked.size() % 2 != 0) {
                    tempGtsLinked.add(gtsLinked.getFirst());
                    gtsLinked.removeFirst();
                }
                if (ptsLinked.size() == 0) break;
                if (gtsLinked.size() == 0) {
                    Collections.shuffle(tempGtsLinked);
                    gtsLinked.addAll(tempGtsLinked);
                    tempGtsLinked = new LinkedList<>();
                }
                GT _gt1 = gtsLinked.getFirst();
                GT _gt2 = gtsLinked.getLast();
                if (checkPairGT(_gt1, _gt2) && checkQuantity()) {
                    _gt1.addGt(_gt2.getName());
                    _gt2.addGt(_gt1.getName());
                    pcs.add(new PC(_gt1, _gt2, ptsLinked.getFirst()));
                    tempGtsLinked.add(_gt1);
                    tempGtsLinked.add(_gt2);

                    gtsLinked.removeFirst();
                    gtsLinked.removeLast();
                    ptsLinked.removeFirst();
                } else {
//                    gtsLinked.addLast(gtsLinked.getFirst());
//                    gtsLinked.removeFirst();
                    tempGtsLinked.add(_gt1);
                    tempGtsLinked.add(_gt2);
                    gtsLinked.removeFirst();
                    gtsLinked.removeLast();
                }
                count++;
            }
        }
        int a = 0;
    }

//    public static void main(String[] args) throws IOException {
//        String filePath = "C:\\Users\\Tran Phu Quy\\Downloads\\tk.doraneko.pcgt\\test.xlsx";
//        ReadExcelUtil readExcelUtil = new ReadExcelUtil(filePath);
//        Iterator<Row> gtRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.GT_SHEET_NAME));
//        Iterator<Row> ptRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.PT_SHEET_NAME));
//        gts = GetData.getGts(gtRows);
//        pts = GetData.getPts(ptRows);
//        phanCong();
//        int a = 0;
//
//    }

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
        return gtsLinked;
    }

    public void setGtsLink(LinkedList<GT> gtsLink) {
        this.gtsLinked = gtsLink;
    }

    public LinkedList<PT> getPtsLink() {
        return ptsLinked;
    }

    public void setPtsLink(LinkedList<PT> ptsLink) {
        this.ptsLinked = ptsLink;
    }

    public List<PC> getPcs() {
        return this.pcs;
    }

    public void setPcs(List<PC> pcs) {
        this.pcs = pcs;
    }

    public LinkedList<GT> getGtsLinked() {
        return gtsLinked;
    }

    public void setGtsLinked(LinkedList<GT> gtsLinked) {
        this.gtsLinked = gtsLinked;
    }

    public LinkedList<PT> getPtsLinked() {
        return ptsLinked;
    }

    public void setPtsLinked(LinkedList<PT> ptsLinked) {
        this.ptsLinked = ptsLinked;
    }

    public LinkedList<GT> getTempGtsLinked() {
        return tempGtsLinked;
    }

    public void setTempGtsLinked(LinkedList<GT> tempGtsLinked) {
        this.tempGtsLinked = tempGtsLinked;
    }

}
