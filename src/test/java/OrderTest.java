import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.objects.CookiePopup;
import page.objects.HomePage;
import page.objects.OrderFormPage;
import page.objects.Url;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
//класс с автотестом заказа самоката
public class OrderTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String telephone;
    private final String duration;
    private final String color;
    private final String comment;

    private WebDriver driver;

    public OrderTest(String name, String surname, String address, String metro,
                     String telephone, String duration, String color, String comment){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.telephone = telephone;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][] {
                {"Борис", "Бритва", "1-я Тверская-Ямская", "Черкизовская", "88007773535",
                        "четверо суток","серая безысходность", "хочу синий самокат"},
                {"Галина", "Иванова", "2-я Тверская-Ямская", "Сокольники", "89807773535",
                        "семеро суток","чёрный жемчуг", "а можно розовый самокат"},
        };
    }


    @Test
    public void checkOrderClickOnUpperButtonTest(){
        // переход на страницу тестового приложения
        driver.get(Url.URL_SCOOTER);

        //нажимаем на кнопку куки
        CookiePopup objCookiePopup = new CookiePopup(driver);
        objCookiePopup.clickCookieButton();

       //нажимаем на кнопку заказать
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickOrderButtonUpper();

        //ожидаем загрузки первой страницы с формой Для кого самокат
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);
        objOrderFormPage.waitForLoadForm();

        //заполняем поля формы Для кого самокат
        objOrderFormPage.setName(name);
        objOrderFormPage.setSurname(surname);
        objOrderFormPage.setAddress(address);
        objOrderFormPage.setMetro(metro);
        objOrderFormPage.setTelephone(telephone);

        //кликакаем на кнопку далее страницы Для кого самокат
        objOrderFormPage.clickButtonNext();

        //ожидаем загрузки второй страницы с формой Про аренду
        objOrderFormPage.waitForLoadDetailsForm();

        //заполняем поля формы Про аренду

        objOrderFormPage.setDuration(duration);
        objOrderFormPage.setColor(color);
        objOrderFormPage.setComment(comment);
        objOrderFormPage.setDate();
        objOrderFormPage.clickOrderScooterButton();
        objOrderFormPage.clickYes();
        String textInOrderConfirmedHeader = objOrderFormPage.textInOrderConfirmedHeader();

        assertThat(textInOrderConfirmedHeader, startsWith("Заказ оформлен"));

    }
    @Test
    public void checkOrderClickOnLowerButtonTest(){
        // переход на страницу тестового приложения
        driver.get(Url.URL_SCOOTER);

        //нажимаем на кнопку куки
        CookiePopup objCookiePopup = new CookiePopup(driver);
        objCookiePopup.clickCookieButton();

        //нажимаем на кнопку заказать
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickOrderButtonDown();

        //ожидаем загрузки первой страницы с формой Для кого самокат
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);
        objOrderFormPage.waitForLoadForm();

        //заполняем поля формы Для кого самокат
        objOrderFormPage.setName(name);
        objOrderFormPage.setSurname(surname);
        objOrderFormPage.setAddress(address);
        objOrderFormPage.setMetro(metro);
        objOrderFormPage.setTelephone(telephone);

        //кликакаем на кнопку далее страницы Для кого самокат
        objOrderFormPage.clickButtonNext();

        //ожидаем загрузки второй страницы с формой Про аренду
        objOrderFormPage.waitForLoadDetailsForm();

        //заполняем поля формы Про аренду

        objOrderFormPage.setDuration(duration);
        objOrderFormPage.setColor(color);
        objOrderFormPage.setComment(comment);
        objOrderFormPage.setDate();
        objOrderFormPage.clickOrderScooterButton();
        objOrderFormPage.clickYes();
        String textInOrderConfirmedHeader = objOrderFormPage.textInOrderConfirmedHeader();

        assertThat(textInOrderConfirmedHeader, startsWith("Заказ оформлен"));

    }
    @After
    public void teardown() {
        // Закрываем браузер
       driver.quit();
    }
}
