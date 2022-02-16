
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
    
}
