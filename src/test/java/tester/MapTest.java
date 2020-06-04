package tester;

import adapters.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for Map class
 * @author Giacomo Camposampiero
 */
public class MapTest {
    
    Map instance;
    
    public MapTest() {
    }
    
    @Before
    public void setUp() {
        instance = new Map();
    }

    /**
     * Test of clear method, of class Map.
     * This test tests the behaviour of clear method when is called on an empty Map.
     * Depends on the correctness of methods isEmpty()
     */
    @Test
    public void testClear_empty() {
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una mappa vuota, controllo che la dimensione sia nulla", true, result);    
    }
    
    /**
     * Test of clear method, of class Map.
     * This test tests the behaviour of clear() method when is called on a non-empty Map.
     * Depends on the correctness of methods isEmpty() and put()
     */
    @Test
    public void testClear_notEmpty() {
        instance.put("pippo", "pippo");
        instance.put("pluto", "pluto");
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una mappa popolata, controllo che la dimensione sia nulla", true, result);    
    }

    /**
     * Test of containsKey method, of class Map.
     * This test tests the behaviour of containsKey() method when is called on an empty Map
     */
    @Test
    public void testContainsKey_empty() {
        boolean result = instance.containsKey("pluto");
        assertEquals("una mappa vuota non può contenere chiavi", false, result);
    }
    
    /**
     * Test of containsKey method, of class Map.
     * This test tests the behaviour of containsKey() method when is called on a Map which not contains that key
     * Depends on the correctness of method put() 
     */
    @Test
    public void testContainsKey_notContained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsKey("pluto");
        assertEquals("chiave non contenuta nella mappa", false, result);
    }
    
    /**
     * Test of containsKey method, of class Map.
     * This test tests the behaviour of containsKey() method when is called on a Map which contains that key
     * Depends on the correctness of method put() 
     */
    @Test
    public void testContainsKey_contained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsKey("pippo");
        assertEquals("chiave contenuta nella mappa, controllo che la trovi", true, result);
    }
    
    /**
     * Test of containsKey method, of class Map.
     * This test tests the behaviour of containsKey() method when is called on a Map which contains a key that hash the same hash
     * Depends on the correctness of method put() 
     */
    @Test
    public void testContainsKey_sameHash() {
        instance.put("AaAaAa", "asso");
        boolean result = instance.containsKey("AaAaBB");
        assertEquals("il confronto non dovrebbe essere basato solo sul confronto dell'hash", false, result);
    }
    
    /**
     * Test of containsKey method, of class Map.
     * This test tests the behaviour of containsKey() method when is called on a Map using as parameter a null reference (NullPointerException expected)
     */
    @Test
    public void testContainsKey_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsKey(null);
                });
    }

    /**
     * Test of containsValue method, of class Map.
     * This test tests the behaviour of containsValue() method when is called on an empty Map
     */
    @Test
    public void testContainsValue_empty() {
        boolean result = instance.containsValue("pippo");
        assertEquals("una mappa vuota non contiene valori", false, result);    
    }
    
    /**
     * Test of containsValue method, of class Map.
     * This test tests the behaviour of containsValue() method when is called on a Map which not contains the value specified
     * Depends on the correctness of method put()
     */
    @Test
    public void testContainsValue_notContained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsValue("pluto");
        assertEquals("valore non contenuto nella mappa", false, result);    
    }
    
    /**
     * Test of containsValue method, of class Map.
     * This test tests the behaviour of containsValue() method when is called on a Map which not contains the value specified
     * Depends on the correctness of method put()
     */
    @Test
    public void testContainsValue_contained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsValue("asso");
        assertEquals("valore contenuto nella mappa, verifica anche che sia utilizzato il metodo equals() per confrontare gli oggetti", true, result);    
    }
    
    /**
     * Test of containsKey method, of class Map.
     * This test tests the behaviour of containsValue() method when is called on a Map using as parameter a null reference (NullPointerException expected)
     */
    @Test
    public void testContainsValue_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsValue(null);
                });
    }
    
    /**
     * Test of equals method, of class Map.
     * This test tests the behaviour of equals() method when both the two maps compared are empty
     */
    @Test
    public void testEquals_empty() {
        Map other = new Map();
        boolean result = instance.equals(other);
        assertEquals("due mappe vuote dovrebbero essere equivalenti", true, result);    
    }
    
    /**
     * Test of equals method, of class Map.
     * This test tests the behaviour of equals() method when one of the two maps compared is empty
     * Depends on the correctness of method put()
     */
    @Test
    public void testEquals_oneIsEmpty() {
        Map other = new Map();
        other.put("pippo", "asso");
        boolean result = instance.equals(other);
        assertEquals("una mappa vuota e l'altra no, non sono equivalenti", false, result);    
    }
    
    /**
     * Test of equals method, of class Map.
     * This test tests the behaviour of equals() method when the two maps are not equivalents
     * Depends on the correctness of method put()
     */
    @Test
    public void testEquals_notEquivalents() {
        instance.put("pluto", "beta");
        Map other = new Map();
        other.put("pippo", "asso");
        boolean result = instance.equals(other);
        assertEquals("mappe non vuote e non equivalenti tra loro", false, result);    
    }
    
    /**
     * Test of equals method, of class Map.
     * This test tests the behaviour of equals() method when the two maps are equivalents
     * Depends on the correctness of method put()
     */
    @Test
    public void testEquals_equivalents() {
        instance.put("pippo", "asso");
        Map other = new Map();
        other.put("pippo", "asso");
        boolean result = instance.equals(other);
        assertEquals("mappe non vuote ed equivalenti tra loro", true, result);    
    }
    
    /**
     * Test of equals method, of class Map.
     * This test tests the behaviour of equals() method when the two contains the same set of keys, but different values
     * Depends on the correctness of method put()
     */
    @Test
    public void testEquals_sameKeys() {
        instance.put("pippo", "beta");
        Map other = new Map();
        other.put("pippo", "asso");
        boolean result = instance.equals(other);
        assertEquals("mappe aventi lo stesso set di chiavi, ma values differenti", false, result);    
    }
    
    /**
     * Test of equals method, of class Map.
     * This test tests the behaviour of equals() method when the two contains the same set of values, but different keys
     * Depends on the correctness of method put()
     */
    @Test
    public void testEquals_sameValues() {
        instance.put("pluto", "asso");
        Map other = new Map();
        other.put("pippo", "asso");
        boolean result = instance.equals(other);
        assertEquals("mappe aventi lo stesso set di values, ma keys differenti", false, result);    
    }
    
    /**
     * Test of entrySet method, of class Map.
     */
    @Test
    public void testEntrySet() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Map.
     * This test tests the behaviour of get() method when is called on an empty Map
     */
    @Test
    public void testGet_empty() {
        Object result = instance.get("pippo");
        assertEquals("metodo invocato su di una mappa vuota, null expected", null, result);  
    }
    
    /**
     * Test of get method, of class Map.
     * This test tests the behaviour of get() method when is called using a not contained key as parameter
     * Depends on the correctness of methods put()
     */
    @Test
    public void testGet_notContained() {
        instance.put("pippo", "asso");
        Object result = instance.get("pluto");
        assertEquals("metodo invocato utilizzando come parametro una chiave non presente", null, result);  
    }
    
    /**
     * Test of get method, of class Map.
     * This test tests the behaviour of get() method when is called using a contained key as parameter
     * Depends on the correctness of methods put()
     */
    @Test
    public void testGet_contained() {
        instance.put("pippo", "asso");
        Object result = instance.get("pippo");
        assertEquals("metodo invocato utilizzando come parametro una chiave presente", "asso", result);  
    }
    
    /**
     * Test of get method, of class Map.
     * This test tests the behaviour of get() method when is called using a key which is not contained, but has the same hash of one of the key contained
     * Depends on the correctness of methods put()
     */
    @Test
    public void testGet_hash() {
        instance.put("AaAaAa", "asso");
        Object result = instance.get("AaAaBB");
        assertEquals("test utilizzo hash nel confronto della chiave", null, result);  
    }
    
    /**
     * Test of get method, of class Map.
     * This test tests the coherence between the methods containsKey() and get()
     * In fact, this implementation of the map doesn't accept null elements, so a null value is returned only when the key is not contained in the map
     * Depends on the correctness of methods put() and containsKey()
     */
    @Test
    public void testGet_containsKey() {
        instance.put("pippo", "asso");
        boolean result = instance.get("pluto") == null && !instance.containsKey("pluto");
        assertEquals("controllo coerenza tra metodi get e containsKey, chiave non contenuta", true, result);  
        result = instance.get("pippo") == null && !instance.containsKey("pippo");
        assertEquals("controllo coerenza tra metodi get e containsKey, chiave contenuta", false, result);
    }
    
    /**
     * Test of get method, of class Map.
     * This test tests the behaviour of get() method when is called on a Map using as parameter a null reference (NullPointerException expected)
     */
    @Test
    public void testGet_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.get(null);
                });
    }

    /**
     * Test of isEmpty method, of class Map.
     * This test tests the behaviour of the method isEmpty() when called on an empty Map
     */
    @Test
    public void testIsEmpty_empty() {
        boolean result = instance.isEmpty();
        assertEquals("controllo su collezione vuota", true, result); 
    }
    
    /**
     * Test of isEmpty method, of class Map.
     * This test tests the behaviour of the method isEmpty() when called on an empty Map
     * Depends on the correctness of method put()
     */
    @Test
    public void testIsEmpty_notEmpty() {
        instance.put("pippo", "asso");
        boolean result = instance.isEmpty();
        assertEquals("controllo su collezione non vuota", false, result); 
    }
    
    /**
     * Test of isEmpty method, of class Map.
     * This test tests the coherence between methods size() and isEmpty()
     */
    @Test
    public void testIsEmpty_size() {
        boolean size = (instance.size() == 0);
        boolean isEmpty = instance.isEmpty();
        assertEquals("i metodi size() e isEmpty() devono essere coerenti", size, isEmpty);
    }
    
    /**
     * Test of keySet method, of class Map.
     */
    @Test
    public void testKeySet() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class Map.
     * This test tests the behaviour of method put() when is called on an empty Map
     */
    @Test
    public void testPut() {

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putAll method, of class Map.
     */
    @Test
    public void testPutAll() {

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Map.
     */
    @Test
    public void testRemove() {

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Map.
     */
    @Test
    public void testSize() {

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of values method, of class Map.
     */
    @Test
    public void testValues() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
