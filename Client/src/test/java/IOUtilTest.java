

import dk.dtu.compute.se.pisd.roborally.fileaccess.IOUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the IOUtil class.
 */
public class IOUtilTest {

    @Test
    public void testReadString() {
        String expected = "Hello, world!";
        InputStream inputStream = new ByteArrayInputStream(expected.getBytes());
        String result = IOUtil.readString(inputStream);
        assertEquals(expected, result);
    }

    @Test
    public void testReadStringEmptyStream() {
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        String result = IOUtil.readString(inputStream);
        assertEquals("", result);
    }


}
