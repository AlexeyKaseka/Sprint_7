import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.Courier;

import static org.hamcrest.CoreMatchers.is;

public class CourierTest {
    private CourierSteps courierSteps;
    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;

        courierSteps = new CourierSteps();

        courier = new Courier(
                Constants.COIRIER_LOGIN,
                Constants.COURIER_PASSWORD,
                Constants.COURIER_FIRST_NAME
        );


    }

    @Test
    @DisplayName("Создание курьера с валидными данными")
    @Description("Позитивный тест: проверка успешного создания курьера с полными данными (логин, пароль, имя)")
    public void shouldCreateCourierWithValidData() {
        courierSteps.createCourier(courier)
                .then()
                .statusCode(Constants.STATUS_CODE_CREATE)
                .body("ok", is(true));

    }

    @After
    public void tearDown() {
        Courier loginCourier = new Courier(
                Constants.COIRIER_LOGIN,
                Constants.COURIER_PASSWORD
        );

        int courierId = courierSteps.getCourierId(loginCourier);
        courierSteps.deleteCourier(courierId)
                .then()
                .statusCode(Constants.STATUS_CODE_OK);
    }


}