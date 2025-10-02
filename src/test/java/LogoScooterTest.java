import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.objects.CookiePopup;
import page.objects.HomePage;
import utils.Utils;

import static org.junit.Assert.assertEquals;

public class LogoScooterTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void goToHomePageOnLogoClickTest(){
        // переход на страницу тестового приложения
        driver.get(Utils.URL_SCOOTER);

        //нажимаем на кнопку куки
        CookiePopup objCookiePopup = new CookiePopup(driver);
        objCookiePopup.clickCookieButton();

        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickOrderButton("верхняя");
        //нажимаем на логотип самоката
        objHomePage.clickOnLogoScooter();
        //ждем загрузки домашней страницы
        objHomePage.waitForUrlOfHomePage();
        //сохраняем фактический урл в переменную
        String actualUrl = objHomePage.getActualUrl();
        //сравниваем ожидаемый и фактический урлы
        assertEquals(Utils.URL_SCOOTER,actualUrl);



    }
    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }

}
