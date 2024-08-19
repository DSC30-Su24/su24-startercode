import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


class SortsTest {

    ArrayList<Integer> list;

    @BeforeEach
    public void createLists(){
        list = new ArrayList<>();
    }

    @Test
    public void testBucketSort(){
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        list = Sorts.bucketSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray());

        list.clear();
        list.addAll(Arrays.asList(5, 4, 3, 2, 1));
        list = Sorts.bucketSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray());

        list.clear();
        list.addAll(Arrays.asList(1, 10, 2, 9, 3, 8, 4, 7, 5, 6));
        list = Sorts.bucketSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, list.toArray());

        list.clear();
        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int val = (int) (Math.random() * 100);
            list.add(val);
            expected.add(val);
        }
        list = Sorts.bucketSort(list);
        Collections.sort(expected);
        assertArrayEquals(expected.toArray(), list.toArray());
    }

    @Test
    public void testCountSort(){
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        list = Sorts.countSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray());

        list.clear();
        list.addAll(Arrays.asList(5, 4, 3, 2, 1));
        list = Sorts.countSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray());

        list.clear();
        list.addAll(Arrays.asList(1, 10, 2, 9, 3, 8, 4, 7, 5, 6));
        list = Sorts.countSort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, list.toArray());

        list.clear();
        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int val = (int) (Math.random() * 100);
            list.add(val);
            expected.add(val);
        }
        list = Sorts.countSort(list);
        Collections.sort(expected);
        assertArrayEquals(expected.toArray(), list.toArray());
    }

}