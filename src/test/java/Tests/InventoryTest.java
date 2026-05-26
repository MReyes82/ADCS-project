package Tests;

import Base.BaseTest;
import Utils.TestData;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Pruebas de filtros de inventario alineadas con la matriz funcional.
 */
public class InventoryTest extends BaseTest
{
    @Test(priority = 1) // P-FILTER-01P
    public void pFilter01PLowToHighWithStandardUser()
    {
        loginAs(TestData.STANDARD_USER);
        inventoryPage.selectSortOption("lohi");

        assertPricesAscending();
    }

    @Test(priority = 2) // P-FILTER-01N
    public void pFilter01NLowToHighWithProblemUser()
    {
        loginAs(TestData.PROBLEM_USER);
        inventoryPage.selectSortOption("lohi");

        assertPricesAscending();
    }

    @Test(priority = 3) // P-FILTER-02P
    public void pFilter02PHighToLowWithStandardUser()
    {
        loginAs(TestData.STANDARD_USER);
        inventoryPage.selectSortOption("hilo");

        assertPricesDescending();
    }

    @Test(priority = 4) // P-FILTER-02N
    public void pFilter02NHighToLowWithProblemUser()
    {
        loginAs(TestData.PROBLEM_USER);
        inventoryPage.selectSortOption("hilo");

        assertPricesDescending();
    }

    @Test(priority = 5) // P-FILTER-03P
    public void pFilter03PNameAtoZWithStandardUser()
    {
        loginAs(TestData.STANDARD_USER);
        prepareDifferentNameOrder();
        inventoryPage.selectSortOption("az");

        assertNamesAscending();
    }

    @Test(priority = 6) // P-FILTER-03N
    public void pFilter03NNameAtoZWithProblemUser()
    {
        loginAs(TestData.PROBLEM_USER);
        prepareDifferentNameOrder();
        inventoryPage.selectSortOption("az");

        assertNamesAscending();
    }

    @Test(priority = 7) // P-FILTER-04P
    public void pFilter04PNameZtoAWithStandardUser()
    {
        loginAs(TestData.STANDARD_USER);
        inventoryPage.selectSortOption("za");

        assertNamesDescending();
    }

    @Test(priority = 8) // P-FILTER-04N
    public void pFilter04NNameZtoAWithProblemUser()
    {
        loginAs(TestData.PROBLEM_USER);
        inventoryPage.selectSortOption("za");

        assertNamesDescending();
    }

    @Test(priority = 9) // P-FILTER-05P
    public void pFilter05PLowToHighWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        inventoryPage.selectSortOption("lohi");
        dismissAlertIfPresent();

        assertPricesAscending();
    }

    @Test(priority = 10) // P-FILTER-05N
    public void pFilter05NLowToHighExceptionWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        inventoryPage.selectSortOption("lohi");

        Assert.assertTrue(inventoryPage.isAlertPresent(), "El sistema debe mostrar un prompt de error controlado.");
        inventoryPage.dismissAlert();
        Assert.assertEquals(inventoryPage.getProductNames(), TestData.ALL_PRODUCTS);
    }

    @Test(priority = 11) // P-FILTER-06P
    public void pFilter06PHighToLowWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        inventoryPage.selectSortOption("hilo");
        dismissAlertIfPresent();

        assertPricesDescending();
    }

    @Test(priority = 12) // P-FILTER-06N
    public void pFilter06NHighToLowExceptionWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        inventoryPage.selectSortOption("hilo");

        Assert.assertTrue(inventoryPage.isAlertPresent(), "El sistema debe mostrar un prompt de error controlado.");
        inventoryPage.dismissAlert();
        Assert.assertEquals(inventoryPage.getProductNames(), TestData.ALL_PRODUCTS);
    }

    @Test(priority = 13) // P-FILTER-07P
    public void pFilter07PNameAtoZWithErrorUserBlocked()
    {
        throw new SkipException("Caso bloqueado: no es posible colocar el listado en un orden distinto por falla previa de ordenamiento con error_user.");
    }

    @Test(priority = 14) // P-FILTER-07N
    public void pFilter07NNameAtoZExceptionWithErrorUserBlocked()
    {
        throw new SkipException("Caso bloqueado: no es posible cumplir la precondicion de orden distinto para validar Name A to Z con error_user.");
    }

    @Test(priority = 15) // P-FILTER-08P
    public void pFilter08PNameZtoAWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        inventoryPage.selectSortOption("za");
        dismissAlertIfPresent();

        assertNamesDescending();
    }

    @Test(priority = 16) // P-FILTER-08N
    public void pFilter08NNameZtoAExceptionWithErrorUser()
    {
        loginAs(TestData.ERROR_USER);
        inventoryPage.selectSortOption("za");

        Assert.assertTrue(inventoryPage.isAlertPresent(), "El sistema debe mostrar un prompt de error controlado.");
        inventoryPage.dismissAlert();
        Assert.assertEquals(inventoryPage.getProductNames(), TestData.ALL_PRODUCTS);
    }

    private void prepareDifferentNameOrder()
    {
        inventoryPage.selectSortOption("za");
        Assert.assertTrue(isNamesDescending(), "No fue posible preparar el listado en Name Z to A para comprobar el cambio posterior a Name A to Z.");
    }

    private void assertPricesAscending()
    {
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> expected = new ArrayList<>(prices);
        expected.sort(Comparator.naturalOrder());
        Assert.assertEquals(prices, expected, "Los productos deben ordenarse por precio de menor a mayor.");
    }

    private void assertPricesDescending()
    {
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> expected = new ArrayList<>(prices);
        expected.sort(Comparator.reverseOrder());
        Assert.assertEquals(prices, expected, "Los productos deben ordenarse por precio de mayor a menor.");
    }

    private void assertNamesAscending()
    {
        List<String> names = inventoryPage.getProductNames();
        List<String> expected = new ArrayList<>(names);
        expected.sort(Comparator.naturalOrder());
        Assert.assertEquals(names, expected, "Los productos deben ordenarse alfabeticamente de A a Z.");
    }

    private void assertNamesDescending()
    {
        Assert.assertTrue(isNamesDescending(), "Los productos deben ordenarse alfabeticamente de Z a A.");
    }

    private boolean isNamesDescending()
    {
        List<String> names = inventoryPage.getProductNames();
        List<String> expected = new ArrayList<>(names);
        expected.sort(Comparator.reverseOrder());
        return names.equals(expected);
    }

    private void dismissAlertIfPresent()
    {
        if (inventoryPage.isAlertPresent())
        {
            inventoryPage.dismissAlert();
        }
    }
}
