import static org.junit.Assert.fail;
import org.junit.Test;

public class WWTest {

    @Test
    public void testTest() {
        assert(1 == 1);
    }

    @Test
    public void simpleOneNewlineTest() {
        String t = "Hello there";
        String f = "Hello\nthere";
        int l = 7;
        try{
            String answer = WordWrapper.wrap(t, l);
            System.out.println(answer);
            assert(answer.equals(f));
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
        String text = " There is a leading space in this sentance. I hope it doesn't make this test fail.";
        String formatted = " There is a leading space in this sentance. I\nhope it doesn't make this test fail.";
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
        try {
            String text = "aaaaaaaaaaaaa";
            int l = 4;
            WordWrapper.wrap(text, l);
            fail("LineOverflowException expected");
        } catch (LineOverflowException e) {
            System.out.println("Test passed.");
        }
    }
}
