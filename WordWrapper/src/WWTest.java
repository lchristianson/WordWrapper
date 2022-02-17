
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
        String answer = WordWrapper.wrap(t, l);
        System.out.println(answer);
        assert(answer.equals(f));
    }

    @Test
    public void twoNewlineTest() {
        String text = "This will be the first text requiring more than one newline to successfully wrap the text.";
        String formatted = "This will be the first text requiring more\nthan one newline to successfully wrap the\ntext.";
        int l = 42;
        String answer = WordWrapper.wrap(text, l);
        assert(answer.equals(formatted));
    }

    @Test
    public void noNewlineTest() {
        String text = "Hopefully this test will not produce a newline";
        int l = 200;
        String answer = WordWrapper.wrap(text, l);
        assert(answer.equals(text));

    }

    @Test
    public void leadingSpaceTest() {
        String text = " There is a leading space in this sentance. I hope it doesn't make this test fail.";
        String formatted = " There is a leading space in this sentance. I\nhope it doesn't make this test fail.";
        int l = 46;
        String answer = WordWrapper.wrap(text,l);
        assert(answer.equals(formatted));
    }

    @Test
    public void existingNewlinesTest() {
        String text = "Hello everybody\nThis\nis a test";
        int l = 15;
        String answer = WordWrapper.wrap(text, l);
        assert(answer.equals(text));
    }
    
    @Test
    public void existingNewlinesTest2() {
        String text = "Here are\nsome more\nnewlines. We\nshouldn't\nsee any extra\nnewlines made\nby the wrap\nfunction.";
        int l = 14;
        String answer = WordWrapper.wrap(text, l);
        assert(answer.equals(text));
    }

    @Test
    public void longWordTest() {
        Exception thrown = assertThrows(LineOverflowException.class, () -> {
            String text = "aaaaaaaaaa";
            int l = 3;
            String answer = WordWrapper.wrap(text, l);
        });
    }
}
