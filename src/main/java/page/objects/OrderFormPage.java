package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;


public class OrderFormPage {
    private final WebDriver driver;
    private final WebDriverWait waitDriver;

    //локаторы для формы заказа и ее полей
    private final By orderForm = By.xpath(".//div[text()='Для кого самокат']/parent::div");
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By telephoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //локатор для кнопки далее
    private final By buttonNext = By.xpath(".//button[text()='Далее']");

    //локаторы для полей второй страницы формы заказа
    private final By detailsForm = By.xpath(".//div[text()='Про аренду']/parent::div");
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By datePicker = By.className("react-datepicker__month");
    private final By durationOfRentalField = By.className("Dropdown-root");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //локатор для кнопки Заказать
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    //локатор для окна "Хотите оформить заказ"
    private final By formConfirm = By.className("Order_Overlay__3KW-T");
    //локатор для кнопки Да
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //локатор для текста в окне подтвержденного заказа
    private final By textOfConfirmedOrder = By.className("Order_Text__2broi");



    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(Utils.EXPLICIT_WAIT_3SEC));
    }

    //метод для ожидания появления элемента
    public void waitForElementVisible(By locator) {
         waitDriver.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //метод для ожидания появления формы заказа
    public void waitForLoadForm(){
        waitForElementVisible(orderForm);
    }
    //методЫ для заполнения формы
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    //устанавливаем станцию метро какую нам надо передавать каждый раз в метод
    public void setMetro(String metro) {
        driver.findElement(metroField).click();

        By metroSelect = By.xpath(".//div[@class='select-search__select']//*[text()='" + metro +"']");
        waitForElementVisible(metroSelect);
        driver.findElement(metroSelect).click();
    }
    public void setTelephone(String telephone) {
        driver.findElement(telephoneField).sendKeys(telephone);
    }

    //метод для ожидания кнопки "Далее" кликабельной и нажатия на нее
    public void clickButtonNext(){
        waitDriver.
                until(ExpectedConditions.elementToBeClickable(buttonNext));
        driver.findElement(buttonNext).click();
    }
    //метод для ожидания появления формы заказа - второй страницы
    public void waitForLoadDetailsForm(){
        waitForElementVisible(detailsForm);
    }

    //методЫ для заполнения формы
    public void setDate() {
        driver.findElement(dateField).click();
        waitDriver.
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(datePicker));
        driver.findElement(datePicker).click();
    }
    public void setDuration(String duration) {
        driver.findElement(durationOfRentalField).click();
        By durationSelect = By.xpath(".//div[@class='Dropdown-menu']//*[text()='"+duration+"']");
        waitForElementVisible(durationSelect);
        driver.findElement(durationSelect).click();
    }
    public void setColor(String color) {
        By colorSelect = By.xpath(".//label[text()='"+color+"']");
        driver.findElement(colorSelect).click();
    }
    public void setComment(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }

    //метод для клика по кнопке Заказать после заполнения полей формы
    public void clickOrderScooterButton() {
        driver.findElement(orderButton).click();
    }
    //метод для клика по кнопке "Да" на странице "Хотите оформить заказ"
    public void clickYes() {
        waitForElementVisible(formConfirm);
        driver.findElement(yesButton).click();
    }

    //метод для получения текста со страницы с подтвержденным заказом
    public String getTextOfConfirmedOrder(){
        waitForElementVisible(textOfConfirmedOrder);
        return driver.findElement(textOfConfirmedOrder).getText();
    }

}
