package services.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/23/2019
 */
public class ReadExcelUtil {
    private String filePath;

    private XSSFWorkbook book;

    public ReadExcelUtil(String filePath) throws IOException {
        this.filePath = filePath;
        this.book = new XSSFWorkbook(new FileInputStream(new File(this.filePath)));
    }

    public XSSFSheet getSheetByIndex(int index) {
        return this.book.getSheetAt(index);
    }

    public XSSFSheet getSheetByName(String sheetName) {
        return this.book.getSheet(sheetName);
    }

    public Iterator<Row> getRows(XSSFSheet sheet) {
        return sheet.iterator();
    }

    /**
     * Test
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Tran Phu Quy\\Downloads\\tk.doraneko.pcgt\\test.xlsx";
        ReadExcelUtil readExcelUtil = new ReadExcelUtil(filePath);
        Iterator<Row> gtRows = readExcelUtil.getRows(readExcelUtil.getSheetByName("GT"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Tran Phu Quy\\Downloads\\tk.doraneko.pcgt\\test.txt"), StandardCharsets.UTF_8));
        while (gtRows.hasNext()) {
            Row row = gtRows.next();
            Iterator<Cell> iCell = row.iterator();

            while (iCell.hasNext()) {
                Cell cell = iCell.next();
                String s = CellUtil.getCellValue(cell);
                bw.write(s);
                System.out.println(cell.getColumnIndex());
            }
        }
        bw.close();
    }
}
