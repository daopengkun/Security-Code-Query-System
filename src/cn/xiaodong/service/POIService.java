package cn.xiaodong.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import cn.xiaodong.dao.CodeDao;
import cn.xiaodong.domain.Code;
import cn.xiaodong.utils.UUIDUtils;


/**
 * Excel文件数据读写
 * @author 朱晓东
 *
 */
public class POIService{

	/**
	 * 将Excel表中的数据导入到数据库
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void DataImport(String PathAndName) throws Exception {
		
		//测试信息
		System.out.println("POIService已经转跳成功！");

		//需求分析
		//1.读取到Excel文件

		//传递的参数PathAndName包含了文件路径和文件名称
		//PathAndName=D:\Code\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Html5LotteryCE\WEB-INF\Upload\config001\config001.xlsx

		//支持xls、xlsx两种文件格式
		String FileName=PathAndName;

		FileInputStream f = new FileInputStream(FileName);
		Workbook wb = WorkbookFactory.create(f);
		Sheet sheet = wb.getSheetAt(0);


		//2.读取Excel中的数据	
		int rowStart = 0;
		int rowEnd = sheet.getLastRowNum();

		//使用for循环处理每一行的数据
		for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}

			//处理一行的数据
			Cell cCode = row .getCell(0);
			Cell cCompany = row .getCell(1);
			
			//将读取到的数据封装起来
			Code c = new Code();
			c.setId( UUIDUtils.getCode() );
			c.setCode( getCellValue(cCode) );
			c.setCompany( getCellValue(cCompany) );
			c.setQueryTimes( (int) 0 );
			
			//将数据写到数据库
			CodeDao.add(c);
		}
		
	}

	/**
	 * 读取单元格中的数据
	 * 根据单元格的不同类型进行处理
	 * 返回值为String
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell){

		//对于空的单元格，不处理
		if(cell==null){
			return null;
		}

		switch (cell.getCellTypeEnum()) {
		case STRING:
			return cell.getRichStringCellValue().getString();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				//如果数据是data类型
				//data类型的数据转换成String            	  
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = cell.getDateCellValue();  
				String str=sdf.format(date);
				return str;
			} else {
				//如果数据是String类型
				//将double转换成String
				double d = cell.getNumericCellValue();
				long l = (long) d;
				String str = Long.toString(l);
				return str;                
			}
		case BLANK:
			System.out.println();
			return null;
		case BOOLEAN:
			boolean b = cell.getBooleanCellValue();
			//boolean转换成String
			String str_b = String.valueOf(b);
			return str_b;
		case FORMULA:
			return cell.getCellFormula();
		default:
			System.out.println();
			return null;
		}		
	}

}
