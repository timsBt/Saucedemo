package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerUtils implements ITestListener {

    @Override
    public void onTestFailure(final ITestResult result) {
        System.out.println("<------------------------------ TEST FAILED ------------------------------");
    }
}
