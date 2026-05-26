package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Pagina de checkout con acciones para completar la compra y validar errores.
 */
public class CheckoutPage extends BasePage {

    private final By checkoutBtn = By.id("checkout");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By orderConfirmation = By.id("checkout_complete_container");
    private final By errorMessage = By.xpath("//div[@class='error-message-container error']");

    public CheckoutPage(WebDriver driver, int seconds) {
        super(driver, seconds);
    }

    /**
     * Inicia el flujo de checkout desde el carrito.
     */
    public void clickCheckout() {
        click(checkoutBtn);
    }

    /**
     * Completa el formulario de datos del comprador.
     * @param firstName nombre
     * @param lastName apellido
     * @param postalCode codigo postal
     */
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    /**
     * Continua al siguiente paso del checkout.
     */
    public void clickContinue() {
        click(continueBtn);
    }

    /**
     * Finaliza la compra.
     */
    public void clickFinish() {
        click(finishBtn);
    }

    /**
     * Espera la confirmacion de la orden.
     */
    public void waitForOrderConfirmation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
    }

    /**
     * Espera la visibilidad del mensaje de error.
     */
    public void waitForErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }

    /**
     * Verifica si un elemento clave del checkout esta visible.
     * @param elementName nombre logico del elemento
     * @return true si el elemento es visible
     */
    public boolean isElementDisplayed(String elementName) {
        By element = switch(elementName) {
            case "orderConfirmation" -> orderConfirmation;
            case "errorMessage" -> errorMessage;
            default -> throw new IllegalArgumentException("Unknown element: " + elementName);
        };
        return driver.findElement(element).isDisplayed();
    }
}
