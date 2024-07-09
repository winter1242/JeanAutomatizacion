package pages;


public class ListProductosPage extends BasePage{
    private String agregarCarritoButton="(//span[text()='Agregar']/parent::button)";
    private String masUnidadesButton="(//button[contains(@class,'QuantitySelectorDefault_plus')])";
    private String nombreProducto="((//div[contains(@class,'QuantitySelectorDefault_defaultStyles__qlGCK')]/parent::div/parent::div/parent::article)/div[1]/a/div[1]/p)";
    private String precioProducto="((//div[contains(@class,'QuantitySelectorDefault_defaultStyles__qlGCK')]/parent::div/parent::div/parent::article)/div[1]/a/div[2]/div/div[2]/p)";
    private String cantProducto="(//p[contains(@class,'QuantitySelectorDefault_custom-quantity-selector__text')])";
    private String carritoComprasButton="//button[@aria-label='Cart toggle button']";
    

    public ListProductosPage() {
        super(driver);
    }

    public void clicOnAgregarCarritoButton(int numeroAleatorio){      
        clickElementButton(agregarCarritoButton+"["+numeroAleatorio+"]",2500);
    }
    public void clicOnCarritoCompras(){
        clickElement(carritoComprasButton);
    }
    public int cantProductos(){
        return cantValues(agregarCarritoButton);
    }
    public void esperaIniCIAL(int time){
        waitInicial(time);
    }
    public int cantProdSelec(){
        return cantValues(masUnidadesButton);
    }

    public void clicOnAgregarCantProdButton(int numeroAdic){
        clickElementButton(masUnidadesButton+"["+numeroAdic+"]",1000);
        //clickElementButton(masUnidadesButton+"["+numeroAdic+"]");

    }
    public String obtenerPrecioProduc(int precProduc){
        return textElement(precioProducto+"["+precProduc+"]");
    }
    public String obtenerCantProduc(int cantProd){
        return textElement(cantProducto+"["+cantProd+"]");
    }
    public String obtenerNombreProduc(int numProd){
        return textElement(nombreProducto+"["+numProd+"]");
    }
}
