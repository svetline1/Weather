import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Svetline1Test {

    //TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"



    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']"
                )
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();


        WebElement h2CityCountryNameHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );


        Thread.sleep(2000);
        String actualResult = h2CityCountryNameHeader.getText();
        //Thread.sleep(5000);

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

//    Все теcты необходимо писать
//    в проекте Weather (Maven/TestNG 7.4.0/Selenium3.141.59),
//    в зеленой папке java,
//    в тестовом классе YourNickOnGitHubTest.
//
//    Для всех тест кейсов -
//            System.setProperty("webdriver.chrome.driver", "YouPathToFile/chromedriver");
//    WebDriver driver = new ChromeDriver();
//
//    String url = "https://openweathermap.org/";
//
//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что
//    title этой страницы OpenWeatherMap API guide - OpenWeatherMap


    @Test
    public void WhenClickHeaderMenuGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement headerMenuGuide = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[@href='/guide']")
        );

        headerMenuGuide.click();
        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        Thread.sleep(2000);

        driver.quit();
    }

//    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void changeTemperatureModeToImperial() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String TemperatureMode = "Imperial: °F, mph";
        String expectedResult = "F";

        driver.get(url);
        Thread.sleep(5000);

        WebElement switchTemperatureModeContainer = driver.findElement(
                By.xpath("//div[@id='weather-widget']//div[@class='switch-container']/div[text() = 'Imperial: °F, mph']")
        );

        switchTemperatureModeContainer.click();
        Thread.sleep(3000);

        WebElement newTempearture = driver.findElement(
                By.xpath("//div [@class='grid-container grid-4-5']/div/div/div/span")
        );

        String actualResult = newTempearture.getText();

        Thread.sleep(2000);

        Assert.assertTrue((new String(actualResult)).contains(expectedResult));

        driver.quit();
    }


//    TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
// We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
// You can allow all cookies or manage them individually.”
// 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void footerContainsCookiesPanelAndTwoButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResultText = "We use cookies which are essential for the site to work."
                + " We also use non-essential cookies to help us improve our services. Any data "
                + "collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResultButton1 = "Allow all";
        String expectedResultButton2 = "Manage cookies";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

//        WebElement footerContainsCookiesPanelText = driver.findElement(
//                By.xpath("//p [@class='stick-footer-panel__description']")
//        );

        WebElement footerContainsCookiesPanelText = driver.findElement(
                By.className("stick-footer-panel__description")
        );


        WebElement footerContainsButton_AllowAll = driver.findElement(
                By.xpath("//div [@class='stick-footer-panel__btn-container']/button")
        );

        WebElement footerContainsButton_ManageCookies = driver.findElement(
                By.xpath("//div [@class='stick-footer-panel__btn-container']/a")
        );

        String actualResultText = footerContainsCookiesPanelText.getText();
        String actualResultButton1 = footerContainsButton_AllowAll.getText();
        String actualResultButton2 = footerContainsButton_ManageCookies.getText();

        Assert.assertEquals(actualResultText, expectedResultText);
        Assert.assertEquals(actualResultButton1, expectedResultButton1);
        Assert.assertEquals(actualResultButton2, expectedResultButton2);


        Thread.sleep(2000);

        driver.quit();
    }

//    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start”
// и “Ask a question”


    @Test
    public void menuSupportHasThreeSubmenues() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String menuName = "Support";

        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//nav [@id='nav-website']//div[@id='support-dropdown']")
        );

        menuSupport.click();

        WebElement submenuNameFAQ = driver.findElement(
                By.xpath("//a[text() = 'FAQ']")
        );

        WebElement submenuNameHowToStart = driver.findElement(
                By.xpath("//a[text() = 'How to start']")
        );

        WebElement submenuNameAskQuestion = driver.findElement(
                By.xpath("//a[text() = 'Ask a question']")
        );


        String actualResult1 = submenuNameFAQ.getText();
        String actualResult2 = submenuNameHowToStart.getText();
        String actualResult3 = submenuNameAskQuestion.getText();


        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        Thread.sleep(2000);

        driver.quit();
    }


//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testCaptchaInMenuSupportAskAQuestion() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "tester@test.com";
        //String Subject = "Subject";
        String Message = "MessageMessage MessageMessage MessageMessage";

        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//nav [@id='nav-website']//div[@id='support-dropdown']")
        );

        menuSupport.click();

        Thread.sleep(500);

        WebElement askAQuestionMenu = driver.findElement(By.linkText("Ask a question"));
        askAQuestionMenu.click();

        driver.get("https://home.openweathermap.org/questions");

        WebElement emailField = driver.findElement(By.id("question_form_email"));
        emailField.click();
        emailField.sendKeys(email);

        WebElement subjectField = driver.findElement(By.id("question_form_subject"));
        emailField.click();








        //Thread.sleep(2000);

        driver.quit();
    }

//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С


    @Test
    public void changeTemperatureFromImperialToMetric() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String imperialTemperature = "Imperial: °F, mph";
        String metricTemperature = "Metric: °C, m/s";

        String expectedResult1 = "F";
        String expectedResult2 = "C";

        driver.get(url);
        Thread.sleep(5000);

        WebElement imperialTemp = driver.findElement(By.xpath("//div [@class='switch-container']"
                + "/div [text() = 'Imperial: °F, mph']"));

        imperialTemp.click();

        String actualResult1 = imperialTemp.getText();

        WebElement metricTemp = driver.findElement(By.xpath("//div [@class='switch-container']"
                + "/div [text() = 'Metric: °C, m/s']"));

        imperialTemp.click();

        String actualResult2 = metricTemp.getText();

        Assert.assertTrue((new String(actualResult1)).contains(expectedResult1));
        Assert.assertTrue((new String(actualResult2)).contains(expectedResult2));

        driver.quit();
    }

//    TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void tapCompanyLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(5000);

        WebElement CompanyLogo = driver.findElement(By.xpath("//img [@src='/themes/openweathermap/assets/"
                + "img/logo_white_cropped.png']"));

        CompanyLogo.click();
        Thread.sleep(5000);

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

//    TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок

    @Test
    public void countOrangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        int expectedResult = 30;

        driver.get(url);
        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//a [@href='/api']")
        );

        menuSupport.click();

        Thread.sleep(500);

        int actualResult = driver.findElements(By.xpath("//a [@class='btn_block orange round']")).size();

        Assert.assertEquals(actualResult,expectedResult);


        driver.quit();
    }











}
