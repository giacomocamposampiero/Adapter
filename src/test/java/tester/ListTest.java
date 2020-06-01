package tester;

import adapters.List;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;
import java.util.NoSuchElementException;
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
        result = instance.add("pluto") && instance.get(2).equals("pluto");
        assertEquals("inserisco una seconda stringa, verifico che sia inserita alla fine della lista", true, result);

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
        assertEquals("controllo che gli elementi della collezione sianio aggiunti nell'ordine in cui sono restituiti dall'iteratore", true, result);
        
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
        list.add("topolino");
        list.add("paperino");
        result = instance.addAll(1, list) && (instance.size()==3);
        HIterator itInst = instance.iterator();
        HIterator itList = list.iterator();
        int i = 0;
        while(itInst.hasNext()) {
            Object o = itInst.next();
            if(i>0 && itList.hasNext()) {
                result = result && o.equals(itList.next());
            }
            i++;
        }
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
     * Tests the behaviour of a sequence of add() and remove() invocations.
     * Verifies also the coherence of the methods size() & isEmpty()
     */
    @Test
    public void testAddRemoveSizeEmpty() {
        instance.add("pippo");
        instance.add("pluto");
        assertEquals("controllo validità metodo size()", 2, instance.size());
        assertEquals("controllo validità metodo isEmpty()", false, instance.isEmpty());
        instance.remove("pippo");
        instance.remove("pluto");
        assertEquals("controllo validità metodo size()", 0, instance.size());
        assertEquals("controllo validità metodo isEmpty()", true, instance.isEmpty());
    }
    
    /**
     * Test of clear method, of class List.
     * Depends also on the correctness of methods add(), clear() and isEmpty()
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
     * Test the coherence of methods hashCode() and equals(). Depends also on the
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
        
        //TODOS remove
        fail("The test case is a prototype.");
        
        //controllo eccezioni
        assertThrows("l'iteratore non ha un elemento successivo", NoSuchElementException.class,
                () -> {
                    instance.clear();
                    instance.iterator().next();
                });
        assertThrows("remove invocato prima di next", IllegalStateException.class,
                () -> {
                    instance.clear();
                    instance.add("pippo");
                    instance.iterator().remove();
                });
        assertThrows("remove invocato due volte sullo stesso elemento", IllegalStateException.class,
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
     * Test of remove method, of class List.
     * Depends also on add(), clear(), get() methods
     */
    @Test
    public void testRemove_Object() {
        boolean result = instance.remove("pippo");
        assertEquals("rimozione di un elemento in una collezione vuota", false, result);
        String elem = "pippo";
        instance.add(elem);
        result = instance.remove(elem);
        assertEquals("rimozione di un elemento presente", true, result);
        result = instance.remove(elem);
        assertEquals("rimozione di un elemento eliminato in precedenza", false, result);
        instance.add(elem);
        result = instance.remove("pippo");
        assertEquals("verifica dell'utilizzo del metodo equals() sull'oggetto passato come parametro", true, result);
        instance.clear();
        instance.add("pippo");
        instance.add("pluto");
        instance.add("pippo");
        result = instance.remove("pippo") && instance.get(0).equals("pluto") && instance.get(1).equals("pippo");
        assertEquals("verifica che, in caso di duplicati, si rimuova l'istanza con indice minore", true, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.remove(null);
                });
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class List.
     * Depends also on the correctness of methods add(), contains() and iterator()
     */
    @Test
    public void testToArray_0args() {
        Object[] expected = new Object[0];
        Object[] result = instance.toArray();
        assertArrayEquals("toArray invocato su un set vuoto deve ritornare un array vuoto", expected, result);
        instance.add("pippo");
        instance.add("pluto");
        result = instance.toArray();
        HIterator it = instance.iterator();
        boolean check = (result.length == instance.size());
        for(int i=0; i<result.length; i++) {
            check = check && instance.contains(result[i]) && it.next().equals(result[i]);
        }   
        assertEquals("toArray invocato su un set pieno, controllo anche che gli elementi siano presenti nell'array nello stesso ordine nel quale sono restituiti dall'iteratore", true, check);        
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