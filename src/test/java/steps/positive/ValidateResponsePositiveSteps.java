package steps.positive;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import models.SessionIdPojo;
import util.RestWrapper;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class ValidateResponsePositiveSteps extends RestWrapper {

    // Распаковка json по pojo обьекту
    public int dbConnectionReturnSessionId() {
        SessionIdPojo sessionId = RestAssured.
                given().spec(API_GET_DBCONNECTION).
                when().get("?login=" + LOGIN + "&password=" + PASSWORD).
                then().statusCode(200).
                extract().body().as(SessionIdPojo.class);

        return sessionId.getSessionID().length();
    }

    // Валидация json по схеме
    public void getDbConnectionValidateSchema() {
        ValidatableResponse a =  RestAssured.given().
                spec(API_GET_DBCONNECTION).log().uri().
                when().get("?login=" + LOGIN + "&password=" + PASSWORD).
                then().log().status().
                assertThat().body(matchesJsonSchemaInClasspath("jsons/schemas/AUTH_SHEMA.json"));
        log.info("Схема запроса /dbconnection и ожидаемая AUTH_SCHEMA.json совпадают");
    }

    // сравнивание возвращаемого списка name с ожидаемым списком
    public void getFormsContainNames(List<String> names) {
         RestAssured.given().
                 spec(API_GET_FORMS).log().uri().
                 header("sessionID", SESSION_ID).
                 when().get("?formid=" + FORM_ID).
                 then().log().status().
                 spec(new ResponseSpecBuilder().
                         expectStatusCode(200).
                         expectBody("root.folders.name", equalTo(names)).
                         build());
         log.info("GET запрос /forms содержит следующие поля names: " + names.toString());
    }

    public void getFormByIdContainsFields() {
        ValidatableResponse a =  RestAssured.given().
                spec(API_GET_FORM).log().uri().
                header("sessionID", SESSION_ID).
                when().get("?formid=" + FORM_ID).
                then().log().status().
                assertThat().body(matchesJsonSchemaInClasspath("jsons/schemas/GET_FORM_BY_ID_SCHEMA.json"));
        log.info("Схема запроса /form и ожидаемая GET_FORM_BY_ID_SCHEMA.json совпадают");
    }

    public void getFormFiltersContainsFields() {
        ValidatableResponse a =  RestAssured.given().
                spec(API_GET_FORM_FILTERS).log().uri().
                header("sessionID", SESSION_ID).
                when().get("?formid=" + FORM_ID).
                then().log().status().
                assertThat().body(matchesJsonSchemaInClasspath("jsons/schemas/FORM_FILTERS_SCHEMA.json"));
        log.info("Схема запроса /formfilters и ожидаемая GET_FORM_BY_ID_SCHEMA.json совпадают");
    }
}
