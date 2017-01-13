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
 * 	класс содержащий тесты
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
	 * тест : проверка телефонов
	 * цель : проверить, что все указанные телефоны соответсвуют действительным
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
	 * тест: проверка радио кнопок
	 * цель: проверить, что все радио кнопки присутствуют и на все их можно нажать
	 */
	@Test
	public void radioButtonTest() {
		log.info("-- start radioButtonTest --");
		steps.openMainPage();
		
		// счет в сбербанке
		steps.clickButtonByXpath(property.getProperty("radio.accountInSber"));
		// наличные
		steps.clickButtonByXpath(property.getProperty("radio.cash"));
		// карта сбербанка
		steps.clickButtonByXpath(property.getProperty("radio.cardInSber"));
		// на карту сбербанка
		steps.clickButtonByXpath(property.getProperty("radio.inCardSber"));
		// выдать наличные
		steps.clickButtonByXpath(property.getProperty("radio.toGiveCash"));
		// на счет в сбербанке
		steps.clickButtonByXpath(property.getProperty("radio.toAccountInSber"));
		// интернет банк
		steps.clickButtonByXpath(property.getProperty("radio.internetBank"));
		// Банкомат /УС
		steps.clickButtonByXpath(property.getProperty("radio.atmYs"));
		// Отделение (ВСП)
		steps.clickButtonByXpath(property.getProperty("radio.departmentVSP"));
		// Премьер
		steps.clickButtonByXpath(property.getProperty("radio.premier"));
		// Сбербанк первый
		steps.clickButtonByXpath(property.getProperty("radio.sberFirst"));
		// Нет пакета
		steps.clickButtonByXpath(property.getProperty("radio.noPackage"));
		// Выбрать
		steps.clickButtonByXpath(property.getProperty("radio.choose"));
		// Текущее
		steps.clickButtonByXpath(property.getProperty("radio.current"));
	}
	
	/**
	 * тест: проверка кнопоки "показать"
	 * цель: проверить, что кнопка присутсвует и по нажатию появляется информация по результату конвертирования
	 */
	@Test
	public void displayButtonTest() {
		log.info("-- start displayButtonTest --");
		steps.openMainPage();
		
		steps.clickButtonByXpath(property.getProperty("display.button"));
		steps.waitElementShowByXpath(property.getProperty("display.youWillReceive"));
	}

}
