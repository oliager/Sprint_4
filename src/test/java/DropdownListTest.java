import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import page.objects.CookiePopup;
import page.objects.HomePage;
import utils.Utils;

import static org.hamcrest.CoreMatchers.is;

//класс с автотестом вопросов и ответов выпадающего списка
@RunWith(Parameterized.class)
public class DropdownListTest {
    private final int numberOfQuestion;
    private final String question;
    private final String answer;
    private WebDriver driver;

    public DropdownListTest(int numberOfQuestion, String question, String answer){
        this.numberOfQuestion = numberOfQuestion;
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkDropdownListTest(){
        // переход на страницу тестового приложения
        driver.get(Utils.URL_SCOOTER);

        //нажимаем на кнопку куки
        CookiePopup objCookiePopup = new CookiePopup(driver);
        objCookiePopup.clickCookieButton();

        HomePage objHomePage = new HomePage(driver);

        //нажимаем на вопрос выпадающего списка
        objHomePage.clickDropdownListButton(numberOfQuestion);
        //сохраняем фактический текст вопроса
        String actualQuestion = objHomePage.getTextOfQuestion(numberOfQuestion);
        //сравниваем фактический и ожидаемый текст вопроса
        MatcherAssert.assertThat(actualQuestion, is(question));
        //сохраняем фактический текст ответа
        String actualAnswer = objHomePage.getTextOfAnswer(numberOfQuestion);
        //сравниваем фактический и ожидаемый текст ответа
        MatcherAssert.assertThat(actualAnswer, is(answer));

    }
    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
