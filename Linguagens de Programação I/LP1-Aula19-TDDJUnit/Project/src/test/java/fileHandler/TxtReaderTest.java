package fileHandler;

import org.junit.jupiter.api.Test;

public class TxtReaderTest {
    @Test
    public void testReadFile() {
        TxtReader.readFile("src/test/java/fileHandler/ex4.txt");

    }
}
