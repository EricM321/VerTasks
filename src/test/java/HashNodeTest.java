import org.junit.Test;
import task2.HashNode;

import static org.junit.Assert.*;

public class HashNodeTest {

    @Test
    // [the name of the tested feature]_[expected input / tested state]_[expected behavior]
    public void hashCode_NullValuesGiven_ProducesDefaultValue(){
        HashNode one = new HashNode(null, null);
        assertEquals(3757, one.hashCode());
    }

    @Test
    public void hashCode_IntValuesGiven_ProducesExpectedValue(){
        HashNode one = new HashNode(1, 1);
        assertEquals(3775, one.hashCode());
    }

    @Test
    public void hashCode_StringValuesGiven_ProducesExpectedValue(){
        HashNode one = new HashNode("one", "one");
        assertEquals(1987033, one.hashCode());
    }

    @Test
    public void hashCode_CombinationValuesGiven_ProducesExpectedValue(){
        HashNode one = new HashNode("one", 1);
        assertEquals(1876852, one.hashCode());
    }

    @Test
    public void equals_UsedOnSelf_ExpectTrue(){
        HashNode one = new HashNode("one", 1);
        assertTrue(one.equals(one));
    }

    @Test
    public void equals_SameValuesGiven_ExpectTrue(){
        HashNode one = new HashNode("one", 1);
        HashNode two = new HashNode("one", 1);
        assertTrue(one.equals(two));
    }

    @Test
    public void equals_DifferentValuesGiven_ExpectFalse(){
        HashNode one = new HashNode("one", 1);
        HashNode two = new HashNode("two", 1);
        assertFalse(one.equals(two));
    }
}
