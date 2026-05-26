package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Pagina del carrito con acciones para agregar, quitar y validar productos.
 */
public class CartPage extends BasePage {

    private final By goToCartLink = By.xpath("//a[@class='shopping_cart_link']");
    private final By badgeCart = By.xpath("//span[@class='shopping_cart_badge']");
    private final By cartItem = By.cssSelector(".cart_item");
    private final By detailButton = By.cssSelector(".inventory_details_desc_container button");

    public CartPage(WebDriver driver, int seconds)
    {
        super(driver, seconds);
    }

    /**
     * Agrega un producto localizando su tarjeta por nombre visible.
     */
    public void addProduct(String productName)
    {
        click(productButton(productName));
    }

    /**
     * Agrega varios productos para cubrir los casos de carrito completo.
     */
    public void addProducts(List<String> productNames)
    {
        for (String productName : productNames)
        {
            addProduct(productName);
        }
    }

    /**
     * SauceDemo oculta el badge cuando el carrito queda vacio; se normaliza a 0.
     */
    public int getCartBadgeCount()
    {
        if (!isPresent(badgeCart))
        {
            return 0;
        }
        return Integer.parseInt(getText(badgeCart));
    }

    /**
     * Obtiene el texto actual del boton asociado a un producto.
     */
    public String getProductButtonText(String productName)
    {
        return getText(productButton(productName));
    }

    /**
     * Valida que el producto agregado haya cambiado su boton a Remove.
     */
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
     * Remueve un producto desde la pagina de inventario.
     */
    public void removeProduct(String productName)
    {
        click(productButton(productName));
    }

    /**
     * Remueve un producto desde la vista del carrito.
     */
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

    public boolean isCartEmpty()
    {
        return findElements(cartItem).isEmpty();
    }

    /**
     * Confirma si un producto sigue listado en el carrito.
     */
    public boolean isProductInCart(String productName)
    {
        return isPresent(cartProductName(productName));
    }

    /**
     * Abre el detalle de producto para validar acciones desde esa pantalla.
     */
    public void openProductDetails(String productName)
    {
        click(productNameLink(productName));
    }

    /**
     * Remueve el producto desde su pantalla de detalle.
     */
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
