package tester;

import adapters.List;
import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Giacomo Camposampiero
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListTest {

    private List instance;

    @Before
    public void setUp() {
        instance = new List();
    }    
    
    /**
     * @title Test #1 of add method, of class List.
     * @description This test tests the behaviour of the method add() when called on a not-empty list. More in details, it only tests that the element is appended at the end of the list.
     * @expectedResults The add method is expected to append the new element at the end of the list.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The collection instance must be a new istance of List.
     * @postConditions The collection instance is directly modified by the execution of the method tested.
     */
    @Test
    public void testAdd_append() {
        instance.add("pippo");
        assertEquals("stringa inserita a fine lista", "pippo", instance.get(instance.size()-1));
        instance.add("pluto");
        assertEquals("stringa inserita a fine lista", "pluto", instance.get(instance.size()-1));
    }
    
    /**
     * @title Test #2 of add method, of class List.
     * @description This test tests the behaviour of the method add() when called on a not-empty list using a parameter which is already contained in the list.
     * @expectedResults List allows duplicates, the new entry should be accepted.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The collection instance must be a new istance of List.
     * @postConditions The collection instance is directly modified by the execution of the method tested.
     */
    @Test
    public void testAdd_duplicates() {
        instance.add("pippo");
        boolean result = instance.add("pippo");
        assertEquals("inserimento duplicato", true, result);
        assertEquals("controllo aumento dimensione", 2, instance.size());
    }

    /**
     * Test of add method, of class List. 
     * Depends also on the correctness of methods get() and size()
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
        result = instance.get(2).equals("topolino") && instance.size() == 3;
        assertEquals("inserisco in coda ad una lista piena", true, result);
        instance.add(1, "pippo");
        result = instance.get(1).equals("pippo") && instance.size() == 4;
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
     * @title Test #1 of addAll method, of class List.
     * @description This test tests the behaviour of the method addAll() when called on a not-empty list. More in details, it only tests that the element is appended at the end of the list in the precise order defined by the iterator.
     * @expectedResults The add method is expected to append the new elements at the end of the list.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The collection instance must be a new istance of List.
     * @postConditions The collection instance is directly modified by the execution of the method tested.
     */
    @Test
    public void testAddAll_append() {
        HCollection param = new List();
        param.add("pippo");
        param.add("pluto");
        instance.add("paperino");
        instance.addAll(param);
        boolean res = true;
        HIterator instIt = instance.iterator();
        instIt.next();
        HIterator it = param.iterator();
        while(it.hasNext()) res &= it.next().equals(instIt.next());
        assertEquals("elementi aggiunti in fondo nell'ordine corretto", true, res);
    }
    
    /**
     * @title Test #2 of addAll method, of class List.
     * @description This test tests the behaviour of the method addAll() when called using a collection which contains elements which are already contained in the list.
     * @expectedResults The elementes which are already contained should be added without problems in the list, duplicates are allowed.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods size() and add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance has to be modified by the execution of the method tested.
     */
    @Test
    public void testAddAll_contained() {
        instance.add("pippo");
        HCollection param = new List();
        param.add("pippo");
        param.add("pluto");
        boolean result = instance.addAll(param);
        assertEquals("ci sono elementi nuovi, vengono aggiunti e lo stato della lista cambia", true, result);
        assertEquals("tutti gli elementi sono aggiunti", 3, instance.size());
    }

    /**
     * Test of addAll method, of class List.
     * Depends also on the correctness of methods add(), clear(), iterator() and size()
     */
    @Test
    public void testAddAll_int_HCollection() {
        List list = new List();
        boolean result = instance.addAll(list) || !instance.isEmpty();
        assertEquals("aggiunta una collezione vuota, che non modifica lo stato della lista", false, result);
        list.add("pippo");
        list.add("pluto");
        result = instance.addAll(0, list) && (instance.size()==2);
        assertEquals("aggiunta di una collezione piena, che modifica lo stato della lista", true, result);
        list.clear();
        instance.clear();
        list.add("topolino");
        list.add("paperino");
        result = instance.addAll(0, list) && (instance.size()==2);
        HIterator itInst = instance.iterator();
        HIterator itList = list.iterator();
        while(itInst.hasNext() && itList.hasNext()) 
                result = result && itInst.next().equals(itList.next());
        assertEquals("aggiunta di una collezione piena nel mezzo, controllo che gli elementi della collezione sianio aggiunti nell'ordine in cui sono restituiti dall'iteratore", true, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.addAll(null);
                });
        assertThrows("si specifica una posizione >> size()", IndexOutOfBoundsException.class,
                () -> {
                    instance.addAll(100000, list);
                });
        assertThrows("si specifica una posizione < 0", IndexOutOfBoundsException.class,
                () -> {
                    instance.addAll(-20, list);
                });
        //il caso in cui si aggiunge una collection con all'interno uno o più elementi null non può essere controllato, non dispongo di classi che accettano come elementi null
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }
  
    /**
     * @title Test #1 of contains method, of class List.
     * @description This test tests the behaviour of the method contains() when called on a list which contains multiple elements which equals to the parameter.
     * @expectedResults The parameter should be contained.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method add().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance doesn't have to be directly modified by the execution of the method tested.
     */
    @Test
    public void testContains_multiple() {
        instance.add("pippo");
        instance.add("pippo");
        instance.add("pippo");
        boolean result = instance.contains("pippo");
        assertEquals("collezione che contiene più istanze del parametro", true, result);
    }
    
    /**
     * @title Test #1 of containsAll method, of class List.
     * @description This test tests the behaviour of the method containsAll() when called on a list which contains multiple elements which equals to the parameter.
     * @expectedResults The parameter should be contained.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method add().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance doesn't have to be directly modified by the execution of the method tested.
     */
    @Test
    public void testContainsAll_multiple() {
        instance.add("pippo");
        instance.add("pippo");
        instance.add("pluto");
        instance.add("paperino");
        List param = new List();
        param.add("pippo");
        param.add("pippo");
        param.add("pluto");
        boolean result = instance.containsAll(param);
        assertEquals("collezione che contiene più istanze del parametro", true, result);
    }
    
    /**
     * @title Test #1 of equals method, of class List.
     * @description This test tests the behaviour of the method equals() when two empty lists are compared
     * @expectedResults Two empty lists should equals.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of any other method.
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance doesn't have to be directly modified by the execution of the method tested.
     */
    @Test
    public void testEquals_empty() {
        List instance2 = new List();
        boolean result = instance.equals(instance2);
        assertEquals("confronto di due list vuoti", true, result);
    }
    
    /**
     * @title Test #2 of equals method, of class List.
     * @description This test tests the behaviour of the method equals() when the two lists don't contains the same elements.
     * @expectedResults Two empty lists should not equals.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method add.
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance doesn't have to be directly modified by the execution of the method tested.
     */
    @Test
    public void testEquals_differents() {
        instance.add("pippo");
        List instance2 = new List();
        boolean result = instance.equals(instance2);
        assertEquals("confronto di due list diversi", false, result);
    }
    
    /**
     * @title Test #3 of equals method, of class List.
     * @description This test tests the behaviour of the method equals() when the two lists contains the same elements. It's also tested that the method is simmetric.
     * @expectedResults Two empty lists should equals.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance doesn't have to be directly modified by the execution of the method tested.
     */
    @Test
    public void testEquals_equals() {
        instance.add("pippo");
        List other = new List();
        other.add("pippo");
        boolean result = instance.equals(other);
        assertEquals("confronto di due list uguali", true, result);
        result = other.equals(instance);
        assertEquals("il confronto deve essere simmetrico", true, result);
    }

    /**
     * @title Test #1 of hashCode method, of class List.
     * @description This test tests the behaviour of the method hashCode() when called on an empty List. The hash of a List is defined with a specific formula in the documentation.
     * @expectedResults The hashcode of an empty List must be 1.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of any other method.
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testHashCode_empty() {
        int result = instance.hashCode();
        assertEquals("per definizione, l'hashcode di un list vuoto deve essere 1", 1, result);
    }
    
    /**
     * @title Test #2 of hashCode method, of class List.
     * @description This test tests the behaviour of the method hashCode() when called on a not-empty List. The hash of a List is defined with a specific formula in the documentation.
     * @expectedResults The hashcode of a list must equals to the result of the specified formula.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void testHashCode_notEmpty() {
        String elem1 = "pippo", elem2 = "pluto";
        instance.add(elem1);
        instance.add(elem2);
        int result = instance.hashCode();
        int expected = 31 + elem1.hashCode();
        expected = expected * 31 + elem2.hashCode();
        assertEquals("hash di un list non vuoto", expected, result);
    }

    /**
     * @title Test of hashCode and equals methods, of class List.
     * @description This test tests the coherence between the hashCode and equals methods. More in details, if two objects equals each other they must have the same hash. If two object has the same hash, not necessarly they equals each other.
     * @expectedResults The equals method is expected to be coherent with the hashcode. 
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance is not directly modified by the execution of the method tested.
     */
    @Test
    public void testHashEquals() {
        instance.add("pippo");
        int hash = instance.hashCode();
        List instance2 = new List();
        instance2.add("pippo");
        boolean result = (hash == instance2.hashCode()) && instance.equals(instance2);
        assertEquals("controllo coerenza hashCode ed equals", true, result);
    }

    /**
     * Test of get method, of class List.
     * Depends also on the correctness of method add()
     */
    @Test
    public void testGet() {
        String elem = "pippo";
        String elem2 = "pluto";
        instance.add(0, elem);
        boolean result = elem.equals(instance.get(0));
        assertEquals("metodo invocato su una lista con un solo elemento", true, result);
        instance.add(1, elem2);
        instance.add(2, "paperino");
        result = elem2.equals(instance.get(1));
        assertEquals("metodo invocato su una lista con più elementi", true, result);
        
        //controllo eccezioni
        assertThrows("si specifica una posizione >= size()", IndexOutOfBoundsException.class,
                () -> {
                    instance.get(instance.size());
                });
        assertThrows("si specifica una posizione < 0", IndexOutOfBoundsException.class,
                () -> {
                    instance.get(-20);
                });
    }

    /**
     * Test of indexOf method, of class List.
     * Depends also on the correctness of method add()
     */
    @Test
    public void testIndexOf() {
        String elem = "pippo";
        int result = instance.indexOf(elem);
        assertEquals("metodo invocato su lista vuota", -1, result);
        instance.add("pluto");
        result = instance.indexOf(elem);
        assertEquals("metodo invocato su lista popolata, elemento non presente", -1, result);
        instance.add("pippo");
        result = instance.indexOf(elem);
        assertEquals("metodo invocato su lista popolata dove l'elemento è presente, viene testato anche l'utilizzo del metodo equals degli oggetti per fare confronti", 1, result);
        instance.add("pippo");
        result = instance.indexOf(elem);
        assertEquals("controllo che nel caso di occorrenze multiple il metodo restituisca l'indice minore tra quelli degli elementi uguali", 1, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.indexOf(null);
                });
        //ClassCastException non può essere lanciata per definizione
    }

    /**
     * Test of lastIndexOf method, of class List.
     * Depends also on the correctness of method add()
     */
    @Test
    public void testLastIndexOf() {
        String elem = "pippo";
        int result = instance.lastIndexOf(elem);
        assertEquals("metodo invocato su lista vuota", -1, result);
        instance.add("pluto");
        result = instance.lastIndexOf(elem);
        assertEquals("metodo invocato su lista popolata, elemento non presente", -1, result);
        instance.add("pippo");
        result = instance.lastIndexOf(elem);
        assertEquals("metodo invocato su lista popolata dove l'elemento è presente, viene testato anche l'utilizzo del metodo equals degli oggetti per fare confronti", 1, result);
        instance.add("pippo");
        result = instance.lastIndexOf(elem);
        assertEquals("controllo che nel caso di occorrenze multiple il metodo restituisca l'indice maggiore tra quelli degli elementi uguali", 2, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.lastIndexOf(null);
                });
        //ClassCastException non può essere lanciata per definizione
    }

    /**
     * Test of listIterator method, of class List.
     */
    @Test
    public void testListIterator_0args() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of listIterator method, of class List.
     */
    @Test
    public void testListIterator_int() {
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class List.
     * Depends also on the correctness of methods add(), get() and size()
     */
    @Test
    public void testRemove_int() {
        instance.add("pippo");
        instance.add("pluto");
        instance.add("topolino");
        Object result = instance.remove(0);
        assertEquals("l'elemento è stato rimosso dalla lista", 2, instance.size());
        boolean check = result.equals("pippo") && instance.get(0).equals("pluto") && instance.get(1).equals("topolino");
        assertEquals("gli elementi a destra di quello rimosso sono stati scalati di uno e l'oggetto restituito è effettivamente quello cancellato", true, check);
        
        //controllo eccezioni
        assertThrows("si specifica una posizione >= size()", IndexOutOfBoundsException.class,
                () -> {
                    instance.remove(instance.size());
                });
        assertThrows("si specifica una posizione < 0", IndexOutOfBoundsException.class,
                () -> {
                    instance.remove(-20);
                });
    }

    /**
     * @title Test #1 of remove method, of class List.
     * @description This test tests the behaviour of remove() method when called on a not-empty collection and the specified element is contained. More in details, it tests wheter the removed element is that one with the lowest index or not. It also tests that the method removes only one element from the list.
     * @expectedResults The element remove is the one which has the lowest index between the equals object of the list.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods add(), isEmpty() and get().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should be modified directly by the execution of the method.
     */
    @Test
    public void testRemove() {
        instance.add("pippo");
        instance.add("pluto");
        instance.add("pippo");
        boolean result = instance.remove("pippo");
        String[] expected = {"pluto", "pippo"};
        for(int i=0; i<2; i++) result &= instance.get(i).equals(expected[i]);
        assertEquals("verifica che, in caso di duplicati, si rimuova l'istanza con indice minore", true, result);
        assertEquals("l'elemento rimosso deve essere solo uno", 2, instance.size());
    }
    
    /**
     * @title Test #1 of retainAll method, of Class list.
     * @description This test tests the behaviour of retainAll() method when called on a collection which has multiple istances of an element contained in the param.
     * @expectedResults All the instances of the object should be mantained in the collection.
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correctness of methods add() and size().
     * @preConditions The list instance must be a new istance of Collection.
     * @postConditions The list instance should be directly modified  by the execution of the method.
     */
    @Test
    public void testRetainAll_duplicates() {    
        instance.add("asso");
        instance.add("pluto");
        instance.add("pluto");
        instance.add("pluto");
        HCollection param = new List();
        param.add("pluto");
        param.add("asso");
        boolean result = instance.retainAll(param);
        assertEquals("tutti gli elementi devono essere mantenuti", false, result);
        assertEquals("la dimensione non deve variare", 4, instance.size());
    }
    
    /**
     * Test of set method, of class List.
     * Depends also on the correctness of methods add() and get()
     */
    @Test
    public void testSet() {
        instance.add("pippo");
        instance.add("pluto");
        instance.add("topolino");
        Object[] arr = instance.toArray();
        Object result = instance.set(0, "pluto");
        assertEquals("controllo che l'oggetto restituito dal metodo corrisponda a quello che è stato sostituito ", true, result.equals("pippo"));
        assertEquals("controllo che l'oggetto all'indice specificato sia stato effettivamente sostituito", true, instance.get(0).equals("pluto"));
        boolean check = true;
        for(int i=1; i<arr.length; i++) check = check && arr[i].equals(instance.get(i));
        assertEquals("controllo che il resto della lista non sia stato alterato dalla modifica", true, check);
        
        //controllo eccezioni
        assertThrows("parametro element null", NullPointerException.class,
                () -> {
                    instance.set(0, null);
                });
        assertThrows("si specifica una posizione >= size()", IndexOutOfBoundsException.class,
                () -> {
                    instance.set(-1, "pippo");
                });
        assertThrows("si specifica una posizione < 0", IndexOutOfBoundsException.class,
                () -> {
                    instance.set(instance.size(), "pippo");
                });
    }

    /**
     * Test of subList method, of class List.
     */
    @Test
    public void testSubList() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * @title Test #1 of toArray method, of Class list.
     * @description This test tests the behaviour of toArray() method when called on a non-empty collection. More in details, it tests that the returned array elements are placed in the right order defined by the iterator.
     * @expectedResults The returned array must contains the list elements placed in the order defined by the iterator.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The list instance must be a new istance of Collection.
     * @postConditions The list instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testToArray_notEmpty() {    
        instance.add("pippo");
        instance.add("pluto");
        
        Object[] result = instance.toArray();
        HIterator it = instance.iterator();
        boolean check = true;
        for(int i=0; it.hasNext() && i<result.length; i++) {
            check = check && it.next().equals(result[i]);
        }
        assertEquals("gli elementi sono copiati nell'array nell'ordine definito dall'iteratore", true, check);
    }
    
    /**
     * @title Test #1 of parametric toArray method, of class List.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty list passing an array which length is greater than list size. More in detail, it tests that the elements are returned in the right order.
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, but the first size() must be modified, as they should contains the elements of this list in the right order. 
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should not be directly modified by the call of the method tested.
     */
    @Test
    public void testParametricToArray_notEmpty() {
        String[] param = new String[10];
        for(int i=0; i<10; i++) param[i] = "pippo";
        instance.add("pluto");
        instance.add("paperino");
        instance.add("topolino");
        
        Object[] result = instance.toArray(param);
        HIterator it = instance.iterator();
        boolean check = true;
        for(int i=0; it.hasNext(); i++) {
            check = check && it.next().equals(result[i]);
        }
        assertEquals("gli elementi della lista sono stati copiati nell'ordine definito dall'iteratore", true, check);
    }
    
    /**
     * @title Test #2 of parametric toArray method, of class List.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty collection using an array which is not big enough to contain the whole list. More in details, the it tests the order of the returned array.
     * @expectedResults The array returned should be a new istance of a generic Object array (the parameter should not be modified), and it should contains all the elements contained in the list in the proper order.
     * @actualResult As expected result.
     * @dependencies Depends on the correctess of method add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be directly modified by the call of this method.
     */
    @Test
    public void testParametricToArray_notBigEnough() {
        String[] param = new String[1];
        param[0] = "pippo";
        instance.add("pluto");
        instance.add("paperino");
        instance.add("topolino");
        
        Object[] result = instance.toArray(param);
        HIterator it = instance.iterator();
        boolean check = true;
        for(int i=0; it.hasNext(); i++) {
            check = check && it.next().equals(result[i]);
        }
        assertEquals("gli elementi della lista sono stati copiati nell'ordine definito dall'iteratore", true, check);
    }
    
}