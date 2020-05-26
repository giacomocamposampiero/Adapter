package adapter;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

/**
 *
 * @author como
 * @param <E>
 */
public class HashSet<E> implements Set<E> {
    
    private final Hashtable<Integer,E> struct;

    /**
     *
     */
    public HashSet() {
        this.struct = new Hashtable<>();
    }
    
    /**
     *
     * @return
     */
    @Override
    public int size() {
        return struct.size();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param <T>
     * @param ts
     * @return
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends E> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    //*************************    
    //MANCANO EQUALS E HASHCODE
    //*************************
    
    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @param <E>
     * @return  nothing, always throws UnsupportedOperationException
     */
    public static <E> Set<E> of() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E... elements) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3, E e4) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Unsupported method
     * @return  nothing, always throws UnsupportedOperationException
     */
    static <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}