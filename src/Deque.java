import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author brlv
 *
 * generalization of a stack and a queue that
 * supports inserting and removing items from
 * either the front or the back of the data structure
 *
 * @param <Item> item type
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * @author brlv
     * helper linked list class.
     */
    private class Node {
        /**
         * Item.
         */
        private Item item;
        /**
         * pointer to previous node.
         */
        private Node prev;
        /**
         * pointer to next node.
         */
        private Node next;
    }

    /**
     * size of deque.
     */
    private int size;

    /**
     * First node.
     */
    private Node first;

    /**
     * Last node.
     */
    private Node last;

    /**
     * construct an empty deque.
     */
     public Deque() {
         first = null;
         last = null;
         size = 0;
     }

    /**
     * @author brlv
     * an iterator, doesn't implement remove() since it's optional.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * current node.
         */
        private Node current = first;

        /*
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /*
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /*
         * @see java.util.Iterator#next()
         */
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /*
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    /**
     * is the deque empty?
     * @return is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the deque.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * insert the item at the front.
     * @param item the item should be insert to the front
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node oldfirst = first;

        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldfirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.prev = first;
        }

        size++;
    }

    /**
     * insert the item at the end.
     * @param item the item should be insert to the end
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldlast = last;

        last = new Node();
        last.item = item;
        last.prev = oldlast;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }

        size++;
    }

    /**
     * delete and return the item at the front.
     * @return the item at the front
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Item item = first.item;

        first = first.next;
        if (first != null) { // to avoid loitering
            first.prev = null;
        }
        size--;
        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    /**
     * delete and return the item at the end.
     * @return item at the end
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Item item = last.item;

        last = last.prev;
        if (last != null) { // to avoid loitering
            last.next = null;
        }
        size--;
        if (isEmpty()) {
            first = null;
        }

        return item;
    }
}
