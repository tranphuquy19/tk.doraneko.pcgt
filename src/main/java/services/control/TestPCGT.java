package services.control;

import commons.Constants;
import org.apache.poi.ss.usermodel.Row;
import services.excel.ExportDataToExcelUtil;
import services.excel.GetData;
import services.excel.ReadExcelUtil;
import services.model.PC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class TestPCGT {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Tran Phu Quy\\Downloads\\tk.doraneko.pcgt\\test.xlsx";
        ReadExcelUtil readExcelUtil = new ReadExcelUtil(filePath);
        Iterator<Row> gtRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.GT_SHEET_NAME));
        Iterator<Row> ptRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.PT_SHEET_NAME));
        PCGT pcgt = new PCGT();
        pcgt.setGts(GetData.getGts(gtRows));
        pcgt.setPts(GetData.getPts(ptRows));
        pcgt.phanCong();
        ExportDataToExcelUtil excelFile = new ExportDataToExcelUtil("C:\\Users\\Tran Phu Quy\\Downloads\\tk.doraneko.pcgt\\test-all.xls", (ArrayList<PC>) pcgt.getPcs(), pcgt.getTempGtsLinked());
        int a = 0;

    }
}
