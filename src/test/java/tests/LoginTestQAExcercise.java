package tests;

import org.testng.annotations.Test;

import pages.LoginPageQAExcercise;
import utils.AssertionUtils;
import utils.DataUtil;

import java.util.Map;

public class LoginTestQAExcercise extends BaseTest {

    @Test(dataProvider = "excelDataQAExcercise", dataProviderClass = DataUtil.class)
    public void testLoginExcel(Map<String, String> row) {
        LoginPageQAExcercise loginPage = new LoginPageQAExcercise();
        loginPage.open();
        loginPage.login(row.get("username"), row.get("password"));
        String msg = loginPage.getErrorMessage();
        AssertionUtils.assertTrue(msg.contains(row.get("expected")),
                "Validation failed for user: " + row.get("username"));
    }

   
}