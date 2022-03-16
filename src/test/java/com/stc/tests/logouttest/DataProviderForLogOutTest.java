package com.stc.tests.logouttest;


import org.testng.annotations.DataProvider;

public class DataProviderForLogOutTest {

    @DataProvider(name = "incorrectToken")
    public Object[][] incorrectToken() {
        return new Object[][] {
                    {" "},
                    {"" + "234567"}
            };
    }

}
