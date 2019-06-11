
import jxl.*;
import jxl.format.UnderlineStyle;
import jxl.write.*;

import java.io.File;

public class CreateXLS { 
	public static void main(String args[]) { 
		try { 
			//打开文件
			WritableWorkbook book= Workbook.createWorkbook(new File("E:\\测试.xls"));
			//生成名为“第一页”的工作表，参数0表示这是第一页 
			WritableSheet sheetOne=book.createSheet("第一页",0); 
			
			/**
			 * 定义单元格样式
			 */
			WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 11,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 11,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 11,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
 
			WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
			wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
			wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
			
			WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title); // 单元格定义
			wcf_title1.setBackground(jxl.format.Colour.LIGHT_GREEN); // 设置单元格的背景颜色
			wcf_title1.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcf_title1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
			
			WritableCellFormat wcf_title2 = new WritableCellFormat(wf_title); // 单元格定义
			wcf_title2.setBackground(jxl.format.Colour.YELLOW2); // 设置单元格的背景颜色
			wcf_title2.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcf_title2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
 
			WritableCellFormat wcf_head1 = new WritableCellFormat(wf_head); 
			wcf_head1.setBackground(jxl.format.Colour.LIGHT_GREEN);
			wcf_head1.setAlignment(jxl.format.Alignment.CENTRE); 
			wcf_head1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
			
			WritableCellFormat wcf_head2 = new WritableCellFormat(wf_head); 
			wcf_head2.setBackground(jxl.format.Colour.YELLOW2);
			wcf_head2.setAlignment(jxl.format.Alignment.CENTRE); 
			wcf_head2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
 
 
			WritableCellFormat wcf_table1 = new WritableCellFormat(wf_table); 
			wcf_table1.setBackground(jxl.format.Colour.LIGHT_GREEN); 
			wcf_table1.setAlignment(jxl.format.Alignment.CENTRE); 
			wcf_table1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
			
			WritableCellFormat wcf_table2 = new WritableCellFormat(wf_table); 
			wcf_table2.setBackground(jxl.format.Colour.YELLOW2); 
			wcf_table2.setAlignment(jxl.format.Alignment.CENTRE); 
			wcf_table2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
			
			sheetOne.setColumnView(0, 15); // 设置列的宽度
			sheetOne.setColumnView(1, 15); // 设置列的宽度
			sheetOne.setColumnView(2, 15); // 设置列的宽度
			sheetOne.setColumnView(3, 15); // 设置列的宽度
			sheetOne.setColumnView(4, 15); // 设置列的宽度
			sheetOne.setColumnView(5, 15); // 设置列的宽度
			sheetOne.setColumnView(6, 15); // 设置列的宽度
			sheetOne.setColumnView(7, 15); // 设置列的宽度
			sheetOne.setColumnView(8, 15); // 设置列的宽度
			sheetOne.setColumnView(9, 15); // 设置列的宽度
			sheetOne.setColumnView(10, 15); // 设置列的宽度
			sheetOne.setColumnView(11, 15); // 设置列的宽度
			sheetOne.setColumnView(12, 15); // 设置列的宽度
			sheetOne.setColumnView(13, 15); // 设置列的宽度
 
			//在Label对象的构造子中指名单元格位置是第一列第一行(0,0) 
			//以及单元格内容为test 
			Label title=new Label(0,0,"统计",wcf_title);
			Label titleOne=new Label(0,1,"统计1",wcf_title1);
			Label titleTwo=new Label(2,1,"统计2",wcf_title2);
 
			Label column1=new Label(0,2,"姓名",wcf_head1);
			Label column2=new Label(1,2,"所选课程",wcf_head1);
			
			Label column3=new Label(2,2,"姓名",wcf_head2);
			Label column4=new Label(3,2,"所选课程",wcf_head2);
 
 
 
			//或者WritableCell cell =  new jxl.write.Number(column, row, value, wcf)
			//将定义好的单元格添加到工作表中 
			sheetOne.addCell(title); 
			sheetOne.addCell(titleOne); 
			sheetOne.addCell(titleTwo); 
 
			sheetOne.addCell(column1); 
			sheetOne.addCell(column2); 
			sheetOne.addCell(column3); 
			sheetOne.addCell(column4); 
 
 
			//合： 第1列第1行  到 第13列第1行
			sheetOne.mergeCells(0, 0, 4, 0);
			sheetOne.mergeCells(0, 1, 1, 1); 
			sheetOne.mergeCells(2, 1, 4, 1);
			
			
			
			
			
			
			/*动态数据   */
			Label content1=new Label(0,3,"张三",wcf_table1);
			Label content2=new Label(0,4,"张三",wcf_table1);
			Label content3=new Label(0,5,"张三",wcf_table1);
			Label kecheg1=new Label(1,3,"语文",wcf_table1);
			Label kecheg2=new Label(1,4,"数学",wcf_table1);
			Label kecheg3=new Label(1,5,"英语",wcf_table1);
			
			sheetOne.addCell(content1);
			sheetOne.addCell(content2);
			sheetOne.addCell(content3);
			sheetOne.addCell(kecheg1);
			sheetOne.addCell(kecheg2);
			sheetOne.addCell(kecheg3);
			
			sheetOne.mergeCells(0, 3, 0, 2+3); 
			
			
			Label content11=new Label(2,3,"李四",wcf_table2);
			Label content22=new Label(2,4,"李四",wcf_table2);
			Label content33=new Label(2,5,"李四",wcf_table2);
			Label kecheg11=new Label(3,3,"语文",wcf_table2);
			Label kecheg22=new Label(3,4,"数学",wcf_table2);
			Label kecheg33=new Label(3,5,"英语",wcf_table2);
			
			sheetOne.addCell(content11);
			sheetOne.addCell(content22);
			sheetOne.addCell(content33);
			sheetOne.addCell(kecheg11);
			sheetOne.addCell(kecheg22);
			sheetOne.addCell(kecheg33);
			
			sheetOne.mergeCells(2, 3, 2, 2+3); 
 
 
			//写入数据并关闭文件 
			book.write(); 
			book.close(); 
		}catch(Exception e){ 
			System.out.println(e); 
		} 
	} 
}