
package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utility.CucumberReport;

import java.io.File;

public class ReportListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        File reportFolder =
                new File("target/cucumber-reports");

        deleteFolder(reportFolder);
    }

    @Override
    public void onFinish(ISuite suite) {
        CucumberReport.generateReport();
    }

    private void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            folder.delete();
        }
    }
}
