package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import fixture.UserConstants;
import org.hamcrest.Matchers;
import pages.DbUtils.DbUtils;

import static org.hamcrest.CoreMatchers.equalTo;


public class TestAPI {

    public void postClearNickname() {
        Response getBearer =
                RestAssured.given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("email", UserConstants.EMAIL_FOR_API_TEST)
                    .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                    .when().post("https://t.motorsport.tv/api/usermanagement/auth");

        Response getProfileInfoNickname =
                RestAssured.given()
                    .header("bearer", getBearer.body().jsonPath().getString("data.token"))
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("nickname","ClearedByAutoTests")
                    .when().post("https://t.motorsport.tv/api/usermanagement/front/user");
                getProfileInfoNickname.then().statusCode(200);

    }

    public void postClearToOtherGender() {
        Response getBearer =
                RestAssured.given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", UserConstants.EMAIL_FOR_API_TEST)
                        .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");

        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer.body().jsonPath().getString("data.token"))
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("gender","Other")
                        .when().post("https://t.motorsport.tv/api/usermanagement/front/user");
        getProfileInfo.then().statusCode(200);

    }

    public void postClearNameSurname() {
        Response getBearer =
                RestAssured.given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", UserConstants.EMAIL_FOR_API_TEST)
                        .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");

        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer.body().jsonPath().getString("data.token"))
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("firstName","ClearedByAutoTests")
                        .formParam("lastName","ClearedByAutoTests")
                        .when().post("https://t.motorsport.tv/api/usermanagement/front/user");
        getProfileInfo.then().statusCode(200);

    }

    public void getSubscriptionInfo() {
        Response getBearer =
                RestAssured.given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", UserConstants.EMAIL_FOR_API_TEST)
                        .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");

        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer.body().jsonPath().getString("data.token"))
                        .contentType("application/x-www-form-urlencoded")
                        .get("https://t.motorsport.tv/api/subscriptionmanagement/front/subscription/active");

        getProfileInfo.then().body("data.subscription.status", Matchers.notNullValue()).statusCode(200);
    //     getProfileInfo.then().body("data.subscription.googleStatus", notNullValue()).statusCode(200);
    }

    public void postConfirmEmailForNewUser() {

        String registerToken = DbUtils.getUserConfirmationToken(UserConstants.EMAIL_FOR_REGISTRATION_TEST);

        RestAssured.given().param("token", registerToken).
                post("https://t.motorsport.tv/api/usermanagement/confirm-email").then().statusCode(200);
    }

    public void postCheckThatEmailConfirmed() {
        Response getBearer =
                RestAssured.given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", UserConstants.EMAIL_FOR_REGISTRATION_TEST)
                        .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");
        getBearer.then().body("data.user.is_email_confirmed",equalTo(true)).statusCode(200);
    }

    public void checkPPVAccessPermanent() {
        Response getBearer =
                RestAssured.given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", UserConstants.EMAIL_FOR_API_TEST)
                        .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");
        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer.body().jsonPath().getString("data.token"))
                        .contentType("application/x-www-form-urlencoded")
                        .when().get("https://t.motorsport.tv/api/v1/content/info/episode/34417");
        getProfileInfo.then().body("data.access.granted.permanent",equalTo(true)).statusCode(200);

    }

    public void checkPPVAccessRent() {
        Response getBearer =
                RestAssured.given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", UserConstants.EMAIL_FOR_API_TEST)
                        .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");
        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer.body().jsonPath().getString("data.token"))
                        .contentType("application/x-www-form-urlencoded")
                        .when().get("https://t.motorsport.tv/api/v1/content/info/episode/34417");
        getProfileInfo.then().body("data.access.granted.permanent",equalTo(false)).statusCode(200);

    }

}