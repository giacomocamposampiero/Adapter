package tester;

import adapters.List;
import adapters.Set;
import interfaces.HCollection;
import interfaces.HIterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SetTest {

    private Set instance;

    @Before
    public void executedBeforeEach() {
        instance = new Set();
    }

    public SetTest() {
    }

    /**
     * Test of size method, of class Set.
     */
    @Test
    public void testSize() {
        int result = instance.size();
        assertEquals("la dimensione di un Set appena creato deve essere 0", 0, result);
    }

    /**
     * Test of isEmpty method, of class Set.
     */
    @Test
    public void testIsEmpty() {
        boolean result = instance.isEmpty();
        assertEquals("un Set appena creato deve essere vuoto", false, result);
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
     * Test of add method, of class Set.
     */
    @Test
    public void testAdd() {
        String elem = "pippo";
        boolean result = instance.add(elem);
        assertEquals("primo elemento che inserisco", true, result);
        result = instance.add(elem);
        assertEquals("inserisco per la seconda volta lo stesso elemento, dovrebbe non inserire", false, result);
        result = instance.add("pippo");
        assertEquals("verifica dell'effettivo utilizzo del metodo equals() per il confronto tra parametro e oggetti contenuti", false, result);
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
     * Test of remove method, of class Set.
     */
    @Test
    public void testRemove() {
        boolean result = instance.remove("pippo");
        assertEquals("rimozione di un elemento in una collezione vuota", false, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.remove(null);
                });
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of remove method, used after the invocation of the method add().
     * Depends on the correctness of the method add()
     */
    @Test
    public void testRemoveAdd() {
        String elem = "pippo";
        instance.add(elem);
        boolean result = instance.remove(elem);
        assertEquals("rimozione di un elemento presente", true, result);
        result = instance.remove(elem);
        assertEquals("rimozione di un elemento eliminato in precedenza", false, result);
        instance.add(elem);
        result = instance.remove("pippo");
        assertEquals("verifica dell'utilizzo del metodo equals() sull'oggetto passato come parametro", true, result);
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
     * Test of contains method, of class Set. Depends on the correctness of the
     * methods add() and remove()
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
     * Test of containsAll method, of class Set. Depends on the correctness of
     * the methods add() and remove()
     */
    @Test
    public void testContainsAll() {
        Set list = new Set();
        boolean result = instance.containsAll(list);
        assertEquals("metodo invocato su una collezione vuota", false, result);
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
        assertThrows("si usa come parametro una collezione che contiene un riferimento a null", NullPointerException.class,
                () -> {
                    List param = new List();
                    list.add("pippo");
                    list.add("pluto");
                    list.add(null);
                    instance.containsAll(param);
                });
        //ClassCastException non può essere lanciata per definizione
    }

    /**
     * Test of addAll method, of class Set. Depends on the correcteness of the
     * methods clear(), containAll(), add(), remove(), size(), isEmpty()
     */
    @Test
    public void testAddAll() {
        Set list = new Set();
        boolean result = instance.addAll(list) && instance.isEmpty();
        assertEquals("aggiunta una collezione vuota, che non modifica lo stato del set", false, result);
        instance.add("pippo");
        list.add("pippo");
        result = instance.addAll(list) && instance.size() == 1;
        assertEquals("aggiunta di una collezione con elementi già presenti", false, result);
        list.add("pluto");
        result = instance.addAll(list) && instance.size() == 2;
        assertEquals("aggiunta di una collezione con nuovi elementi e elementi già presenti", true, result);
        list.clear();
        list.add("topolino");
        result = instance.addAll(list) && instance.size() == 3;
        assertEquals("aggiunta di una collezione con soli nuovi elementi", true, result);
        list.add("pippo");
        list.add("pluto");
        result = instance.containsAll(list);
        assertEquals("controllo che siano stati effettivamente inseriti gli elementi", true, result);

        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsAll(null);
                });
        assertThrows("si usa come parametro una collezione che contiene un riferimento a null", NullPointerException.class,
                () -> {
                    List param = new List();
                    list.add("pippo");
                    list.add("pluto");
                    list.add(null);
                    instance.addAll(param);
                });
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of retainAll method, of class Set. Depends on the correctness of
     * methods add(), clear(), isEmpty(), remove(), size()
     */
    @Test
    public void testRetainAll() {
        Set list = new Set();
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
        assertThrows("si usa come parametro una collezione che contiene un riferimento a null", NullPointerException.class,
                () -> {
                    List param = new List();
                    list.add("pippo");
                    list.add("pluto");
                    list.add(null);
                    instance.retainAll(param);
                });
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of removeAll method, of class Set.
     */
    @Test
    public void testRemoveAll() {
        Set list = new Set();
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
        result = instance.retainAll(list) && instance.isEmpty();
        assertEquals("rimuove tutti gli elementi del set", true, result);
        instance.add("pluto");
        instance.add("pippo");
        result = instance.retainAll(list) && instance.size() == 1;
        assertEquals("rimuove una parte degli elementi della lista", true, result);

       //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.containsAll(null);
                });
        assertThrows("si usa come parametro una collezione che contiene un riferimento a null", NullPointerException.class,
                () -> {
                    List param = new List();
                    list.add("pippo");
                    list.add("pluto");
                    list.add(null);
                    instance.addAll(param);
                });
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * Test of clear method, of class Set. Depends on the correcteness of method
     * add()
     */
    @Test
    public void testClear() {
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una lista vuota, controllo che la dimensione sia nulla", true, result);
        instance.add("pippo");
        instance.add("pluto");
        result = instance.isEmpty();
        assertEquals("pulizia di una lista piena, controllo che la dimensione sia nulla", true, result);
    }

    /**
     * Test of iterator method, of class Set. Depends on the correcteness of
     * method add()
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
        assertEquals("l'iteratore contiene tutti e solo gli oggetti contenuti nella lista", false, result);

        //controllo eccezioni
        assertThrows("l'iteratore non ha un elemento successivo", NoSuchElementException.class,
                () -> {
                    instance.clear();
                    instance.iterator().next();
                });
        assertThrows("remove invocato prima di next", ClassCastException.class,
                () -> {
                    instance.clear();
                    instance.add("pippo");
                    instance.iterator().remove();
                });
        assertThrows("remove invocato due volte sullo stesso elemento", ClassCastException.class,
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
     * Test of toArray method, of class Set.
     */
    @Test
    public void testToArray_0args() {
        Object[] expected = new Object[0];
        Object[] result = instance.toArray();
        assertArrayEquals("toArray invocato su un set vuoto deve ritornare un array vuoto", expected, result);
        instance.add("pippo");
        instance.add("pluto");
        result = instance.toArray();
        expected = new Object[]{"pippo", "pluto"};
        assertArrayEquals("toArrray invocato su un set pieno", expected, result);        
    }

    /**
     * Test of equals method, of class Set.
     */
    @Test
    public void testEquals() {
        instance.add("pippo");
        Set instance2 = new Set();
        boolean result = instance.equals(instance2);
        assertEquals("confronto di due set diversi", false, result);
        instance2.add("pippo");
        result = instance.equals(instance2);
        assertEquals("confronto di due set diversi", true, result);
    }

    /**
     * Test of hashCode method, of class Set.
     */
    @Test
    public void testHashCode() {
        int result = instance.hashCode();
        assertEquals("per definizione, l'hashcode di un set vuoto deve essere 0", 0, result);
        String elem = "pippo";
        instance.add(elem);
        result = instance.hashCode();
        assertEquals("per definizione, l'hashcode di un set con un elemento deve essere uguale all'hashcode dell'elemento", elem.hashCode(), result);
        String elem2 = "pluto";
        instance.add(elem2);
        result = instance.hashCode();
        assertEquals("per definizione, l'hashcode di un set con un elemento deve essere uguale alla somma degli hash dei suoi elementi", elem.hashCode() + elem2.hashCode(), result);
    }

    /**
     * Test the coherence of methods hashCode() and equals().
     */
    @Test
    public void testHashEquals() {
        instance.add("pippo");
        int hash = instance.hashCode();
        Set instance2 = new Set();
        instance2.add("pippo");
        boolean result = (hash == instance2.hashCode()) && instance.equals(instance2);
        assertEquals("confronto di due set diversi", true, result);
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