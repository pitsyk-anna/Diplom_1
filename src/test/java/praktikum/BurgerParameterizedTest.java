package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class BurgerParameterizedTest {
    private final float bunPrice;
    private final float ingredientSaucePrice;
    private final float ingredientFillingPrice;

    public BurgerParameterizedTest(float bunPrice, float ingredientSaucePrice, float ingredientFillingPrice) {
        this.bunPrice = bunPrice;
        this.ingredientSaucePrice = ingredientSaucePrice;
        this.ingredientFillingPrice = ingredientFillingPrice;
    }

    @Parameterized.Parameters(name = "price = {0}")
    public static Object[][] getPriceBurger() {
        return new Object[][]{
                {1f, 2f, 3f},
                {10f, 20f, 30f},
                {100f, 200f, 300f},
                {1000f, 2000f, 3000f},
                {100.5f, 200.5f, 300.5f}
        };
    }
    @Test
        public void getPriceTest() {
        Bun bun = new Bun("Тестовая булка", bunPrice);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Тестовый соус", ingredientSaucePrice);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "Тестовая начинка", ingredientFillingPrice);

        Burger burger= new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expected = bunPrice * 2 + ingredientSaucePrice + ingredientFillingPrice;
        float actual = burger.getPrice();

        assertEquals("Ожидаемый и фактический результаты не совпадают", expected, actual, 0.01f);
        }
}
