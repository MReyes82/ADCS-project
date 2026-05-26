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
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    /**
     * Captura solo el nombre para poder validar campos de forma independiente.
     */
    public void enterFirstName(String firstName)
    {
        clearAndType(firstNameField, firstName);
    }

    /**
     * Captura solo el apellido; los defectos de problem_user/error_user se evidencian aqui.
     */
    public void enterLastName(String lastName)
    {
        clearAndType(lastNameField, lastName);
    }

    /**
     * Captura el codigo postal del formulario de checkout.
     */
    public void enterPostalCode(String postalCode)
    {
        clearAndType(postalCodeField, postalCode);
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
     * Confirma la finalizacion de compra sin usar un selector generico por nombre.
     */
    public boolean isOrderConfirmationDisplayed()
    {
        return isDisplayed(orderConfirmation);
    }

    /**
     * Devuelve false si el error no existe, evitando NoSuchElementException en casos negativos.
     */
    public boolean isErrorMessageDisplayed()
    {
        return isDisplayed(errorMessage);
    }

    public String getFirstNameValue()
    {
        return getAttribute(firstNameField, "value");
    }

    public String getLastNameValue()
    {
        return getAttribute(lastNameField, "value");
    }

    public String getPostalCodeValue()
    {
        return getAttribute(postalCodeField, "value");
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }
}
