import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import page.objects.CookiePopup;
import page.objects.HomePage;
import page.objects.Url;

//класс с автотестом выпадающего списка
public class DropdownListTest {
    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
    }


    @Test
    public void checkDropdownListTest(){
        // переход на страницу тестового приложения
        driver.get(Url.URL_SCOOTER);

        //нажимаем на кнопку куки
        CookiePopup objCookiePopup = new CookiePopup(driver);
        objCookiePopup.clickCookieButton();

        //нажимаеи на стрелку выпадающего списка
        HomePage objHomePage = new HomePage(driver);

        for(int i=0; i<objHomePage.getElements(); i++){
            objHomePage.clickDropdownListButton(i);
            objHomePage.checkTextNotHidden(i);
        }
    }
    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
