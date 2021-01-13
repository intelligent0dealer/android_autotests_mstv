package pages;


import io.restassured.response.Response;
import models.fixture.UserFixture;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import pages.DbUtils.DbUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class TestAPI {
    public static void getSubscriptionInfo() {
        Response getBearer =
                given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("email", "intelligent.dealer1605+59@gmail.com")
                    .formParam("password", "12345678")
                    .when().post("https://t.motorsport.tv/api/usermanagement/auth");

        Response getProfileInfo =
                given()
                    .header("bearer", getBearer.body().jsonPath().getString("data.token"))
                    .contentType("application/x-www-form-urlencoded")
                    .get("https://t.motorsport.tv/api/subscriptionmanagement/front/subscription/active");

        getProfileInfo.then().body("data.subscription.status",notNullValue()).statusCode(200);
        //  getProfileInfo.then().body("data.subscription.googleStatus", notNullValue()).statusCode(200);
    }

    public static void confirmationRegisterNewUser() {

        String registerToken = DbUtils.getUserConfirmationToken(UserFixture.EMAIL_FOR_API_TEST.getValue());

        given().param("token", registerToken).
                post("https://t.motorsport.tv/api/usermanagement/confirm-email").then().statusCode(200);
    }

    public static void verificationEmailConfirmed() {
        Response getBearer =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", "intelligent.dealer1605+59@gmail.com")
                        .formParam("password", "12345678")
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");
        getBearer.then().body("data.user.is_email_confirmed",equalTo(false)).statusCode(200);
    }
}