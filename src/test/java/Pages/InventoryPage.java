package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Pagina de inventario con filtros y utilidades para obtener items.
 */
public class InventoryPage extends BasePage
{
    private final By filterDropdown = By.cssSelector(".product_sort_container");
    private final By inventoryList = By.xpath("//div[@class='inventory_list']");
    private final By inventoryListElement = By.cssSelector(".inventory_item");
    private final By inventoryItemName = By.cssSelector(".inventory_item_name");
    private final By inventoryItemPrice = By.cssSelector(".inventory_item_price");

    public InventoryPage(WebDriver driver, int seconds)
    {
        super(driver, seconds);
    }

    /**
     * Selecciona el ordenamiento usando los valores nativos del select de SauceDemo.
     */
    public void selectSortOption(String optionValue)
    {
        Select select = new Select(findElement(filterDropdown));
        select.selectByValue(optionValue);
    }

    /**
     * Espera a que la lista de inventario este visible.
     */
    public void waitForInventory()
    {
        waitForElementVisibility(inventoryList);
    }

    /**
     * Devuelve los nombres en el mismo orden en que se muestran en inventario.
     */
    public List<String> getProductNames()
    {
        List<String> names = new ArrayList<>();
        for (WebElement item : getInventoryItems())
        {
            names.add(item.findElement(inventoryItemName).getText());
        }
        return names;
    }

    /**
     * Convierte los precios visibles a numeros para validar ordenamientos.
     */
    public List<Double> getProductPrices()
    {
        List<Double> prices = new ArrayList<>();
        for (WebElement item : getInventoryItems())
        {
            String price = item.findElement(inventoryItemPrice).getText().replace("$", "");
            prices.add(Double.parseDouble(price));
        }
        return prices;
    }

    public boolean isAlertPresent()
    {
        return waitForAlert(Duration.ofSeconds(2));
    }

    /**
     * Acepta y cierra el alert del navegador.
     */
    public void dismissAlert()
    {
        acceptAlert();
    }

    private List<WebElement> getInventoryItems()
    {
        return findElement(inventoryList).findElements(inventoryListElement);
    }
}
