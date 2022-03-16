package core.actions;

import core.EndPoints;
import core.pojo.SessionInfo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

/**
 * Created by gorchakov on 12.02.2020.
 */
public class SessionAction {

    static RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

    static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

    public static ValidatableResponse login(SessionInfo sessionInfo) {
        return
                given()
                    .spec(requestSpecification)
                    .body(sessionInfo).
                when().
                    post(EndPoints.SESSION).
                then().
                    spec(responseSpecification);
    }

    public static ValidatableResponse logout(String sessionId) {
        return
                given()
                    .spec(requestSpecification)
                    .header("X-Session-ID", sessionId).
                when().
                    delete(EndPoints.SESSION).
                then().
                    spec(responseSpecification);
    }

    public static ValidatableResponse isActive(String sessionId) {
        return
                given()
                    .spec(requestSpecification)
                    .header("X-Session-ID", sessionId).
                when().
                    get(EndPoints.SESSION).
                then().
                    spec(responseSpecification);
    }


}
