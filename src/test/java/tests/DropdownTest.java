package tests;

import org.testng.annotations.Test;
import pages.DropdownPage;
import utils.AssertionUtils;

public class DropdownTest extends BaseTest {

    @Test
    public void testSelectOption1() {
        DropdownPage dropdownPage = new DropdownPage();
        dropdownPage.open();

        dropdownPage.selectByValue("1"); // Option 1
        String selected = dropdownPage.getSelectedOption();

        AssertionUtils.assertEquals(selected, "Option 1",
                "Dropdown selection failed for Option 1");
    }

    @Test
    public void testSelectOption2() {
        DropdownPage dropdownPage = new DropdownPage();
        dropdownPage.open();

        dropdownPage.selectByText("Option 2");
        String selected = dropdownPage.getSelectedOption();

        AssertionUtils.assertEquals(selected, "Option 2",
                "Dropdown selection failed for Option 2");
    }
}