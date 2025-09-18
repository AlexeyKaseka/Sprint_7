import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.practicum.Courier;

import static io.restassured.RestAssured.given;


public class CourierSteps {
    @Step("Создание курьера")
    public Response createCourier(Courier courier) {
        return given()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .body(courier).when().post(Constants.CREATE_COURIR_ENDPOINT);
    }

    @Step("Авторизация курьера")
    public Response authorisationCoirier(Courier courier) {
        return given()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .body(courier).when().post(Constants.LOGIN_COURIER_ENDPOINT);
    }

    @Step("Получение id курьера после успешной авторизации")
    public int getCourierId(Courier courier) {
        Response response = authorisationCoirier(courier);
        return response.then().extract().path("id");
    }

    @Step("Удаление курьера")
    public Response deleteCourier(int courierID) {
        return given()
                .header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .delete(Constants.DELETE_COURIER_ENDPOIT + courierID);
    }

}

