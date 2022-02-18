import static org.junit.Assert.fail;

import org.junit.Test;

public class WWTest {

    @Test
    public void testTest() {
        assert(true);
    }

    @Test
    public void simpleOneNewlineTest() {
        String text = "Hello there";
        String formatted = "Hello\nthere";
        int l = 7;
        try{
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(formatted));
        }catch (LineOverflowException e) {
            fail("No Exception should occur.");
        }
        
    }

    @Test
    public void twoNewlineTest() {
        String text = "This will be the first text requiring more than one newline to successfully wrap the text.";
        String formatted = "This will be the first text requiring more\nthan one newline to successfully wrap the\ntext.";
        int l = 42;
        try{
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(formatted));
        } catch (LineOverflowException e) {
            fail("No Exception should occur.");
        }
    }

    @Test
    public void noNewlineTest() {
        String text = "Hopefully this test will not produce a newline";
        int l = 200;
        try {
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(text));
        } catch (LineOverflowException e) {
            fail("No Exception should occur.");
        }

    }

    @Test
    public void leadingSpaceTest() {
        String text = " There is a leading space in this sentence. I hope it doesn't make this test fail.";
        String formatted = " There is a leading space in this sentence. I\nhope it doesn't make this test fail.";
        int l = 46;
        try{
            String answer = WordWrapper.wrap(text,l);
            assert(answer.equals(formatted));
        } catch (LineOverflowException e) {
            fail("No Exception should occur.");
        }
    }

    @Test
    public void existingNewlinesTest() {
        String text = "Hello everybody\nThis\nis a test";
        int l = 15;
        try{
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(text));
        } catch (LineOverflowException e) {
            fail("No Exception should occur.");
        }
    }
    
    @Test
    public void existingNewlinesTest2() {
        String text = "Here are\nsome more\nnewlines. We\nshouldn't\nsee any extra\nnewlines made\nby the wrap\nfunction.";
        int l = 14;
        try{
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(text));
        } catch (LineOverflowException e) {
            fail("No Exception should occur.");
        }
    }

    @Test
    public void longWordTest() {
        String text = "aaaaaaaaaaaaa";
        int l = 4;
        try {
            WordWrapper.wrap(text, l);
            fail("LineOverflowException expected");
        } catch (LineOverflowException e) {
            System.out.println("Test passed.");
        }
    }

    @Test
    public void longWordTest2() {
        String text = "hi, this should throw an exception.";
        int l = 8;
        try {
            WordWrapper.wrap(text, l);
            fail("LineOverflowException expected");
        } catch (LineOverflowException e) {
            System.out.println("Test passed.");
        }
    }

    @Test
    public void multipleSpacesTest() {
        String text = "This line of       text has multiple spaces where a line will be inserted";
        String formatted = "This line of    \n  text has\nmultiple spaces\nwhere a line\nwill be inserted";
        int l = 16;
        try {
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(formatted));
        } catch (LineOverflowException e) {
            fail("No exception should occur.");
        }
    }

    @Test
    public void longerTextTest() {
        String text = "As the short northern day merged into night, we found ourselves almost broad upon the wintry ocean, whose freezing spray cased us in ice, as in polished armor.";
        String formatted = "As the short northern day\nmerged into night, we\nfound ourselves almost\nbroad upon the wintry\nocean, whose freezing\nspray cased us in ice, as\nin polished armor.";
        int l = 25;
        try {
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(formatted));
        } catch (LineOverflowException e) {
            fail("No exceptions expected.");
        }
    }

    @Test
    public void multipleSpacesTest2() {
        String text = "aaa   aaa";
        String formatted = "aaa  \naaa";
        int l = 5;
        try {
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(formatted));
        } catch (LineOverflowException e) {
            fail("No exception should occur.");
        }
    }

    @Test
    public void nullStringTest() {
        String text = null;
        int l = 2;
        try {
            String answer = WordWrapper.wrap(text, l);
            assert(answer == null);
        } catch (Exception e) {
            fail("No exception should occur.");
        }
    }

    @Test
    public void tabTest() {
        String text = "\tThis string begins with a tab.";
        String formatted = "\tThis\nstring begins\nwith a tab.";
        int l = 16;
        try {
            String answer = WordWrapper.wrap(text, l);
            assert(answer.equals(formatted));
        } catch (LineOverflowException e) {
            fail("No exception should occur.");
        }
    }
}
