package tests;

import org.testng.annotations.Test;
import pages.DragAndDropPage;
import utils.AssertionUtils;

public class DragAndDropTest extends BaseTest {

    @Test
    public void testDragAndDrop() {
        DragAndDropPage dragPage = new DragAndDropPage();
        dragPage.open();

        // Capture before drag
        String headerA_before = dragPage.getHeaderA();
        String headerB_before = dragPage.getHeaderB();

        // Perform drag and drop
        dragPage.dragAtoB();

        
        
        
        // Capture after drag
        String headerA_after = dragPage.getHeaderA();
        String headerB_after = dragPage.getHeaderB();

        // Assertions
        AssertionUtils.assertNotEquals(headerA_before, headerA_after,
                "Header A should change after drag and drop");
        AssertionUtils.assertNotEquals(headerB_before, headerB_after,
                "Header B should change after drag and drop");
    }
}