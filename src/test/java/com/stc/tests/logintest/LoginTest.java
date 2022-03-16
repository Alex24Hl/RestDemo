package com.stc.tests.logintest;

import core.Consts;
import core.EndPoints;
import core.actions.SessionAction;
import core.pojo.ClientInfo;
import core.pojo.SessionInfo;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Created by gorchakov on 14.02.2020.
 */
public class LoginTest {

    @BeforeClass
    private void beforeClass() { RestAssured.baseURI = EndPoints.BASE_PATH; }

    @Test(dataProvider = "positiveSessionData", dataProviderClass = DataProviderForLoginTest.class)
    public void positiveLogin(SessionInfo sessionInfo) {
        SessionAction.login(sessionInfo)
                .statusCode(200)
                .body("session_id", Matchers.notNullValue());
    }

    @Test
    public void sessionIdMatchesUUID() {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(Consts.PASSWORD)
                .build();

        SessionAction.login(sessionInfo).
                statusCode(200).
                body("session_id", Matchers.matchesPattern("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}"));
    }

    @Test(dataProvider = "incorrectUserNameData",dataProviderClass = DataProviderForLoginTest.class)
    public void incorrectUserNameData(String userName) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(userName)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(Consts.PASSWORD)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();

        SessionAction.login(sessionInfo)
                .statusCode(404)
                .body("session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("USER_NOT_FOUND"),
                        "message", Matchers.notNullValue()
                );
    }

    @Test(dataProvider = "incorrectPasswordData", dataProviderClass = DataProviderForLoginTest.class)
    public void incorrectPassword(String password) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(password)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();

        SessionAction.login(sessionInfo)
                .statusCode(401)
                .body(
                        "session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("INCORRECT_PASSWORD"),
                        "message", Matchers.notNullValue()
                );
    }

    @Test(dataProvider = "emptyPasswordData", dataProviderClass = DataProviderForLoginTest.class)
    public void emptyPassword(String password) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(password)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();

        SessionAction.login(sessionInfo)
                .statusCode(400)
                .body(
                        "session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("PASSWORD_IS_EMPTY"),
                        "message", Matchers.equalTo("Password is empty.")
                );
    }

    @Test(dataProvider = "emptyUserNameData", dataProviderClass = DataProviderForLoginTest.class)
    public void emptyUserName(String userName) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(userName)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(Consts.PASSWORD)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();

        SessionAction.login(sessionInfo)
                .statusCode(400)
                .body(
                        "session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("USERNAME_NOT_CORRECT"),
                        "message", Matchers.notNullValue()
                );
    }

    @Test(dataProvider = "notFoundUserNameData", dataProviderClass = DataProviderForLoginTest.class)
    public void notFoundUserName(String userName) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(userName)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(Consts.PASSWORD)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();

        SessionAction.login(sessionInfo)
                .statusCode(404)
                .body(
                        "session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("USER_NOT_FOUND"),
                        "message", Matchers.notNullValue()
                );
    }

    @Test(dataProvider = "notFoundPassword" , dataProviderClass = DataProviderForLoginTest.class)
    public void notFoundPassword(String password) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setPassword(password)
                .setDomainId(Consts.DOMAIN_ID)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();
        SessionAction.login(sessionInfo)
                .statusCode(401)
                .body("session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("INCORRECT_PASSWORD"),
                        "message", Matchers.notNullValue()
                );
    }


    @Test(dataProvider = "registerUserName", dataProviderClass = DataProviderForLoginTest.class)
    public void registerUserName(String userName) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(userName)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(Consts.PASSWORD)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();

        SessionAction.login(sessionInfo)
                .statusCode(404)
                .body("session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("USER_NOT_FOUND"),
                        "message",Matchers.notNullValue()
                );
    }

    @Test(dataProvider = "registerPassword",dataProviderClass = DataProviderForLoginTest.class)
    public void registerPassword(String password) {
        SessionInfo sessionInfo = SessionInfo.newBuilder()
                .setUserName(Consts.LOGIN)
                .setDomainId(Consts.DOMAIN_ID)
                .setPassword(password)
                .setClientInfo(ClientInfo.newBuilder()
                        .setType("MOBILE")
                        .setDescription("iPhone X, 128GB, iOS 12")
                        .build())
                .build();

        SessionAction.login(sessionInfo)
                .statusCode(401)
                .body("session_id", Matchers.nullValue(),
                        "reason", Matchers.equalTo("INCORRECT_PASSWORD"),
                        "message",Matchers.notNullValue()
                );
    }
}
