import org.junit.*;

import static org.junit.Assert.*;
public class OpenHashSetTest {

    private OpenHashSet hash = new OpenHashSet();

    @Test
    public void addTest() {
        assertTrue(hash.add("aa"));
        assertEquals(1, hash.curNumOfItems);

        assertTrue(hash.add("bb"));
        assertEquals(2, hash.curNumOfItems);

        assertFalse(hash.add("aa"));
        assertEquals(2, hash.curNumOfItems);
    }

    @Test
    public void deleteTest() {
        hash.add("aa");
        assertEquals(16, hash.curCapacity);
        assertTrue(hash.delete("aa"));
        assertEquals(0, hash.curNumOfItems);
        assertEquals(8, hash.curCapacity);

        assertFalse(hash.delete("cc"));
    }

    @Test
    public void reSizeTest() {
        for (int i = 0; i < 128; i++) {
            hash.add("a" + i);
            if (i < 12) {
                assertEquals(16, hash.curCapacity);
            }
            if (12 < i & i < 24) {
                assertEquals(32, hash.curCapacity);
            }
            if (24 < i & i < 48) {
                assertEquals(64, hash.curCapacity);
            }
            if (48 < i & i < 96) {
                assertEquals(128, hash.curCapacity);
            }
            assertEquals(i+1, hash.curNumOfItems);
        }
    }

    @Test
    public void containTest() {
        for (int i = 0; i < 128; i++) {
            hash.add("a" + i);
        }
        assertTrue(hash.contains("a1"));
        assertTrue(hash.contains("a127"));
        assertFalse(hash.contains("a199"));

    }

    @Test
    public void consTest() {
        String[] data = new String[50];
        String[] data1 = new String[50];
        for (int i = 0; i < 50; i++) {
            data[i] = "b" +i;
            if (i % 2 == 1) {
                data1[i] = "a" +(i-1);
            }
            else {
                data1[i] = "a" +i;
            }
        }
        OpenHashSet h = new OpenHashSet(data);
        OpenHashSet h1 = new OpenHashSet(data1);

        assertEquals(50, h.size());
        assertEquals(25, h1.size());
    }
}
