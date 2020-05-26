package adapter;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

/**
 *
 * @author como
 * @param <E>
 */
public class VectorList<E> implements List<E> {

    /**
     *
     * @return
     */
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * @param i
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(int i, Collection<? extends E> clctn) {
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
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param operator
     */
    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        List.super.replaceAll(operator); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param c
     */
    @Override
    public void sort(Comparator<? super E> c) {
        List.super.sort(c); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public E get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @param e
     * @return
     */
    @Override
    public E set(int i, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @param e
     */
    @Override
    public void add(int i, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public E remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public List<E> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @param <E>
     * @return
     */
    public static <E> List<E> of() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E... elements) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3, E e4) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
