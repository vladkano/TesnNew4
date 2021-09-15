package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import io.github.bonigarcia.wdm.WebDriverManager;
import order.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends TestBase {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.get(getUrl + "catalog/?utm_source=test&utm_medium=test&utm_campaign=test");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        basket = new Basket(driver);
        order = new Order(driver);
    }

    public void payConfirmAndHeaderCheck() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getPayHeader();
//        assertEquals("Заплатить", header);
        assertEquals("Оплата заказа", header.substring(0,13));
    }

    public void noPayConfirmAndHeaderCheck() {
        String code2 = order.getPhonePassword();
        order.confirmWithPassword(code2);
        String header = order.getOrderHeader();
        assertEquals("Мы приняли ваш заказ", header);
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дороже 5000
    @Test()
    public void courierDeliveryAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAllStrings("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void courierDeliveryAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithWhatsApp("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Санкт-Петербург", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: доставка курьером товар дешевле 5000(Платная доставка)
    @Test()
    public void courierDeliveryAndPhoneLessThan5000() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.orderWithAllStrings("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Нижний Новгород", "ул Ефремова, д 10", "2", "2", "2",
                "2", "Test Comment", "Test");
        assertTrue(finalPrice > price);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void courierDeliveryAndWALessThan5000() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithWhatsApp("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Санкт-Петербург", "пр-кт Просвещения, д 10", "2", "2", "2", "2", "Test Comment", "Test");
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Забрать в фирменном магазине
    //Цветной:
    @Test()
    public void tsvetnoyAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoy("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void tsvetnoyAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoyWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void tsvetnoyAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreTsvetnoySms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    //Метрополис:
    @Test()
    public void metropolisAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void metropolisAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void metropolisAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreMetropolisSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    //Атриум:
    @Test()
    public void atriumAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void atriumAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void atriumAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithCompanyStoreAtriumSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    //У Красного моста:
    @Test()
    public void redBridgeAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgePhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void redBridgeAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgeWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void redBridgeAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithRedBridgeSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    //Афимолл:
    @Test()
    public void afimollAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAfimollPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void afimollAndWA() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAfimollWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void afimollAndSms() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithAfimollSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
        payConfirmAndHeaderCheck();
    }

    //Проверка перехода к оплате заказа на сайте, способ доставки: Доставить в другую страну
    @Test()
    public void internationalAndPhone() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
        order.internationalWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Минск", "улица Пушкина 12", "Test");
        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
        assertTrue(finalPrice > price);
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void internationalAndWhatsApp() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.internationalWithPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
                "Рим", "Гладиаторов дом 20м", "Test");
        payConfirmAndHeaderCheck();
    }

    //Доставка до постомата
    //работает только для ЕКБ
    @Test()
    public void postomatAndRussian() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithPickPointPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Россия", "Екатеринбург", "родонитовая");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void postomatAndBelarus() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithPickPointSMS("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Беларусь\n", "Минск\n", "Ленина");
        payConfirmAndHeaderCheck();
    }

    @Test()
    public void postomatAndKazakhstan() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        order.orderWithPickPointWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест", "Казахстан\n", "Нур-Султан\n", "Петрова");
        payConfirmAndHeaderCheck();
    }


    //Оформление заказов
    //Тестовый заказ без оплаты, способ доставки: доставка курьером товар дороже 5000:
//    @Test()
//    public void noPayCourierDeliveryAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.orderWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
//                "Сочи", "ул Горького, д 87", "2а", "", "1",
//                "нет", "Test Comment2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayCourierDeliveryAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.orderWithDvdNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: доставка курьером товар дешевле 5000(Платная доставка):
//    @Test()
//    public void noPayCourierDeliveryAndPhoneLessThan5000() {
//        basket.clickToAnotherItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        int price = parseInt(order.getFirstPrice().replaceAll("[^A-Za-z0-9]", ""));
//        int finalPrice = parseInt(order.getFinalPrice().replaceAll("[^A-Za-z0-9]", ""));
//        order.orderWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
//                "Сочи", "ул Горького, д 87", "2а", "", "1",
//                "нет", "Test Comment2");
//        assertTrue(finalPrice > price);
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayCourierDeliveryAndWALessThan5000() {
//        basket.clickToAnotherItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.orderWithDvdNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест",
//                "Москва", "Рублёвское шоссе, д 1", "", "", "", "2", "Москва2");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Цветной
//    @Test()
//    public void noPayTsvetnoyAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.tsvetnoyWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayTsvetnoyAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.tsvetnoyWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayTsvetnoyAndSms() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.tsvetnoyWithNoPayAndSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Метрополис
//    @Test()
//    public void noPayMetropolisAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.metropolisWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayMetropolisAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.metropolisWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayMetropolisAndSms() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.metropolisWithNoPayAndSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: У красного моста
//    @Test()
//    public void noPayRedBridgeAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.redBridgeWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayRedBridgeAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.redBridgeWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayRedBridgeAndSms() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.redBridgeWithNoPayAndSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Атриум
//    @Test()
//    public void noPayAtriumAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.atriumWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAtriumAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.atriumWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAtriumAndSMS() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.atriumWithNoPayAndSMS("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    //Тестовый заказ без оплаты, способ доставки: Афимолл
//    @Test()
//    public void noPayAfimollAndPhone() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.afimollWithNoPayAndPhone("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAfimollAndWA() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.afimollWithNoPayAndWA("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }
//
//    @Test()
//    public void noPayAfimollAndSMS() {
//        basket.clickToItemButton();
//        basket.clickToItemInBasketButton();
//        basket.clickToBasketButton();
//        order.afimollWithNoPayAndSms("9126459328", "rundkvist@poisondrop.ru", "Александр Тест");
//        noPayConfirmAndHeaderCheck();
//    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }
}
