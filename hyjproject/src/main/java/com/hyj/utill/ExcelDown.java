package com.hyj.utill;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelDown {

	public byte[] excelDownload(){
	    List<Object> header = new ArrayList<Object>();

	    header.add("카카오");
	    header.add("네이버");
	    header.add("삼성");
	    header.add("엘지");
	    header.add("대우");

	    header.add("현대");
	    header.add("쿠팡");
	    header.add("티몬");
	    header.add("한성");
	    header.add("두산");

	    header.add("기아");
	    header.add("KT");
	    header.add("SK");
	    header.add("STX");
	    header.add("넥센");

	    //1차로 WORKBOOK을 생성
	    XSSFWorkbook mWorkbook = new XSSFWorkbook();
	    //2차로 SHEET를 생성
	    XSSFSheet sheet = mWorkbook.createSheet("CompanyReportOne");
	    //하나의 행을 생성 값이 0이면 첫번째 줄 생성
	    XSSFRow headerRow = sheet.createRow(0);

	    int headerCount = header.size();

	    for (int i=0; i<headerCount; i++) {
	        //생성된 ROW에 컬럼을 생성한다.
	    	XSSFCell headerCell = headerRow.createCell(i, XSSFCell.CELL_TYPE_STRING);
	        //컬럼에 문자열 집어넣음
	    	headerCell.setCellValue(String.valueOf(header.get(i)));

	        //셀의 스타일 설정
	        XSSFCellStyle cellStyle = mWorkbook.createCellStyle();
	        
	        //아래
	        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        
	        //왼쪽
	        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

	        //오른쪽
	        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
	        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

	        //위
	        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
	        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

	        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());

	        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

	        headerCell.setCellStyle(cellStyle);

	        sheet.setColumnWidth(i, 5000);
	    }
	    //시트번호 2번째거 예제
	    XSSFSheet sheet2 = mWorkbook.createSheet("CompanyReportTwo");
	    XSSFRow headerRow2 = sheet2.createRow(0);
	    XSSFRow headerRow3 = sheet2.createRow(1);
	    
	    XSSFCell headerCell2 = headerRow2.createCell(0);
	    headerCell2.setCellValue("TWO-ONE-ONE");
	    XSSFCell headerCell3 = headerRow2.createCell(2);
	    headerCell3.setCellValue("TWO-ONE-THREE");
	    
	    XSSFCell headerCell4 = headerRow3.createCell(0);
	    headerCell4.setCellValue("TWO-TWO-ONE");
	    XSSFCell headerCell5 = headerRow3.createCell(1);
	    headerCell5.setCellValue("TWO-TWO-TWO");
	    
	    byte[] bytes =new byte[0];
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    try {
	        mWorkbook.write(out);
	        bytes = out.toByteArray();
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return bytes;
	}
	
	public int readExcel(MultipartFile weeklyFile) {
		InputStream fis;
		try {
			fis = weeklyFile.getInputStream();
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
			int rowindex=0;
			int columnindex=0;
			//시트 수 (첫번째에만 존재하므로 0을 준다)
			//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
			XSSFSheet sheet=workbook.getSheetAt(0);
			//행의 수
			int rows=sheet.getPhysicalNumberOfRows();
			log.info(String.valueOf(rows));
			log.info(String.valueOf(rowindex));
			log.info(String.valueOf(columnindex));
			
			//첫번째 행부터 읽게 할려면 0을 주면된다.
			for(rowindex=0;rowindex<rows;rowindex++){
			    //행을읽는다
			    XSSFRow row=sheet.getRow(rowindex);
			    if(row !=null){
			        //셀의 수
			        int cells=row.getPhysicalNumberOfCells();
			        
			        //셀의 수 만큼 돌린다.
			        for(columnindex=0;columnindex<=cells;columnindex++){
			            //셀값을 읽는다
			            XSSFCell cell=row.getCell(columnindex);
			            String value="";
			            //셀이 빈값일경우를 위한 널체크
			            if(cell==null){
			                continue;
			            }else{
			                //타입별로 내용 읽기
			                switch (cell.getCellType()){
			                case XSSFCell.CELL_TYPE_FORMULA:
			                    value=cell.getCellFormula();
			                    break;
			                case XSSFCell.CELL_TYPE_NUMERIC:
			                    value=cell.getNumericCellValue()+"";
			                    break;
			                case XSSFCell.CELL_TYPE_STRING:
			                    value=cell.getStringCellValue()+"";
			                    break;
			                case XSSFCell.CELL_TYPE_BLANK:
			                    value=cell.getBooleanCellValue()+"";
			                    break;
			                case XSSFCell.CELL_TYPE_ERROR:
			                    value=cell.getErrorCellValue()+"";
			                    break;
			                }
			            }
			            System.out.println("각 셀 내용 :"+value);
			            log.info(value);
			        }
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	    
		return 1;
	}
}
