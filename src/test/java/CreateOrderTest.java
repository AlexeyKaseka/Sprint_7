
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.practicum.Order;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private OrderSteps orderSteps;
    private Order order;


    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation,
                           String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name =
            "Тест #{index}: " +
                    "Имя заказчика: {0} | " +
                    "Фамилия заказчика: {1} | " +
                    "Адрес: {2} (м. {3}) | " +
                    "Телефон: {4} | " +
                    "Аренда дней: {5} | " +
                    "Дата доставки: {6} суток| " +
                    "Комментарий заказчика: {7} | " +
                    "цвет: {8} | "
    )

    public static Object[][] testData() {
        return Constants.ALL_ORDER_TEST_CASES;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        orderSteps = new OrderSteps();
        order = new Order(firstName, lastName, address, metroStation, phone,
                rentTime, deliveryDate, comment, color);


    }

    @Test
    @DisplayName("Создание списка заказов")
    @Description("Позитивный тест: проверка успешного создания списка заказа с полными данными")
    public void createOrderWithValidData() {
        orderSteps.createOrder(order)
                .then()
                .statusCode(Constants.STATUS_CODE_CREATE)
                .body("track", is(notNullValue()));

    }


    @After
    public void tearDown() {


        int trackNumber = orderSteps.getOrderTrackNumber(order);
        orderSteps.cancelOrder(trackNumber);


    }
}