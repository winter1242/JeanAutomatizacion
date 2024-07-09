package pages;


public class CarritoComprasPage  extends BasePage{

 
    private String carritoNombreComp="(//span[@data-molecule-product-detail-name-span='true'])";
    private String cantCarritoComp="(//span[@data-molecule-quantity-und-value='true'])";
    private String precioCarritoComp="(//span[@data-molecule-summary-item-value='true'])[3]";

    public CarritoComprasPage() {
        super(driver);

    }
    public void esperaIniCIAL(int time){
        waitInicial(time);
    }
    public int cantProdSelec(){
        return cantValues(carritoNombreComp);
    }
    public String nombreProducto(int cant){
        return textElement(carritoNombreComp+"["+cant+"]");
    }
    public String cantProducto(int cant){
        return textElement(cantCarritoComp+"["+cant+"]");
    }

    public String precioCarrito(){
        return textElement(precioCarritoComp);
    }
    
    
}
