package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

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
        //создание мок
        Bun bunMock = Mockito.mock(Bun.class);
        Ingredient sauceMock = Mockito.mock(Ingredient.class);
        Ingredient fillingMock = Mockito.mock(Ingredient.class);
        //настройка моков
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);
        Mockito.when(sauceMock.getPrice()).thenReturn(ingredientSaucePrice);
        Mockito.when(fillingMock.getPrice()).thenReturn(ingredientFillingPrice);

        //передаем моки в объект
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        float expected = bunPrice * 2 + ingredientSaucePrice + ingredientFillingPrice;
        float actual = burger.getPrice();

        assertEquals("Ожидаемый и фактический результаты не совпадают", expected, actual, 0.01f);
    }
}
