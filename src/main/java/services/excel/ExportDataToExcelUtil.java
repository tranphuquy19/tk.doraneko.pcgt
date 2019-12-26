package services.excel;

import commons.Constants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.model.GT;
import services.model.PC;
import services.model.PT;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    private List<PT> pts = new ArrayList<>();
    private LinkedList<GT> gtHls = new LinkedList<>();

    public ExportDataToExcelUtil() {
    }

    /**
     * @param filePath
     * @param pcs
     * @param gtHls
     * @param pts
     */
    public ExportDataToExcelUtil(String filePath, ArrayList<PC> pcs, LinkedList<GT> gtHls, ArrayList<PT> pts) {
        this.filePath = filePath;
        this.pcs = pcs;
        this.pts = pts;
        this.gtHls = gtHls;
    }

    public void createFile() throws UnsupportedEncodingException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet pcSheet = book.createSheet("PC");

        //------------------ALL------------------------
        int rowIndex = 0;
        Cell cell;
        Row row;

        row = pcSheet.createRow(rowIndex);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue(new String("Cộng hòa Xã hội Chủ nghĩa Việt Nam".getBytes(), "UTF-8"));

        row = pcSheet.createRow(++rowIndex);
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue(new String("    Dộc lập - Tự do - Hạnh phúc".getBytes(), "UTF-8"));

        row = pcSheet.createRow(++rowIndex);

        cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
        cell.setCellValue("TT");
        cell = row.createCell(Constants.PC_CARD_INDEX, CellType.STRING);
        cell.setCellValue("So The");
        cell = row.createCell(Constants.PC_NAME, CellType.STRING);
        cell.setCellValue(new String("Ho Tên".getBytes(), "UTF-8"));
        cell = row.createCell(Constants.PC_DOB, CellType.STRING);
        cell.setCellValue(new String("Ngày sinh".getBytes(), "UTF-8"));
        cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
        cell.setCellValue(new String("Dơn vị công tác".getBytes(), "UTF-8"));
        cell = row.createCell(Constants.PC_PT, CellType.STRING);
        cell.setCellValue(new String("Phòng thi".getBytes(), "UTF-8"));
        cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
        cell.setCellValue(new String("Chức vụ".getBytes(), "UTF-8"));
        cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
        cell.setCellValue(new String("Ghi chú".getBytes(), "UTF-8"));

        for (PC pcItem : pcs) {
            row = pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex - 2);
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
            cell.setCellValue(new String("Giám thị 1".getBytes(), "UTF-8"));
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
//            cell.setCellValue(pcItem.getGt1().getNote());
            cell.setCellValue(" ");

            row = pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex - 2);
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
            cell.setCellValue(new String("Giám thị 2".getBytes(), "UTF-8"));
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
            cell.setCellValue(" ");
        }

        //------------------GTHL ALL------------------------
        int arv = (int) (pts.size() / gtHls.size());
        int currentPT = 1;
        for (GT gtItem : gtHls) {
            row = pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex - 2);
            cell = row.createCell(Constants.PC_CARD_INDEX, CellType.NUMERIC);
            cell.setCellValue(gtItem.getCardIndex());
            cell = row.createCell(Constants.PC_NAME, CellType.STRING);
            cell.setCellValue(gtItem.getName());
            cell = row.createCell(Constants.PC_DOB, CellType.STRING);
            cell.setCellValue(gtItem.getDob());
            cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
            int ptIndex = gtHls.indexOf(gtItem) % arv;
//            System.out.println(ptIndex);
            cell.setCellValue(pts.get(ptIndex).getAddress());
            cell = row.createCell(Constants.PC_PT, CellType.STRING);
            cell.setCellValue(" ");
            cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
            cell.setCellValue(new String("Giám thị hành lang".getBytes(), "UTF-8"));
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
            int nextIndex = 0;
            if (currentPT + arv * 2 + 1 >= pts.size()) {
                nextIndex = pts.size();
            } else nextIndex = currentPT + arv;
            cell.setCellValue(pts.get(currentPT - 1).getName() + "-" + pts.get(nextIndex - 1).getName());
            if ((currentPT + arv * 2 < pts.size())) currentPT += arv;
        }

        rowIndex++;
        rowIndex++;
        row = pcSheet.createRow(++rowIndex);
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue(new String("Chủ tich hội dồng thi".getBytes(), "UTF-8"));

        row = pcSheet.createRow(++rowIndex);
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue(new String("    Trần Văn Thi".getBytes(), "UTF-8"));

        //------------------PCx------------------------
        int countAll = pcs.size();
        XSSFSheet _pcSheet = null;
        rowIndex = 0;
        for (int i = 0; i < countAll; i++) {
            System.out.println(i);
            PC pcItem = pcs.get(i);

            String _name = "";
            if (i % 10 == 0) {
                _name = "PC" + book.getNumberOfSheets();

                _pcSheet = book.createSheet(_name);
                rowIndex = 0;
                row = _pcSheet.createRow(rowIndex);
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(new String("Cộng hòa Xã hội Chủ nghĩa Việt Nam".getBytes(), "UTF-8"));

                row = _pcSheet.createRow(++rowIndex);
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(new String("    Dộc lập - Tự do - Hạnh phúc".getBytes(), "UTF-8"));

                row = _pcSheet.createRow(++rowIndex);
                cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
                cell.setCellValue("TT");
                cell = row.createCell(Constants.PC_CARD_INDEX, CellType.STRING);
                cell.setCellValue("So The");
                cell = row.createCell(Constants.PC_NAME, CellType.STRING);
                cell.setCellValue(new String("Ho Tên".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_DOB, CellType.STRING);
                cell.setCellValue(new String("Ngày sinh".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
                cell.setCellValue(new String("Dơn vị công tác".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_PT, CellType.STRING);
                cell.setCellValue(new String("Phòng thi".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
                cell.setCellValue(new String("Chức vụ".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
                cell.setCellValue(new String("Ghi chú".getBytes(), "UTF-8"));
            }

            row = _pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex - 2);
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
            cell.setCellValue(new String("Giám thị 1".getBytes(), "UTF-8"));
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
//            cell.setCellValue(pcItem.getGt1().getNote());
            cell.setCellValue(" ");

            row = _pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex - 2);
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
            cell.setCellValue(new String("Giám thị 2".getBytes(), "UTF-8"));
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
//            cell.setCellValue(pcItem.getGt1().getNote());
            cell.setCellValue(" ");
        }

        //------------------HL------------------------

//        int hlCount = Math.min(gtHls.size(), 20);

        XSSFSheet ___pcSheet = null;
        int countHL = 0;
        arv = ((int) pts.size() / gtHls.size());
        currentPT = 1;
        for (GT gtItem : gtHls) {
            if (countHL % 20 == 0) {
                ___pcSheet = book.createSheet("HL" + book.getNumberOfSheets());
                rowIndex = 0;

                row = ___pcSheet.createRow(rowIndex);
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(new String("Cộng hòa Xã hội Chủ nghĩa Việt Nam".getBytes(), "UTF-8"));

                row = ___pcSheet.createRow(++rowIndex);
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(new String("    Dộc lập - Tự do - Hạnh phúc".getBytes(), "UTF-8"));

                row = ___pcSheet.createRow(++rowIndex);

                cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
                cell.setCellValue("TT");
                cell = row.createCell(Constants.PC_CARD_INDEX, CellType.STRING);
                cell.setCellValue("So The");
                cell = row.createCell(Constants.PC_NAME, CellType.STRING);
                cell.setCellValue(new String("Ho Tên".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_DOB, CellType.STRING);
                cell.setCellValue(new String("Ngày sinh".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
                cell.setCellValue(new String("Dơn vị công tác".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_PT, CellType.STRING);
                cell.setCellValue(new String("Phòng thi".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
                cell.setCellValue(new String("Chức vụ".getBytes(), "UTF-8"));
                cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
                cell.setCellValue(new String("Ghi chú".getBytes(), "UTF-8"));
            }
            row = ___pcSheet.createRow(++rowIndex);
            cell = row.createCell(Constants.PC_INDEX, CellType.STRING);
            cell.setCellValue(rowIndex - 2);
            cell = row.createCell(Constants.PC_CARD_INDEX, CellType.NUMERIC);
            cell.setCellValue(gtItem.getCardIndex());
            cell = row.createCell(Constants.PC_NAME, CellType.STRING);
            cell.setCellValue(gtItem.getName());
            cell = row.createCell(Constants.PC_DOB, CellType.STRING);
            cell.setCellValue(gtItem.getDob());
            cell = row.createCell(Constants.PC_ADDRESS, CellType.STRING);
            int ptIndex = gtHls.indexOf(gtItem) % arv;
//            System.out.println(ptIndex);
            cell.setCellValue(pts.get(ptIndex).getAddress());
            cell = row.createCell(Constants.PC_PT, CellType.STRING);
            cell.setCellValue(" ");
            cell = row.createCell(Constants.PC_ROLE, CellType.STRING);
            cell.setCellValue(new String("Giám thị hành lang".getBytes(), "UTF-8"));
            cell = row.createCell(Constants.PC_NOTE, CellType.STRING);
            int nextIndex = 0;
            if (currentPT + arv * 2 + 1 >= pts.size()) {
                nextIndex = pts.size();
            } else nextIndex = currentPT + arv;
            cell.setCellValue(pts.get(currentPT - 1).getName() + "-" + pts.get(nextIndex - 1).getName());
            if ((currentPT + arv * 2 < pts.size())) currentPT += arv;
            countHL++;
        }

        File file = new File(this.filePath);
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            System.out.println("Created File");
        } catch (IOException e) {
            e.printStackTrace();
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
