package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using a sorted array for storage.
 *
 * Because Java does not allow generic arrays (!), this is implemented as an
 * array of Object rather than of PriorityItem&lt;T&gt;, which would be natural.
 * Array elements accessed then have to be cast to PriorityItem&lt;T&gt; before
 * using their getItem() or getPriority() methods.
 * 
 * This is an example of Java's poor implementation getting in the way. Java
 * fanboys will no doubt explain at length why it has to be this way, but note
 * that Eiffel allows it because Eiffel generics were done right from the start,
 * rather than being tacked on as an afterthought and limited by issues of
 * backward compatibility. Humph!
 * 
 * @param <T> The type of things being stored.
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T> {
    
    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    
    //private final Object[] heap;
    static int[] heap;
    //private int heapsize;
    static int heaplength;
    private int heapIndex;
    
    public HeapPriorityQueue(int size) {
        storage = new Object[size];
        heap = new int[size];
        capacity = size;
        tailIndex = -1;
        //heap = new Object[size];
        //heapsize = size;
        heaplength = -1;
    }
    
    node heaphead;
    
    class node {
        
        int val;
        node next;
        node heapnext;
        private T item;
        
        private node(T item, int priority) {
            this.item = item;
            this.val = priority;
        }
    }
    
    

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            int j = 0;
            while (j <= heaplength) {
                System.out.print(heap[j] + " ");
                j++;
            }
            return ((PriorityItem<T>) storage[0]).getItem();
        }
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        
        tailIndex = tailIndex + 1;
        heaplength = heaplength + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() < priority) {
                storage[i] = storage[i - 1];
                i = i - 1;
            }
            storage[i] = new PriorityItem<>(item, priority);
            
            
            int heapint = priority;
            heap[heaplength] = heapint;
            int parentIndex = (heapIndex - 1) / 2;
            while (heap[parentIndex] < heap[heapIndex] && heapIndex > 0) {
                int tempIndex = heap[parentIndex];
                heap[parentIndex] = heap[heapIndex];
                heap[heapIndex] = tempIndex;
                heapIndex = parentIndex;
            }
            
            
        }
    }
    
    static void nodeDown(int heapIndex) {
        int currentIndex = heapIndex;
            int leftside = (heapIndex * 2) + 1;
            int rightside = (heapIndex * 2) + 2;
            if (heap[heapIndex] < heap[leftside]) {
                if (heaplength >= leftside) {
                    currentIndex = leftside;
                }
            }
            if (heap[heapIndex] < heap[rightside]) {
                if (heaplength >= rightside) {
                    currentIndex = rightside;
                }
            }
            if (currentIndex != heapIndex) {
                int temp= heap[heapIndex];
                heap[heapIndex] = heap[currentIndex];
                heap[currentIndex] = temp;
                nodeDown(currentIndex);
            }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            for (int i = 0; i < tailIndex; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;
            
            heap[heapIndex] = heap[0] + 1;
            int parentIndex = (heapIndex - 1) / 2;
            while (heap[parentIndex] < heap[heapIndex] && heapIndex > 0) {
                int tempIndex = heap[parentIndex];
                heap[parentIndex] = heap[heapIndex];
                heap[heapIndex] = tempIndex;
                heapIndex = parentIndex;
            }
            heap[0] = heap[heaplength];
            heaplength = heaplength - 1;
            nodeDown(heapIndex);
            
            
            
        }
    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }
}
