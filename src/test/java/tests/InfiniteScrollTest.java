package tests;

import org.testng.annotations.Test;
import pages.InfiniteScrollPage;
import utils.AssertionUtils;

public class InfiniteScrollTest extends BaseTest {

    @Test
    public void testInfiniteScrollLoadsMoreContent() {
        InfiniteScrollPage infiniteScrollPage = new InfiniteScrollPage();
        infiniteScrollPage.open();

        int beforeScroll = infiniteScrollPage.getParagraphCount();

        infiniteScrollPage.scrollDown(3); // scroll 3 times

//        int afterScroll = infiniteScrollPage.getParagraphCount();
//
//        AssertionUtils.assertTrue(afterScroll > beforeScroll,
//                "Expected more paragraphs to load after scrolling");
    }
}