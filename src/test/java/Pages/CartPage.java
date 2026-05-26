package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Pagina del carrito con acciones para agregar, quitar y validar productos.
 */
public class CartPage extends BasePage {

    private final By addToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeCartBtn = By.id("remove-sauce-labs-backpack");
    private final By goToCartLink = By.xpath("//a[@class='shopping_cart_link']");
    private final By badgeCart = By.xpath("//span[@class='shopping_cart_badge']");
    private final By cardItem = By.xpath("//div[@class='cart_item_label']");

    public CartPage(WebDriver driver, int seconds)
    {
        super(driver, seconds);
    }

    /**
     * Agrega el producto configurado al carrito.
     */
    public void addToCart(){
        click(addToCartBtn);
    }

    /**
     * Espera a que el carrito refleje el producto agregado.
     */
    public void waitForCart(){
        wait.until(ExpectedConditions.and(
                ExpectedConditions.textToBePresentInElementLocated(badgeCart, "1"),
                ExpectedConditions.visibilityOfElementLocated(removeCartBtn)));

    }

    /**
     * Obtiene el numero mostrado en el badge del carrito.
     * @return texto del badge
     */
    public String getNumberBadgeCart(){
        return driver.findElement(badgeCart).getText();
    }

    /**
     * Verifica si un elemento del carrito esta visible segun su nombre clave.
     * @param elementName nombre logico del elemento
     * @return true si el elemento es visible
     */
    public boolean isElementDisplayed(String elementName){
        By element = switch(elementName) {
            case "removeBtn" -> removeCartBtn;
            case "addToCartBtn" -> addToCartBtn;
            case "cardItem" -> cardItem;
            default -> throw new IllegalArgumentException("Unknown element: " + elementName);
        };
        return driver.findElement(element).isDisplayed();
    }

    /**
     * Quita el producto del carrito.
     */
    public void removeFromCart(){
        click(removeCartBtn);
    }

    /**
     * Navega hacia el carrito desde el icono.
     */
    public void goToCart(){
        click(goToCartLink);
    }

    /**
     * Espera a que desaparezca el badge del carrito.
     */
    public void waitForBadge(){
        waitForElementInvisibility(badgeCart);
    }

    /**
     * Indica si el badge del carrito no esta presente.
     * @return true si no hay badge en el DOM
     */
    public boolean isBadgeCartDisplayed(){
        return driver.findElements(badgeCart).isEmpty();
    }

    /**
     * Indica si el item del carrito no esta presente.
     * @return true si no hay item en el DOM
     */
    public boolean isCardItemDisplayed(){
        return driver.findElements(cardItem).isEmpty();
    }

}
