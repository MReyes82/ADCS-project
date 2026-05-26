package Tests;

import Base.BaseTest;
import Utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Pruebas de autenticacion alineadas con la matriz funcional.
 */
public class AuthTest extends BaseTest
{
    @Test(priority = 1) // P-AUTH-01P
    public void pAuth01PLoginWithValidCredentials()
    {
        authPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        authPage.waitForRedirection();

        Assert.assertEquals(driver.getCurrentUrl(), TestData.INVENTORY_URL);
    }

    @Test(priority = 2) // P-AUTH-01N
    public void pAuth01NLoginWithWrongPassword()
    {
        authPage.login(TestData.STANDARD_USER, TestData.WRONG_PASSWORD);
        authPage.waitForErrorH3();

        Assert.assertEquals(driver.getCurrentUrl(), TestData.BASE_URL);
        Assert.assertTrue(authPage.isH3ErrorDisplayed());
    }

    @Test(priority = 3) // P-AUTH-02N
    public void pAuth02NLoginWithLockedUser()
    {
        authPage.login(TestData.LOCKED_OUT_USER, TestData.PASSWORD);
        authPage.waitForErrorH3();

        Assert.assertEquals(driver.getCurrentUrl(), TestData.BASE_URL);
        Assert.assertTrue(authPage.isH3ErrorDisplayed());
    }
}
