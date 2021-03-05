package api;

import fixture.UserConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import pages.DbUtils.DbUtils;

import static org.hamcrest.CoreMatchers.equalTo;


public class TestAPI {


    private String getBearer(String email, String pass) {
        Response authResponse =
                RestAssured.given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("email", email)
                        .formParam("password", pass)
                        .when().post("https://t.motorsport.tv/api/usermanagement/auth");
        authResponse.then().statusCode(200);
        return authResponse.body().jsonPath().getString("data.token");
    }

    public void postChangeToDefaultNickname(String nickname) {
        Response getProfileInfoNickname =
                RestAssured.given()
                    .header("bearer",getBearer(UserConstants.EMAIL_FOR_API_TEST,UserConstants.PASSWORD_FOR_API_TEST))
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("nickname",nickname)
                    .when().post("https://t.motorsport.tv/api/usermanagement/front/user");
                getProfileInfoNickname.then().statusCode(200);

    }

    public void postClearToOtherGender() {

        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST,UserConstants.PASSWORD_FOR_API_TEST))
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("gender","Other")
                        .when().post("https://t.motorsport.tv/api/usermanagement/front/user");
        getProfileInfo.then().statusCode(200);

    }

    public void postChangeToDefaultNameSurname(String names) {
        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST,UserConstants.PASSWORD_FOR_API_TEST))
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("firstName",names)
                        .formParam("lastName",names)
                        .when().post("https://t.motorsport.tv/api/usermanagement/front/user");
        getProfileInfo.then().statusCode(200);

    }

    public void getSubscriptionInfo() {


        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST,UserConstants.PASSWORD_FOR_API_TEST))
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
        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST,UserConstants.PASSWORD_FOR_API_TEST))
                        .contentType("application/x-www-form-urlencoded")
                        .when().get("https://t.motorsport.tv/api/v1/content/info/episode/34417");
        getProfileInfo.then().body("data.access.granted.permanent",equalTo(true)).statusCode(200);

    }

    public void checkPPVAccessRent() {
        Response getProfileInfo =
                RestAssured.given()
                        .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST,UserConstants.PASSWORD_FOR_API_TEST))
                        .contentType("application/x-www-form-urlencoded")
                        .when().get("https://t.motorsport.tv/api/v1/content/info/episode/34417");
        getProfileInfo.then().body("data.access.granted.permanent",equalTo(false)).statusCode(200);

    }

}