package tester;

import adapter.HashSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashSetTest {
        
    public HashSetTest() { }
    
    /**
     * Test of size method, of class HashSet.
     */
    @Test
    public void testSize() {
        HashSet<String> instance = new HashSet<>();
        int result = instance.size();
        assertEquals("la dimensione di un HashSet appena creato deve essere 0", 0, result);
    }
    
    /**
     * Test of isEmpty method, of class HashSet.
     */
    @Test
    public void testIsEmpty() {
        HashSet<String> instance = new HashSet<>();
        boolean result = instance.isEmpty();
        assertEquals("un HashSet appena creato deve essere vuoto", false, result);
    }
    
    /**
     * Tests the coeherence between size() and isEmpty() methods.
     */
    @Test
    public void testIsEmptySize() {
        HashSet<String> instance = new HashSet<>();
        boolean size = (instance.size() == 0);
        boolean isEmpty = instance.isEmpty();
        assertEquals("i metodi size() e isEmpty() devono essere coerenti", size, isEmpty);
    }
    
    /**
     * Test of add method, of class HashSet.
     */
    @Test
    public void testAdd() {
        HashSet<String> instance = new HashSet<>();
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
        assertThrows("si fornisce come parametro un oggetto che genera un errore di cast", ClassCastException.class, 
                () -> {
                    String param = (String) new Object();
                    instance.add(param);
                }); 
        assertThrows("si fornisce come parametro un oggetto del tipo sbagliato", IllegalArgumentException.class, 
                () -> {
                    //check
                    String param = (String) new Object();
                    instance.add(param);
                }); 
        //UnsupportedOperationException non controllata testata, il metodo deve essere per forza implementarto da consegna
    }
    
    /**
     * Test of remove method, of class HashSet.
     */
    @Test
    public void testRemove() {
        HashSet<String> instance = new HashSet<>();
        boolean result = instance.remove("pippo");
        assertEquals("rimozione di un elemento in una collezione vuota", false, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class, 
                () -> {
                    instance.remove(null);
                });
        assertThrows("si fornisce come parametro un oggetto che genera un errore di cast", ClassCastException.class, 
                () -> {
                    String param = (String) new Object();
                    instance.remove(param);
                });
    }
    
    /**
     * Test of remove method, used after the invocation of the method add().
     * Depends on the correctness of the method add()
     */
    @Test
    public void testRemoveAdd() {
        HashSet<String> instance = new HashSet<>();
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
        HashSet<String> instance = new HashSet<>();
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
     * Test of contains method, of class HashSet.
     * Depends on the correctness of the methods add() and remove()
     */
    @Test
    public void testContains() {
        HashSet<String> instance = new HashSet<>();
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
        assertThrows("si fornisce come parametro un oggetto che genera un errore di cast", ClassCastException.class, 
                () -> {
                    String param = (String) new Object();
                    instance.contains(param);
                });
    }
    
    /**
     * Test of containsAll method, of class HashSet.
     * Depends on the correctness of the methods add() and remove()
     */
    @Test
    public void testContainsAll() {
        HashSet<String> instance = new HashSet<>();
        HashSet<String> list = new HashSet<>();
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
        assertThrows("si fornisce come parametro un oggetto che genera un errore di cast", ClassCastException.class, 
                () -> {
                    Collection<String> param = (Collection<String>) new Object();
                    instance.containsAll(param);
                });
    }

    /**
     * Test of addAll method, of class HashSet.
     * Depends on the correcteness of the methods clear(), containAll(), add(), remove(), size(), isEmpty()
     */
    @Test
    public void testAddAll() {
        HashSet<String> list = new HashSet<>();
        HashSet<String> instance = new HashSet<>();
        boolean result = instance.addAll(list) && instance.isEmpty();
        assertEquals("aggiunta una collezione vuota, che non modifica lo stato del set", false, result);
        instance.add("pippo");
        list.add("pippo");
        result = instance.addAll(list) && instance.size()==1;
        assertEquals("aggiunta di una collezione con elementi già presenti", false, result);
        list.add("pluto");
        result = instance.addAll(list) && instance.size()==2;
        assertEquals("aggiunta di una collezione con nuovi elementi e elementi già presenti", true, result);
        list.clear();
        list.add("topolino");
        result = instance.addAll(list) && instance.size()==3;
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
        assertThrows("si fornisce come parametro un oggetto che genera un errore di cast", ClassCastException.class, 
                () -> {
                    Collection<String> param = (Collection<String>) new Object();
                    instance.containsAll(param);
                });  
        assertThrows("si fornisce come parametro non valido", IllegalArgumentException.class, 
                () -> {
                    Collection<String> param = (Collection<String>) new Object();
                    instance.containsAll(param);
                    //TODO
                });    
    }

    /**
     * Test of retainAll method, of class HashSet.
     * Depends on the correctness of methods add(), clear(), isEmpty(), remove(), size()
     */
    @Test
    public void testRetainAll() {
        HashSet<String> instance = new HashSet<>();
        HashSet<String> list = new HashSet<>();
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
        result = instance.retainAll(list) && instance.size()==1;
        assertEquals("trattiene tutti gli elementi della lista", false, result);
        instance.add("pluto");
        result = instance.retainAll(list) && instance.size()==1;
        assertEquals("trattiene una parte degli elementi della lista", true, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class, 
                () -> {
                    instance.containsAll(null);
                });
        assertThrows("si fornisce come parametro un oggetto che genera un errore di cast", ClassCastException.class, 
                () -> {
                    Collection<String> param = (Collection<String>) new Object();
                    instance.containsAll(param);
                });  
    }

    /**
     * Test of removeAll method, of class HashSet.
     */
    @Test
    public void testRemoveAll() {
        HashSet<String> instance = new HashSet<>();
        HashSet<String> list = new HashSet<>();
        list.add("pippo");
        boolean result = instance.removeAll(list);
        assertEquals("metodo invocato su lista vuota", false, result);
        list.clear();
        result = instance.removeAll(list);
        assertEquals("metodo invocato su lista vuota con parametro vuoto", false, result);
        instance.add("pippo");
        result = instance.removeAll(list) && instance.size()==1;
        assertEquals("non rimuove alcun elemento del set", false, result);
        list.add("pippo");
        result = instance.retainAll(list) && instance.isEmpty();
        assertEquals("rimuove tutti gli elementi del set", true, result);
        instance.add("pluto");
        instance.add("pippo");
        result = instance.retainAll(list) && instance.size()==1;
        assertEquals("rimuove una parte degli elementi della lista", true, result);
        
        //controllo eccezioni
        assertThrows("si usa come parametro un riferimento a null", NullPointerException.class, 
                () -> {
                    instance.containsAll(null);
                });
        assertThrows("si fornisce come parametro un oggetto che genera un errore di cast", ClassCastException.class, 
                () -> {
                    Collection<String> param = (Collection<String>) new Object();
                    instance.containsAll(param);
                });     
    }

    /**
     * Test of clear method, of class HashSet.
     * Depends on the correcteness of method add()
     */
    @Test
    public void testClear() {
        HashSet<String> instance = new HashSet<>();
        instance.clear();
        boolean result = instance.isEmpty();
        assertEquals("pulizia di una lista vuota, controllo che la dimensione sia nulla", true, result);
        instance.add("pippo");
        instance.add("pluto");
        result = instance.isEmpty();
        assertEquals("pulizia di una lista piena, controllo che la dimensione sia nulla", true, result);
    }
    
    /**
     * Test of iterator method, of class HashSet.
     * Depends on the correcteness of method add()
     */
    @Test
    public void testIterator() {
        HashSet<String> instance = new HashSet<>();
        Iterator<String> it = instance.iterator();
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
        while(it.hasNext()) {
            result = result && instance.contains(it.next());
            i++;
        }
        result = result && instance.size()==i;
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
                    Iterator<String> iter = instance.iterator();
                    iter.next();
                    iter.remove();
                    iter.remove();
                });  
    }

    /**
     * Test of toArray method, of class HashSet.
     */
    @Test
    public void testToArray_0args() {
        HashSet instance = new HashSet();
        Object[] expResult = null;
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class HashSet.
     */
    @Test
    public void testToArray_GenericType() {
        Object[] ts = null;
        HashSet instance = new HashSet();
        Object[] expResult = null;
        Object[] result = instance.toArray(ts);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of equals method, of class HashSet.
     */
    @Test
    public void testEquals() {
        
    }
    
    /**
     * Test of hashCode method, of class HashSet.
     */
    @Test
    public void testHashCode() {
        
    }
    
}