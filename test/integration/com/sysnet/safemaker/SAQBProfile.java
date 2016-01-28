package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.LogoutPage;
import com.sysnet.pageobjects.OnlineSaqAttest;
import com.sysnet.pageobjects.PersonalisePage;

public class SAQBProfile {

	private String conifgFilePath;
	private Properties conifgProps;
	private String client;
	private Properties clientProps;
	private String propertyfilepath;
	private String baseUrl;
	private String loginpageUrlSufix;
	private String url;

	private WebDriver driver;
	private Sheet profileSheet;
	private int pRowCount;
	private Sheet merchantSheet;
	private Row prow;
	private String currentScenario;
	private int merchanRowNum=1;
	private Cell usedCell;
	private Cell merchantCell;
	private Cell saqCell;
	private Cell completeCell;
	private Cell attestedCell;
	private Cell scenariocell;
	private Cell usernameCell;
	private int merchantCount;
	private String username;
	private int count=0;
	private By starProfileButton;
	private SeleniumHelper sh;
	private By skiptutorials;
	private String saqType;
	private String merchantfilepath;

	private Workbook wrkBook1;
	private FileInputStream in;
	private String used;
	private String saqSheet;
	private final  Logger log = Logger.getLogger(SAQBProfile.class.getName());


	@Before
	public void setup() throws Exception {
		conifgFilePath = "test/integration/config.Properties";
		conifgProps = new Properties();
		FileInputStream locatorStream = new FileInputStream(conifgFilePath);
		conifgProps.load(locatorStream);
		client = conifgProps.getProperty("client");
		propertyfilepath = "test/integration/" + client + "/locators.properties";
		clientProps = new Properties();
		FileInputStream locatorStream1 = new FileInputStream(propertyfilepath);
		clientProps.load(locatorStream1);

		baseUrl = conifgProps.getProperty("server");
		loginpageUrlSufix = clientProps.getProperty("login.url.suffix");
		url = baseUrl + loginpageUrlSufix;
		System.out.println(url);
		sh= new SeleniumHelper(driver, clientProps);
		merchantfilepath="test/integration/TestAccs.xlsx";
		in = new FileInputStream(merchantfilepath);
		wrkBook1 = (Workbook)WorkbookFactory.create(in);
		merchantSheet= wrkBook1.getSheet("SAQB"); 
		count = merchantSheet.getLastRowNum();
		saqSheet="SAQ type B";
		profileSheet = sh.readExcelFile("test/integration/aibms/Profile.xlsx", saqSheet );
		
		pRowCount = profileSheet.getLastRowNum();

		Browser browser = new Browser();
		driver = browser.getdriver(conifgProps.getProperty("browser").toString());
		driver.get(url);

	}

	@Test
	public void merchantJourney() throws Exception {
		try{
		System.out.println(pRowCount);
		
		checkForMerchants();
		for(int scenario=1; scenario<=pRowCount;){
			prow=profileSheet.getRow(scenario);
			currentScenario=prow.getCell(0).toString();
			
			if(currentScenario!=null ){
				
				System.out.println(currentScenario);
				System.out.println(merchantSheet.getLastRowNum());
				if(merchanRowNum<=count){
					
					Row row = merchantSheet.getRow(merchanRowNum);
					usedCell = row.getCell(1);
					used = usedCell.getStringCellValue().toUpperCase();
					while(used.equals("YES")){
						merchanRowNum++;
						row = merchantSheet.getRow(merchanRowNum);
						usedCell = row.getCell(1);
						used = usedCell.getStringCellValue().toUpperCase();
					}
					System.out.println(used);
					//WaitSeconds.TimeDelay(driver, clientProps);
					merchantCell = row.getCell(0);
					saqCell = row.getCell(2);
					completeCell = row.getCell(3);
					attestedCell = row.getCell(4);
					scenariocell= row.getCell(5);
					usernameCell=row.getCell(6);
					
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						LoginPage login = new LoginPage(driver, clientProps);
						login.LoginUser((String)merchantCell.getStringCellValue(), "Sysnet12");
						
						//Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						System.out.println("sucessfull");
						String mid=merchantCell.getStringCellValue();
						
						username="Test"+mid;
						PersonalisePage pp = new PersonalisePage(driver, clientProps);
						pp.personaliseMerchant(username);
						
						
						Thread.sleep(3000);
						 starProfileButton=By.cssSelector(clientProps.getProperty("profile.button.starsprofile.css"));
						 
						
							driver.findElement(starProfileButton).click();
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						log.info("Merchant profile initiated for "+saqSheet+ " "+currentScenario);
						System.out.println(prow.getLastCellNum());
						for (int screenNumber = 1; screenNumber < prow.getLastCellNum(); screenNumber++) {
							
							if(prow.getCell(screenNumber)!=null && (prow.getCell(screenNumber).getCellType()==Cell.CELL_TYPE_STRING)){
								System.out.println(screenNumber);
								System.out.println(prow.getCell(screenNumber).getStringCellValue());
								ProfileQuestion.answer(prow.getCell(screenNumber).getStringCellValue(), driver, clientProps);
								
								Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
								NextButton.click(driver, clientProps);
								Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
								
							}
						}
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						skiptutorials=By.cssSelector(clientProps.getProperty("tutorials.button.skip.css"));
						driver.findElement(skiptutorials).click();
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						//saqType=driver.findElement(By.cssSelector(clientProps.getProperty("dashboard.saqtype.test.css"))).getText();
						OnlineSaqAttest osa= new OnlineSaqAttest(driver, clientProps);
						osa.saqCompliant();
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						driver.navigate().refresh();
						Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
						LogoutPage lop = new LogoutPage(driver, clientProps);
						lop.userLogout();
						merchanRowNum++;
						scenario++;
						log.info("Merchant "+username+" is sucessfully Attested");
					}
					
				
			}
			usernameCell.setCellValue(username);
			usedCell.setCellValue("YES");
			Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
			Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
			
			Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
			FileOutputStream out = new FileOutputStream(merchantfilepath);
			wrkBook1.write(out);
			out.close();
			
			
			in = new FileInputStream(merchantfilepath);
			wrkBook1 = (Workbook)WorkbookFactory.create(in);
			merchantSheet= wrkBook1.getSheet("SAQB");
			Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
			
		}

		}catch(Exception e){
			log.error("Merchant journey for merchant:"+username+"and SAQ type"+saqSheet.toString()+" intrupted by an exception", e);
		}


		
	}



	@After
	 public void close() {
	  driver.close();
	 }
	
	 private void checkForMerchants() throws Exception {
		// TODO Auto-generated method stub
		 merchantCount=merchantSheet.getLastRowNum();
		 if(pRowCount>merchantCount){
				String warning="This script needs sufficient number of Merchants";
				JOptionPane.showMessageDialog(null,warning);
				Thread.sleep(100);
				
				driver.quit();
			}
	}
}
