package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Pagina del carrito con acciones para agregar, quitar y validar productos.
 */
public class CartPage extends BasePage {

    private final By addToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeCartBtn = By.id("remove-sauce-labs-backpack");
    private final By goToCartLink = By.xpath("//a[@class='shopping_cart_link']");
    private final By badgeCart = By.xpath("//span[@class='shopping_cart_badge']");
    private final By cardItem = By.xpath("//div[@class='cart_item_label']");
    private final By cartItem = By.cssSelector(".cart_item");
    private final By detailButton = By.cssSelector(".inventory_details_desc_container button");

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

    public void addProduct(String productName)
    {
        click(productButton(productName));
    }

    public void addProducts(List<String> productNames)
    {
        for (String productName : productNames)
        {
            addProduct(productName);
        }
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

    public int getCartBadgeCount()
    {
        if (!isPresent(badgeCart))
        {
            return 0;
        }
        return Integer.parseInt(getText(badgeCart));
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

    public String getProductButtonText(String productName)
    {
        return getText(productButton(productName));
    }

    public boolean isProductButtonRemove(String productName)
    {
        return "Remove".equals(getProductButtonText(productName));
    }

    public boolean areProductButtonsRemove(List<String> productNames)
    {
        for (String productName : productNames)
        {
            if (!isProductButtonRemove(productName))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Quita el producto del carrito.
     */
    public void removeFromCart(){
        click(removeCartBtn);
    }

    public void removeProduct(String productName)
    {
        click(productButton(productName));
    }

    public void removeProductFromCart(String productName)
    {
        click(cartProductButton(productName));
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

    public boolean isCartEmpty()
    {
        return findElements(cartItem).isEmpty();
    }

    public boolean isProductInCart(String productName)
    {
        return isPresent(cartProductName(productName));
    }

    public void openProductDetails(String productName)
    {
        click(productNameLink(productName));
    }

    public void removeProductFromDetail()
    {
        click(detailButton);
    }

    public String getDetailButtonText()
    {
        return getText(detailButton);
    }

    private By productButton(String productName)
    {
        return By.xpath("//div[contains(@class,'inventory_item')][.//div[contains(@class,'inventory_item_name') and normalize-space()="
                + xpathText(productName) + "]]//button");
    }

    private By productNameLink(String productName)
    {
        return By.xpath("//div[contains(@class,'inventory_item_name') and normalize-space()="
                + xpathText(productName) + "]");
    }

    private By cartProductName(String productName)
    {
        return By.xpath("//div[contains(@class,'cart_item')][.//div[contains(@class,'inventory_item_name') and normalize-space()="
                + xpathText(productName) + "]]");
    }

    private By cartProductButton(String productName)
    {
        return By.xpath("//div[contains(@class,'cart_item')][.//div[contains(@class,'inventory_item_name') and normalize-space()="
                + xpathText(productName) + "]]//button");
    }

    private String xpathText(String text)
    {
        return "'" + text + "'";
    }
}
