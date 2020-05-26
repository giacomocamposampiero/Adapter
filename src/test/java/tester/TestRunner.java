package tester;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        /**
        String[] test = {"HashMapTester"};
        for (String name : test) {
            Class clazz = null;
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException e) {
                continue;
            }
            Result result = JUnitCore.runClasses(clazz);
            System.out.println(result);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.out.println("Test successful == " + result.wasSuccessful());
            System.out.println("___________________\n\n");
        }
        */
    }
    
}
