package tests;

import org.testng.annotations.Test;
import pages.HoversPage;
import utils.AssertionUtils;

public class HoversTest extends BaseTest {

    @Test
    public void testHoverOverFirstFigure() {
        HoversPage hoversPage = new HoversPage();
        hoversPage.open();

        hoversPage.hoverOverFigure(0); // first figure
        String caption = hoversPage.getCaption(0);

        AssertionUtils.assertTrue(caption.contains("user1"),
                "Caption should contain user1");
    }

    @Test
    public void testHoverOverSecondFigure() {
        HoversPage hoversPage = new HoversPage();
        hoversPage.open();

        hoversPage.hoverOverFigure(1); // second figure
        String caption = hoversPage.getCaption(1);

        AssertionUtils.assertTrue(caption.contains("user2"),
                "Caption should contain user2");
    }

    @Test
    public void testHoverOverThirdFigure() {
        HoversPage hoversPage = new HoversPage();
        hoversPage.open();

        hoversPage.hoverOverFigure(2); // third figure
        String caption = hoversPage.getCaption(2);

        AssertionUtils.assertTrue(caption.contains("user3"),
                "Caption should contain user3");
    }
}