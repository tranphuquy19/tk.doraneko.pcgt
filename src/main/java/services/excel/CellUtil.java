package services.excel;

import org.apache.poi.ss.usermodel.Cell;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class CellUtil {
    public static String getCellValue(Cell cell){
        switch (cell.getCellType()){
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
}
