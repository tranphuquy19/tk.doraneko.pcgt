package services.control;

import commons.Constants;
import org.apache.poi.ss.usermodel.Row;
import services.excel.ExportDataToExcelUtil;
import services.excel.GetData;
import services.excel.ReadExcelUtil;
import services.model.PC;
import services.model.PT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class MasterControl {
    public static String excute(String filename, String workingDir) throws IOException {
        String filePath = workingDir + "\\" + filename;
        ReadExcelUtil readExcelUtil = new ReadExcelUtil(filePath);
        Iterator<Row> gtRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.GT_SHEET_NAME));
        Iterator<Row> ptRows = readExcelUtil.getRows(readExcelUtil.getSheetByName(Constants.PT_SHEET_NAME));
        PCGT pcgt = new PCGT();
        pcgt.setGts(GetData.getGts(gtRows));
        pcgt.setPts(GetData.getPts(ptRows));
        pcgt.phanCong();
        String toFilename = new Random().nextInt(10001) + "-done-" + filename;
        ExportDataToExcelUtil excelFile = new ExportDataToExcelUtil(
                workingDir + toFilename,
                (ArrayList<PC>) pcgt.getPcs(),
                pcgt.getTempGtsLinked(),
                (ArrayList<PT>) pcgt.getPts());
        excelFile.createFile();
        return toFilename;
    }
}
