//package task5;
//
//import api.pojosClasses.*;
//import io.qameta.allure.junit4.DisplayName;
//import io.restassured.http.ContentType;
//import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.response.Response;
//import org.junit.Test;
//
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.*;
//
//public class ApiTests {
//
//    private static final String BASE_URL = "https:reqres.in/api";
//    private static final String USER_LIST_SCHEMA = "task5_resources/UserListSchema.json";
//    private static final String USER_SINGLE_SCHEMA = "task5_resources/UserSingleSchema.json";
//    private static final String RESOURCE_LIST_SCHEMA = "task5_resources/ResourcesListSchema.json";
//    private static final String RESOURCE_SINGLE_SCHEMA = "task5_resources/ResourсesSingleSchema.json";
//    private static final String CREATE_USER_SCHEMA = "task5_resources/CreateUserResponseSchema.json";
//    private static final String UPDATE_USER_SCHEMA = "task5_resources/UpdateUserResponseSchema.json";
//    private static final String REGISTER_SUCCESS_SCHEMA = "task5_resources/RegisterSuccessfulResponseSchema.json";
//    private static final String REGISTER_LOGIN_UNSUCCESSFUL_SCHEMA = "task5_resources/RegisterLoginUnsuccessfulResponseSchema.json";
//    private static final String LOGIN_SUCCESS_SCHEMA = "task5_resources/LoginSuccessfulResponseSchema.json";
//
//    @Test
//    @DisplayName("Получить список пользователей со страницы 2")
//    public void getUsers() {
//        List<UserData> users = given()
//                .when()
//                .get(BASE_URL + "/users?page=2")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(USER_LIST_SCHEMA))
//                .body("page", equalTo(2))
//                .body("per_page", equalTo(6))
//                .body("total", equalTo(12))
//                .body("total_pages", equalTo(2))
//                .extract().jsonPath().getList("data", UserData.class);
//
//        assertThat(users).extracting(UserData::getId).isNotNull();
//        assertThat(users).extracting(UserData::getFirst_name).contains("Tobias");
//        assertThat(users).extracting(UserData::getLast_name).contains("Funke");
//    }
//
//    @Test
//    @DisplayName("Получить пользователя с id=2")
//    public void getUser() {
//        UserData user = given()
//                .when()
//                .get(BASE_URL + "/users/2")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(USER_SINGLE_SCHEMA))
//                .extract().jsonPath().getObject("data", UserData.class);
//
//        assertThat(user.getId()).isEqualTo(2);
//        assertThat(user.getEmail()).isEqualTo("janet.weaver@reqres.in");
//        assertThat(user.getFirst_name()).isEqualTo("Janet");
//        assertThat(user.getLast_name()).isEqualTo("Weaver");
//        assertThat(user.getAvatar()).isEqualTo("https:reqres.in/img/faces/2-image.jpg");
//    }
//
//    @Test
//    @DisplayName("Получить пользователя с id=23")
//    public void getUserNotFound() {
//        given()
//                .when()
//                .get(BASE_URL + "/users/23")
//                .then()
//                .statusCode(404)
//                .body(equalTo("{}"));
//    }
//
//    @Test
//    @DisplayName("Получить список ресурсов")
//    public void getResources() {
//        List<ResourseData> resources = given()
//                .when()
//                .get(BASE_URL + "/unknown")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(RESOURCE_LIST_SCHEMA))
//                .body("page", equalTo(1))
//                .body("per_page", equalTo(6))
//                .body("total", equalTo(12))
//                .body("total_pages", equalTo(2))
//                .extract().jsonPath().getList("data", ResourseData.class);
//
//        assertThat(resources).extracting(ResourseData::getId).isNotNull();
//        assertThat(resources).extracting(ResourseData::getName).contains("fuchsia rose");
//        assertThat(resources).extracting(ResourseData::getYear).contains(2001);
//        assertThat(resources).extracting(ResourseData::getColor).contains("#C74375");
//    }
//
//    @Test
//    @DisplayName("Получить ресурс с id=2")
//    public void getResource() {
//        ResourseData resource = given()
//                .when()
//                .get(BASE_URL + "/unknown/2")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(RESOURCE_SINGLE_SCHEMA))
//                .extract().jsonPath().getObject("data", ResourseData.class);
//
//        assertThat(resource).isNotNull();
//        assertThat(resource.getId()).isEqualTo(2);
//        assertThat(resource.getName()).isEqualTo("fuchsia rose");
//        assertThat(resource.getYear()).isEqualTo(2001);
//        assertThat(resource.getColor()).isEqualTo("#C74375");
//        assertThat(resource.getPantone_value()).isEqualTo("17-2031");
//    }
//
//
//    @Test
//    @DisplayName("Получить ресурс с id=23")
//    public void getResourceNotFound() {
//        given()
//                .when()
//                .get(BASE_URL + "/unknown/23")
//                .then()
//                .statusCode(404)
//                .body(equalTo("{}"));
//    }
//
//    @Test
//    @DisplayName("Создать пользователя")
//    public void createUser() {
//        UserRequest rq = UserRequest.builder()
//                .name("morpheus")
//                .job("leader")
//                .build();
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .body(rq)
//                .when()
//                .post(BASE_URL + "/users")
//                .then()
//                .statusCode(201)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(CREATE_USER_SCHEMA))
//                .extract().response();
//
//         Извлекаем значение из ответа и преобразуем его в строку перед сравнением
//        String name = response.jsonPath().get("name");
//        assertThat(name).isEqualTo("morpheus");
//    }
//
//////    @Test
//////    @DisplayName("Обновить пользователя PUT")
//////    public void updateUserPut() {
//////        UserRequest rq = UserRequest.builder()
//////                .name("morpheus")
//////                .job("zion resident")
//////                .build();
//////
//////        UserResponse rs = given()
//////                .contentType(ContentType.JSON)
//////                .body(rq)
//////                .when()
//////                .put(BASE_URL + "/users/2")
//////                .then()
//////                .statusCode(200)
//////                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(UPDATE_USER_SCHEMA))
//////                .extract().as(UserResponse.class);
//////         Извлекаем значение из ответа и преобразуем его в строку перед сравнением
//////        String name = response.jsonPath().get("name");
//////        assertThat(name).isEqualTo("morpheus");
//////
//////        assertThat(rs).isNotNull();
//////        assertUserResponse(rs, rq.getName(), rq.getJob());
//////    }
//
//    @Test
//    @DisplayName("Обновить пользователя PATCH")
//    public void updateUserPatch() {
//        UserRequest rq = UserRequest.builder()
//                .name("morpheus")
//                .job("zion resident")
//                .build();
//
//        UserResponse rs = given()
//                .contentType(ContentType.JSON)
//                .body(rq)
//                .when()
//                .patch(BASE_URL + "/users/2")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(UPDATE_USER_SCHEMA))
//                .extract().as(UserResponse.class);
//
//         Извлекаем значение поля "name" как строку из JSON-ответа
//        String name = rs.getName();
//
//         Проверяем, что значение "name" не пустое и соответствует ожидаемому значению
//        assertThat(name).isNotNull();
//        assertThat(name).isEqualTo("morpheus");
//
//         Проверяем остальные поля ответа, если это необходимо
//    }
//
//
//
//
//
//    @Test
//    @DisplayName("Удалить пользователя")
//    public void deleteUser() {
//        given()
//                .when()
//                .delete(BASE_URL + "/users/2")
//                .then()
//                .statusCode(204)
//                .body(equalTo(""));
//    }
//
//    @Test
//    @DisplayName("Успешная регистрация")
//    public void registerSuccessful() {
//        LoginRegisterRequest rq = LoginRegisterRequest.builder()
//                .email("eve.holt@reqres.in")
//                .password("pistol")
//                .build();
//
//        LoginRegisterResponse rs = given()
//                .contentType(ContentType.JSON)
//                .body(rq)
//                .when()
//                .post(BASE_URL + "/register")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(REGISTER_SUCCESS_SCHEMA))
//                .extract().as(LoginRegisterResponse.class);
//
//        assertThat(rs).isNotNull();
//        assertThat(rs.getId()).isEqualTo(4);
//        assertThat(rs.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
//    }
//
//    @Test
//    @DisplayName("Неуспешная регистрация")
//    public void registerUnsuccessful() {
//        LoginRegisterRequest rq = LoginRegisterRequest.builder()
//                .email("sydney@fife")
//                .build();
//
//        LoginRegisterResponse rs = given()
//                .contentType(ContentType.JSON)
//                .body(rq)
//                .when()
//                .post(BASE_URL + "/register")
//                .then()
//                .statusCode(400)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(REGISTER_LOGIN_UNSUCCESSFUL_SCHEMA))
//                .extract().as(LoginRegisterResponse.class);
//
//        assertThat(rs).isNotNull();
//        assertThat(rs.getError()).isEqualTo("Missing password");
//    }
//
//    @Test
//    @DisplayName("Успешная авторизация")
//    public void loginSuccessful() {
//        LoginRegisterRequest rq = LoginRegisterRequest.builder()
//                .email("eve.holt@reqres.in")
//                .password("cityslicka")
//                .build();
//
//        LoginRegisterResponse rs = given()
//                .contentType(ContentType.JSON)
//                .body(rq)
//                .when()
//                .post(BASE_URL + "/login")
//                .then()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(LOGIN_SUCCESS_SCHEMA))
//                .extract().as(LoginRegisterResponse.class);
//
//        assertThat(rs).isNotNull();
//        assertThat(rs.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
//    }
//
//    @Test
//    @DisplayName("Неуспешная авторизация")
//    public void loginUnsuccessful() {
//        LoginRegisterRequest rq = LoginRegisterRequest.builder()
//                .email("peter@klaven")
//                .build();
//
//        LoginRegisterResponse rs = given()
//                .contentType(ContentType.JSON)
//                .body(rq)
//                .when()
//                .post(BASE_URL + "/login")
//                .then()
//                .statusCode(400)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(REGISTER_LOGIN_UNSUCCESSFUL_SCHEMA))
//                .extract().as(LoginRegisterResponse.class);
//
//        assertThat(rs).isNotNull();
//        assertThat(rs.getError()).isEqualTo("Missing password");
//    }
//
//    @Test
//    @DisplayName("Получить список пользователей с задержкой в 3 секунды")
//    public void getUsersDelay() {
//        List<UserData> users = given()
//                .when()
//                .get(BASE_URL + "/users?delay=3")
//                .then()
//                .statusCode(200)
//                .time(both(greaterThan(3000L)).and(lessThan(6000L)))
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(USER_LIST_SCHEMA))
//                .body("page", equalTo(1))
//                .body("per_page", equalTo(6))
//                .body("total", equalTo(12))
//                .body("total_pages", equalTo(2))
//                .extract().jsonPath().getList("data", UserData.class);
//
//        assertThat(users).extracting(UserData::getId).isNotNull();
//        assertThat(users).extracting(UserData::getFirst_name).contains("George");
//        assertThat(users).extracting(UserData::getLast_name).contains("Bluth");
//    }
//
//    private void assertUserDetails(UserData user, int id, String email, String firstName, String lastName, String avatar) {
//        assertThat(user).extracting(UserData::getId).isEqualTo(id);
//        assertThat(user).extracting(UserData::getEmail).isEqualTo(email);
//        assertThat(user).extracting(UserData::getFirst_name).isEqualTo(firstName);
//        assertThat(user).extracting(UserData::getLast_name).isEqualTo(lastName);
//        assertThat(user).extracting(UserData::getAvatar).isEqualTo(avatar);
//    }
//
//    private void assertResourceDetails(ResourseData resource, int id, String name, int year, String color, String pantoneValue) {
//        assertThat(resource).extracting(ResourseData::getId).isEqualTo(id);
//        assertThat(resource).extracting(ResourseData::getName).isEqualTo(name);
//        assertThat(resource).extracting(ResourseData::getYear).isEqualTo(year);
//        assertThat(resource).extracting(ResourseData::getColor).isEqualTo(color);
//        assertThat(resource).extracting(ResourseData::getPantone_value).isEqualTo(pantoneValue);
//    }
//
//    private void assertUserResponse(UserResponse response, String name, String job) {
//        assertThat(response).extracting(UserResponse::getName).isEqualTo(name);
//        assertThat(response).extracting(UserResponse::getJob).isEqualTo(job);
//    }
//}
