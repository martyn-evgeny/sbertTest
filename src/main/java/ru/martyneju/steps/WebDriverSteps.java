package ru.martyneju.steps;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

/**
 *  класс, для работы с селениум.
 *  содержит шаги тестирования.
 * 
 * @author emartyn
 *
 */
public class WebDriverSteps {
	private WebDriver driver;
	private Properties property;
	
	public WebDriverSteps(WebDriver driver, Properties property) {
		this.driver = driver;
		this.property=property;
	}
	
    public void quit() {
        driver.quit();
    }

    @Step
    public void openMainPage() {
        driver.get(property.getProperty("MainPage"));
        new WebDriverWait(driver, 15)
        	.withMessage("Could not load converter page")
        	.until(getPredicateFindElementByXpath(property.getProperty("converter")));
    }
    
    @Attachment
    @Step
    public String getTelefonByClass(String className) {
    	WebElement element = driver.findElement(By.className(className));
    	return element.findElement(By.className("header-phone__link")).getText();
    }
    
    @Step
    public void clickButtonByXpath(String xpath) {
    	driver.findElement(By.xpath(xpath)).click();
    }
    
    @Step
    public void waitElementShowByXpath(String xpath) {
    	new WebDriverWait(driver, 10)
    	.withMessage("Element not find")
    	.until(getPredicateFindElementByXpath(xpath));	
    }
    
    private Predicate<WebDriver> getPredicateFindElementByXpath(final String xpath) {
        return new Predicate<WebDriver>() {
            public boolean apply(WebDriver input) {
                return driver.findElement(By.xpath(xpath)).isDisplayed();
            }
        };
    }
}
