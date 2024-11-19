package pages;

import com.microsoft.playwright.*;

public class Login {
    private final Page page;

    private final String usernameField = "#user-name";
    private final String passwordField = "#password";
    private final String loginBtn = "#login-button";

    public Login(Page page) {
        this.page = page;
    }
    public void Enterusername(String username) {
        page.fill(usernameField, username);
    }

        public void EnterPass(String password){
            page.fill(passwordField,password);
}
    public void ClickLogin(String loginBtn) {
        page.click(loginBtn);
    }

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new
                    BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            //This For navigate
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
            // Verifikasi bahwa elemen inventory list ada di halaman
            if (page.isVisible(".inventory_list")) {
                System.out.println("Login berhasil!");
            } else {
                System.out.println("Login gagal!");
            }
// Tutup browser
            browser.close();
        }
    }
}

