package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderFormPage {
    private WebDriver driver;

    //локаторы для формы заказа и ее полей
    private By orderForm = By.xpath(".//div[text()='Для кого самокат']/parent::div");
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By telephoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //локатор для кнопки далее
    private By buttonNext = By.xpath(".//button[text()='Далее']");

    //локаторы для второй страницы формы заказа
    private By detailsForm = By.xpath(".//div[text()='Про аренду']/parent::div");
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By datePicker = By.className("react-datepicker__month");
    private By durationOfRentalField = By.className("Dropdown-root");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //локатор для кнопки Заказать
    private By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    private By formConfirm = By.className("Order_Overlay__3KW-T");
    private By yesButton = By.xpath(".//button[text()='Да']");
    private By textInOrderConfirmedHeader = By.className("Order_ModalHeader__3FDaJ");

    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для ожидания появления формы заказа
    public void waitForLoadForm(){
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(orderForm));
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
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(metroSelect));
        driver.findElement(metroSelect).click();
    }
    public void setTelephone(String telephone) {
        driver.findElement(telephoneField).sendKeys(telephone);
    }

    //метод для становления кнопки далее кликабельной и нажатия на нее
    public void clickButtonNext(){
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.elementToBeClickable(buttonNext));
        driver.findElement(buttonNext).click();
    }
    //метод для ожидания появления формы заказа
    public void waitForLoadDetailsForm(){
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(detailsForm));
    }

    //методЫ для заполнения формы
    public void setDate() {
        driver.findElement(dateField).click();
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(datePicker));
        driver.findElement(datePicker).click();
    }
    public void setDuration(String duration) {
        driver.findElement(durationOfRentalField).click();
        By durationSelect = By.xpath(".//div[@class='Dropdown-menu']//*[text()='"+duration+"']");
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(durationSelect));
        driver.findElement(durationSelect).click();
    }
    public void setColor(String color) {
        By colorSelect = By.xpath(".//label[text()='"+color+"']");
        driver.findElement(colorSelect).click();
    }
    public void setComment(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }
    public void clickOrderScooterButton() {
        driver.findElement(orderButton).click();
    }
    public void clickYes() {
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(formConfirm));
        driver.findElement(yesButton).click();
    }
    //метод для проверки появления окна подтверждения заказа -Заказ оформлен
    public String textInOrderConfirmedHeader(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textInOrderConfirmedHeader));
        return driver.findElement(textInOrderConfirmedHeader).getText();
    }

}
