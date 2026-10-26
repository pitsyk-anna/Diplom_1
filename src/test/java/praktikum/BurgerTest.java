package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;
    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientSauce;

    @Mock
    private Ingredient ingredientFilling;

    @Before
    public void setUp() {
        burger = new Burger();
    }
    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals("Не та булочка, которую ожидаем", bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientSauce);
        assertEquals("Ингредиент не добавлен", List.of(ingredientSauce), burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.removeIngredient(0); //удаляем первый ингердиент
        assertEquals("Должен остаться один ингредиент", 1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.moveIngredient(0, 1);
        assertEquals("Начинка должна быть на первом месте", ingredientFilling, burger.ingredients.get(0));
        assertEquals("Соус должен быть на втором месте", ingredientSauce, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptTest() {
        //Стабы
        //Булочка
        Mockito.when(bun.getName()).thenReturn("Краторная булка N-200i");
        Mockito.when(bun.getPrice()).thenReturn(1255.0f);

        //Соус
        Mockito.when(ingredientSauce.getName()).thenReturn("Соус Spicy-X");
        Mockito.when(ingredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientSauce.getPrice()).thenReturn(90.0f);

        //Начинка
        Mockito.when(ingredientFilling.getName()).thenReturn("Хрустящие минеральные кольца");
        Mockito.when(ingredientFilling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientFilling.getPrice()).thenReturn(300.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        //ОР цена
        float expectedPrice = 2900.0f;
        String priceString = String.format("%f", expectedPrice);

        //ОР чек
        String expected = "(==== Краторная булка N-200i ====)\r\n"
                + "= sauce Соус Spicy-X =\r\n"
                + "= filling Хрустящие минеральные кольца =\r\n"
                + "(==== Краторная булка N-200i ====)\r\n"
                + "\r\nPrice: " + priceString + "\r\n";
        //ФР
        String actual = burger.getReceipt();

        assertEquals("Ожидаемый и фактический результаты не совпадают", expected, actual);
    }
    }

