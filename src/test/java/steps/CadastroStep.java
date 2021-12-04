package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class CadastroStep {
    private final String baseURL = "http://multibags.1dt.com.br";
    private final WebDriver driver = new ChromeDriver();

    @Dado("usuario acessou cadastro para criar nova conta")
    public void usuario_acessou_cadastro_para_criar_nova_conta() {
        driver.get(baseURL);
    }

    @Dado("usuario preencheu o formulario com informacoes validas")
    public void usuario_preencheu_o_formulario_com_informacoes_validas(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        String usuario = dataTable.asMaps().get(0).get("usuario");
        System.out.println("Usuario vindo da feature = " + usuario);
    }

    @Quando("usuario submeter os dados")
    public void usuario_submeter_os_dados() {
    }

    @Então("usuario o cadastro deve ter sido efetuado com sucesso")
    public void usuario_o_cadastro_deve_ter_sido_efetuado_com_sucesso() {
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}