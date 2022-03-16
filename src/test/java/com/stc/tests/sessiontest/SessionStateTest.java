package com.stc.tests.sessiontest;

import core.Consts;
import core.EndPoints;
import core.actions.SessionAction;
import core.pojo.SessionInfo;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by gorchakov on 14.02.2020.
 */
public class SessionStateTest {

    @BeforeClass
    private void beforeClass() {
        RestAssured.baseURI = EndPoints.BASE_PATH;
    }

    @Test
    public void sessionIsActive() {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(Consts.PASSWORD)
                .build();

        String sessionId = SessionAction.login(sessionInfo).
                                statusCode(200).
                                extract().
                                path("session_id");

        SessionAction.isActive(sessionId).
            statusCode(200).
            body("is_active", Matchers.is(true));
    }

    @Test
    public void sessionInactiveAfterTimeout() throws InterruptedException {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setPassword(Consts.PASSWORD)
                .setDomainId(Consts.DOMAIN_ID)
                .build();

        String sessionId = SessionAction.login(sessionInfo)
                .statusCode(200)
                .extract()
                .path("session_id");

        int minutes = 30;
        if (minutes > 1 && minutes < 30) {
            Thread.sleep(minutes * 60000);
            SessionAction.isActive(sessionId)
                    .statusCode(200)
                    .body("is_active", Matchers.is(false));
        } else {
            Thread.sleep(minutes * 60000);
            SessionAction.isActive(sessionId)
                    .statusCode(200)
                    .body("is_active", Matchers.is(true));
        }
    }
}
