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
 * @author isaac
 */
public class UnsortedLinkedPriorityQueueTest {
    
    public final static UnsortedLinkedPriorityQueue<String> instance = new UnsortedLinkedPriorityQueue<>(100);
    // Change nameInput to desired name.
    public String nameInput = "Ike";
    public String nameInput2 = "Zeke";
    public String nameInput3 = "Zac";
    // Change priorityInput to desired priority.
    public int priorityInput = 1;
    public int priorityInput2 = 2;
    public int priorityInput3 = 3;
    
    public UnsortedLinkedPriorityQueueTest() {
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
     * Test of head method, of class UnsortedLinkedPriorityQueue.
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
    }

    /**
     * Test of add method, of class UnsortedLinkedPriorityQueue.
     */
    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        instance.add(nameInput3, priorityInput3);
        instance.add(nameInput2, priorityInput2);
        instance.add(nameInput, priorityInput);
    }

    /**
     * Test of remove method, of class UnsortedLinkedPriorityQueue.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        instance.remove();
    }

    /**
     * Test of isEmpty method, of class UnsortedLinkedPriorityQueue.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class UnsortedLinkedPriorityQueue.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "[("+nameInput3+", "+priorityInput3+"), ("+nameInput2+", "+priorityInput2+"), ("+nameInput+", "+priorityInput+")]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
