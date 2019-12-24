package services.excel;

import commons.Constants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.model.GT;
import services.model.PC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class ExportDataToExcelUtil {
    private String filePath;
    private List<PC> pcs = new ArrayList<>();
    private LinkedList<GT> gtHls = new LinkedList<>();

    public ExportDataToExcelUtil(){}

    /**
     *
     * @param filePath filePath
     * @param pcs pcs
     * @param gtHls gtHls
     */
    public ExportDataToExcelUtil(String filePath, ArrayList<PC> pcs, LinkedList<GT> gtHls) {
        this.filePath = filePath;
        this.pcs = pcs;
        this.gtHls = gtHls;
    }

    public void createFile() {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet pcSheet = book.createSheet("PC");

        int rowIndex = 0;
        Cell cell;
        Row row;

        row  = pcSheet.createRow(rowIndex);

        cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
        cell.setCellValue("TT");
        cell = row.createCell(Constants.PC_CARD_INDEX, CellType.STRING);
        cell.setCellValue("Số thẻ");
        cell = row.createCell(Constants.PC_NAME, CellType.STRING);
        cell.setCellValue("Ho Tên");
        cell = row.createCell(Constants.PC_DOB, CellType.STRING);
        cell.setCellValue("Ngày sinh");
        cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
        cell.setCellValue("Don Vi Cong Tac");
        cell = row.createCell(Constants.PC_PT, CellType.STRING);
        cell.setCellValue("Phòng thi");
        cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
        cell.setCellValue("Chức vụ");
        cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
        cell.setCellValue("Ghi Chú");

        for(PC pcItem : pcs){
            row = pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex + 1);
            cell = row.createCell(Constants.PC_CARD_INDEX, CellType.NUMERIC);
            cell.setCellValue(pcItem.getGt1().getCardIndex());
            cell = row.createCell(Constants.PC_NAME, CellType.STRING);
            cell.setCellValue(pcItem.getGt1().getName());
            cell = row.createCell(Constants.PC_DOB, CellType.STRING);
            cell.setCellValue(pcItem.getGt1().getDob());
            cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
            cell.setCellValue(pcItem.getPt().getAddress());
            cell = row.createCell(Constants.PC_PT, CellType.STRING);
            cell.setCellValue(pcItem.getPt().getName());
            cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
            cell.setCellValue("Giám thị 1");
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
            cell.setCellValue(pcItem.getGt1().getNote());

            row = pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex + 1);
            cell = row.createCell(Constants.PC_CARD_INDEX, CellType.NUMERIC);
            cell.setCellValue(pcItem.getGt2().getCardIndex());
            cell = row.createCell(Constants.PC_NAME, CellType.STRING);
            cell.setCellValue(pcItem.getGt2().getName());
            cell = row.createCell(Constants.PC_DOB, CellType.STRING);
            cell.setCellValue(pcItem.getGt2().getDob());
            cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
            cell.setCellValue(pcItem.getPt().getAddress());
            cell = row.createCell(Constants.PC_PT, CellType.STRING);
            cell.setCellValue(pcItem.getPt().getName());
            cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
            cell.setCellValue("Giám thị 2");
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
            cell.setCellValue(pcItem.getGt2().getNote());
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<PC> getPcs() {
        return pcs;
    }

    public void setPcs(LinkedList<PC> pcs) {
        this.pcs = pcs;
    }

    public LinkedList<GT> getGtHls() {
        return gtHls;
    }

    public void setGtHls(LinkedList<GT> gtHls) {
        this.gtHls = gtHls;
    }
}
