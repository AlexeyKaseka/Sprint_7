import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.Courier;

import static org.hamcrest.CoreMatchers.is;

public class CreateCourierTest {
    private CourierSteps courierSteps;
    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;

        courierSteps = new CourierSteps();


    }

    @Test
    @DisplayName("Создание курьера с валидными данными")
    @Description("Позитивный тест: проверка успешного создания курьера с полными данными (логин, пароль, имя)")
    public void createCourierWithValidData() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.COURIER_PASSWORD,
                Constants.COURIER_FIRST_NAME
        );

        courierSteps.createCourier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_CREATE)
                .body("ok", is(true));

    }

    @Test
    @DisplayName("Создание курьера без имени")
    @Description("Позитивный тест: проверка успешного создания курьера с не полными данными (логин, пароль)")
    public void createCourierWithOutFirstName() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.COURIER_PASSWORD,
                null
        );
        courierSteps.createCourier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_CREATE)
                .body("ok", is(true));

    }


    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Негативный тест: проверка не успешного создания курьера с не полными данными (пароль, имя)")
    public void createCourierWithOutLogin() {
        courier = new Courier(
                null,
                Constants.COURIER_PASSWORD,
                Constants.COURIER_FIRST_NAME
        );
        courierSteps.createCourier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_BAD_REQUEST)
                .body("message", is(Constants.COURIER_BAD_REQUEST_MESSEGE));


    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Негативный тест: проверка не успешного создания курьера с не полными данными (логин, имя)")
    public void createCourierWithOutPassword() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                null,
                Constants.COURIER_FIRST_NAME
        );
        courierSteps.createCourier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_BAD_REQUEST)
                .body("message", is(Constants.COURIER_BAD_REQUEST_MESSEGE));

    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Негативный тест: проверка не успешного создания двух одинаковых курьеров")
    public void createTwoIdenticalCourier() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.COURIER_PASSWORD,
                Constants.COURIER_FIRST_NAME
        );
        courierSteps.createCourier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_CREATE)
                .body("ok", is(true));

        courierSteps.createCourier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_CONFLICT)
                .body("message", is(Constants.COURIER_CONFLICT_MESSAGE));

    }


    @After
    public void tearDown() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.COURIER_PASSWORD
        );
        Response loginResponse = courierSteps.authorisationCoirier(courier);

        if (loginResponse.getStatusCode() == Constants.STATUS_CODE_OK) {
            int courierId = courierSteps.getCourierId(courier);
            courierSteps.deleteCourier(courierId);
        }

    }


}