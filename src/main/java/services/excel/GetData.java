package services.excel;

import commons.Constants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import services.model.GT;
import services.model.PT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class GetData {

    private static GT getGt(Iterator<Cell> gtCells) {
        GT gt = new GT();
        while (gtCells.hasNext()) {
            Cell cell = gtCells.next();
            switch (cell.getColumnIndex()) {
                case Constants.GT_CARD_INDEX:
                    try {
                        gt.setCardIndex(Integer.parseInt(CellUtil.getCellValue(cell)));
                    } catch (NumberFormatException e) {
                        gt.setCardIndex((int) Double.parseDouble(CellUtil.getCellValue(cell)));
                    }
                    break;
                case Constants.GT_NAME:
                    gt.setName(CellUtil.getCellValue(cell));
                    break;
                case Constants.GT_DOB:
                    gt.setDob(CellUtil.getCellValue(cell));
                    break;
                case Constants.GT_WORKPLACE:
                    gt.setWorkplace(CellUtil.getCellValue(cell));
                    break;
                case Constants.GT_NOTES:
                    gt.setNote(CellUtil.getCellValue(cell));
                    break;
            }
        }
        return gt;
    }

    public static ArrayList<GT> getGts(Iterator<Row> gtRows) {
        ArrayList<GT> gts = new ArrayList<>();
        gtRows.next();// header
        while (gtRows.hasNext()) {
            Row row = gtRows.next();
            gts.add(getGt(row.iterator()));
        }
        return gts;
    }

    public static ArrayList<PT> getPts(Iterator<Row> ptRows) {
        ArrayList<PT> pts = new ArrayList<>();
        ptRows.next();
        while (ptRows.hasNext()) {
            Row row = ptRows.next();
            pts.add(getPt(row.iterator()));
        }
        return pts;
    }

    private static PT getPt(Iterator<Cell> ptCells) {
        PT pt = new PT();
        while(ptCells.hasNext()){
            Cell cell = ptCells.next();
            switch (cell.getColumnIndex()){
                case Constants.PT_NAME:
                    pt.setName(CellUtil.getCellValue(cell));
                    break;
                case Constants.PT_NOTES:
                    pt.setNote(CellUtil.getCellValue(cell));
                    break;
            }
        }
        return pt;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Tran Phu Quy\\Downloads\\tk.doraneko.pcgt\\test.xlsx";
        ReadExcelUtil readExcelUtil = new ReadExcelUtil(filePath);
        Iterator<Row> gtRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.GT_SHEET_NAME));
        Iterator<Row> ptRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.PT_SHEET_NAME));
        ArrayList<GT> gts = getGts(gtRows);
        ArrayList<PT> pts = getPts(ptRows);
        int a = 0;
    }
}
