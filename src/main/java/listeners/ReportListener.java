
package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utility.CucumberReport;

public class ReportListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println("Test Suite Started");
    }

    @Override
    public void onFinish(ISuite suite) {
        CucumberReport.generateReport();
    }
}
