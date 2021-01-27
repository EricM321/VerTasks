import org.junit.Before;
import org.junit.Test;
import task2.CustomMapImpl;
import task2.HashNode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CustomMapImplTest {

    private CustomMapImpl customMap;

    @Before
    public void setUp() {
        customMap = new CustomMapImpl();
    }

    @Test
    // [the name of the tested feature]_[expected input / tested state]_[expected behavior]
    public void size_EmptyMap_DefaultValue(){
        assertEquals(0, customMap.size());
    }

    @Test
    public void remove_EmptyMap_Null(){
        assertNull( customMap.remove(1));
    }

    @Test
    public void get_EmptyMap_Null(){
        assertNull( customMap.get(1));
    }

    @Test
    public void getIndex_EmptyMap_HashModulo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateGetIndexMethod = CustomMapImpl.class.getDeclaredMethod("getIndex", Object.class);
        privateGetIndexMethod.setAccessible(true);

        HashNode one = new HashNode(1, 1);
        int returnValue = (int) privateGetIndexMethod.invoke(customMap, one);

        assertEquals(3775 % 10, returnValue);
    }

    @Test
    public void put_EmptyMap_SizeIncrease(){
        assertEquals(0, customMap.size());
        customMap.put(1,1);
        assertEquals(1, customMap.size());
    }

    @Test
    public void put_SameKey_NoSizeIncrease(){
        customMap.put(1,1);
        customMap.put(1,2);
        assertEquals(1, customMap.size());
    }

    @Test
    public void get_AfterPut_ValueReturned(){
        customMap.put(1,2);
        assertEquals(customMap.get(1), 2);
    }

    @Test
    public void put_HashCollision_SizeIncrease() {
        //Apparently all ints going through the getIndex produced the same index
        customMap.put(1,2);
        assertEquals(1, customMap.size());

        customMap.put(2,2);
        assertEquals(2, customMap.size());
    }

    @Test
    public void put_DifferentTypes_SizeIncrease() {
        customMap.put(1,1);
        assertEquals(1, customMap.size());

        customMap.put("two",2);
        assertEquals(2, customMap.size());
    }

    @Test
    public void remove_PopulatedMap_SizeReduced(){
        customMap.put("one", 1);
        assertEquals(1, customMap.size());

        assertEquals(customMap.remove("one"), 1);
        assertEquals(0, customMap.size());
    }

    @Test
    public void maxBuckets_PopulateMap_SizeAtMax(){
        for(int i = 0; i < 100; i++){
            customMap.put("" + i + "", i);
        }

        assertEquals(100, customMap.size());
    }

    @Test
    public void maxBuckets_PopulateMap_OverMax(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        for(int i = 0; i < 101; i++){
            customMap.put("" + i + "", i);
        }

        assertEquals(100, customMap.size());
        assertEquals("MAX STORAGE REACHED!", outputStreamCaptor.toString().trim());
    }

}
