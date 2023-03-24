package queuemanager;

/**
 
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {
    
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
    public UnsortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    @Override
    public T head() throws QueueUnderflowException {
        int arc;
        int arcv;
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            
            /*
            for (int i = 0; i < tailIndex; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;
            int i = tailIndex;
            */
            /*
            int i = tailIndex;
            int pr = tailIndex;
            while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() < ((PriorityItem<T>) storage[pr]).getPriority()) {
                storage[i] = storage[i - 1];
                i = i - 1;
            }
            
            while (((PriorityItem<T>) storage[i-1]).getPriority() != ((PriorityItem<T>) storage[i]).getPriority() || ((PriorityItem<T>) storage[i-1]).getPriority() == ((PriorityItem<T>) storage[i]).getPriority() && i != tailIndex+1) {
                if(((PriorityItem<T>) storage[i]).getPriority() < ((PriorityItem<T>) storage[i-1]).getPriority()) {  
                     i = i - 1;
                 }
                if(((PriorityItem<T>) storage[i]).getPriority() == ((PriorityItem<T>) storage[i-1]).getPriority() && i != tailIndex+1) {  
                     if(((PriorityItem<T>) storage[i-]).getPriority() < ((PriorityItem<T>) storage[i-2]).getPriority()) {
                         i = i - 1;
                     }
                 }
                
            }
            
            */
            
            //Search for highest priority item and return it
            
            //&& ((PriorityItem<T>) storage[i]).getPriority() < ((PriorityItem<T>) storage[i-1]).getPriority()
            
            int tail = 0;
            int i = tailIndex;
            while (i > 0) {
                if (((PriorityItem<T>) storage[i]).getPriority() > ((PriorityItem<T>) storage[tail]).getPriority()) {
                    tail = i;
                }
                i = i - 1;
            }
            
            return ((PriorityItem<T>) storage[tail]).getItem();
            
            
            
            
            
        }
        
        
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Insert into front of queue through tailIndex */
            int i = tailIndex;
            storage[i] = new PriorityItem<>(item, priority);
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            //Search for and delete the highest priority item in the queue
            int xyz = tailIndex;
            
            int tail = 0;
            int i = tailIndex;
            
            while (i > 0) {
                if (((PriorityItem<T>) storage[i]).getPriority() > ((PriorityItem<T>) storage[tail]).getPriority()) {
                    tail = i;
                }
                i = i - 1;
            }
            /*
            int moebius = tail;
            int ouroboros = tailIndex;
            while (moebius > 0) {
                
                storage[tail] = storage[ouroboros];
                tail = tail -1;
                ouroboros = ouroboros - 1;
                moebius = moebius - 1;
                
            }
            */
            storage[tail] = storage[tailIndex];
            tailIndex = tailIndex - 1;
            
            
            
            
            
            
            
            
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
