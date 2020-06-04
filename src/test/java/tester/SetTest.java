package tester;

import adapters.Set;
import interfaces.HIterator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Test suite for Set class
 * @author Giacomo Camposampiero
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetTest {

    private Set instance;

    @Before
    public void executedBeforeEach() {
        instance = new Set();
    }

    public SetTest() {
    }

    /**
     * @title Test of size method, of class Set.
     */
    @Test
    public void testSize() {
        int result = instance.size();
        assertEquals("la dimensione di un Set appena creato deve essere 0", 0, result);
    }

    /**
     * @title Test of isEmpty method, of class Set.
     */
    @Test
    public void testIsEmpty() {
        boolean result = instance.isEmpty();
        assertEquals("un Set appena creato deve essere vuoto", true, result);
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
     * @title Test of add method, of class Set.
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
     * @title Test of remove method, of class Set.
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
     * @title Test of remove method, used after the invocation of the method add().
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
     * @title Test of contains method, of class Set. Depends on the correctness of the
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
     * @title Test of containsAll method, of class Set. 
     * Depends on the correctness of methods add() and remove()
     */
    @Test
    public void testContainsAll() {
        Set list = new Set();
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
     * @title Test of addAll method, of class Set. Depends on the correcteness of the
     * methods clear(), containAll(), add(), remove(), size(), isEmpty()
     */
    @Test
    public void testAddAll() {
        Set list = new Set();
        boolean result = instance.addAll(list) || !instance.isEmpty();
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
        //il caso in cui si aggiunge una collection con all'interno uno o più elementi null non può essere controllato, non dispongo di classi che accettano come elementi null
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * @title Test of retainAll method, of class Set. 
     * Depends on the correctness of methods add(), clear(), isEmpty(), remove(), size()
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
        //NullPointerException non viene lanciata se la collezione contiene un elemento null, non crea problemi
        //IllegalArgumentException non può essere lanciata per definizione, tutte le classi sono sottoclassi di Object
        //ClassCastException non può essere lanciata per definizione
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }

    /**
     * @title Test of removeAll method, of class Set.
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
        result = instance.removeAll(list) && instance.isEmpty();
        assertEquals("rimuove tutti gli elementi del set", true, result);
        instance.add("pluto");
        instance.add("pippo");
        result = instance.removeAll(list) && instance.size() == 1;
        assertEquals("rimuove una parte degli elementi della lista", true, result);

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
     * @title Test of clear method, of class Set. 
     * Depends on the correcteness of methot add()
     */
    @Test
    public void testClear() {
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di un insieme vuoto, controllo che la dimensione sia nulla", true, result);
        instance.add("pippo");
        instance.add("pluto");
        instance.clear();
        result = instance.isEmpty();
        assertEquals("pulizia di un insieme pieno, controllo che la dimensione sia nulla", true, result);
    }

    /**
     * @title Test of iterator method, of class Set. Depends on the correcteness of
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
     * @title Test of toArray method, of class Set.
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
     * @title Test of equals method, of class Set.
     * Depends on the correctness of method add()
     */
    @Test
    public void testEquals() {
        instance.add("pippo");
        Set instance2 = new Set();
        boolean result = instance.equals(instance2);
        assertEquals("confronto di due set diversi", false, result);
        instance2.add("pippo");
        result = instance.equals(instance2);
        assertEquals("confronto di due set uguali", true, result);
        result = instance2.equals(instance);
        assertEquals("il confronto deve essere simmetrico", true, result);
    }

    /**
     * @title Test of hashCode method, of class Set.
     * Depends on the correctness of method add()
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
        assertEquals("per definizione, l'hashcode di un set con più elementi deve essere uguale alla somma degli hash dei suoi elementi", elem.hashCode() + elem2.hashCode(), result);
    }

    /**
     * Test the coherence of methods hashCode() and equals().
     * Depends on the correctness of method add()
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
     * @title Test #1 of parametric toArray method, class Set.
     * @description This test tests the behaviour of the parametric toArray() when an empty array is used as parameter and the set is empty
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, not modified at all.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The set instance must be a new istance of Set.
     * @postConditions The set instance should be modified after the call of this method (more formally, initialInstance.equals(finalInstance) must be true).
     */
    @Test
    public void testParametricToArray_bothEmpty() {
        String[] param = new String[0];
        Object[] result;
        result = instance.toArray(param);
        assertArrayEquals("set e array vuoti, dovrebbe coincidere", param, result);
    }
    
    /**
     * @title Test #2 of parametric toArray method, class Set.
     * @description This test tests the behaviour of the parametric toArray() when a not-empty array is used as parameter and the set is empty. This method test also wheter the method modify the only the first size() cells of the param array.
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, but the first set.size() cells must contains the elements of the set.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The set instance must be a new istance of Set.
     * @postConditions The set instance should be modified after the call of this method (more formally, initialInstance.equals(finalInstance) must be true).
     */
    @Test
    public void testParametricToArray_setEmpty() {
        String[] param = new String[10];
        for(int i=0; i<10; i++) param[i] = "pippo";
        Object[] result;
        result = instance.toArray(param);
        assertArrayEquals("set vuoto e array riempito, dovrebbe coincidere", param, result);
    }
    
    /**
     * @title Test #3 of parametric toArray method, class Set.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty set passing a not-empty array which length is greater than set size.
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, but the first size() must be modified, as they should contains the elements of this set. The elements contained in the array in positions >= size() should not be modified.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method add().
     * @preConditions The set instance must be a new istance of Set.
     * @postConditions The set instance should be modified after the call of this method (more formally, initialInstance.equals(finalInstance) must be true).
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
        assertEquals("gli elementi del set sono stati copiati nelle prime size()-1 posizioni, le successive non sono state modificate", true, check);
    }
    
    /**
     * @title Test #4 of parametric toArray method, class Set.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty set using an array which is not big enough to contain the whole set.
     * @expectedResults The array returned should be a new istance of a generic Object array (the parameter should not be modified), and it should contains all the elements contained in the set.
     * @actualResult As expected result.
     * @dependencies Depends on the correctess of method add().
     * @preConditions The set instance must be a new istance of Set.
     * @postConditions The set instance should be modified after the call of this method (more formally, initialInstance.equals(finalInstance) must be true).
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
        assertEquals("gli elementi del set sono tutti presenti e le dimensioni del risultato e dell'istanza di set coincidono", true, check);
        assertArrayEquals("l'array parametro non è stato modificato", copyParam, param);
    }
    
    /**
     * @title Test #5 of parametric toArray method, class Set.
     * @description This test tests the behaviour of parametric toArray() method when a null reference is used as parameter.
     * @expectedResults A NullPointerException was thrown as result of the call.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The set instance must be a new istance of Set.
     * @postConditions The set instance should be modified after the call of this method (more formally, initialInstance.equals(finalInstance) must be true).
     */
    @Test
    public void testParametricToArray_exceptions() {
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class,
                () -> {
                    instance.toArray(null);
                });
    }
    
}