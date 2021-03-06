package tests.validData;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.validData.StatusCodeValidDataSteps;
import util.LogListener;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Listeners(LogListener.class)
public class StatusCodeValidData {
    StatusCodeValidDataSteps statusCodeValidDataSteps = new StatusCodeValidDataSteps();

    @Flaky
    @Test(groups = { "dbconnection", "validData", "statusCode" })
    @Feature("Проверить код состояния")
    @Story("TA-2А-1")
    @Description("Авторизация в системе с неверным логином и паролем возвращает ошибку авторизации")
    public void dbConnectionWrongLoginDataStatusCodeTest() {
        var statusCode = statusCodeValidDataSteps.dbConnectionGeneratedLoginData();
        assertThat(statusCode).isEqualTo(400);
        log.info("GET запрос /dbconnection возвращает статус код: " + statusCode);
    }

    @Flaky
    @Test(groups = { "forms", "validData", "statusCode" })
    @Feature("Проверить код состояния")
    @Story("TA-2А-2")
    @Description("GET запрос списка форм с валидным sessionID возвращает ошибку")
    public void getFormsWrongSessionIdStatusCodeTest() {
        var statusCode = statusCodeValidDataSteps.getFormsGeneratedSessionId();
        assertThat(statusCode).isEqualTo(400);
        log.info("GET запрос /forms возвращает статус код: " + statusCode);
    }

    @Flaky
    @Test(groups = { "form", "validData", "statusCode" })
    @Feature("Проверить код состояния")
    @Story("TA-2А-3")
    @Description("GET запрос формы по неверному id возвращает ошибку")
    public void getFormByWrongIdStatusCodeTest() {
        var statusCode = statusCodeValidDataSteps.getFormByGeneratedId();
        assertThat(statusCode).isEqualTo(400);
        log.info("GET запрос /forms возвращает статус код: " + statusCode);
    }

    @Flaky
    @Test(groups = { "formfilters", "validData", "statusCode" })
    @Feature("Проверить код состояния")
    @Story("TA-2А-4")
    @Description("GET запрос фильтров для формы по неверному id возвращает ошибку")
    public void getFormFiltersByWrongIdStatusCodeTest() {
        var statusCode = statusCodeValidDataSteps.getFormFiltersByGeneratedId();
        assertThat(statusCode).isEqualTo(400);
        log.info("GET запрос /forms возвращает статус код: " + statusCode);
    }

    @Flaky
    @Test(groups = { "saveForm", "validData", "statusCode" })
    @Feature("Проверить код состояния")
    @Story("TA-2А-5")
    @Description("POST запрос с валидным JSON-обьектом возвращает ошибку")
    public void postSaveFormWrongJsonStatusCodeTest() {
        var statusCode = statusCodeValidDataSteps.postSaveFormWrongJson();
        assertThat(statusCode).isEqualTo(400);
        log.info("GET запрос /forms возвращает статус код: " + statusCode);
    }
}
