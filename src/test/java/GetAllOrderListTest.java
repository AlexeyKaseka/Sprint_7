import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetAllOrderListTest {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        orderSteps = new OrderSteps();


    }


    @Test
    @DisplayName("Получение списков заказов")
    @Description("Позитивный тест: проверка успешного получения списка заказов")
    public void getAllOrderList() {
        orderSteps.getOrderList()
                .then()
                .statusCode(Constants.STATUS_CODE_OK)
                .body("orders", is(notNullValue()));

    }


}
