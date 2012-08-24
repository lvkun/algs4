import java.util.Iterator;


/**
 * @author brlv
 * similar to a stack or queue, except that the item removed is 
 * chosen uniformly at random from items in the data structure.
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    /**
     * init size.
     */
    private static int initSize = 2;
    
    /**
     * queue elements.
     */
    private Item[] queue;
    
    /**
     * number of elements on queue.
     */
    private int size = 0;

    /**
     * construct an empty randomized queue.
     */
    public RandomizedQueue() {
        // cast needed since no generic array creation in Java
        queue = (Item[]) new Object[initSize];
    }
    
    /**
     * is the deque empty?
     * @return is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * return the number of items on the queue.
     * @return the number of items on the queue
     */
    public int size() {
        return size;
    }
    
    /**
     * add the item.
     * @param item the item should be added to the queue
     */
    public void enqueue(Item item) {
        
    }
    
    /**
     * @param capcity
     */
    private void resize(int capacity) {
        
    }
    
    /**
     * delete and return a random item.
     * @return a random item
     */
    public Item dequeue() {
        return null;
    }
    
    /**
     *  return (but do not delete) a random item.
     * @return a random item
     */
    public Item sample() {
        return null;
    }
    
    /*
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Item> iterator() {
        
        return null;
    }
    
}