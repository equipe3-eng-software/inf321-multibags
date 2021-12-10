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
    private static final WebDriver driver = new ChromeDriver();

    private static final String _errorMsg = "loginError";

    @Dado("usuario pre cadastrado")
    public void usuario_pre_cadastrado(DataTable dataTable) {
        Utils.openSignInPage(driver);
        Map<String, String> fieldMap = dataTable.asMaps().get(0);
        Utils.fillForm(driver, fieldMap.get("email"), fieldMap.get("senha"));
    }

    @Entao("sistema deve redirecionar para o dashboard")
    public void sistema_deve_redirecionar_para_o_dashboard() {
        String expectedURL = "http://multibags.1dt.com.br/shop/customer/dashboard.html";
        new WebDriverWait(driver, 30).until(ExpectedConditions.urlToBe(expectedURL));
    }

    @Entao("usuario deve estar autenticado")
    public void usuario_deve_estar_autenticado() {
        WebElement menuContainer = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("customerAccount")));
        assertEquals(true, menuContainer.isDisplayed());
    }

    @Dado("usuario sem cadastro previo")
    public void usuario_sem_cadastro_previo(DataTable dataTable) {
        Utils.openSignInPage(driver);
        Map<String, String> fieldMap = dataTable.asMaps().get(0);
        Utils.fillForm(driver, fieldMap.get("email"), fieldMap.get("senha"));
    }

    @Quando("clica no botao submit")
    public void clica_no_botao_submit() {
        Utils.authenticate(driver);
    }

    @Entao("sistema deve mostrar a mensagem de erro")
    public void sistema_deve_mostrar_a_mensagem_de_erro() {
        Utils.waitForRedirectToFinish(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(_errorMsg)));
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
