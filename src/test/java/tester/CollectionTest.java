package tester;

import interfaces.HCollection;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

/**
 * Test suite for testing the behaviour of List and Set implementation as Collections.
 * This test suite is a parameterized suite which use reflection to test the common behaviour between List and Set defined by Collection interface.
 * @author Giacomo Camposampiero
 */
@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CollectionTest {

    private final String paramClass;
    private HCollection instance;
    
    public CollectionTest(String paramClass) {
        this.paramClass = paramClass;
    }

    @Parameterized.Parameters(name="{0}")
    public static Collection classesToTest() {
        return Arrays.asList(new Object[][]{
            {"List"},
            {"Set"},
        });
    }
    
    @Before
    public void initialize() {
        try {
            instance = (HCollection) Class.forName("adapters."+paramClass).getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | SecurityException  ex) {
            System.exit(1);
        }
    }
       
  
    

}


/**
     * @title Test of .
     * @description 
     * @expectedResults T
     * @actualResult As expected result.
     * @dependencies This set has no dependencies on other class methods.
     * @preConditions The set instance must be a new istance of .
     * @postConditions The set instance should be m
     */