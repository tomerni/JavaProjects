import org.junit.*;

import static org.junit.Assert.*;
public class ClosedHashSetTest {

    private ClosedHashSet hash = new ClosedHashSet();
    private ClosedHashSet hash1 = new ClosedHashSet(1f, 0.25f);

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
    public void reSizeTest1() {
        for (int i = 0; i < 129; i++) {
            hash1.add("a" + i);
            if (i < 16) {
                assertEquals(16, hash1.curCapacity);
            }
            if (16 <= i & i < 32) {
                assertEquals(32, hash1.curCapacity);
            }
            if (32 <= i & i < 64) {
                assertEquals(64, hash1.curCapacity);
            }
            if (64 <= i & i < 128) {
                assertEquals(128, hash1.curCapacity);
            }
            assertEquals(i+1, hash1.curNumOfItems);
        }

        for (int i = 0; i < 129; i++) {
            hash1.delete("a" + i);
            if (i == 60){
                assertEquals(256, hash1.capacity());
            }

        }
        assertEquals(0, hash1.size());
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
