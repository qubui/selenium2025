package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.AssertionUtils;
import utils.DataUtil;

import java.util.Map;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "excelData", dataProviderClass = DataUtil.class)
    public void testLoginExcel(Map<String, String> row) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(row.get("username"), row.get("password"));
        String msg = loginPage.getFlashMessage();
        AssertionUtils.assertTrue(msg.contains(row.get("expected")),
                "Validation failed for user: " + row.get("username"));
    }

    @Test(dataProvider = "csvData", dataProviderClass = DataUtil.class)
    public void testLoginCSV(String[] row) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(row[0], row[1]);
        String msg = loginPage.getFlashMessage();
        AssertionUtils.assertTrue(msg.contains(row[2]),
                "Validation failed for user: " + row[0]);
    }

    @Test(dataProvider = "jsonData", dataProviderClass = DataUtil.class)
    public void testLoginJSON(Map<String, Object> row) {
        LoginPage loginPage = new LoginPage();
        loginPage.login((String) row.get("username"), (String) row.get("password"));
        String msg = loginPage.getFlashMessage();
        AssertionUtils.assertTrue(msg.contains((String) row.get("expected")),
                "Validation failed for user: " + row.get("username"));
    }
}