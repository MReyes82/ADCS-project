package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Capa base de Page Object con esperas explicitas y utilidades comunes de Selenium.
 */
public abstract class BasePage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, int seconds)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    // Encontrar WebElement con wait explicito (ToBeClickable)
    protected WebElement findElement(By locator)
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findElements(By locator)
    {
        return driver.findElements(locator);
    }

    // Mandar señal de click al elemento (usando wait explicito)
    protected void click(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Mandar valor de string al elemento
    protected void type(By locator, String text)
    {
        findElement(locator).sendKeys(text);
    }

    protected void clearAndType(By locator, String text)
    {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Obtener el valor de texto de un WebElement
    protected String getText(By locator)
    {
        return findElement(locator).getText();
    }

    protected String getAttribute(By locator, String attributeName)
    {
        return findElement(locator).getAttribute(attributeName);
    }

    // Usar wait hasta que el elemento sea visible
    protected void waitForElementVisibility(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Revisa si el WebElement esta visible
    protected boolean isDisplayed(By locator)
    {
        try
        {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e)
        {
            return false;
        }
    }

    protected boolean isPresent(By locator)
    {
        return !findElements(locator).isEmpty();
    }

    // Usar espera explicita para el cambio de url
    protected void waitForCurrentUrl(String urlFragment)
    {
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    protected boolean waitForAlert(Duration timeout)
    {
        try
        {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    protected String acceptAlert()
    {
        try
        {
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            return alertText;
        } catch (NoAlertPresentException e)
        {
            return "";
        }
    }
}
