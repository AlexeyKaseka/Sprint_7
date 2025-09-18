import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.practicum.Order;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Создание заказа")
    public Response createOrder(Order order) {
        return given()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .body(order).when().post(Constants.CREATE_ORDER_ENDPOINT);
    }

    @Step("Получение списка заказов")
    public Response getOrderList() {
        return given()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .when().get(Constants.GET_LIST_ORDER_ENDPOINT);
    }

    @Step("Получение трек номера заказа")
    public int getOrderTrackNumber(Order order) {
        Response response = createOrder(order);
        return response.then().extract().path("track");
    }

    @Step("Отмена заказа")
    public Response cancelOrder(int trackNumber) {
        return given()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .put(Constants.CANCEL_ORDER_ENDPOINT + trackNumber);
    }


}
