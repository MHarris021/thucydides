package net.thucydides.core.reports.integration

import net.thucydides.core.ThucydidesReports
import net.thucydides.core.model.Story
import net.thucydides.core.model.TestOutcome
import net.thucydides.core.util.MockEnvironmentVariables
import net.thucydides.core.webdriver.SystemPropertiesConfiguration
import org.openqa.selenium.WebDriver
import spock.lang.Specification

import java.nio.file.Files

class WhenGeneratingReportsUsingTheReportService extends Specification {

    File outputDir;

    def environmentVariables = new MockEnvironmentVariables();
    def configuration = new SystemPropertiesConfiguration(environmentVariables);

    def setup() {
        outputDir = Files.createTempDirectory("reports").toFile()
    }

    def cleanup() {
        outputDir.deleteDir()
    }

    def "should generate reports using each of the subscribed reporters"() {
        given:
            configuration.setOutputDirectory(outputDir)
            ThucydidesReports.setupListeners(configuration)
            def testOutcomes = [TestOutcome.forTestInStory("some test", Story.called("some story"))]
        when:
            ThucydidesReports.getReportService(configuration).generateReportsFor(testOutcomes)
        then:
            outputDir.list().findAll { it.endsWith(".html")}.size() == 1
            outputDir.list().findAll { it.endsWith(".xml")}.size() == 1
            outputDir.list().findAll { it.endsWith(".json")}.size() == 1
    }
}