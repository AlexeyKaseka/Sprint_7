


public class Constants {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String CREATE_COURIR_ENDPOINT = "/api/v1/courier";
    public static final String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";

    public static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";
    public static final String GET_LIST_ORDER_ENDPOINT = "/api/v1/orders";


    public static final int STATUS_CODE_OK = 200;
    public static final int STATUS_CODE_CREATE = 201;
    public static final int STATUS_CODE_BAD_REQUEST = 400;
    public static final int STATUS_COD_NOT_FOUND = 404;
    public static final int STATUS_CODE_CONFLICT = 409;

    public static final String COURIER_BAD_REQUEST_MESSEGE = "Недостаточно данных для создания учетной записи";
    public static final String COURIER_CONFLICT_MESSEGE = "Этот логин уже используется";
    public static final String LOGIN_BAD_REQUEST_MESSEGE = "Недостаточно данных для входа";
    public static final String LOGIN_NOT_FOUND_MESSEGE = "Учетная запись не найдена";

    public static final String CONTENT_TYPE = "Content-type";
    public static final String APPLICATION_JSON = "application/json";

}
