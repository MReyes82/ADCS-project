package Base;

import Pages.AuthPage;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.InventoryPage;
import Utils.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Base comun para los tests de matriz: crea el navegador, instancia pages y concentra flujos repetidos.
 */
public abstract class BaseTest
{
    protected WebDriver driver;
    protected AuthPage authPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(createChromeOptions());
        driver.manage().window().setSize(new Dimension(1366, 900));
        driver.get(TestData.BASE_URL);

        authPage = new AuthPage(driver, 10);
        inventoryPage = new InventoryPage(driver, 10);
        cartPage = new CartPage(driver, 10);
        checkoutPage = new CheckoutPage(driver, 10);
    }

    /**
     * Cierra el navegador despues de cada caso para aislar estado entre usuarios.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

    /**
     * Login reusable para pruebas que empiezan en inventario.
     */
    protected void loginAs(String username)
    {
        authPage.login(username, TestData.PASSWORD);
        authPage.waitForRedirection();
        inventoryPage.waitForInventory();
    }

    /**
     * Prepara el flujo comun de checkout con un producto en carrito.
     */
    protected void startCheckoutWithBackpack(String username)
    {
        loginAs(username);
        cartPage.addProduct(TestData.BACKPACK);
        cartPage.goToCart();
        checkoutPage.clickCheckout();
    }

    private ChromeOptions createChromeOptions()
    {
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--disable-search-engine-choice-screen");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--no-first-run");
        return chromeOptions;
    }
}
