package com.stc.tests.logintest;

import core.Consts;
import core.pojo.ClientInfo;
import core.pojo.SessionInfo;
import org.testng.annotations.DataProvider;

import java.util.Locale;

public class DataProviderForLoginTest {

    @DataProvider(name = "positiveSessionData")
    public Object[][] positiveSessionDataProvider() {
        return new Object[][] {
                {
                        SessionInfo.newBuilder()
                                .setUserName(Consts.LOGIN)
                                .setDomainId(Consts.DOMAIN_ID)
                                .setPassword(Consts.PASSWORD)
                                .setClientInfo(ClientInfo.newBuilder()
                                        .setType(Consts.CLIENT_TYPE)
                                        .setDescription(Consts.CLIENT_DESCRIPTION)
                                        .build())
                                .build()
                },
                {
                        SessionInfo.newBuilder()
                                .setUserName(Consts.LOGIN)
                                .setDomainId(Consts.DOMAIN_ID)
                                .setPassword(Consts.PASSWORD)
                                .build()
                }
        };
    }

    @DataProvider(name = "incorrectPasswordData")
    public Object[][] incorrectPasswordDataProvider() {
        return new Object[][] {
                {" "},
                {" " + Consts.PASSWORD},
                {Consts.PASSWORD + " "},
                {Consts.PASSWORD + "абегд"},
                {Consts.PASSWORD + "0"},
                {Consts.PASSWORD + "12.0"},
                {Consts.PASSWORD + "5,4801856е+26"},
                {Consts.PASSWORD + "adjkhjshf"},
                {Consts.PASSWORD + "."},
                {Consts.PASSWORD + "<script>123<script>"},
                {Consts.PASSWORD + "!№#$%^&*()--_–+=@±§?"},
                {Consts.PASSWORD + String.valueOf(Integer.MAX_VALUE)},
                {Consts.PASSWORD + String.valueOf(Double.MAX_VALUE)},
                {Consts.PASSWORD + "漢字"}
        };
    }

    @DataProvider(name = "incorrectUserNameData")
    public Object[][] incorrectUserNameData() {
        return new Object[][] {
                {Consts.LOGIN + "абегд"},
                {Consts.LOGIN + "0"},
                {Consts.LOGIN + "12.0"},
                {Consts.LOGIN + "5,4801856е+26"},
                {Consts.LOGIN + "adjkhjshf"},
                {Consts.LOGIN + "."},
                {Consts.LOGIN + "<script>123<script>"},
                {Consts.LOGIN + "!№#$%^&*()--_–+=@±§?"},
                {Consts.LOGIN + String.valueOf(Integer.MAX_VALUE)},
                {Consts.LOGIN + String.valueOf(Double.MAX_VALUE)},
                {Consts.LOGIN + "漢字"}
        };
    }

    @DataProvider(name = "emptyPasswordData")
    public Object[][] emptyPasswordDataProvider() {
        return new Object[][] {
                {""},
                {null}
        };
    }

    @DataProvider(name = "emptyUserNameData")
    public Object[][] emptyUserNameDataProvider() {
        return new Object[][] {
                {""},
                {null}
        };
    }

    @DataProvider(name = "notFoundUserNameData")
    public Object[][] notFoundUserNameDataProvider() {
        return new Object[][] {
                {" "},
                {" " + Consts.LOGIN},
                {Consts.LOGIN + " "}
        };
    }

    @DataProvider(name = "notFoundPassword")
    public Object[][] notFoundPassword() {
        return new Object[][] {
                {" " + Consts.PASSWORD},
                {Consts.PASSWORD + " "}
        };
    }

    @DataProvider(name = "registerUserName")
    public Object[][] registerUserName() {
        return new Object[][] {
                {" "},
                {Consts.LOGIN.toUpperCase()}
        };
    }

    @DataProvider(name = "registerPassword")
    public Object[][] registerPassword() {
        return new Object[][] {
                {" "},
                {Consts.PASSWORD.toUpperCase()}
        };
    }
}
