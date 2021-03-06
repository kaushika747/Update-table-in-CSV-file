package CSVFile;
import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;

public class CSVFileRecordChange {
	String CsvPath = "C:\\Users\\DELL\\OneDrive\\Desktop\\Students Data.csv";
	WebDriver driver;

	@BeforeTest
	public void setup() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"D:\\DXC Selenium Automation Testing\\Update Records in CSV File\\Browser Jars\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://only-testing-blog.blogspot.com/2014/05/form.html");
	}

	@Test
	public void locatorsTestingBlog() throws Exception {
		CSVReader reader = new CSVReader(new FileReader(CsvPath));
		String[] csvCell;
		while ((csvCell = reader.readNext()) != null) {
			String FName = csvCell[0];
			String LName = csvCell[1];
			String Email = csvCell[2];
			String MNumb = csvCell[3];
			String CName = csvCell[4];

			driver.findElement(By.name("FirstName")).sendKeys(FName);
			driver.findElement(By.name("LastName")).sendKeys(LName);
			driver.findElement(By.name("EmailID")).sendKeys(Email);
			driver.findElement(By.name("MobNo")).sendKeys(MNumb);
			driver.findElement(By.name("Company")).sendKeys(CName);

			Thread.sleep(8000);
			driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
			Thread.sleep(8000);
			driver.switchTo().alert().accept();
		}
		reader.close();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
