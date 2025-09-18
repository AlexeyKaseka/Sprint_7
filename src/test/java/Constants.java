


public class Constants {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String CREATE_COURIR_ENDPOINT = "/api/v1/courier";
    public static final String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";
    public static final String DELETE_COURIER_ENDPOIT = "/api/v1/courier/";

    public static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";
    public static final String GET_LIST_ORDER_ENDPOINT = "/api/v1/orders";
    public static final String CANCEL_ORDER_ENDPOINT = "/api/v1/orders/cancel";

    public static final int STATUS_CODE_OK = 200;
    public static final int STATUS_CODE_CREATE = 201;
    public static final int STATUS_CODE_BAD_REQUEST = 400;
    public static final int STATUS_COD_NOT_FOUND = 404;
    public static final int STATUS_CODE_CONFLICT = 409;

    public static final String COURIER_BAD_REQUEST_MESSEGE = "Недостаточно данных для создания учетной записи";
    public static final String COURIER_CONFLICT_MESSAGE = "Этот логин уже используется. Попробуйте другой.";
    public static final String LOGIN_BAD_REQUEST_MESSEGE = "Недостаточно данных для входа";
    public static final String LOGIN_NOT_FOUND_MESSEGE = "Учетная запись не найдена";

    public static final String CONTENT_TYPE = "Content-type";
    public static final String APPLICATION_JSON = "application/json";

    public static final String COURIER_LOGIN = "Pashka575759";
    public static final String COURIER_PASSWORD = "12345";
    public static final String COURIER_FIRST_NAME = "Pashka";
    public static final String INCORRECT_COURIER_LOGIN = "Pashka5";
    public static final String INCORRECT_COURIER_PASSWORD = "12";

    public static final Object[][] ALL_ORDER_TEST_CASES = {
            {"Petr", "Petrov", "Arbat, 12", "Arbatskaya", "+7 800 355 35 35", 3,
                    "2025-09-06", "Petr, come back to Arbat", new String[]{"BLACK"}},


            {"Petr", "Petrov", "Arbat, 12", "Arbatskaya", "+7 800 355 35 35", 3,
                    "2025-09-06", "Petr, come back to Arbat", new String[]{"GREY"}},


            {"Petr", "Petrov", "Arbat, 12", "Arbatskaya", "+7 800 355 35 35", 3,
                    "2025-09-06", "Petr, come back to Arbat", new String[]{"BLACK", "GREY"}},


            {"Petr", "Petrov", "Arbat, 12", "Arbatskaya", "+7 800 355 35 35", 3,
                    "2025-09-06", "Petr, come back to Arbat", null}
    };


}
