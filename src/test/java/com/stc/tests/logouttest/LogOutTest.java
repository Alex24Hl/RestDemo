package com.stc.tests.logouttest;

import core.Consts;
import core.EndPoints;
import core.actions.SessionAction;
import core.pojo.ClientInfo;
import core.pojo.SessionInfo;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogOutTest {

    @BeforeClass
    private void beforeClass() { RestAssured.baseURI = EndPoints.BASE_PATH; }

    @Test
    public void logOutSession() {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(Consts.PASSWORD)
                .build();

        String sessionId = SessionAction.login(sessionInfo)
                .statusCode(200)
                .extract()
                .path("session_id");
        SessionAction.logout(sessionId)
                .statusCode(204);

    }

    @Test
    public void doubleLogOut() {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setPassword(Consts.PASSWORD)
                .setDomainId(Consts.DOMAIN_ID)
                .build();

        String sessionId = SessionAction.login(sessionInfo)
                .statusCode(200)
                .extract()
                .path("session_id");

        SessionAction.logout(sessionId)
                .statusCode(204);
        SessionAction.logout(sessionId)
                .statusCode(401);
    }

    @Test(dataProvider = "incorrectToken", dataProviderClass = DataProviderForLogOutTest.class)
    public void incorrectToken(String token) {
        SessionAction.logout(token)
                .statusCode(400)
                .body("reason", Matchers.equalTo("ILLEGAL_ARGUMENT"),
                        "message", Matchers.notNullValue());
    }
}
