package utility;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberReport {

    public static void generateReport() {

        File reportOutputDirectory = new File("target/cucumber-reports");

        List<String> jsonFiles = new ArrayList<>();

        jsonFiles.add("target/cucumber-reports/cucumber.json");

        Configuration configuration = new Configuration( reportOutputDirectory,"Selenium Automation Framework");

        configuration.setBuildNumber("1");

        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Environment", "QA");

        ReportBuilder reportBuilder =
                new ReportBuilder(jsonFiles, configuration);

        reportBuilder.generateReports();

        System.out.println("Report generated at: "
                + reportOutputDirectory.getAbsolutePath());
    }
}
