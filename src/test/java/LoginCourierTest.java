
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.Courier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {

    private CourierSteps courierSteps;
    private Courier courier;


    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;

        courierSteps = new CourierSteps();
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.COURIER_PASSWORD,
                Constants.COURIER_FIRST_NAME
        );

        courierSteps.createCourier(courier);


    }


    @Test
    @DisplayName("Авторизация курьера с валидными данными")
    @Description("Позитивный тест: проверка успешной авторизации курьера с полными данными (логин, пароль)")
    public void authorisationCourierWithValidData() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.COURIER_PASSWORD

        );

        courierSteps.authorisationCoirier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_OK)
                .body("id", is(notNullValue()));

    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Негативный тест: проверка не успешной авторизации курьера с не полными данными (пароль)")
    public void authorisationCourierWithOutLogin() {
        courier = new Courier(
                null,
                Constants.COURIER_PASSWORD

        );

        courierSteps.authorisationCoirier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_BAD_REQUEST)
                .body("message", is(Constants.LOGIN_BAD_REQUEST_MESSEGE));

    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Негативный тест: проверка не успешной авторизации курьера с не полными данными (логин)")
    public void authorisationCourierWithOutPassword() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                null

        );

        courierSteps.authorisationCoirier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_BAD_REQUEST)
                .body("message", is(Constants.LOGIN_BAD_REQUEST_MESSEGE));

    }


    @Test
    @DisplayName("Авторизация курьера с несуществующим логином")
    @Description("Негативный тест: проверка не успешной авторизации курьера с несуществующим логином")
    public void authorisationCourierWithIncorrectLogin() {
        courier = new Courier(
                Constants.INCORRECT_COURIER_LOGIN,
                Constants.COURIER_PASSWORD

        );

        courierSteps.authorisationCoirier(courier)
                .then()
                .statusCode(Constants.STATUS_COD_NOT_FOUND)
                .body("message", is(Constants.LOGIN_NOT_FOUND_MESSEGE));

    }

    @Test
    @DisplayName("Авторизация курьера с несуществующим паролем")
    @Description("Негативный тест: проверка не успешной авторизации курьера с несуществующим паролем")
    public void authorisationCourierWithIncorrectPassword() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.INCORRECT_COURIER_PASSWORD

        );

        courierSteps.authorisationCoirier(courier)
                .then()
                .statusCode(Constants.STATUS_COD_NOT_FOUND)
                .body("message", is(Constants.LOGIN_NOT_FOUND_MESSEGE));

    }


    @After
    public void tearDown() {
        courier = new Courier(
                Constants.COURIER_LOGIN,
                Constants.COURIER_PASSWORD
        );

        int courierId = courierSteps.getCourierId(courier);
        courierSteps.deleteCourier(courierId);


    }

}

