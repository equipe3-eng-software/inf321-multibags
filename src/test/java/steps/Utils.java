package steps;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Utils {
    private static final int pageLoadTimeout = 30;
    private static final String _btnSignIn = "genericLogin-button";

    public static void waitForRedirectToFinish(WebDriver d) throws TimeoutException {
        new WebDriverWait(d, pageLoadTimeout).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    public static void fillForm(WebDriver d, String email, String senha) {
        WebElement elInputEmail = d.findElement(By.id("signin_userName"));
        elInputEmail.sendKeys(email);
        WebElement elInputSenha = d.findElement(By.id("signin_password"));
        elInputSenha.sendKeys(senha);
    }

    public static void openSignInPage(WebDriver d) {
        d.manage().window().maximize();
        d.get("http://multibags.1dt.com.br/shop/customer/customLogon.html");
        new WebDriverWait(d, pageLoadTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(_btnSignIn)));
    }

    public static void authenticate(WebDriver d) {
        WebElement btnLogin = d.findElement(By.id(_btnSignIn));
        btnLogin.click();
    }
}
