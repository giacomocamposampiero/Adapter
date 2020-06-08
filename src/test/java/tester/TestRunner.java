package tester;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("Avvio dei test... \n\n");
        String[] test = {"tester.MapTest", "tester.ListTest", "tester.SetTest", "tester.CollectionTest"};
        for (String name : test) {
            Class clazz = null;
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException e) {
                continue;
            }
            Result result = JUnitCore.runClasses(clazz);
            System.out.println("Now executing test: " + name);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.out.println("Test successful: " + result.wasSuccessful()+".");
            System.out.println("Test statistics: "+(result.getRunCount()-result.getFailureCount())+" succeeded, "+result.getIgnoreCount()+" ignored, "+result.getFailureCount()+" failed.");
            System.out.println("___________________\n");
        }
    }
    
}