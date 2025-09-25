package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    //локатор для кнопок списка вопросы о важном
    private By dropdownListButton = By.className("accordion__button");
    //локатор для текста элементов раскрывающегося списка
    private By dropdownListText = By.className("accordion__panel");

    //локатор для кнопки заказать сверху страницы
    private By orderButtonUpper = By.className("Button_Button__ra12g");

    //локатор для кнопки заказать снизу страницы
    private By orderButtonDown = By.className("Button_Middle__1CSJM");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для получения количества стрелок для нажатия
    public int getElements(){
        return driver.findElements(dropdownListButton).size();
    }
    // метод для нажатия на кнопку-стрелочку раскрывающегося списка
    public void clickDropdownListButton(int i) {
        driver.findElements(dropdownListButton).get(i).click();
    }
    // метод для ожидания и проверки, что текст появился
    public void checkTextNotHidden(int i) {
        WebElement textElement = driver.findElements(dropdownListText).get(i);
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOf(textElement));
    }

    //метод для нажатия на кнопку заказать вехнюю
    public void clickOrderButtonUpper() {
        driver.findElement(orderButtonUpper).click();
    }
    //метод для нажатия на кнопку заказать нижнюю
    public void clickOrderButtonDown() {
        driver.findElement(orderButtonDown).click();
    }

}