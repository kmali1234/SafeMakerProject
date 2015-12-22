package com.sysnet.profileexcelgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.format.Alignment;
import jxl.write.WritableCellFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ScenarioFileGenerator {
	private Document doc;
	private Row firstrow;
	private FileOutputStream fileOut;
	private Row scenarioRow;
	private StringBuffer sb;
	private String line;
	private String line1;
	private String newLine="\n";
	private String data;
	private Cell cell;
	private Sheet sheet;




	public  void CreateScenarioFile() throws Exception {
		FileInputStream inp = new FileInputStream(new File("test/integration/com/sysnet/profileexcelgenerator/Profile.xlsx"));
		Workbook wb = WorkbookFactory.create(inp);
		WritableCellFormat cellFormat = new WritableCellFormat();
	    cellFormat.setAlignment(Alignment.RIGHT);
	    cellFormat.setWrap(true);
			
		
		String profilePath=JOptionPane.showInputDialog(null," Enter the New Profile file Path \n eg. C:\\\\Users\\\\davuluris\\\\Desktop\\\\Filename.xml");
		if(profilePath!= null && !profilePath.isEmpty()){
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(profilePath);
		}
		NodeList listOfQuestionsScreen = doc.getElementsByTagName("ProfileScreen");
		int totalQuestionsScreens = listOfQuestionsScreen.getLength();
		for(int j=1; j<=8; j++){
			sheet = wb.getSheet("Sheet"+j);
			
			sheet.createRow(0);
			firstrow=sheet.getRow(0);
			firstrow.createCell(0).setCellValue("Scenario");
			typeScenarioCell(sheet, cellFormat);
			
			int colnum=1;
			typeScenarioCell(sheet, cellFormat);
			int col=1;
			for(int i=0; i<totalQuestionsScreens;i++){
				
				Node questionScreenNode = listOfQuestionsScreen.item(i);
				Element questionScreenElement = (Element)questionScreenNode;
				String screenNameAttributeValue = questionScreenElement.getAttribute("name");
				firstrow.createCell(colnum++).setCellValue(screenNameAttributeValue);
				sheet.setColumnWidth(1, 5);
				
				NodeList profileQuestion=questionScreenElement.getElementsByTagName("ProfileQuestion");
				sb = new StringBuffer("");
				for(int k=0; k<profileQuestion.getLength();k++){
					Node profileQuestionNode = profileQuestion.item(k);
					Element profileQuestionElement = (Element)profileQuestionNode;
					
					if(profileQuestionElement.hasAttribute("name")){
						String nameAttributeValue = profileQuestionElement.getAttribute("name");
						Matcher m = Pattern.compile("([\\w]*)_list_([\\w]*)").matcher(nameAttributeValue);
						Matcher m1=Pattern.compile("-?[0-9]+").matcher(nameAttributeValue);
						if(m.matches() || m1.matches())
						{
						}else if(profileQuestionNode.getNodeType() == Node.ELEMENT_NODE && profileQuestionElement.hasAttribute("name")){
							System.out.println(nameAttributeValue);
							
							if(profileQuestionElement.getAttribute("questionType").equals("YESNO_RADIO")) {
								
								line=nameAttributeValue+":yes";
								sb.append(line);
								sb.append(newLine);
								line1=nameAttributeValue+":no";
								sb.append(line1);
								sb.append(newLine);
								
							} else if(profileQuestionElement.getAttribute("questionType").equals("YESNO_CHECKBOX")){
								
								line=nameAttributeValue+":check";
								sb.append(line);
								sb.append(newLine);
								
							} else if(profileQuestionElement.getAttribute("questionType").equals("POPUP_BUTTON")){
								
								if(nameAttributeValue.equals("otherSaqType")){
									
									line=nameAttributeValue+":pickSAQType";
									
								} else{
									line=nameAttributeValue+":pick";
								}
								sb.append(line);
								sb.append(newLine);
							} else if(profileQuestionElement.getAttribute("questionType").equals("FREETEXT")) {
								
								line=nameAttributeValue+":text";
								sb.append(line);
								sb.append(newLine);
								
							}else if(profileQuestionElement.getAttribute("questionType").equals("TEXTAREA")) {
								
								line=nameAttributeValue+":text";
								sb.append(line);
								sb.append(newLine);
							}else if(profileQuestionElement.getAttribute("questionType").equals("DATE")) {
								
								line=nameAttributeValue+":date";
								sb.append(line);
								sb.append(newLine);
							}else if(profileQuestionElement.getAttribute("questionType").equals("INFO")) {
								
								line=nameAttributeValue+":INFO";
								sb.append(line);
								sb.append(newLine);
							}
						}
				}
				
				
			}
			
				data=sb.toString();
				System.out.println("page"+i);
				typeCellData(sheet,col, data, cellFormat);
				col++;
		}
		}
		

		fileOut=new FileOutputStream(new File("test/integration/com/sysnet/profileexcelgenerator/Profile.xlsx"));
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		
		
		
		}
				
				







		private void typeCellData(Sheet sheet, int col, String data, WritableCellFormat cellFormat) {
		// TODO Auto-generated method stub
			for(int p=1; p<=4; p++){
				
				scenarioRow=sheet.getRow(p);
				System.out.println(col);
				scenarioRow.createCell(col).setCellValue(data);
				
				
			}
		
		}









		private void typeScenarioCell(Sheet sheet, WritableCellFormat cellFormat) {
		// TODO Auto-generated method stub
			for(int p=1; p<=4; p++){
			
				sheet.createRow(p);
				scenarioRow=sheet.getRow(p);
				scenarioRow.setHeight((short)2000);
				scenarioRow.createCell(0);
				cell=scenarioRow.getCell(0);
				cell.setCellValue("Scenario"+p);
				
				
				
				
			}
		}



		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
			ScenarioFileGenerator sfg=new ScenarioFileGenerator();
			sfg.CreateScenarioFile();
			

		}


}
