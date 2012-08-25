
public class Subset {
    
    public static void main(final String[] args) {
        if (args.length != 1) {
            throw new java.lang.IllegalArgumentException();
        }
        
        int k = Integer.parseInt(args[0]);
        
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            queue.enqueue(str);
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
