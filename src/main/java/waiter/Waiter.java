package waiter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * A class that holds only Selenium wait methods
 */
public class Waiter {
    //hardcoded timeout value; up to how much to wait for a condition to take place
    private final int TIMEOUT = 30;

    //GET METHODS

    /**
     * Open a specified url and wait for the page to load completely.
     * Should replace driver.get().
     * Will wait for the TIMEOUT constant value amount of seconds for the page to load.
     *
     * @param url    - the url to open in the browser
     * @param driver - the driver instance
     */
    public void get(String url, WebDriver driver) {
        get(url, driver, TIMEOUT);
    }

    /**
     * Open a specified url and wait for the page to load completely.
     * Should replace driver.get().
     * Will wait for the specified number of seconds.
     *
     * @param url              - the url to open in the browser
     * @param driver           - the driver instance
     * @param specifiedTimeout - number of seconds to wait for
     */
    public void get(String url, WebDriver driver, int specifiedTimeout) {
        driver.get(url);
        waitForPageLoadComplete(driver, specifiedTimeout);
    }

    /**
     * Method that opens a url and waits for page load complete and for a specified WebElement to be displayed.
     * Uses Selenium's isDisplayed() method to check for element.
     * Wait for up to TIMEOUT.
     *
     * @param url     - url of the page that needs to open
     * @param element - the element to wait for
     * @param driver  - the WebDriver instance
     */
    public void getAndWaitForElementToBeDisplayed(String url, WebElement element, WebDriver driver) {
        getAndWaitForElementToBeDisplayed(url, element, driver, TIMEOUT);
    }

    /**
     * Method that opens a url and waits for page load complete and for a specified WebElement to be displayed.
     * Uses Selenium's isDisplayed() method to check for element.
     * Wait for up to the specified amount of seconds.
     *
     * @param url              - url of the page that needs to open
     * @param element          - the element to wait for
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - number of seconds to wait for
     */
    public void getAndWaitForElementToBeDisplayed(String url, WebElement element, WebDriver driver, int
            specifiedTimeout) {
        get(url, driver, specifiedTimeout);
        waitForElementToBeDisplayed(element, driver, specifiedTimeout);
    }

    /**
     * Method that opens a URL in the browser and waits for another URL to be loaded.
     * Also waits for page to load completely after expected URL is loaded in the browser.
     * Useful when a URL you want to open performs a redirect to another page.
     * Wait for TIMEOUT number of seconds.
     *
     * @param urlToGet     - the URL to open initially
     * @param urlToWaitFor - the URL you expect to be redirected to
     * @param driver       - the WebDriver instance
     */
    public void getUrlAndWaitForUrl(String urlToGet, String urlToWaitFor, WebDriver driver) {
        getUrlAndWaitForUrl(urlToGet, urlToWaitFor, driver, TIMEOUT);
    }

    /**
     * Method that opens a URL in the browser and waits for another URL to be loaded.
     * Also waits for page to load completely after expected URL is loaded in the browser.
     * Useful when a URL you want to open performs a redirect to another page.
     * Wait for a specifiedTimeout number of seconds.
     *
     * @param urlToGet         - the URL to open initially
     * @param urlToWaitFor     - the URL you expect to be redirected to
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - number of seconds to wait for
     */
    public void getUrlAndWaitForUrl(String urlToGet, String urlToWaitFor, WebDriver driver, int specifiedTimeout) {
        driver.get(urlToGet);
        waitForUrl(urlToWaitFor, driver, specifiedTimeout);
    }

    //PAGE LOAD METHODS

    /**
     * Wait for a page to load completely for up to TIMEOUT seconds.
     *
     * @param driver - the WebDriver instance
     */
    public void waitForPageLoadComplete(WebDriver driver) {
        waitForPageLoadComplete(driver, TIMEOUT);
    }

    /**
     * Wait for a page to load completely for the specified number of seconds.
     *
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    //ELEMENT WAIT METHODS

    /**
     * Method that waits for the Selenium isDisplayed() method to return true.
     * Hence waits for an element to be displayed.
     * Will wait for up to TIMEOUT seconds.
     *
     * @param element - the WebElement to be displayed
     * @param driver  - the WebDriver instance
     */
    public void waitForElementToBeDisplayed(WebElement element, WebDriver driver) {
        waitForElementToBeDisplayed(element, driver, TIMEOUT);
    }

    /**
     * Method that waits for the Selenium isDisplayed() method to return true.
     * Hence waits for an element to be displayed.
     * Will wait for up to the specified amount of seconds.
     *
     * @param element          - the WebElement to be displayed
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void waitForElementToBeDisplayed(WebElement element, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> elementIsDisplayed = arg0 -> element.isDisplayed();
        wait.until(elementIsDisplayed);
    }

    // URL wait methods

    /**
     * Wait for a URL to open in the browser and for the page to load completely.
     * Wait for TIMEOUT number of seconds.
     *
     * @param url    - the URL to open
     * @param driver - the WebDriver instance
     */
    public void waitForUrl(String url, WebDriver driver) {
        waitForUrl(url, driver, TIMEOUT);
    }

    /**
     * Wait for a URL to open in the browser and for the page to load completely.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param url              - the URL to open
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void waitForUrl(String url, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().equals(url);
        wait.until(urlIsCorrect);
        waitForPageLoadComplete(driver, specifiedTimeout);
    }

    /**
     * Wait for a URL containing a specified String to open in the browser.
     * The URL will not equal the specified String. Just contain it.
     * Wait for TIMEOUT number of seconds.
     *
     * @param expectedString - the String that needs to be included in the URL
     * @param driver         - the WebDriver instance
     */
    public void waitForUrlContains(String expectedString, WebDriver driver) {
        waitForUrlContains(expectedString, driver, TIMEOUT);
    }

    /**
     * Wait for a URL containing a specified String to open in the browser.
     * The URL will not equal the specified String. Just contain it.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param expectedString   - the String that needs to be included in the URL
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void waitForUrlContains(String expectedString, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().contains(expectedString);
        wait.until(urlIsCorrect);
        waitForPageLoadComplete(driver, specifiedTimeout);
    }


    /**
     * Wait for an expected URL to load in the browser, but ignore the case of the url.
     * Compares a lower case value of the actual url in the browser with the lower case value of the expected url.
     * Wait for the TIMEOUT number of seconds.
     *
     * @param url    - the url expected to load in the browser, ignoring its case
     * @param driver - the WebDriver instance
     */
    public void waitForUrl_IgnoreCase(String url, WebDriver driver) {
        waitForUrl_IgnoreCase(url, driver, TIMEOUT);
    }

    /**
     * Wait for an expected URL to load in the browser, but ignore the case of the url.
     * Compares a lower case value of the actual url in the browser with the lower case value of the expected url.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param url              - the url expected to load in the browser, ignoring its case
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void waitForUrl_IgnoreCase(String url, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().toLowerCase().equals(url.toLowerCase
                ());
        wait.until(urlIsCorrect);
        waitForPageLoadComplete(driver, specifiedTimeout);
    }

    /**
     * Wait for a URL containing a specified String to open in the browser, ignoring the case of the url.
     * Checks whether a lower case value of the actual URL contains a lower case value of the expected String.
     * Wait for the TIMEOUT number of seconds.
     *
     * @param expectedString - the String that needs to be included in the URL
     * @param driver         - the WebDriver instance
     */
    public void waitForUrlContains_IgnoreCase(String expectedString, WebDriver driver) {
        waitForUrlContains_IgnoreCase(expectedString, driver, TIMEOUT);
    }

    /**
     * Wait for a URL containing a specified String to open in the browser, ignoring the case of the url.
     * Checks whether a lower case value of the actual URL contains a lower case value of the expected String.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param expectedString   - the String that needs to be included in the URL
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void waitForUrlContains_IgnoreCase(String expectedString, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().toLowerCase().contains(expectedString
                .toLowerCase());
        wait.until(urlIsCorrect);
        waitForPageLoadComplete(driver, specifiedTimeout);
    }

    /**
     * Wait for the URL in the browser to start with a specified String.
     * Wait for the TIMEOUT number of seconds.
     *
     * @param expectedString - the expected String to be found at the start of the URL loaded in the browser
     * @param driver         - the WebDriver instance
     */
    public void waitForUrlStartsWith(String expectedString, WebDriver driver) {
        waitForUrlStartsWith(expectedString, driver, TIMEOUT);
    }

    /**
     * Wait for the URL in the browser to start with a specified String.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param expectedString   - the expected String to be found at the start of the URL loaded in the browser
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void waitForUrlStartsWith(String expectedString, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().startsWith(expectedString);
        wait.until(urlIsCorrect);
        waitForPageLoadComplete(driver, specifiedTimeout);
    }

    // CLICK AND WAIT METHODS

    /**
     * Click on a WebElement and than wait for a specified URL to load in the browser.
     * Wait for TIMEOUT number of seconds.
     *
     * @param element - the WebElement to click on
     * @param url     - the URL you need are waiting to load
     * @param driver  - the WebDriver instance
     */
    public void clickElementAndWaitForUrl(WebElement element, String url, WebDriver driver) {
        clickElementAndWaitForUrl(element, url, driver, TIMEOUT);
    }

    /**
     * Click on a WebElement and than wait for a specified URL to load in the browser.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param element          - the WebElement to click on
     * @param url              - the URL you need are waiting to load
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void clickElementAndWaitForUrl(WebElement element, String url, WebDriver driver, int specifiedTimeout) {
        element.click();
        waitForUrl(url, driver, specifiedTimeout);
    }

    /**
     * Click on a WebElement and than wait for the URL that loads in the browser to contain a specified String.
     * Wait for the TIMEOUT number of seconds.
     *
     * @param element             - the WebElement to click on
     * @param expectedStringInUrl - the String you expect to be contained in the URL that loads after clicking the
     *                            WebElement
     * @param driver              - the WebDriver instance
     */
    public void clickElementAndWaitForUrlContains(WebElement element, String expectedStringInUrl, WebDriver driver) {
        clickElementAndWaitForUrlContains(element, expectedStringInUrl, driver, TIMEOUT);

    }

    /**
     * Click on a WebElement and than wait for the URL that loads in the browser to contain a specified String.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param element             - the WebElement to click on
     * @param expectedStringInUrl - the String you expect to be contained in the URL that loads after clicking the
     *                            WebElement
     * @param driver              - the WebDriver instance
     * @param specifiedTimeout    - amount of seconds you want to wait for
     */
    public void clickElementAndWaitForUrlContains(WebElement element, String expectedStringInUrl, WebDriver driver, int
            specifiedTimeout) {
        element.click();
        waitForUrlContains(expectedStringInUrl, driver, specifiedTimeout);
    }

    /**
     * Click on a WebElement and than wait for a specified URL to load in the browser, ignoring the case.
     * Wait for TIMEOUT number of seconds.
     *
     * @param element - the WebElement to click on
     * @param url     - the URL you need are waiting to load
     * @param driver  - the WebDriver instance
     */
    public void clickElementAndWaitForUrl_IgnoreCase(WebElement element, String url, WebDriver driver) {
        clickElementAndWaitForUrl_IgnoreCase(element, url, driver, TIMEOUT);
    }

    /**
     * Click on a WebElement and than wait for a specified URL to load in the browser, ignoring the case.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param element          - the WebElement to click on
     * @param url              - the URL you need are waiting to load
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void clickElementAndWaitForUrl_IgnoreCase(WebElement element, String url, WebDriver driver, int
            specifiedTimeout) {
        element.click();
        waitForUrl_IgnoreCase(url, driver, specifiedTimeout);
    }

    /**
     * Click on a WebElement and than wait for the URL that loads in the browser to contain a specified String,
     * ignoring the case.
     * Wait for the TIMEOUT number of seconds.
     *
     * @param element             - the WebElement to click on
     * @param expectedStringInUrl - the String you expect to be contained in the URL that loads after clicking the
     *                            WebElement
     * @param driver              - the WebDriver instance
     */
    public void clickElementAndWaitForUrlContains_IgnoreCase(WebElement element, String expectedStringInUrl, WebDriver
            driver) {
        clickElementAndWaitForUrlContains_IgnoreCase(element, expectedStringInUrl, driver, TIMEOUT);

    }

    /**
     * Click on a WebElement and than wait for the URL that loads in the browser to contain a specified String,
     * ignoring the case.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param element             - the WebElement to click on
     * @param expectedStringInUrl - the String you expect to be contained in the URL that loads after clicking the
     *                            WebElement
     * @param driver              - the WebDriver instance
     * @param specifiedTimeout    - amount of seconds you want to wait for
     */
    public void clickElementAndWaitForUrlContains_IgnoreCase(WebElement element, String expectedStringInUrl, WebDriver
            driver, int specifiedTimeout) {
        element.click();
        waitForUrlContains_IgnoreCase(expectedStringInUrl, driver, specifiedTimeout);
    }

    /**
     * Click on a WebElement and wait for the URL in the browser to start with an expected String.
     * Wait for the TIMEOUT number of seconds.
     *
     * @param element          - the WebElement to click on
     * @param expectedString   - the String you expected the URL in the browser to start with
     * @param driver           - the WebDriver instance
     */
    public void clickElementAndWaitForUrlStartsWith(WebElement element, String expectedString, WebDriver
            driver) {
        clickElementAndWaitForUrlStartsWith(element, expectedString, driver, TIMEOUT);
    }

    /**
     * Click on a WebElement and wait for the URL in the browser to start with an expected String.
     * Wait for the specifiedTimeout number of seconds.
     *
     * @param element          - the WebElement to click on
     * @param expectedString   - the String you expected the URL in the browser to start with
     * @param driver           - the WebDriver instance
     * @param specifiedTimeout - amount of seconds you want to wait for
     */
    public void clickElementAndWaitForUrlStartsWith(WebElement element, String expectedString, WebDriver
            driver, int specifiedTimeout) {
        element.click();
        waitForUrlStartsWith(expectedString, driver, specifiedTimeout);
    }

}