package adapters;

import interfaces.HCollection;
import interfaces.HIterator;

/**
 * Implementation of Collection interface Java 1.4.2.
 *
 * @author Giacomo Camposampiero
 */
public class Collection extends List implements HCollection {

    @Override
    public boolean add(Object o) {
        return super.add(o);
    }

    @Override
    public boolean addAll(HCollection c) {
        return super.addAll(c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean containsAll(HCollection c) {
        return super.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public HIterator iterator() {
        return super.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean removeAll(HCollection c) {
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(HCollection c) {
        return super.retainAll(c);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return super.toArray(a);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o.getClass().equals(this.getClass()))) {
            return false;
        }
        Collection other = (Collection) o;
        if (other.size() != size()) {
            return false;
        }
        HIterator it = iterator(), oit = other.iterator();
        while (it.hasNext() && oit.hasNext()) {
            if (!it.next().equals(oit.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        HIterator it = iterator();
        while(it.hasNext())
            hashCode += it.next().hashCode();
        return hashCode;
    }

}
