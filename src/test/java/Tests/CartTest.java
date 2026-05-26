package Tests;

import Base.BaseTest;
import Utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Pruebas de carrito alineadas con la matriz funcional.
 */
public class CartTest extends BaseTest
{
    @Test(priority = 1) // P-CART-05P
    public void pCart05PAddAllProductsWithStandardUser()
    {
        loginAs(TestData.STANDARD_USER);
        cartPage.addProducts(TestData.ALL_PRODUCTS);

        Assert.assertEquals(cartPage.getCartBadgeCount(), TestData.ALL_PRODUCTS.size());
        Assert.assertTrue(cartPage.areProductButtonsRemove(TestData.ALL_PRODUCTS));
    }

    @Test(priority = 2) // P-CART-05N
    public void pCart05NAddAllProductsWithProblemUser()
    {
        loginAs(TestData.PROBLEM_USER);
        cartPage.addProducts(TestData.ALL_PRODUCTS);

        Assert.assertEquals(cartPage.getCartBadgeCount(), TestData.ALL_PRODUCTS.size());
        Assert.assertTrue(cartPage.areProductButtonsRemove(TestData.ALL_PRODUCTS));
    }

    @Test(priority = 3) // P-CART-01P
    public void pCart01PAddSpecificProduct()
    {
        loginAs(TestData.STANDARD_USER);
        cartPage.addProduct(TestData.BACKPACK);

        Assert.assertEquals(cartPage.getCartBadgeCount(), 1);
        Assert.assertTrue(cartPage.isProductButtonRemove(TestData.BACKPACK));
    }

    @Test(priority = 4) // P-CART-02P
    public void pCart02PRemoveSpecificProductFromInventory()
    {
        loginAs(TestData.STANDARD_USER);
        cartPage.addProduct(TestData.BACKPACK);
        cartPage.removeProduct(TestData.BACKPACK);

        Assert.assertEquals(cartPage.getCartBadgeCount(), 0);
        Assert.assertEquals(cartPage.getProductButtonText(TestData.BACKPACK), "Add to cart");
    }

    @Test(priority = 5) // P-CART-03P
    public void pCart03PAddFourthProductWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        cartPage.addProducts(TestData.THREE_ERROR_USER_PRODUCTS);
        cartPage.addProduct(TestData.BOLT_T_SHIRT);

        Assert.assertEquals(cartPage.getCartBadgeCount(), 4);
        Assert.assertTrue(cartPage.isProductButtonRemove(TestData.BOLT_T_SHIRT));
    }

    @Test(priority = 6) // P-CART-04P
    public void pCart04PRemoveSpecificProductFromInventoryWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        cartPage.addProduct(TestData.BACKPACK);
        cartPage.removeProduct(TestData.BACKPACK);

        Assert.assertEquals(cartPage.getCartBadgeCount(), 0);
        Assert.assertEquals(cartPage.getProductButtonText(TestData.BACKPACK), "Add to cart");
    }

    @Test(priority = 7) // P-CART-06P
    public void pCart06PRemoveSpecificProductFromCartWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        cartPage.addProduct(TestData.BACKPACK);
        cartPage.goToCart();
        cartPage.removeProductFromCart(TestData.BACKPACK);

        Assert.assertEquals(cartPage.getCartBadgeCount(), 0);
        Assert.assertTrue(cartPage.isCartEmpty());
        Assert.assertFalse(cartPage.isProductInCart(TestData.BACKPACK));
    }

    @Test(priority = 8) // P-CART-07P
    public void pCart07PRemoveSpecificProductFromDetailWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        cartPage.addProduct(TestData.BACKPACK);
        cartPage.openProductDetails(TestData.BACKPACK);
        cartPage.removeProductFromDetail();

        Assert.assertEquals(cartPage.getCartBadgeCount(), 0);
        Assert.assertEquals(cartPage.getDetailButtonText(), "Add to cart");
    }
}
