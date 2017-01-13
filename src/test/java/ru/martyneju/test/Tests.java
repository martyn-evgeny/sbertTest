package ru.martyneju.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.martyneju.steps.WebDriverSteps;

/**
 * 	����� ���������� �����
 * 
 * @author emartyn
 *
 */
public class Tests {
	
	private WebDriverSteps steps;
	private Logger log;
	private Properties property = new Properties();
	
	private String fileProperty= "src/main/resources/config.properties";
	private String webdriverGeckoDriver = "webdriver.gecko.driver";
	
	@Before
	public void setUp() throws FileNotFoundException, IOException {		
		log = LoggerFactory.getLogger(this.getClass().getName());
		property.load(new FileInputStream(fileProperty));
		System.setProperty(webdriverGeckoDriver, property.getProperty(webdriverGeckoDriver));
		steps = new WebDriverSteps(new FirefoxDriver(), property);
	}
	
	@After
	public void tearDown() {
		if(steps!=null) {
			steps.quit();
		}
	}
	
	/**
	 * ���� : �������� ���������
	 * ���� : ���������, ��� ��� ��������� �������� ������������ ��������������
	 */
	@Test
	public void telephoneTest() {
		log.info("-- start telephoneTest --");
		steps.openMainPage();
		
		String mobileTel = steps.getTelefonByClass("header-phone_letter_t");	
		Assert.assertEquals(mobileTel,property.getProperty("telephone.mobile") );
		
		String moscovTel = steps.getTelefonByClass("header-phone_letter_s");
		Assert.assertEquals(moscovTel, property.getProperty("telephone.moscov"));
		
		String freeInRusTel = steps.getTelefonByClass("header-phone_letter_f");	
		Assert.assertEquals(freeInRusTel, property.getProperty("telephone.freeInRus"));		
	}
	
	/**
	 * ����: �������� ����� ������
	 * ����: ���������, ��� ��� ����� ������ ������������ � �� ��� �� ����� ������
	 */
	@Test
	public void radioButtonTest() {
		log.info("-- start radioButtonTest --");
		steps.openMainPage();
		
		// ���� � ���������
		steps.clickButtonByXpath(property.getProperty("radio.accountInSber"));
		// ��������
		steps.clickButtonByXpath(property.getProperty("radio.cash"));
		// ����� ���������
		steps.clickButtonByXpath(property.getProperty("radio.cardInSber"));
		// �� ����� ���������
		steps.clickButtonByXpath(property.getProperty("radio.inCardSber"));
		// ������ ��������
		steps.clickButtonByXpath(property.getProperty("radio.toGiveCash"));
		// �� ���� � ���������
		steps.clickButtonByXpath(property.getProperty("radio.toAccountInSber"));
		// �������� ����
		steps.clickButtonByXpath(property.getProperty("radio.internetBank"));
		// �������� /��
		steps.clickButtonByXpath(property.getProperty("radio.atmYs"));
		// ��������� (���)
		steps.clickButtonByXpath(property.getProperty("radio.departmentVSP"));
		// �������
		steps.clickButtonByXpath(property.getProperty("radio.premier"));
		// �������� ������
		steps.clickButtonByXpath(property.getProperty("radio.sberFirst"));
		// ��� ������
		steps.clickButtonByXpath(property.getProperty("radio.noPackage"));
		// �������
		steps.clickButtonByXpath(property.getProperty("radio.choose"));
		// �������
		steps.clickButtonByXpath(property.getProperty("radio.current"));
	}
	
	/**
	 * ����: �������� ������� "��������"
	 * ����: ���������, ��� ������ ����������� � �� ������� ���������� ���������� �� ���������� ���������������
	 */
	@Test
	public void displayButtonTest() {
		log.info("-- start displayButtonTest --");
		steps.openMainPage();
		
		steps.clickButtonByXpath(property.getProperty("display.button"));
		steps.waitElementShowByXpath(property.getProperty("display.youWillReceive"));
	}

}
