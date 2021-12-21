package steps;

import io.qameta.allure.Step;
import models.SessionIdPojo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static org.assertj.core.api.Assertions.assertThat;

public class DbConnectionSteps extends BaseSteps {
    private static final RequestSpecification API_PATH = getReqSpec("/dbconnection");

    @Step("GET запрос '/dbconnection'. Вернуть sessionID")
    public static String getDbConnectionAndGetSessionId() {
        return RestAssured.given().spec(API_PATH).
                when().
                    get("?login=" + LOGIN + "&password=" + PASSWORD)
                .then().
                    statusCode(200).
                    extract().path("sessionID");
    }
}
