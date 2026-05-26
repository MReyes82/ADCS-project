package Utils;

import java.util.List;

public final class TestData
{
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";
    public static final String CHECKOUT_STEP_ONE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    public static final String CHECKOUT_STEP_TWO_URL = "https://www.saucedemo.com/checkout-step-two.html";

    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String ERROR_USER = "error_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String WRONG_PASSWORD = "secret_sauce123";

    public static final String BACKPACK = "Sauce Labs Backpack";
    public static final String BIKE_LIGHT = "Sauce Labs Bike Light";
    public static final String BOLT_T_SHIRT = "Sauce Labs Bolt T-Shirt";
    public static final String FLEECE_JACKET = "Sauce Labs Fleece Jacket";
    public static final String ONESIE = "Sauce Labs Onesie";
    public static final String TEST_ALL_THINGS_T_SHIRT = "Test.allTheThings() T-Shirt (Red)";

    public static final List<String> ALL_PRODUCTS = List.of(
            BACKPACK,
            BIKE_LIGHT,
            BOLT_T_SHIRT,
            FLEECE_JACKET,
            ONESIE,
            TEST_ALL_THINGS_T_SHIRT
    );

    public static final List<String> THREE_ERROR_USER_PRODUCTS = List.of(
            BACKPACK,
            BIKE_LIGHT,
            ONESIE
    );

    public static final String FIRST_NAME = "Pancho";
    public static final String LAST_NAME = "Perez";
    public static final String POSTAL_CODE = "12345";

    private TestData()
    {
    }
}
