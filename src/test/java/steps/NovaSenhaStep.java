package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class NovaSenhaStep {
    private final WebDriver driver = new ChromeDriver();

    private final int pageLoadTimeout = 30;

    @Dado("que estou na tela de perfil do usuario")
    public void que_estou_na_tela_de_perfil_do_usuario(DataTable dataTable) {
        Utils.openSignInPage(driver);
        Map<String, String> fieldMap = dataTable.asMaps().get(0);
        Utils.fillForm(driver, fieldMap.get("email"), fieldMap.get("senha"));
        Utils.authenticate(driver);
        Utils.waitForRedirectToFinish(driver);
        String expectedURL = "http://multibags.1dt.com.br/shop/customer/dashboard.html";
        new WebDriverWait(driver, pageLoadTimeout).until(ExpectedConditions.urlToBe(expectedURL));
        WebElement menuContainer = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("customerAccount")));
        assertEquals(true, menuContainer.isDisplayed());
    }

    @Quando("clico no botao alterar senha")
    public void clico_no_botao_alterar_senha() {
        WebElement ul = driver.findElement(By.cssSelector("ul[class='nav nav-list']"));
        List<WebElement> links = ul.findElements(By.tagName("li"));
        for (WebElement element: links) {
            WebElement navLink = element.findElement(By.tagName("a"));
            if(navLink.getText().toLowerCase().equals("change password")) {
                String redirectURL = navLink.getAttribute("href");
                driver.navigate().to(redirectURL);
                break;
            }
        }
    }

    @Entao("abrira o formulario com os campos")
    public void abrira_o_formulario_com_os_campos() {
        Utils.waitForRedirectToFinish(driver);
        String expectedURL = "http://multibags.1dt.com.br/shop/customer/password.html";
        new WebDriverWait(driver, 30).until(ExpectedConditions.urlToBe(expectedURL));
    }

    @Entao("ao inserir os dados corretamente o botao de submissao sera habilitado")
    public void ao_inserir_os_dados_corretamente_o_botao_de_submissao_sera_habilitado(DataTable dataTable) {
        Map<String, String> fieldMap = dataTable.asMaps().get(0);
        WebElement elOldPwd = driver.findElement(By.id("currentPassword"));
        WebElement elNewPwd = driver.findElement(By.id("password"));
        WebElement elNewPwd2 = driver.findElement(By.id("checkPassword"));
        elOldPwd.sendKeys(fieldMap.get("senha_antiga"));
        elNewPwd.sendKeys(fieldMap.get("nova_senha"));
        elNewPwd2.sendKeys(fieldMap.get("nova_senha"));

        WebElement btnChangePwd = driver.findElement(By.id("submitChangePassword"));
        assertEquals(btnChangePwd.getAttribute("disabled"), null);
    }

    @Entao("devo efetuar a acao de clique")
    public void devo_efetuar_a_acao_de_clique() {
        WebElement btnChangePwd = driver.findElement(By.id("submitChangePassword"));
        btnChangePwd.click();
    }

    @Entao("devo receber a mensagem de sucesso")
    public void devo_receber_a_mensagem_de_sucesso() {
        Utils.waitForRedirectToFinish(driver);
        WebElement alertSuccess = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("store.success")));
        assertTrue(alertSuccess.isDisplayed());
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
