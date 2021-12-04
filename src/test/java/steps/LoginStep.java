package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static org.junit.Assert.*;

public class LoginStep {
    private final String baseURL = "http://multibags.1dt.com.br/shop/customer/customLogon.html";
    private final WebDriver driver = new ChromeDriver();

    private final int pageLoadTimeout = 10;
    private final String _email = "signin_userName";
    private final String _senha = "signin_password";
    private final String btnClassName = "genericLogin-button";
    private final String _errorMsg = "loginError";

    private void waitForRedirectToFinish() throws TimeoutException {
        new WebDriverWait(driver, pageLoadTimeout).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    private void fillForm(Map<String, String> fieldMap) {
        WebElement elInputEmail = driver.findElement(By.id(_email));
        elInputEmail.sendKeys(fieldMap.get("email"));
        WebElement elInputSenha = driver.findElement(By.id(_senha));
        elInputSenha.sendKeys(fieldMap.get("senha"));
    }

    private void openSignInPage() {
        driver.get(baseURL);
        new WebDriverWait(driver, pageLoadTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className(btnClassName)));
    }

    @Dado("usuario pre cadastrado")
    public void usuario_pre_cadastrado(DataTable dataTable) {
        openSignInPage();
        fillForm(dataTable.asMaps().get(0));
    }

    @Entao("sistema deve redirecionar para o dashboard")
    public void sistema_deve_redirecionar_para_o_dashboard() {
        waitForRedirectToFinish();
        String expectedURL = "http://multibags.1dt.com.br/shop/customer/dashboard.html";
        String currentURL  = driver.getCurrentUrl();
        assertEquals(expectedURL, currentURL);
    }

    @Entao("usuario deve estar autenticado")
    public void usuario_deve_estar_autenticado() {
        WebElement menuContainer = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("customerAccount")));
        assertEquals(true, menuContainer.isDisplayed());
    }

    @Dado("usuario sem cadastro previo")
    public void usuario_sem_cadastro_previo(DataTable dataTable) {
        openSignInPage();
        fillForm(dataTable.asMaps().get(0));
    }

    @Quando("clica no botao submit")
    public void clica_no_botao_submit() {
        WebElement btnCriarConta = driver.findElement(By.className(btnClassName));
        btnCriarConta.click();
    }

    @Entao("sistema deve mostrar a mensagem de erro")
    public void sistema_deve_mostrar_a_mensagem_de_erro() {
        waitForRedirectToFinish();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(_errorMsg)));
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
