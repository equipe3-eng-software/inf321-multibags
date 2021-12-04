package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static org.junit.Assert.*;


public class CadastroStep {
    private final String baseURL = "http://multibags.1dt.com.br/shop/customer/registration.html";
    private final WebDriver driver = new ChromeDriver();

    private final String _nome = "firstName";
    private final String _sobrenome = "lastName";
    private final String _pais = "registration_country";
    private final String _estado = "hidden_zones";
    private final String _email = "emailAddress";
    private final String _senha = "password";
    private final String _senha2 = "passwordAgain";
    private final String btnClassName = "login-btn";

    @Dado("usuario acessou cadastro para criar nova conta")
    public void usuario_acessou_cadastro_para_criar_nova_conta() {
        driver.get(baseURL);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(_nome)));
    }

    @Dado("usuario preencheu o formulario com informacoes validas")
    public void usuario_preencheu_o_formulario_com_informacoes_validas(DataTable dataTable) {
        Map<String, String> fieldMap = dataTable.asMaps().get(0);
        String nome = fieldMap.get("nome");
        String sobrenome = fieldMap.get("sobrenome");
        String pais = fieldMap.get("pais");
        String estado = fieldMap.get("estado");
        String email = fieldMap.get("email");
        String senha = fieldMap.get("senha");

        WebElement elInputNome = driver.findElement(By.id(_nome));
        WebElement elInputSobrenome = driver.findElement(By.id(_sobrenome));
        WebElement elSelectPais = driver.findElement(By.id(_pais));
        Select selectPais = new Select(elSelectPais);
        WebElement elInputEstado = driver.findElement(By.id(_estado));
        WebElement elInputEmail = driver.findElement(By.id(_email));
        WebElement elInputSenha = driver.findElement(By.id(_senha));
        WebElement eliInputSenha2 = driver.findElement(By.id(_senha2));

        elInputNome.sendKeys(nome);
        elInputSobrenome.sendKeys(sobrenome);
        selectPais.selectByValue(pais);
        elInputEstado.sendKeys(estado);
        elInputEmail.sendKeys(email);
        elInputSenha.sendKeys(senha);
        eliInputSenha2.sendKeys(senha);
    }

    @Quando("usuario submeter os dados")
    public void usuario_submeter_os_dados() {
        WebElement btnCriarConta = driver.findElement(By.className(btnClassName));
        btnCriarConta.click();
    }

    @Então("usuario o cadastro deve ter sido efetuado com sucesso")
    public void usuario_o_cadastro_deve_ter_sido_efetuado_com_sucesso() {
        WebElement menuContainer = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("customerAccount")));
        assertEquals(true, menuContainer.isDisplayed());
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}