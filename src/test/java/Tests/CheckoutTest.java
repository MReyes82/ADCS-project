package Tests;

import Base.BaseTest;
import Utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Pruebas de checkout alineadas con la matriz funcional.
 */
public class CheckoutTest extends BaseTest
{
    @Test(priority = 1) // P-CHOUT-01P
    public void pChout01PCompleteCheckoutWithStandardUser()
    {
        startCheckoutWithBackpack(TestData.STANDARD_USER);
        checkoutPage.fillCheckoutInfo(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        checkoutPage.waitForOrderConfirmation();

        Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed());
    }

    @Test(priority = 2) // P-CHOUT-02N
    public void pChout02NEmptyCheckoutFieldsWithStandardUser()
    {
        startCheckoutWithBackpack(TestData.STANDARD_USER);
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isErrorMessageDisplayed());
        Assert.assertEquals(checkoutPage.getCurrentUrl(), TestData.CHECKOUT_STEP_ONE_URL);
    }

    @Test(priority = 3) // P-CHOUT-03P
    public void pChout03PEnterPersonalDataWithProblemUser()
    {
        startCheckoutWithBackpack(TestData.PROBLEM_USER);
        checkoutPage.enterFirstName(TestData.FIRST_NAME);
        checkoutPage.enterLastName(TestData.LAST_NAME);
        checkoutPage.enterPostalCode(TestData.POSTAL_CODE);

        Assert.assertEquals(checkoutPage.getFirstNameValue(), TestData.FIRST_NAME);
        Assert.assertEquals(checkoutPage.getLastNameValue(), TestData.LAST_NAME);
        Assert.assertEquals(checkoutPage.getPostalCodeValue(), TestData.POSTAL_CODE);
    }

    @Test(priority = 4) // P-CHOUT-04P
    public void pChout04PEnterPersonalDataWithErrorUser()
    {
        startCheckoutWithBackpack(TestData.ERROR_USER);
        checkoutPage.enterFirstName(TestData.FIRST_NAME);
        checkoutPage.enterLastName(TestData.LAST_NAME);
        checkoutPage.enterPostalCode(TestData.POSTAL_CODE);

        Assert.assertEquals(checkoutPage.getFirstNameValue(), TestData.FIRST_NAME);
        Assert.assertEquals(checkoutPage.getLastNameValue(), TestData.LAST_NAME);
        Assert.assertEquals(checkoutPage.getPostalCodeValue(), TestData.POSTAL_CODE);
    }

    @Test(priority = 5) // P-CHOUT-05N
    public void pChout05NMissingLastNameWithErrorUser()
    {
        startCheckoutWithBackpack(TestData.ERROR_USER);
        checkoutPage.enterFirstName(TestData.FIRST_NAME);
        checkoutPage.enterPostalCode(TestData.POSTAL_CODE);
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.isErrorMessageDisplayed(), "El sistema debe mostrar error por campo Last Name vacio.");
        Assert.assertEquals(checkoutPage.getCurrentUrl(), TestData.CHECKOUT_STEP_ONE_URL);
    }
}
