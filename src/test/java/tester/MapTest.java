package tester;

import adapters.Map;
import interfaces.HMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Test suite for Map class
 * @author Giacomo Camposampiero
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapTest {
    
    Map instance;
    
    public MapTest() {
    }
    
    @Before
    public void setUp() {
        instance = new Map();
    }

    /**
     * @title Test #1 of clear method, of class Map.
     * @description This test tests the behaviour of clear method when is called on an empty Map.
     * @expectedResults The map (which was already empty) is expected to be still empty after the call of the method.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method isEmpty()
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance should not be modified by the call of this method.
     */
    @Test
    public void testClear_empty() {
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una mappa vuota, controllo che la dimensione sia nulla", true, result);    
    }
    
    /**
     * @title Test #2 of clear method, of class Map.
     * @description This test tests the behaviour of clear() method when is called on a non-empty Map.
     * @expectedResults The map (which has been filled with different entry) is expected to be empty after the call of the method.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods isEmpty() and put()
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance must contains no entries after the execution of the test.
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
     * @title Test #1 of containsKey method, of class Map.
     * @description This test tests the behaviour of containsKey() method when is called on an empty Map
     * @expectedResults The map doesn't contain the specified key, as the map is empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testContainsKey_empty() {
        boolean result = instance.containsKey("pluto");
        assertEquals("una mappa vuota non può contenere chiavi", false, result);
    }
    
    /**
     * @title Test #2 of containsKey method, of class Map.
     * @description This test tests the behaviour of containsKey() method when is called on a Map which not contains that key
     * @expectedResults The map isn't expected to contain the specified key.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but it has to contain the element added).
     */
    @Test
    public void testContainsKey_notContained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsKey("pluto");
        assertEquals("chiave non contenuta nella mappa", false, result);
    }
    
    /**
     * @title Test #3 of containsKey method, of class Map.
     * @description This test tests the behaviour of containsKey() method when is called on a Map which contains that key
     * @expectedResults The map is expected to contains the specified key, which has been added to the map before the invocation of the method.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but it has to contain the element added).
     */
    @Test
    public void testContainsKey_contained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsKey("pippo");
        assertEquals("chiave contenuta nella mappa, controllo che la trovi", true, result);
    }
    
    /**
     * @title Test #4 of containsKey method, of class Map.
     * @description This test tests the behaviour of containsKey() method when is called on a Map which contains a key that hash the same hash
     * @expectedResults The map is not expected to contain the specified value, as it performs a comparation not only on the hash, but also on the equality of key objects.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but it has to contain the element added).
     */
    @Test
    public void testContainsKey_sameHash() {
        instance.put("AaAaAa", "asso");
        boolean result = instance.containsKey("AaAaBB");
        assertEquals("il confronto non dovrebbe essere basato solo sul confronto dell'hash", false, result);
    }
    
    /**
     * @title Test #5 of containsKey method, of class Map.
     * @description This test tests the behaviour of containsKey() method when is called on a Map using as parameter a null reference (NullPointerException expected)
     * @expectedResults The map is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies  This test doesn't depend on the correctness of any other method of the class.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testContainsKey_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsKey(null);
                });
    }

    /**
     * @title Test #1 of containsValue method, of class Map.
     * @description This test tests the behaviour of containsValue() method when is called on an empty Map
     * @expectedResults The map is expected not to contained the specified value, as the map is empty.
     * @actualResult As expected result.
     * @dependencies  This test doesn't depend on the correctness of any other method of the class.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testContainsValue_empty() {
        boolean result = instance.containsValue("pippo");
        assertEquals("una mappa vuota non contiene valori", false, result);    
    }
    
    /**
     * @title Test #2 of containsValue method, of class Map.
     * @description This test tests the behaviour of containsValue() method when is called on a Map which not contains the value specified
     * @expectedResults The map is expected not to contained the specified value, which wasn't putted to the map before.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put()
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contains the element that was added).
     */
    @Test
    public void testContainsValue_notContained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsValue("pluto");
        assertEquals("valore non contenuto nella mappa", false, result);    
    }
    
    /**
     * @title Test #3 of containsValue method, of class Map.
     * @description This test tests the behaviour of containsValue() method when is called on a Map which not contains the value specified
     * @expectedResults The map is expected to contain the value specified, as it was previously added to the map.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put()
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contains the element that was added).
     */
    @Test
    public void testContainsValue_contained() {
        instance.put("pippo", "asso");
        boolean result = instance.containsValue("asso");
        assertEquals("valore contenuto nella mappa, verifica anche che sia utilizzato il metodo equals() per confrontare gli oggetti", true, result);    
    }
    
    /**
     * @title Test #4 of containsKey method, of class Map.
     * @description This test tests the behaviour of containsValue() method when is called on a Map using as parameter a null reference (NullPointerException expected)
     * @expectedResults The map is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies  This test doesn't depend on the correctness of any other method of the class.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testContainsValue_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsValue(null);
                });
    }
    
    /**
     * @title Test #1 of equals method, of class Map.
     * @description This test tests the behaviour of equals() method when both the two maps compared are empty
     * @expectedResults Both maps are empty, so they are expected to be equal.
     * @actualResult As expected result.
     * @dependencies  This test doesn't depend on the correctness of any other method of the class.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testEquals_empty() {
        HMap other = new Map();
        boolean result = instance.equals(other);
        assertEquals("due mappe vuote dovrebbero essere equivalenti", true, result);    
    }
    
    /**
     * @title Test #2 of equals method, of class Map.
     * @description This test tests the behaviour of equals() method when one of the two maps compared is empty
     * @expectedResults The maps are expected not to equals, as one of them is empty and the other is not.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
     */
    @Test
    public void testEquals_oneIsEmpty() {
        Map other = new Map();
        other.put("pippo", "asso");
        boolean result = instance.equals(other);
        assertEquals("una mappa vuota e l'altra no, non sono equivalenti", false, result);    
    }
    
    /**
     * @title Test #3 of equals method, of class Map.
     * @description This test tests the behaviour of equals() method when the two maps are not equivalents
     * @expectedResults The maps are expected not to equals, as they contain different entries.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
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
     * @title Test #4 of equals method, of class Map.
     * @description This test tests the behaviour of equals() method when the two maps are equivalents
     * @expectedResults The maps are expected to equals, as they contain the same entries.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entries added while testing).
     */
    @Test
    public void testEquals_equivalents() {
        instance.put("pippo", "asso");
        instance.put("paperino", "masso");
        Map other = new Map();
        other.put("pippo", "asso");
        other.put("paperino", "masso");
        boolean result = instance.equals(other);
        assertEquals("mappe non vuote ed equivalenti tra loro", true, result);    
    }
    
    /**
     * @title Test #5 of equals method, of class Map.
     * @description This test tests the behaviour of equals() method when the two contains the same set of keys, but different values
     * @expectedResults The maps are expected not to equals, as they contain the same the same key set, but with different values associated.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
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
     * @title Test #6 of equals method, of class Map.
     * @description This test tests the behaviour of equals() method when the two contains the same set of values, but different keys
     * @expectedResults The maps are expected not to equals, as they contain the same the same values set, but with different keys associated.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
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
     * @title Test of entrySet method, of class Map.
     */
    @Test
    public void testEntrySet() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * @title Test #1 of get method, of class Map.
     * @description This test tests the behaviour of get() method when is called on an empty Map
     * @expectedResults The map is expected to return a null reference, which is the return value used when the specified key isn't contained in the map (which is empty).
     * @actualResult As expected result.
     * @dependencies  The correctness of this test doesn't depends on any other method of class Map.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testGet_empty() {
        Object result = instance.get("pippo");
        assertEquals("metodo invocato su di una mappa vuota, null expected", null, result);  
    }
    
    /**
     * @title Test #2 of get method, of class Map.
     * @description This test tests the behaviour of get() method when is called using a not contained key as parameter
     * @expectedResults The map is expected to return a null reference, which is the return value used when the specified key isn't contained in the map.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
     */
    @Test
    public void testGet_notContained() {
        instance.put("pippo", "asso");
        Object result = instance.get("pluto");
        assertEquals("metodo invocato utilizzando come parametro una chiave non presente", null, result);  
    }
    
    /**
     * @title Test #3 of get method, of class Map.
     * @description This test tests the behaviour of get() method when is called using a contained key as parameter
     * @expectedResults The map is expected to return the value associated with the specified key in the map.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
     */
    @Test
    public void testGet_contained() {
        instance.put("pippo", "asso");
        Object result = instance.get("pippo");
        assertEquals("metodo invocato utilizzando come parametro una chiave presente", "asso", result);  
    }
    
    /**
     * @title Test #4 of get method, of class Map.
     * @description This test tests the behaviour of get() method when is called using a key which is not contained, but has the same hash of one of the key contained
     * @expectedResults The map is expected to return a null reference, as the specified key has the same hash of a key contained in the map, but the comparison isn't performed just with an hash comparison.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
     */
    @Test
    public void testGet_hash() {
        instance.put("AaAaAa", "asso");
        Object result = instance.get("AaAaBB");
        assertEquals("test utilizzo hash nel confronto della chiave", null, result);  
    }
    
    /**
     * @title Test #5 of get method, of class Map.
     * @description This test tests the behaviour of get() method when is called on a Map using as parameter a null reference (NullPointerException expected)
     * @expectedResults The map is expected to throw a NullPointerException when a null reference is used as parameter.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testGet_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.get(null);
                });
    }
    
    /**
     * @title Test of get and containsKey method, of class Map.
     * @description This test tests the coherence between the methods containsKey() and get(). In fact, this implementation of the map doesn't accept null elements, so a null value is returned only when the key is not contained in the map
     * @expectedResults A coherent behaviour between containsKey() and get() methods is expected.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
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
     * @title Test #1 of hashCode method, of class Map.
     * @description This test tests the behaviour of hashCode() method when is called on an empty map.
     * @expectedResults The result hash is expected to be 0, as is definide as the sum of all the map entries hashcode, and the map is empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of other methods of the class Map.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testHashCode_empty() {
        int result = instance.hashCode();
        assertEquals("hash di una mappa vuota deve essere zero", 0, result);        
    }
    
    /**
     * @title Test #2 of hashCode method, of class Map.
     * @description This test tests the behaviour of hashCode() method when is called on a not-empty map.
     * @expectedResults The result hash is expected to the sum of all the map entries hashcode, as it defined in this way.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of the method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but the map still have to contain the entry inserted during the testing process).
     */
    @Test
    public void testHashCode_notEmpty() {
        String val1 = "pippo", key1 = "asso", val2 = "pluto", key2 = "topolino";
        instance.put(key1, val1);
        instance.put(key2, val2);
        int expected = (key1.hashCode()^val1.hashCode()) + (key2.hashCode()^val2.hashCode());
        int result = instance.hashCode();
        assertEquals("hash di una mappa piena deve essere uguale alla somma degli hash delle sue entry", expected, result);        
    }

    /**
     * @title Test #1 of isEmpty method, of class Map.
     * @description This test tests the behaviour of the method isEmpty() when called on an empty Map
     * @expectedResults A just instancied map is expected to be empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of any other method of Map.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testIsEmpty_empty() {
        boolean result = instance.isEmpty();
        assertEquals("controllo su collezione vuota", true, result); 
    }
    
    /**
     * @title Test #2 of isEmpty method, of class Map.
     * @description This test tests the behaviour of the method isEmpty() when called on an empty Map.
     * @expectedResults An element was added to the map, the map should not be empty.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
     */
    @Test
    public void testIsEmpty_notEmpty() {
        instance.put("pippo", "asso");
        boolean result = instance.isEmpty();
        assertEquals("controllo su collezione non vuota", false, result); 
    }
    
    /**
     * @title Test #3 of isEmpty method, of class Map.
     * @description This test tests the coherence between methods size() and isEmpty().
     * @expectedResults A coherent behaviour is expected between size() and isEmpty() methods.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testIsEmpty_size() {
        boolean size = (instance.size() == 0);
        boolean isEmpty = instance.isEmpty();
        assertEquals("i metodi devono essere coerenti quando la collezione è vuota", size, isEmpty);
        instance.put("pippo", "pluto");
        size = (instance.size() == 0);
        isEmpty = instance.isEmpty();
        assertEquals("i metodi devono essere coerenti quando la collezione è piena", size, isEmpty);
    }
    
    /**
     * @title Test of keySet method, of class Map.
     */
    @Test
    public void testKeySet() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * @title Test #1 of put method, of class Map.
     * @description This test tests the behaviour of method put() when is called on an empty Map.
     * @expectedResults The map is expected to accept the new entry (as it is empty) and to return a null reference.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method isEmpty().
     * @preConditions The set instance must be a new istance of Map.
     * @postConditions The set instance should be modified by the call of the tested method (new entries added).
     */
    @Test
    public void testPut_empty() {
        Object result = instance.put("pippo", "pluto");
        assertEquals("aggiunta a mappa vuota", null, result);
        assertEquals("dimensione mappa cambiata", false, instance.isEmpty());
    }
    
    /**
     * @title Test #2 of put method, of class Map.
     * @description This test tests the behaviour of method put() when is called on a not-empty Map, but the entry is not contained in the map.
     * @expectedResults The map is expected to accept the new entry and to return a null reference.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The set instance must be a new istance of Map.
     * @postConditions The set instance should be modified by the call of the tested method (new entries added).
     */
    @Test
    public void testPut_notContained() {
        instance.put("paperino", "asso");
        Object result = instance.put("pippo", "pluto");
        assertEquals("aggiunta di entry non presente", null, result);
        assertEquals("dimensione mappa cambiata", 2, instance.size());
        
    }
    
    /**
     * @title Test #3 of put method, of class Map.
     * @description This test tests the behaviour of method put() when is called on a not-empty Map and the entry is already contained in the map.
     * @expectedResults The map is expected to update the old value associated with that key and to update it with the new one specified.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods size() and get().
     * @preConditions The set instance must be a new istance of Map.
     * @postConditions The set instance should be modified by the call of the tested method (values modified).
     */
    @Test
    public void testPut_contained() {
        instance.put("pippo", "asso");
        Object result = instance.put("pippo", "pluto");
        assertEquals("aggiunta di entry con chiave già presente", "asso", result);
        assertEquals("dimensione mappa non cambiata", 1, instance.size());
        assertEquals("valore aggiornato", "pluto", instance.get("pippo"));
    }
    
    /**
     * @title Test #4 of put method, of class Map.
     * @description This test tests the behaviour of method put() when is called on a not-empty Map and the map contains a key with the same hash of the key specified, but a differente value.
     * @expectedResults The map is expected to add the new entry, as the key comparison isn't based only on hash comparison.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The set instance must be a new istance of Map.
     * @postConditions The set instance should be modified by the call of the tested method (values modified).
     */
    @Test
    public void testPut_hash() {
        instance.put("AaAaAa", "asso");
        Object result = instance.put("AaAaBB", "pluto");
        assertEquals("aggiunta di entry con chiave che ha hash uguale ad una già presente", null, result);
        assertEquals("dimensione mappa cambiata", 2, instance.size());
    }
    
    /**
     * @title Test #5 of put method, of class Map.
     * @description This test tests the behaviour of put() method when is called on a Map using as parameter a null reference (NullPointerException expected)
     * @expectedResults The map is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies  This test doesn't depend on the correctness of any other method of the class.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testPut_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.put(null,"pippo");
                });
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.put("pippo", null);
                });
        assertThrows("entrambi i parametri sono riferimenti a null", NullPointerException.class,
                () -> {
                    instance.put(null, null);
                });
    }
    
    /**
     * @title Test of put and remove methods, of class Map.
     * @description This test tests the behaviour of class Map when multiple put/remove operations are performed in sequence.
     * @expectedResults The map is expected to perform all the put/remove operations successfully.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of methods size() and isEmpty().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance has to be modified by the operations perfomed on it.
     */
    @Test
    public void testPut_Remove() {
        instance.put("pippo", "pluto");
        assertEquals("inserito il primo elemento", 1, instance.size());
        instance.put("pippo", "pluto");
        instance.put("asso", "topolino");    
        assertEquals("inserimento di due elementi, di cui uno già presente", 2, instance.size());
        instance.remove("pippo");
        assertEquals("rimozione di un elemento", 1, instance.size());
        instance.put("pippo", "pluto");
        assertEquals("inserimento di un elemento appena eliminato", 2, instance.size());
        instance.remove("pippo");
        instance.remove("asso");
        assertEquals("inserimento di tutti gli elementi della mappa", true, instance.isEmpty());
    }

    /**
     * @title Test #1 of putAll method, of class Map.
     * @description This test tests the behaviour of class Map when putAll() method is called using an empty map as parameter
     * @expectedResults The map shouldn't do anything.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method isEmpty
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance shouldn't be modified by the operations perfomed on it.
     */
    @Test
    public void testPutAll_empty() {
        instance.putAll(instance);
        assertEquals("la mappa deve rimanere inalterata", true, instance.isEmpty());
    }
    
    /**
     * @title Test #2 of putAll method, of class Map.
     * @description This test tests the behaviour of class Map when putAll() method is called using a map which contains only entries which are not contained in this map.
     * @expectedResults The map must add all the elements of the other map.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of methods size() and put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance has to be modified by the operations perfomed on it.
     */
    @Test
    public void testPutAll_notContained() {
        Map other = new Map();
        other.put("pippo", "asso");
        other.put("pluto", "topolino");
        other.put("paperino", "pluto");
        instance.putAll(other);
        assertEquals("la mappa deve aggiungere tutti gli elementi dell'altra mappa", 3, instance.size());
    }
    
    /**
     * @title Test #3 of putAll method, of class Map.
     * @description This test tests the behaviour of class Map when putAll() method is called using a map which contains a miscellaneous of elements that could be problematic.
     * @expectedResults The map must add just some of the entries of the other map.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of methods size() and put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance has to be modified by the operations perfomed on it.
     */
    @Test
    public void testPutAll_miscellaneous() {
        instance.put("AaAaAa", "asso");
        instance.put("pippo", "pluto");
        Map other = new Map();
        other.put("pippo", "asso");
        other.put("pluto", "topolino");
        other.put("paperino", "pluto");
        other.put("AaAaBB", "paperino");
        instance.putAll(other);
        assertEquals("la mappa deve aggiungere 3 soli elementi su 4", 5, instance.size());
    }
    
    /**
     * @title Test #4 of putAll method, of class Map.
     * @description This test tests the behaviour of class Map when putAll() method is called using a null reference as parameter. The result of an invocation of the method using as parameter a map which contains null elements cannot be tested, as we don't have Map implementations which support null elements.
     * @expectedResults The map must should throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance has to be modified by the operations perfomed on it.
     */
    @Test
    public void testPutAll_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.putAll(null);
                });
    }

    /**
     * @title Test #1 of remove method, of class Map.
     * @description This test tests the behaviour of class Map when remove() method is called on an empty map.
     * @expectedResults The map is expected to return a null reference, as the element wasn't contained in the map (which is empty).
     * @actualResult As expected result.
     * @dependencies  This test doesn't depend on the correctness of any other method of the class.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance is not modified as result of the test of the method.
     */
    @Test
    public void testRemove_empty() {
        Object result = instance.remove("pippo");
        assertEquals("rimozione su lista vuota", null, result);
    }
    
    /**
     * @title Test #2 of remove method, of class Map.
     * @description This test tests the behaviour of class Map when remove() method is called on a map which not contains the specified key.
     * @expectedResults The map is expected to return a null reference, as the element wasn't contained in the map.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of the methods put() and size().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance is not modified as direct result of the test of the method (but the map still has to contain the value inserted during the test).
     */
    @Test
    public void testRemove_notContained() {
        instance.put("pluto", "asso");
        Object result = instance.remove("pippo");
        assertEquals("rimozione di un elemento non presente", null, result);
        assertEquals("dimensione costante", 1, instance.size());
    }
    
    /**
     * @title Test #3 of remove method, of class Map.
     * @description This test tests the behaviour of class Map when remove() method is called on a map which contains the specified key.
     * @expectedResults The map is expected to return the previous value which was mapped with the key specified. The size of the map has to decrease.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of the methods put() and isEmpty().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance is modified as direct result of the test of the method.
     */
    @Test
    public void testRemove_contained() {
        instance.put("pluto", "asso");
        Object result = instance.remove("pluto");
        assertEquals("rimozione di un elemento presente", "asso", result);
        assertEquals("dimensione diminuita", true, instance.isEmpty());
    }
    
    /**
     * @title Test #4 of remove method, of class Map.
     * @description This test tests the behaviour of class Map when remove() method is called on a map using a key which has the same hash of a key contained, but is not the same object.
     * @expectedResults The map is expected to return a null reference, as the key is not really contained in the map.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of the methods put() and size().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance is not modified as direct result of the test of the method.
     */
    @Test
    public void testRemove_hash() {
        instance.put("AaAaAa", "asso");
        Object result = instance.remove("AaAaBB");
        assertEquals("rimozione di una chiave con hash uguale ad una chiave presente", null, result);
    }
    
    /**
     * @title Test #5 of remove method, of class Map.
     * @description This test tests the behaviour of class Map when remove() method is called using a null reference as parameter.
     * @expectedResults The map is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies  The correctness of this method does not depends on the correctness of any other method of Map.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance is not modified as direct result of the test of the method.
     */
    @Test
    public void testRemove_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.remove(null);
                });
    }

    /**
     * @title Test #1 of size method, of class Map.
     * @description This test tests the behaviour of size() method when called on an empty map.
     * @expectedResults The size is expected to be zero, as the map is empty.
     * @actualResult As expected result.
     * @dependencies This test has no dependencies on other class methods.
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void testSize_empty() {
        int result = instance.size();
        assertEquals("la dimensione di una mappa appena creata è 0", 0, result);
    }
    
    /**
     * @title Test #2 of size method, of class Map.
     * @description This test tests the behaviour of size() method when called on a non-empty map.
     * @expectedResults The size is expected to be equal to the number of elements that have been added to the map.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put().
     * @preConditions The map instance must be a new istance of Map.
     * @postConditions The map instance should not be modified by the execution of the method (but still have to contain the entry added while testing).
     */
    @Test
    public void testSize_notEmpty() {
        instance.put("pippo", "asso");
        int result = instance.size();
        assertEquals("la dimensione corrisponde al numero di entry aggiunte", 1, result);
    }    

    /**
     * @title Test of values method, of class Map.
     */
    @Test
    public void testValues() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}