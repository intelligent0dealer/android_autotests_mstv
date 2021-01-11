package pages;


import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class TestAPI {

    public static void sampleAPI() {
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
        getProfileInfo.then().body("data.subscription.googleStatus", notNullValue()).statusCode(200);
    }
}