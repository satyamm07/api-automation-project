package com.core.helperServices;
import org.testng.annotations.DataProvider;

public class TestDataHelperService {

    @DataProvider(name = "TestName")
    public static Object[][] getValue() {
        return new Object[][] {
                {"one"}, {"two"}, {"three"}
        };
    }
}
