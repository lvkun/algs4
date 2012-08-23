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
    public final Iterator<Item> iterator()  {
        return new ListIterator();
    }

    /**
    * construct an empty deque.
    */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * is the deque empty?
     * @return is empty or not
     */
    public final boolean isEmpty() {
        return size == 0;
    }

    /**
     * return size.
     * @return size
     */
    public final int size() {
        return size;
    }

    /**
     * add item to the front of deque.
     * @param item the item should be insert to the front
     */
    public final void addFirst(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        first.prev = oldfirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.prev = first;
        }

        size++;
    };
}
