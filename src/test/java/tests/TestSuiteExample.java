package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.nio.file.Paths;

public class TestSuiteExample {
    private Playwright playwright;
    private Browser browser;
    @BeforeClass
    public void setUp() {
        System.out.println("Setting up Playwright and Browser");
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }


    @Test(groups = "login success")
    public void testLogin() {
        this.setUp();
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        //This for Username
        page.locator("[data-test=\"username\"]").dblclick();
        page.locator("[data-test=\"username\"]").fill("standard_user");
        page.locator("[data-test=\"username\"]").press("Tab");
        //this for fill Password
        page.locator("[data-test=\"password\"]").fill("secret_sauce");
        page.locator("[data-test=\"login-button\"]").click();
        // Tunggu hingga halaman berikutnya dimuat dan verifikasi login
        page.waitForSelector(".inventory_list");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/Log_success.png")));
        // Verifikasi bahwa elemen inventory list ada di halaman
        if (page.isVisible(".inventory_list")) {
            System.out.println("Login berhasil!");
        } else {
            System.out.println("Login gagal!");
        }
        page.close();
    }
    @Test(groups = "login failed")
    public void testSearch() {
        this.setUp();
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        page.locator("[data-test=\"username\"]").dblclick();
        page.locator("[data-test=\"username\"]").fill("standard_user");
        page.locator("[data-test=\"username\"]").press("Tab");
        page.locator("[data-test=\"password\"]").fill("12333");
        page.locator("[data-test=\"login-button\"]").click();

        PlaywrightAssertions.assertThat(page.locator("[data-test=\"error\"]")).containsText("not match");
        System.out.println("Muncul Error!");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/Log_failed.png")));
        page.close();
    }

    @Test(groups = "buying_success")
    public void testBuy() {
        this.setUp();
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        //This for Username
        page.locator("[data-test=\"username\"]").dblclick();
        page.locator("[data-test=\"username\"]").fill("standard_user");
        page.locator("[data-test=\"username\"]").press("Tab");
        //this for fill Password
        page.locator("[data-test=\"password\"]").fill("secret_sauce");
        page.locator("[data-test=\"login-button\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-backpack\"]").click();
        page.locator("[data-test=\"shopping-cart-link\"]").click();
        page.locator("[data-test=\"checkout\"]").click();
        page.locator("[data-test=\"firstName\"]").click();
        page.locator("[data-test=\"firstName\"]").fill("Dimas_mute");
        page.locator("[data-test=\"firstName\"]").press("Tab");
        page.locator("[data-test=\"lastName\"]").fill("Sule_Diana");
        page.locator("[data-test=\"lastName\"]").press("Tab");
        page.locator("[data-test=\"postalCode\"]").fill("12810");
        page.locator("[data-test=\"continue\"]").click();
        page.locator("[data-test=\"finish\"]").click();
        page.locator("[data-test=\"complete-header\"]").click();



        PlaywrightAssertions.assertThat(page.locator("//h2[contains(.,'Thank you for your order!')]"));
        System.out.println("Belanja Berhasil");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/buying_suc.png")));
        page.close();
    }
    @Test(groups = "buying_Failed")
    public void testBuyFailed() {
        this.setUp();
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        //This for Username
        page.locator("[data-test=\"username\"]").dblclick();
        page.locator("[data-test=\"username\"]").fill("standard_user");
        page.locator("[data-test=\"username\"]").press("Tab");
        //this for fill Password
        page.locator("[data-test=\"password\"]").fill("secret_sauce");
        page.locator("[data-test=\"login-button\"]").click();
        page.locator("[data-test=\"add-to-cart-sauce-labs-backpack\"]").click();
        page.locator("[data-test=\"shopping-cart-link\"]").click();
        page.locator("[data-test=\"checkout\"]").click();
        page.locator("[data-test=\"firstName\"]").click();
        page.locator("[data-test=\"firstName\"]").fill("x");
        page.locator("[data-test=\"firstName\"]").press("Tab");
        page.locator("[data-test=\"lastName\"]").fill("");
        page.locator("[data-test=\"lastName\"]").press("Tab");
        page.locator("[data-test=\"postalCode\"]").fill("");
        page.locator("[data-test=\"continue\"]").click();



        PlaywrightAssertions.assertThat(page.locator("[data-test=\"error\"]"));
        System.out.println("Belanja gagal");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/buying_failed.png")));
        page.close();

    }



    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
