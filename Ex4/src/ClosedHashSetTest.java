import org.junit.*;

import static org.junit.Assert.*;
public class ClosedHashSetTest {

    private ClosedHashSet hash = new ClosedHashSet();

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
    public void reSizeTest() {
        for (int i = 0; i < 128; i++) {
            hash.add("a" + i);
            if (i < 12) {
                assertEquals(16, hash.curCapacity);
            }
            if (12 <= i & i < 24) {
                assertEquals(32, hash.curCapacity);
            }
            if (24 <= i & i < 48) {
                assertEquals(64, hash.curCapacity);
            }
            if (48 <= i & i < 96) {
                assertEquals(128, hash.curCapacity);
            }
            assertEquals(i+1, hash.curNumOfItems);
        }

        for (int i = 0; i < 128; i++) {
            hash.delete("a" + i);
            if (i == 60){
                assertEquals(256, hash.capacity());
            }

        }
        assertEquals(0, hash.size());
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
        ClosedHashSet h = new ClosedHashSet(data);
        ClosedHashSet h1 = new ClosedHashSet(data1);

        assertEquals(50, h.size());
        assertEquals(25, h1.size());
    }
}
