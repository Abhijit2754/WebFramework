
package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.CucumberReport;

import java.io.File;
import java.io.IOException;

public class ReportListener implements ISuiteListener {

    private static final Logger logger =
            LoggerFactory.getLogger(ReportListener.class);

    @Override
    public void onStart(ISuite suite) {
        logger.info("Test Suite Started: {}", suite.getName());
        File reportFolder =new File("target/cucumber-reports");
        deleteFolder(reportFolder);
        logger.info("Previous report folder cleaned");
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Test Suite Finished: {}", suite.getName());
        try {
            CucumberReport.generateReport();
            logger.info("Cucumber HTML report generated successfully");
        } catch (Exception e) {
            logger.error("Failed to generate Cucumber report", e);
            throw new RuntimeException(
                    "Cucumber report generation failed", e
            );
        }
    }

    private void deleteFolder(File folder) {
        if (!folder.exists()) {
            return;
        }
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else if (!file.delete()) {
                    throw new RuntimeException("Unable to delete file: " +file.getAbsolutePath()
                    );
                }
            }
        }
        if (!folder.delete()) {
            throw new RuntimeException("Unable to delete folder: " +folder.getAbsolutePath());
        }
    }
}
