package tester;

import adapters.List;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;
import java.util.Arrays;
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
     * Test of addAll method, of class List.
     * Depends also on the correctness of methods add(), size(), clear() and get()
     */
    @Test
    public void testAddAll_HCollection() {
        List list = new List();
        boolean result = instance.addAll(list) || !instance.isEmpty();
        assertEquals("aggiunta una collezione vuota, che non modifica lo stato della lista", false, result);
        instance.add("pippo");
        list.add("pippo");
        result = instance.addAll(list) && instance.size() == 2;
        assertEquals("aggiunta di una collezione con elementi già presenti", true, result);
        list.add("pluto");
        result = instance.addAll(list) && instance.size() == 4;
        assertEquals("aggiunta di una collezione con nuovi elementi e elementi già presenti", true, result);
        list.clear();
        list.add("topolino");
        result = instance.addAll(list) && instance.size() == 5;
        assertEquals("aggiunta di una collezione con soli nuovi elementi", true, result);
        list.add("pippo");
        list.add("pluto");
        result = instance.addAll(list) && (instance.size()==8);
        HIterator itInst = instance.iterator();
        HIterator itList = list.iterator();
        int i = 0;
        while(itInst.hasNext()) {
            Object o = itInst.next();
            if(i>4 && itList.hasNext()) {
                result = result && o.equals(itList.next());
            }
            i++;
        }
        assertEquals("controllo che gli elementi della collezione siano aggiunti nell'ordine in cui sono restituiti dall'iteratore", true, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.addAll(null);
                });
        //il caso in cui si aggiunge una collection con all'interno uno o più elementi null non può essere controllato, non dispongo di classi che accettano come elementi null
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
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
     * Test of contains method, of class List.
     * Depends also on the correctness of methods add() and remove()
     */
    @Test
    public void testContains() {
        String elem = "pippo";
        boolean result = instance.contains(elem);
        assertEquals("metodo invocato su una collezione vuota", false, result);
        instance.add(elem);
        result = instance.contains(elem);
        assertEquals("metodo invocato con un parametro valido", true, result);
        result = instance.contains("pippo");
        assertEquals("verifica che il metodo sia basato su equals()", true, result);
        instance.remove(elem);
        result = instance.contains(elem);
        assertEquals("metodo invocato con un parametro che è stato in precedenza cancellato", false, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.contains(null);
                });
        //ClassCastException non può essere controllata per definizione
    }

    /**
     * Test of containsAll method, of class List.
     */
    @Test
    public void testContainsAll() {
        List list = new List();
        boolean result = instance.containsAll(list);
        assertEquals("metodo invocato su una collezione vuota", true, result);
        String elem1 = "pippo", elem2 = "pluto", elem3 = "topolino";
        list.add(elem1);
        list.add(elem2);
        list.add(elem3);
        instance.add("pippo");
        instance.add("pluto");
        instance.add("topolino");
        result = instance.containsAll(list);
        assertEquals("metodo invocato su una collezione valida, verifica anche che il confronto tra elementi sia eseguito mediante il metodo equals()", true, result);
        instance.remove("topolino");
        result = instance.containsAll(list);
        assertEquals("metodo invocato su una collezione valida con parametri non tutti contenuti", false, result);
        list.remove("topolino");
        list.remove("pippo");
        result = instance.containsAll(list);
        assertEquals("metodo invocato per verificare la presenza di un sottoinsieme degli elementi", true, result);
        list.remove("pluto");
        result = instance.containsAll(list);
        assertEquals("metodo invocato con una collezione vuota come parametro", true, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsAll(null);
                });
        //il caso in cui si aggiunge una collection con all'interno uno o più elementi null non può essere controllato, non dispongo di classi che accettano come elementi null
        //ClassCastException non può essere lanciata per definizione
    }
    
    /**
     * Test of equals method, of class Set.
     * Depends also on the correctness of method add()
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
     * Test of hashCode method, of class Set. Depends also on the correctness of
     * method add()
     */
    @Test
    public void testHashCode() {
        int result = instance.hashCode();
        int expected = 1;
        assertEquals("per definizione, l'hashcode di una list vuota deve essere 1", expected, result);
        String elem = "pippo";
        instance.add(elem);
        result = instance.hashCode();
        expected = expected*31 + elem.hashCode();
        assertEquals("controllo la correttezza dell'hashcode di una lista con un solo elemento sulla base dell'algoritmo dato", expected, result);
        String elem2 = "pluto";
        instance.add(elem2);
        result = instance.hashCode();
        expected = expected*31 + elem2.hashCode();
        assertEquals("controllo la correttezza dell'hashcode di una lista con più elementi", expected, result);
    
    }

    /**
     * Test the coherence of methods hashCode() and equals(). Depends also on the
     * correctness of method add()
     */
    @Test
    public void testHashEquals() {
        List other = new List();
        other.add("pippo");
        instance.add("pippo");
        boolean result = (instance.hashCode() == other.hashCode()) ^ instance.equals(other);
        instance.add("pluto");
        result = result || (instance.hashCode() == other.hashCode()) ^ instance.equals(other);
        assertEquals("l'hashcode è coerente con il metodo equals", false, result);
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
     * Test of iterator method, of class List.
     */
    @Test
    public void testIterator() {
        HIterator it = instance.iterator();
        boolean result = it.hasNext();
        assertEquals("iteratore di una collezione vuota non deve avere un next", false, result);
        instance.add("pippo");
        it = instance.iterator();
        result = it.hasNext();
        assertEquals("iteratore di una collezione piena deve avere un next", true, result);
        result = it.next().equals("pippo");
        assertEquals("l'oggetto restituito dall'iteratore corrisponde a quello nella lista", true, result);
        result = it.hasNext();
        assertEquals("iteratore al termine della collezione, non deve avere next", false, result);
        instance.add("pippo");
        instance.add("pluto");
        instance.add("topolino");
        it = instance.iterator();
        result = true;
        int i = 0;
        while (it.hasNext()) {
            result = result && instance.contains(it.next());
            i++;
        }
        result = result && instance.size() == i;
        assertEquals("l'iteratore contiene tutti e solo gli oggetti contenuti nella lista", true, result);
        
        instance.clear();
        instance.add("pippo");
        instance.add("pluto");
        it = instance.iterator();
        Object o = it.next();
        it.remove();
        result = (instance.size() == 1) && !instance.contains(o);
        assertEquals("il metodo remove rimuove correttamente l'oggetto appena restituito dal next", true, result);
                
        //controllo eccezioni
        assertThrows("l'iteratore non ha un elemento successivo", NoSuchElementException.class,
                () -> {
                    instance.clear();
                    instance.iterator().next();
                });
        assertThrows("remove invocato prima di next", exceptions.IllegalStateException.class,
                () -> {
                    instance.clear();
                    instance.add("pippo");
                    instance.iterator().remove();
                });
        assertThrows("remove invocato due volte sullo stesso elemento", exceptions.IllegalStateException.class,
                () -> {
                    instance.clear();
                    instance.add("pippo");
                    HIterator iter = instance.iterator();
                    iter.next();
                    iter.remove();
                    iter.remove();
                });
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
     * @description This test tests the behaviour of remove() method when called on a not-empty collection and the specified element is contained. More in details, it tests wheter the removed element is that one with the lowest index or not.
     * @expectedResults The element remove is the one which has the lowest index between the equals object of the list.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods add(), isEmpty() and get().
     * @preConditions The collection instance must be a new istance of Collection.
     * @postConditions The collection instance should be modified directly by the execution of the method.
     */
    @Test
    public void testRemove_Object() {
        instance.add("pippo");
        instance.add("pluto");
        instance.add("pippo");
        boolean result = instance.remove("pippo") && instance.size()==2;
        String[] expected = {"pluto", "pippo"};
        for(int i=0; i<2; i++) result &= instance.get(i).equals(expected[i]);
        assertEquals("verifica che, in caso di duplicati, si rimuova l'istanza con indice minore", true, result);
    }

    /**
     * Test of removeAll method, of class List.
     * Depends also on the correctness of methods add(), size() and isEmpty()
     */
    @Test
    public void testRemoveAll() {
        List list = new List();
        list.add("pippo");
        boolean result = instance.removeAll(list);
        assertEquals("metodo invocato su lista vuota", false, result);
        list.clear();
        result = instance.removeAll(list);
        assertEquals("metodo invocato su lista vuota con parametro vuoto", false, result);
        instance.add("pippo");
        result = instance.removeAll(list) && instance.size() == 1;
        assertEquals("non rimuove alcun elemento del set", false, result);
        list.add("pippo");
        result = instance.removeAll(list) && instance.isEmpty();
        assertEquals("rimuove tutti gli elementi del set", true, result);
        instance.add("pluto");
        instance.add("pippo");
        result = instance.removeAll(list) && instance.size() == 1;
        assertEquals("rimuove una parte degli elementi della lista", true, result);

       //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.removeAll(null);
                });
        //il caso in cui si aggiunge una collection con all'interno uno o più elementi null non può essere controllato, non dispongo di classi che accettano come elementi null
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of retainAll method, of class List.
     * Depends on the correctness of methods add(), clear(), isEmpty(), remove(), size()
     */
    @Test
    public void testRetainAll() {
        List list = new List();
        list.add("pippo");
        boolean result = instance.retainAll(list);
        assertEquals("metodo invocato su lista vuota", false, result);
        list.clear();
        result = instance.retainAll(list);
        assertEquals("metodo invocato su lista vuota con parametro vuoto", false, result);
        instance.add("pippo");
        result = instance.retainAll(list) && instance.isEmpty();
        assertEquals("non trattiene nessun elemento", true, result);
        instance.add("pippo");
        list.add("pippo");
        result = instance.retainAll(list) && instance.size() == 1;
        assertEquals("trattiene tutti gli elementi della lista", false, result);
        instance.add("pluto");
        result = instance.retainAll(list) && instance.size() == 1;
        assertEquals("trattiene una parte degli elementi della lista", true, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.retainAll(null);
                });
        //NullPointerException non viene lanciata se la collezione contiene un elemento null, non crea problemi
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
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