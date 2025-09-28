package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    private final WebDriver driver;

    //локатор для кнопок-вопросов списка вопросы о важном
    private final By dropdownListButton = By.className("accordion__button");

    //локатор для текста ответов из раскрывающегося списка
    private final By dropdownListAnswersText = By.className("accordion__panel");

    //локатор для кнопки заказать сверху страницы
    private final By orderButtonUpper = By.className("Button_Button__ra12g");

    //локатор для кнопки заказать снизу страницы
    private final By orderButtonDown = By.className("Button_Middle__1CSJM");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // метод для нажатия на кнопку-вопрос раскрывающегося списка, принимает номер вопроса(индекс),
    // получает его из списка и кликает на него
    public void clickDropdownListButton(int questionButtonNumber) {
        driver.findElements(dropdownListButton).get(questionButtonNumber).click();
    }

    //метод для получения текста вопроса, принимает порядковый номер вопроса  и возращает текст вопроса
    public String getTextOfQuestion(int questionNumber){
        return driver.findElements(dropdownListButton).get(questionNumber).getText();
    }
    //метод для получения текста ответа, принимает порядковый номер кнопки и возращает текст ответа
    public String getTextOfAnswer(int answerNumber){
        return driver.findElements(dropdownListAnswersText).get(answerNumber).getText();
    }
    //метод для нажатия на кнопку заказать, принимает позицию кнопки на странице(верхняя/нижняя),
    // сохраняет локатор нужной кнопки в зависимости от позиции  и кликает на нее
    public void clickOrderButton(String position) {
        By locator = position.equalsIgnoreCase("верхняя")? orderButtonUpper : orderButtonDown;
        driver.findElement(locator).click();
    }


}