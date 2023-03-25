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
    
    private final Object[] heap;
    private int heapsize;
    private int heaplength;
    
    public HeapPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
        heap = new Object[size];
        heapsize = 2;
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
            return ((PriorityItem<T>) storage[0]).getItem();
        }
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        int vr;
        node newnode = new node(item,priority);
        tailIndex = tailIndex + 1;
        heaplength = heaplength + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            int h = heaplength;
            while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() < priority) {
                storage[i] = storage[i - 1];
                i = i - 1;
            }
            storage[i] = new PriorityItem<>(item, priority);
            
            if (heaphead != null) {
            node lead = heaphead;
            node successor = heaphead;
            node predecessor = heaphead;
            vr = newnode.val;
            
            while (lead != null && vr > lead.val) {
            System.out.print(lead.item);
            System.out.print(predecessor.item);
            System.out.print(vr);
            predecessor = lead;
            lead = lead.next;
            }
            
            if (newnode.val > predecessor.val) {
            successor = predecessor.next;
            newnode.next = successor;
            predecessor.next = newnode;
            }
            
            else {
            newnode.next = successor;
            heaphead = newnode;
            }

        }
        else {
        newnode.next = null;
        heaphead = newnode;
        }
            
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
            
            if (heaphead != null) {
            node start = heaphead;
            node hunted = heaphead;
            node hunter = heaphead;
            
            
            while (start != null) {
            hunted = start;
            start = start.next;
            }
            if (heaphead.next != null) {
            while (hunter.next != hunted) {    
            hunter = hunter.next;
            }
            hunter.next = null;
            hunted.next = null;
            hunted = null;
            }
            }
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
