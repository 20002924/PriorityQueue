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
public class UnsortedLinkedPriorityQueue<T> implements PriorityQueue<T> {
    
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
    public UnsortedLinkedPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }
    
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

    @Override
    public T head() throws QueueUnderflowException {
        node current = head;
        node highest = head;
        int gx;
         if (head == null) {
            throw new QueueUnderflowException();
        }
         else {
        gx = head.val;
        while(current != null){   
                 if(gx < current.val) {  
                     gx = current.val;
                     highest = current;
                 }
                 if (gx == current.val) {
                     highest = current;
                 }
                 //System.out.print(current.item);
                 current = current.next;
                 
            }
        
        return highest.item;
        }
    
            // Note: Original head of queue loop
    }
    


    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        node newnode = new node(item,priority);
        if(head == null) {  
            head = newnode;    
        }  
        else {   
            node headfirst = head;  
            head = newnode;  
            head.next = headfirst;  
        }
        
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
        
        tailIndex = tailIndex - 1;
        throw new QueueOverflowException();
        } else {
        int i = tailIndex;
        storage[i] = new PriorityItem<>(item, priority);
        }
         
    }

    @Override
    public void remove() throws QueueUnderflowException {
        node imminent = head;
        node chaser = head;
        node delete = head;
        node replace = head;
        node sohigh = head;
        node preordained = head;
        int dm;
        if (head == null) {
            throw new QueueUnderflowException();
        }
        else {
            dm = head.val;
        
            // Note: Original node replace
            
            while(imminent != null){   
                 if(dm < imminent.val) {
                     
                     dm = imminent.val;
                     sohigh = imminent;
                 }
                 if (dm == imminent.val) {
                     sohigh = imminent;
                 }
                 preordained = imminent;
                 imminent = imminent.next;  
            }
            
        // Start or middle of linked list
        if (sohigh.next != null && sohigh != head) {
            while (chaser.next != sohigh) {
                chaser = chaser.next;
            }
            chaser.next = sohigh.next;
            sohigh.next = null;
            sohigh = null;
        }
        // End of linked list
        else if (sohigh.next == null && head.next != null) {
            while (chaser.next != sohigh) {
                chaser = chaser.next;
            }
            chaser.next = null;
            
            sohigh = null;
        }
        // If highest is at start of linked list
        if (sohigh == head) {
           head = sohigh.next;
           sohigh.next = null;
           sohigh = null;
        }
        
             //System.out.print(head.item);
             //System.out.print(chaser.item);
            int tail = 0;
            int i = tailIndex;
            
            while (i > 0) {
                
                if (((PriorityItem<T>) storage[i]).getPriority() > ((PriorityItem<T>) storage[tail]).getPriority()) {
                    tail = i;
                }
                
                //if (((PriorityItem<T>) storage[i]).getItem() == delete.item) {
                //    tail = i;
                //}
                i = i - 1;
            }
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
