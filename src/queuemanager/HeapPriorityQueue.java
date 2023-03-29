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
    
    // Original heap implementation
    
    //private final Object[] heap;
    //static int[] heap;
    //private int heapsize;
    //static int heaplength;
    //private int heapIndex;
    private int storageIndex;
    
    public HeapPriorityQueue(int size) {
        storage = new Object[size];
        //heap = new int[size];
        capacity = size;
        tailIndex = -1;
        //heap = new Object[size];
        //heapsize = size;
        //heaplength = -1;
    }
    
    // Note: original node class
    
    

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            // Note: Heap testing method
            return ((PriorityItem<T>) storage[0]).getItem();
        }
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        
        tailIndex = tailIndex + 1;
        //heaplength = heaplength + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            
            storage[i] = new PriorityItem<>(item, priority);
            
            // This storage method determines where in the heap the new item goes, it does this through the left or right side formula.
            storageIndex = tailIndex;
            //int parentstorageIndex = (storageIndex - 1) / 2;
            while (((PriorityItem<T>) storage[(storageIndex - 1) / 2]).getPriority() < ((PriorityItem<T>) storage[storageIndex]).getPriority() && storageIndex > 0) {
                Object tempIndex = storage[(storageIndex - 1) / 2];
                storage[(storageIndex - 1) / 2] = storage[storageIndex];
                storage[storageIndex] = tempIndex;
                storageIndex = (storageIndex - 1) / 2;
            }

            // Note: original heap add
            
            
        }
    }
    
    // Note: original node down
    
    public void nodeshiftDown(int storageDown) {
        // This method is used to shift down the items in the heap.
        int currentIndex = storageDown;
            int leftside = (0 * 2) + 1;
            int rightside = (0 * 2) + 2;
            // Used to check for left side value and compare.
            if (((PriorityItem<T>) storage[currentIndex]).getPriority() < ((PriorityItem<T>) storage[leftside]).getPriority() && tailIndex >= leftside) {
                currentIndex = leftside;
            }
            // Used to check for right side value and compare.
            if (((PriorityItem<T>) storage[currentIndex]).getPriority() < ((PriorityItem<T>) storage[rightside]).getPriority() && tailIndex >= rightside) {
                currentIndex = rightside;
            }
            // If there is a chance in the values it runs this nodeshiftDown again.
            if (currentIndex != storageDown) {
                Object downIndex = storage[storageDown];
                storage[storageDown] = storage[currentIndex];
                storage[currentIndex] = downIndex;
                nodeshiftDown(currentIndex);
            }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            
            // This method uses the similar action of the add method and searches values to find highest and lowest values.
            storageIndex = tailIndex;
            while (((PriorityItem<T>) storage[(storageIndex - 1) / 2]).getPriority() < ((PriorityItem<T>) storage[storageIndex]).getPriority() && storageIndex > 0) {
                Object tempIndex = storage[(storageIndex - 1) / 2];
                storage[(storageIndex - 1) / 2] = storage[storageIndex];
                storage[storageIndex] = tempIndex;
                storageIndex = (storageIndex - 1) / 2;
            }
            storage[0] = storage[tailIndex];
            tailIndex = tailIndex - 1;
            nodeshiftDown(0);
           
            // Note: original deletion method
            
            
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
