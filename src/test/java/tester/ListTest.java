package tester;

import adapters.List;
import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giacomo Camposampiero
 */
public class ListTest {

    private List instance;

    public ListTest() {
    }

    @Before
    public void setUp() {
        instance = new List();
    }    

    /**
     * Test of add method, of class List. Depends on the correctness of methods
     * get() and size()
     */
    @Test
    public void testAdd_int_Object() {
        instance.add(0, "pippo");
        boolean result = instance.size() == 1;
        assertEquals("inserimento di un oggetto in una lista vuota", true, result);
        String elem = "pluto";
        instance.add(0, elem);
        result = instance.get(0).equals(elem) && instance.size() == 2;
        assertEquals("inserisco in testa ad una lista piena", true, result);
        instance.add(2, "topolino");
        result = instance.get(2).equals(elem) && instance.size() == 3;
        assertEquals("inserisco in coda ad una lista piena", true, result);
        instance.add(1, "pippo");
        result = instance.get(1).equals(elem) && instance.size() == 4;
        assertEquals("inserisco in mezzo ad una lista piena, con duplicato", true, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.add(null);
                });
        assertThrows("si specifica una posizione > size()", IndexOutOfBoundsException.class,
                () -> {
                    instance.add(100000, "pippo");
                });
        assertThrows("si specifica una posizione < 0", IndexOutOfBoundsException.class,
                () -> {
                    instance.add(-20, "pippo");
                });
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of add method, of class List.
     */
    @Test
    public void testAdd_Object() {
        String elem = "pippo";
        boolean result = instance.add(elem);
        assertEquals("primo elemento che inserisco", true, result);
        result = instance.add(elem);
        assertEquals("inserisco per la seconda volta lo stesso elemento, la lista ammette duplicati", true, result);
        result = instance.add("pluto");
        assertEquals("inserisco una seconda stringa", true, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.add(null);
                });
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of addAll method, of class List.
     * Depends on the correctness of methods add(), size() clear() 
     */
    @Test
    public void testAddAll_HCollection() {
        List list = new List();
        boolean result = instance.addAll(list) || !instance.isEmpty();
        assertEquals("aggiunta una collezione vuota, che non modifica lo stato della lista", false, result);
        instance.add("pippo");
        list.add("pippo");
        result = instance.addAll(list) && instance.size() == 2;
        assertEquals("aggiunta di una collezione con elementi già presenti", false, result);
        list.add("pluto");
        result = instance.addAll(list) && instance.size() == 4;
        assertEquals("aggiunta di una collezione con nuovi elementi e elementi già presenti", true, result);
        list.clear();
        list.add("topolino");
        result = instance.addAll(list) && instance.size() == 5;
        assertEquals("aggiunta di una collezione con soli nuovi elementi", true, result);
        result = instance.get(0).equals("pippo") && instance.get(1).equals("pippo") && instance.get(2).equals("pippo") && instance.get(3).equals("pluto") && instance.get(4).equals("topolino");
        assertEquals("controllo che gli elementi siano effettivamente stati inseriti in fondo alla lista e che siano tutti presenti", true, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsAll(null);
                });
        //il caso in cui si aggiunge una collection con all'interno uno o più elementi null non può essere controllato, non dispongo di classi che accettano come elementi null
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of addAll method, of class List.
     */
    @Test
    public void testAddAll_int_HCollection() {
        
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsAll(null);
                });
        //il caso in cui si aggiunge una collection con all'interno uno o più elementi null non può essere controllato, non dispongo di classi che accettano come elementi null
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of clear method, of class List.
     * Depends on the correctness of method add()
     */
    @Test
    public void testClear() {
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una lista vuota, controllo che la dimensione sia nulla", true, result);
        instance.add("pippo");
        instance.add("pluto");
        instance.clear();
        result = instance.isEmpty();
        assertEquals("pulizia di una lista piena, controllo che la dimensione sia nulla", true, result);
    }

    /**
     * Test of contains method, of class List.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object o = null;
        List instance = new List();
        boolean expResult = false;
        boolean result = instance.contains(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAll method, of class List.
     */
    @Test
    public void testContainsAll() {
        System.out.println("containsAll");
        HCollection c = null;
        List instance = new List();
        boolean expResult = false;
        boolean result = instance.containsAll(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of equals method, of class Set.
     * Depends on the correctness of method add()
     */
    @Test
    public void testEquals() {
        instance.add("pippo");
        List instance2 = new List();
        boolean result = instance.equals(instance2);
        assertEquals("confronto di due list diverse", false, result);
        instance2.add("pippo");
        result = instance.equals(instance2);
        assertEquals("confronto di due list uguali", true, result);
        result = instance2.equals(instance);
        assertEquals("il confronto deve essere simmetrico", true, result);
    }

    /**
     * Test of get method, of class List.
     * 
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        List instance = new List();
        Object expResult = null;
        Object result = instance.get(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
        
    /**
     * Test of hashCode method, of class Set. Depends on the correctness of
     * method add()
     */
    @Test
    public void testHashCode() {
        int result = instance.hashCode();
        assertEquals("per definizione, l'hashcode di una list vuota deve essere 0", 0, result);
        String elem = "pippo";
        instance.add(elem);
        result = instance.hashCode();
        assertEquals("per definizione, l'hashcode di una list con un solo elemento deve essere uguale all'hashcode dell'elemento", elem.hashCode(), result);
        String elem2 = "pluto";
        instance.add(elem2);
        result = instance.hashCode();
        assertEquals("per definizione, l'hashcode di una lista con più elementi deve essere uguale alla somma degli hash dei suoi elementi", elem.hashCode() + elem2.hashCode(), result);
    }

    /**
     * Test the coherence of methods hashCode() and equals(). Depends on the
     * correctness of method add()
     */
    @Test
    public void testHashEquals() {
        instance.add("pippo");
        int hash = instance.hashCode();
        List instance2 = new List();
        instance2.add("pippo");
        boolean result = (hash == instance2.hashCode()) && instance.equals(instance2);
        assertEquals("confronto di due set diversi", true, result);
    }

    /**
     * Test of indexOf method, of class List.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Object o = null;
        List instance = new List();
        int expResult = 0;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of isEmpty method, of class Set.
     */
    @Test
    public void testIsEmpty() {
        boolean result = instance.isEmpty();
        assertEquals("una list appena creata deve essere vuota", true, result);
    }

    /**
     * Tests the coeherence between size() and isEmpty() methods.
     */
    @Test
    public void testIsEmptySize() {
        boolean size = (instance.size() == 0);
        boolean isEmpty = instance.isEmpty();
        assertEquals("i metodi size() e isEmpty() devono essere coerenti", size, isEmpty);
    }

    /**
     * Test of iterator method, of class List.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        List instance = new List();
        HIterator expResult = null;
        HIterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lastIndexOf method, of class List.
     */
    @Test
    public void testLastIndexOf() {
        System.out.println("lastIndexOf");
        Object o = null;
        List instance = new List();
        int expResult = 0;
        int result = instance.lastIndexOf(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listIterator method, of class List.
     */
    @Test
    public void testListIterator_0args() {
        System.out.println("listIterator");
        List instance = new List();
        HListIterator expResult = null;
        HListIterator result = instance.listIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listIterator method, of class List.
     */
    @Test
    public void testListIterator_int() {
        System.out.println("listIterator");
        int index = 0;
        List instance = new List();
        HListIterator expResult = null;
        HListIterator result = instance.listIterator(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class List.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove");
        int index = 0;
        List instance = new List();
        Object expResult = null;
        Object result = instance.remove(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class List.
     */
    @Test
    public void testRemove_Object() {
        System.out.println("remove");
        Object o = null;
        List instance = new List();
        boolean expResult = false;
        boolean result = instance.remove(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAll method, of class List.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        HCollection c = null;
        List instance = new List();
        boolean expResult = false;
        boolean result = instance.removeAll(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retainAll method, of class List.
     */
    @Test
    public void testRetainAll() {
        System.out.println("retainAll");
        HCollection c = null;
        List instance = new List();
        boolean expResult = false;
        boolean result = instance.retainAll(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class List.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int index = 0;
        Object element = null;
        List instance = new List();
        Object expResult = null;
        Object result = instance.set(index, element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of size method, of class Set.
     */
    @Test
    public void testSize() {
        int result = instance.size();
        assertEquals("la dimensione di una lista appena creata deve essere 0", 0, result);
    }

    /**
     * Test of subList method, of class List.
     */
    @Test
    public void testSubList() {
        System.out.println("subList");
        int fromIndex = 0;
        int toIndex = 0;
        List instance = new List();
        HList expResult = null;
        HList result = instance.subList(fromIndex, toIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class List.
     */
    @Test
    public void testToArray_0args() {
        System.out.println("toArray");
        List instance = new List();
        Object[] expResult = null;
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test the throwing of UnsupportedOperationException exception.
     */
    @Test
    public void testToArray_1args() {
        assertThrows("invocato metodo toArray parametrico", UnsupportedOperationException.class,
                () -> {
                    instance.toArray(new Object[1]);
                });
    }

}