import java.util.Iterator;
import java.util.NoSuchElementException;


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
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        
        if (size == queue.length) {
            resize(2*queue.length);
        }
        
        queue[size++] = item;
    }
    
    /**
     * @param capcity
     */
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        
        queue = temp;
    }
    
    /**
     * delete and return a random item.
     * @return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        Item item = queue[randomIndex];
        
        // move the last item to fill the gap
        if (randomIndex != size - 1) {
            queue[randomIndex] = queue[size - 1];
        }
        // set the last item to null
        queue[size - 1] = null;
        size--;
        
        if (size < queue.length/4) {
            resize(queue.length/2);
        }
        return item;
    }
    
    /**
     *  return (but do not delete) a random item.
     * @return a random item
     */
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        return queue[randomIndex];
    }
    
    /**
     * @author brlv
     * an iterator, doesn't implement remove() since it's optional
     */
    private class ArrayIterator implements Iterator<Item> {
        /**
         * index.
         */
        private int index = 0;
        /**
         * random index array.
         */
        private int[] random;
        
        /**
         * Create a random index array.
         */
        public ArrayIterator() {
            random = new int[size];
            for (int i = 0; i < random.length; i++) {
                random[i] = i;
            }
            StdRandom.shuffle(random);
        }
        
        /*
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            return index < random.length;
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
            int randomIndex = random[index];
            index++;
            return queue[randomIndex];
        }
    }
    
    /*
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Item> iterator() {
        
        return new ArrayIterator();
    }
    
}