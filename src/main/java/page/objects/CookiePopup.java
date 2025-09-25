package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookiePopup {
    private WebDriver driver;

    //локатор для кнопки принятия куки
    private By cookieButton = By.id("rcc-confirm-button");

    public CookiePopup(WebDriver driver) {
        this.driver = driver;
    }
    // метод для нажатия на кнопку принятия куки
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

}