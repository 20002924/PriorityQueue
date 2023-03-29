/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package queuemanager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20002924
 */
public class SortedArrayPriorityQueueTest {
    
    public final static SortedArrayPriorityQueue<String> instance = new SortedArrayPriorityQueue<>(100);
    // Change nameInput to desired name.
    public String nameInput = "Ike";
    public String nameInput2 = "Zeke";
    public String nameInput3 = "Zac";
    // Change priorityInput to desired priority.
    public int priorityInput = 1;
    public int priorityInput2 = 2;
    public int priorityInput3 = 3;
    
    public SortedArrayPriorityQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of head method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testHead() throws Exception {
        System.out.println("head");
        Object expResult = nameInput;
        // Checks for the expect priority result
        if (priorityInput > priorityInput2 && priorityInput > priorityInput3) {
        expResult = nameInput;
        }
        else if (priorityInput2 > priorityInput && priorityInput2 > priorityInput3) {
        expResult = nameInput2;    
        }
        else if (priorityInput3 > priorityInput && priorityInput3 > priorityInput2) {
        expResult = nameInput3;    
        }
        else {
        expResult = nameInput;    
        }
        Object result = instance.head();
        assertEquals(expResult, result);
        //System.out.println(result);
    }

    /**
     * Test of add method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        instance.add(nameInput, priorityInput);
        instance.add(nameInput2, priorityInput2);
        instance.add(nameInput3, priorityInput3);
        
        //System.out.print(item);
        //System.out.print(priority);
        
    }

    /**
     * Test of remove method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        instance.remove();
    }

    /**
     * Test of isEmpty method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class SortedArrayPriorityQueue.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = null;
        String unoInput = null;
        int unoInput2 = 0;
        String dosInput = null;
        int dosInput2 = 0;
        String tresInput = null;
        int tresInput2 = 0;
        // Finds item order and expects it
        if (priorityInput > priorityInput2 && priorityInput > priorityInput3) {
        unoInput = nameInput;
        unoInput2 = priorityInput;
        if (priorityInput3 > priorityInput2) {
            dosInput = nameInput3;
            dosInput2 = priorityInput3;
            tresInput = nameInput2;
            tresInput2 = priorityInput2;
        }
        else {
            dosInput = nameInput2;
            dosInput2 = priorityInput2;
            tresInput = nameInput3;
            tresInput2 = priorityInput3;
        }
        }
        else if (priorityInput2 > priorityInput && priorityInput2 > priorityInput3) {
        unoInput = nameInput2;
        unoInput2 = priorityInput2;
        if (priorityInput3 > priorityInput) {
            dosInput = nameInput3;
            dosInput2 = priorityInput3;
            tresInput = nameInput;
            tresInput2 = priorityInput;
        }
        else {
            dosInput = nameInput;
            dosInput2 = priorityInput;
            tresInput = nameInput3;
            tresInput2 = priorityInput3;
        }
        }
        if (priorityInput3 > priorityInput2 && priorityInput3 > priorityInput) {
        unoInput = nameInput3;
        unoInput2 = priorityInput3;
        if (priorityInput2 > priorityInput) {
            dosInput = nameInput2;
            dosInput2 = priorityInput2;
            tresInput = nameInput;
            tresInput2 = priorityInput;
        }
        else {
            dosInput = nameInput;
            dosInput2 = priorityInput;
            tresInput = nameInput2;
            tresInput2 = priorityInput2;
        }
        }
        expResult = "[("+unoInput+", "+unoInput2+"), ("+dosInput+", "+dosInput2+"), ("+tresInput+", "+tresInput2+")]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
