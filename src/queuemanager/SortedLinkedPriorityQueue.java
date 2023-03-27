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
public class SortedLinkedPriorityQueue<T> implements PriorityQueue<T> {
    
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
    
    node head;
    //node headfirst;
    
    class node {
        
        int val;
        node next;
        private T item;
        
        private node(T item, int priority) {
            this.item = item;
            this.val = priority;
        }
    }
    
    
    
    public SortedLinkedPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    @Override
    public T head() throws QueueUnderflowException {
        
        node current = head;
        node highest = current;
        //node testlist = head;
         if (head == null) {
            throw new QueueUnderflowException();
        }
        while (current != null) {
            // Search for node at end of list
            
            highest = current;
            current = current.next;
        }
        
        // To test nodes work correctly
        /*
        while (testlist != null) {
            System.out.print(testlist.item);
            testlist = testlist.next;
        }
        */
        
        return highest.item;
        
            
            /*
            node compare = head.next;
            while (head.val < compare.val) {
                head = head.next;
                compare = head.next;
            }

            return head.item;
        }
            */
           
        /*
         node current = head;
         if (head == null) {
            throw new QueueUnderflowException();
        }
        while (current != null) {
            while (head != null) {
            System.out.print(head.val +" "+ head.item);
            head = head.next;
            
        }
    }
        return null;
        */
        
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        int ds;
        node newnode = new node(item,priority);
        
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() > priority || i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() == priority) {
                storage[i] = storage[i - 1];
                i = i - 1;
            }
            storage[i] = new PriorityItem<>(item, priority);
        }
        
        if (head != null) {
            node lead = head;
            node successor = head;
            node predecessor = head;
            ds = newnode.val;
            
            while (lead != null && ds > lead.val) {
            //System.out.print(lead.item);
            //System.out.print(predecessor.item);
            //System.out.print(ds);
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
            head = newnode;
            }

        }
        else {
        newnode.next = null;
        head = newnode;
        }
 
        // Note: Original Node Implementation
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            //Search for and delete the highest priority item in the queue
            int i = tailIndex;
            if (i > 0) {
            while (((PriorityItem<T>) storage[i]).getPriority() < ((PriorityItem<T>) storage[i-1]).getPriority()) {
                i = i - 1;
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;
            }
            else {
                tailIndex = tailIndex - 1;
            }
            
            
            if (head != null) {
            node start = head;
            node hunted = head;
            node hunter = head;
            
            
            while (start != null) {
            hunted = start;
            start = start.next;
            }
            if (head.next != null) {
            while (hunter.next != hunted) {    
            hunter = hunter.next;
            }
            hunter.next = null;
            hunted.next = null;
            hunted = null;
            }
            else {
                head = null;
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
