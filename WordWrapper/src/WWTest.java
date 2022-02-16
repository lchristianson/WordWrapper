import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

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

    
}
