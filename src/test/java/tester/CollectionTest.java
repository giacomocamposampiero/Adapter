package tester;

import interfaces.HCollection;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

/**
 * Test suite for testing the behaviour of List and Set implementation as Collections.
 * This test suite is a parameterized suite which use reflection to test the common behaviour between List and Set defined by Collection interface.
 * @author Giacomo Camposampiero
 */
@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CollectionTest {

    private final String paramClass;
    private HCollection instance;
    
    public CollectionTest(String paramClass) {
        this.paramClass = paramClass;
    }

    @Parameterized.Parameters(name="{0}")
    public static Collection classesToTest() {
        return Arrays.asList(new Object[][]{
            {"List"},
            {"Set"},
        });
    }
    
    @Before
    public void initialize() {
        try {
            instance = (HCollection) Class.forName("adapters."+paramClass).getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | SecurityException  ex) {
            System.exit(1);
        }
    }
    
    /**
     * @title Test #1 of add method, of an istance of Collection interface.
     * @description This test tests the behaviour of the method add() when called on an empty collection.
     * @expectedResults Adding an element to an empty list (as it has just been istantiated) must be always possible.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance is directly modified by the execution of the method tested.
     */
    @Test
    public void testAdd_empty() {
        boolean result = instance.add("pippo");
        assertEquals("primo elemento che inserisco", true, result);
        assertEquals("la dimensione deve essere incrementata di 1", 1, instance.size());
    }
    
    /**
     * @title Test #2 of add method, of an istance of Collection interface.
     * @description This test tests the behaviour of the method add() when called on a not-empty collection.
     * @expectedResults Adding an element which is not contained in the collection should be always possible.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance is directly modified by the execution of the method tested.
     */
    @Test
    public void testAdd_notContained() {
        instance.add("pluto");
        boolean result = instance.add("pippo");
        assertEquals("aggiunta di un elemento non presente, possibile", true, result);
        assertEquals("la dimensione aumenta di 1", 2, instance.size());
    }
    
    /**
     * @title Test #3 of add method, of an istance of Collection interface.
     * @description This test tests the behaviour of the method add() when called on a collection using a null parameter.
     * @expectedResults The class is expected to throw a NullPointerException when a null reference is used in the add method.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of any other method of interface Collection.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void testAdd_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.add(null);
                });
    }
    
    /**
     * @title Test of miscellaneous of methods, of an istance of Collection interface.
     * @description This test tests a complexe burst of add and remove operations. Coherence with methods size() and isEmpty() is tested too.
     * @expectedResults The sequence of operations succeed.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of add(), remove(), size() and isEmpty() 
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void testAddRemoveSizeEmpty() {
        boolean result = instance.add("pippo");
        result &= instance.add("pluto");
        assertEquals("inserimenti riusciti", true, result);
        assertEquals("controllo validità metodo size()", 2, instance.size());
        assertEquals("controllo validità metodo isEmpty()", false, instance.isEmpty());
        result = instance.remove("pippo");
        result |= instance.remove("pluto");
        assertEquals("rimozioni riuscite", false, result);
        assertEquals("controllo validità metodo size()", 0, instance.size());
        assertEquals("controllo validità metodo isEmpty()", true, instance.isEmpty());
        result = instance.add("pippo");
        assertEquals("inserimento elemento precedentemente eliminato", true, (instance.size()==1) && result);
        result = instance.remove("pippo");
        assertEquals("inserimento elemento precedentemente eliminato", true, instance.isEmpty() && result);
    }
    
    /**
     * @title Test #1 of clear method, of an istance of Collection interface.
     * @description This test tests the behaviour of the method clear() when called on an empty collection.
     * @expectedResults A just instancied collection is expected to be empty, should be still empty after the invocation of the method.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods add() and isEmpty.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testClear_empty() {
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una collezione vuota, controllo che la dimensione rimanga 0", true, result);
    }    
     
    /**
     * @title Test #2 of clear method, of an istance of Collection interface.
     * @description This test tests the behaviour of the method clear() when called on a not-empty collection.
     * @expectedResults The collection is expected to be empty after invocation of the method.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods add() and isEmpty().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance hase to be modified by the execution of the method tested.
     */
    @Test
    public void testClear_notEmpty() {
        instance.add("pippo");
        instance.add("pluto");
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una lista piena, controllo che la dimensione sia 0", true, result);
    }
    
    /**
     * @title Test #1 of isEmpty method, of an istance of Collection interface.
     * @description This test tests the behaviour of the method isEmpty() when called on an empty collection
     * @expectedResults A just instancied collection is expected to be empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of any other method.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testIsEmpty_empty() {
        boolean result = instance.isEmpty();
        assertEquals("controllo su collezione vuota", true, result); 
    }
    
    /**
     * @title Test #2 of isEmpty method, of an istance of Collection interface.
     * @description This test tests the behaviour of the method isEmpty() when called on an empty Collection.
     * @expectedResults An element was added to the collection, the collection should not be empty.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance doesn't have to be modified by the execution of the method tested (but still have to contain the entry added while testing).
     */
    @Test
    public void testIsEmpty_notEmpty() {
        instance.add("pippo");
        boolean result = instance.isEmpty();
        assertEquals("controllo su collezione non vuota", false, result); 
    }
    
    /**
     * @title Test #3 of isEmpty method, of an istance of Collection interface.
     * @description This test tests the coherence between methods size() and isEmpty().
     * @expectedResults A coherent behaviour is expected between size() and isEmpty() methods.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of any other method.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance doesn't have to be modified directly by the execution of the method tested.
     */
    @Test
    public void testIsEmpty_size() {
        boolean size = (instance.size() == 0);
        boolean isEmpty = instance.isEmpty();
        assertEquals("i metodi devono essere coerenti quando la collezione è vuota", size, isEmpty);
        instance.add("pippo");
        size = (instance.size() == 0);
        isEmpty = instance.isEmpty();
        assertEquals("i metodi devono essere coerenti quando la collezione è piena", size, isEmpty);
    }
    
    /**
     * @title Test #1 of size method, of an istance of Collection interface.
     * @description This test tests the behaviour of size() method when called on an empty collection.
     * @expectedResults The size is expected to be zero, as the collection is empty.
     * @actualResult As expected result.
     * @dependencies This test has no dependencies on other class methods.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified by the execution of the method.
     */
    @Test
    public void testSize_empty() {
        int result = instance.size();
        assertEquals("la dimensione di una collectionpa appena creata è 0", 0, result);
    }
    
    /**
     * @title Test #2 of size method, of an istance of Collection interface.
     * @description This test tests the behaviour of size() method when called on a non-empty collection.
     * @expectedResults The size is expected to be equal to the number of elements that have been added to thecollection.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testSize_notEmpty() {
        instance.add("pippo");
        int result = instance.size();
        assertEquals("la dimensione corrisponde al numero di elementi aggiunti", 1, result);
    }    
    
    /**
     * @title Test #1 of remove method, of an istance of Collection interface.
     * @description This test tests the behaviour of remove() method when called on an empty collection.
     * @expectedResults The collection is empty, the remove() must return false.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testRemove_empty() {
        boolean result = instance.remove("pippo");
        assertEquals("rimozione di un elemento in una collezione vuota", false, result);
    }
    
    /**
     * @title Test #2 of remove method, of an istance of Collection interface.
     * @description This test tests the behaviour of remove() method when called on a not-empty collection and the specified element is not contained.
     * @expectedResults Specified element is not contained in the collection, the remove must return false.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods add() and isEmpty().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testRemove_notContained() {
        instance.add("pippo");
        boolean result = instance.remove("pluto");
        assertEquals("rimozione oggetto non presente", false, result && !instance.isEmpty());
    }
    
    /**
     * @title Test #3 of remove method, of an istance of Collection interface.
     * @description This test tests the behaviour of remove() method when called on a not-empty collection and the specified element is contained.
     * @expectedResults Specified element is contained in the collection, the remove must return true and remove the element.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods add() and isEmpty().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should be modified directly by the execution of the method.
     */
    @Test
    public void testRemove_contained() {
        instance.add("pippo");
        boolean result = instance.remove("pippo");
        assertEquals("rimozione oggetto presente", true, result && instance.isEmpty());
        result = instance.remove("pippo");
        assertEquals("rimozione oggetto rimosso in precedenza", false, result);
    }
    
    /**
     * @title Test #4 of remove method, of an istance of Collection interface.
     * @description This test tests the behaviour of remove() method when called using a null reference as parameter.
     * @expectedResults The method is expected to throw a NullPointerException as null elements are not supported by this collection.
     * @actualResult As expected result.
     * @dependencies This test does not depends on the correctness of any other method of the interface.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testRemove_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.remove(null);
                });
    }
    
    /**
     * @title Test #1 of toArray method, of an istance of Collection interface.
     * @description This test tests the behaviour of toArray() method when called on an empty collection.
     * @expectedResults The returned array must be an empty array, as the instance is empty.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testToArray_empty() {
        Object[] expected = new Object[0];
        Object[] result = instance.toArray();
        assertArrayEquals("toArray invocato su un set vuoto deve ritornare un array vuoto", expected, result);
    }    
        
    /**
     * @title Test #2 of toArray method, of an istance of Collection interface.
     * @description This test tests the behaviour of toArray() method when called on a non-empty collection.
     * @expectedResults The returned array must be aa generic Object[] array, its size has to be equal to the instance size and it must contains all the elements of the original array.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods size(), add() and contains().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testToArray_notEmpty() {    
        instance.add("pippo");
        instance.add("pluto");
        Object[] result = instance.toArray();
        assertEquals("la lunghezza dell'array deve coincidere con quella dell'istanza", instance.size(), result.length);
        boolean check = true;
        for(int i=0; i<result.length; i++) {
            check = check && instance.contains(result[i]);
        }   
        assertEquals("tutti gli elementi dell'array devono essere contenuti nell'istanza", true, check);
        assertEquals("l'array restituito deve essere un array di Object", Object[].class, result.getClass());
    }
    
    /**
     * @title Test #3 of toArray method, of an istance of Collection interface.
     * @description This test tests that the returned array is safe, no reference are mantained to the main collection.
     * @expectedResults The returned array can be modified without causing any structural modification to the collection.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods add() and size().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testToArray_safe() {    
        instance.add("pippo");
        instance.add("pluto");
        Object[] arr = instance.toArray();
        arr[0] = null;
        arr[1] = "paperino";
        boolean result = instance.contains("pippo") && instance.contains("pluto");
        assertEquals("la collection iniziale contiene ancora entrambi i valori iniziali", true, result);
    }
       
    /**
     * @title Test #1 of parametric toArray method, of an istance of Collection interface.
     * @description This test tests the behaviour of the parametric toArray() when an empty array is used as parameter and the collection is empty.
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, not modified at all.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be directly modified by the call of the method tested.
     */
    @Test
    public void testParametricToArray_bothEmpty() {
        String[] param = new String[0];
        Object[] result;
        result = instance.toArray(param);
        assertArrayEquals("collection e array vuoti, dovrebbe coincidere", param, result);
    }
    
    /**
     * @title Test #2 of parametric toArray method, of an istance of Collection interface.
     * @description This test tests the behaviour of the parametric toArray() when a not-empty array is used as parameter and thecollection is empty. This method test also wheter the method modify the only the firstcollection.size() cells of the param array.
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, but the firstcollection.size() cells must contains the elements of the collection.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be directly modified by the call of the method tested.
     */
    @Test
    public void testParametricToArray_collectionEmpty() {
        String[] param = new String[10];
        for(int i=0; i<10; i++) param[i] = "pippo";
        Object[] result;
        result = instance.toArray(param);
        assertArrayEquals("collection vuoto e array riempito, dovrebbe coincidere", param, result);
    }
    
    /**
     * @title Test #3 of parametric toArray method, of an istance of Collection interface.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty collection passing a not-empty array which length is greater than collection size.
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, but the first size() must be modified, as they should contains the elements of this collection. The elements contained in the array in positions >= size() should not be modified.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method add().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be directly modified by the call of the method tested.
     */
    @Test
    public void testParametricToArray_notEmpty() {
        String[] param = new String[10];
        for(int i=0; i<10; i++) param[i] = "pippo";
        instance.add("pluto");
        instance.add("paperino");
        instance.add("topolino");
        
        Object[] result = instance.toArray(param);
        assertEquals("il tipo dell'array ritornato coincide con quello dell'array parametro", true, result.getClass().equals(param.getClass()));
        boolean check = true;
        for(int i=0; i<instance.size(); i++) {
            check = check && instance.contains(result[i]);
        }
        for(int i=instance.size(); i<result.length; i++) {
            check = check && result[i].equals(param[i]);
        }
        assertEquals("gli elementi del collection sono stati copiati nelle prime size()-1 posizioni, le successive non sono state modificate", true, check);
    }
    
    /**
     * @title Test #4 of parametric toArray method, of an istance of Collection interface.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty collection using an array which is not big enough to contain the whole collection.
     * @expectedResults The array returned should be a new istance of a generic Object array (the parameter should not be modified), and it should contains all the elements contained in the collection.
     * @actualResult As expected result.
     * @dependencies Depends on the correctess of method add().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be directly modified by the call of the method tested.
     */
    @Test
    public void testParametricToArray_notBigEnough() {
        String[] param = new String[1];
        param[0] = "pippo";
        String[] copyParam = Arrays.copyOf(param, param.length);
        instance.add("pluto");
        instance.add("paperino");
        instance.add("topolino");
        
        Object[] result = instance.toArray(param);
        assertEquals("il tipo dell'array ritornato non coincide con quello dell'array parametro", false, result.getClass().equals(param.getClass()));
        boolean check = instance.size() == result.length;
        for (Object tmp : result) {
            check = check && instance.contains(tmp);
        }
        assertEquals("gli elementi del collection sono tutti presenti e le dimensioni del risultato e dell'istanza di collection coincidono", true, check);
        assertArrayEquals("l'array parametro non è stato modificato", copyParam, param);
    }
    
    /**
     * @title Test #5 of parametric toArray method, of an istance of Collection interface.
     * @description This test tests the behaviour of parametric toArray() method when a null reference is used as parameter.
     * @expectedResults A NullPointerException was thrown as result of the call.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should not be directly modified by the call of the method tested.
     */
    @Test
    public void testParametricToArray_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.toArray(null);
                });
    }

}