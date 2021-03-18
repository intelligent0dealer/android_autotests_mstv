package api;

import fixture.UserConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import pages.DbUtils.DbUtils;

import static org.hamcrest.CoreMatchers.equalTo;


public class TestAPI {
    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://t.motorsport.tv/")
            .setContentType(ContentType.URLENC)
            .build();

    private String getBearer(String email, String pass) {
        Response authResponse =
                RestAssured.given(requestSpec)
                        .formParam("email", email)
                        .formParam("password", pass)
                        .when()
                        .post(Endpoints.AUTH);
        authResponse.then().statusCode(200);
        return authResponse.body().jsonPath().getString("data.token");
    }

    public void postChangeToDefaultNickname(String nickname) {
        RestAssured.given(requestSpec)
                .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST))
                .formParam("nickname", nickname)
                .when()
                .post(Endpoints.UserInfo)
                .then()
                .statusCode(200);

    }

    public void postClearToOtherGender() {
        RestAssured.given(requestSpec)
                .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST))
                .contentType(ContentType.URLENC)
                .formParam("gender", "Other")
                .when()
                .post(Endpoints.UserInfo)
                .then()
                .statusCode(200);

    }

    public void postChangeToDefaultNameSurname(String names) {
        RestAssured.given(requestSpec)
                .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST))
                .contentType(ContentType.URLENC)
                .formParam("firstName", names)
                .formParam("lastName", names)
                .when()
                .post(Endpoints.UserInfo)
                .then()
                .statusCode(200);

    }

    public void getSubscriptionInfo() {
        RestAssured.given(requestSpec)
                .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST))
                .get(Endpoints.SubscriptionInfo)
                .then()
                .body("data.subscription.status", Matchers.notNullValue())
                .statusCode(200);
        //     .then().body("data.subscription.googleStatus", notNullValue()).statusCode(200);
    }

    public void postConfirmEmailForNewUser() {

        String registerToken = DbUtils.getUserConfirmationToken(UserConstants.EMAIL_FOR_REGISTRATION_TEST);

        RestAssured.given(requestSpec)
                .param("token", registerToken)
                .post(Endpoints.Confirm_Email)
                .then()
                .statusCode(200);
    }

    public void postCheckThatEmailConfirmed() {
        RestAssured.given(requestSpec)
                .formParam("email", UserConstants.EMAIL_FOR_REGISTRATION_TEST)
                .formParam("password", UserConstants.PASSWORD_FOR_API_TEST)
                .when()
                .post(Endpoints.AUTH)
                .then()
                .body("data.user.is_email_confirmed", equalTo(true))
                .statusCode(200);
    }

    public void checkPPVAccessPermanent(String id) {
        RestAssured.given(requestSpec)
                .pathParams("id",id)
                .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST))
                .when()
                .get(Endpoints.PPV_EpisodeInfo)
                .then()
                .body("data.access.granted.permanent", equalTo(true))
                .statusCode(200);

    }

    public void checkPPVAccessRent(String id) {
        Response getProfileInfo =
                RestAssured.given(requestSpec)
                        .pathParams("id",id)
                        .header("bearer", getBearer(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST))
                        .when()
                        .get(Endpoints.PPV_EpisodeInfo);
        getProfileInfo.then()
                .body("data.access.granted.permanent", equalTo(false))
                .statusCode(200);

    }

    public String getInfoAboutLivestreamEpisode(String id) {
        Response getEpisodeInfo =
                RestAssured.given(requestSpec)
                        .pathParams("id",id)
                        .when()
                        .get(Endpoints.EpisodeInfo);
        getEpisodeInfo.then()
                .statusCode(200)
                .body("data.is_livestream", equalTo(true))
                .body("data.is_record_active", equalTo(false));
        return getEpisodeInfo
                .body()
                .jsonPath()
                .getString("data.livestream_start_datetime");
    }

    public void getCheckThatLivestreamIsLiveNow(String id) {
        RestAssured.given(requestSpec)
                .pathParams("id",id)
                .when()
                .get(Endpoints.EpisodeInfo)
                .then()
                .statusCode(200)
                .body("data.is_livestream", equalTo(true))
                .body("data.is_record_active", equalTo(false));

    }
}