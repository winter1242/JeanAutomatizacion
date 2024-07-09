package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pages.PrincipalPage;
import pages.CarritoComprasPage;
import pages.ListProductosPage;
import java.util.Collections;

public class ExitoSteps {
    //Paginas
    PrincipalPage landingPage=new PrincipalPage();
    ListProductosPage productPage=new ListProductosPage();
    CarritoComprasPage carroComPage=new CarritoComprasPage();

    private Random rand=new Random();
    List<String> listNombre= new ArrayList<>();
    List<String> listPrecio= new ArrayList<>();
    List<String> listCantidad= new ArrayList<>();

    List<String> listNombreCar= new ArrayList<>();
    List<String> listCantidadCar= new ArrayList<>();
    private int cantProduc;
    private int totalPrecio;
    

    @Given("I navigate to {string}")
    public void navigationLandingPage(String linkPage) {
        System.out.println("El link es : "+linkPage);
        landingPage.navigateToExitoPage(linkPage);
    }

    @And("Realizo clic en el Menu")
    public void clicMenu() {
        landingPage.clickOnMenuButton();
 
    }

    @And("Realiza clic en la categoria {string}")
    public void clicCategoria(String categoriaMenu) {
        landingPage.clicOnCatMenuButton(categoriaMenu);
 
    }
    @And("Realiza clic en la subcategoria {string}")
    public void clicSubCategoria(String subCategoriaMenu) {
        landingPage.clicOnSubCatMenuButton(subCategoriaMenu);
 
    }
    @When("Realizo clic en el boton Agregar")
    public void clicBotonAgregar() {
        productPage.esperaIniCIAL(3000);

        List<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            numeros.add(rand.nextInt(productPage.cantProductos())+1);
        }

        // Ordenar la lista en orden descendente
        Collections.sort(numeros, Collections.reverseOrder());
        for(int i=0;i<5;i++){
            //System.out.println("Es la iteracion "+(numeros.get(i)));
            //System.out.println("Texto es  "+productPage.obtenerNombreProduc(numeros.get(i)));
            productPage.clicOnAgregarCarritoButton(numeros.get(i));

            
        }
        
    }
    @And ("Agrego mas cantidades a cada producto")
    public void clicBotonAgregarCant(){
  
        productPage.esperaIniCIAL(3000);
        cantProduc=productPage.cantProdSelec();
        //Se agrega cantidad a cada boton
        for(int i=0;i<cantProduc;i++){
            for(int j=0;j<rand.nextInt(10)+1;j++){
                try{
                    productPage.clicOnAgregarCantProdButton(i+1);
                }
                catch(org.openqa.selenium.StaleElementReferenceException ex){
                    productPage.clicOnAgregarCantProdButton(i+1);
                }
                
            }
 
        }
        productPage.esperaIniCIAL(2000);
        //Guardando los atributos
        totalPrecio=0;
        for(int i=0;i<cantProduc;i++){
            listCantidad.add(productPage.obtenerCantProduc(i+1).replaceAll("[^\\d]", ""));
            listNombre.add(productPage.obtenerNombreProduc(i+1));
            listPrecio.add(productPage.obtenerPrecioProduc(i+1).replaceAll("[^\\d]", ""));
            System.out.println("El nombre del producto es : "+listNombre.get(i));
            System.out.println("La precio del producto es : "+listPrecio.get(i));
            System.out.println("La cantidad del producto es : "+listCantidad.get(i));
            totalPrecio=totalPrecio+((Integer.parseInt(listPrecio.get(i)))*Integer.parseInt(listCantidad.get(i)));
        }
        System.out.println("El total es :"+totalPrecio);
        Collections.sort(listCantidad);
        Collections.sort(listNombre);
        
    }
    @Then ("Ingreso al carrito de compras")
    public void clicBotonCarrito(){
        productPage.clicOnCarritoCompras();
    }
    @And ("Verifico los nombres de los productos")
    public void verificarNombreProducto(){
        for(int i=0;i<cantProduc;i++){
            listNombreCar.add(carroComPage.nombreProducto(i+1));
            System.out.println("Los nombres de los productos en la respuesta es : "+carroComPage.nombreProducto(i+1));
        }
        Collections.sort(listNombreCar);
        Assert.assertEquals(listNombre, listNombreCar);
        
    }
    @And ("Verifico el precio total de los productos")
    public void verificarPrecioTotal(){
        
        Assert.assertEquals(Integer.parseInt(carroComPage.precioCarrito().replaceAll("[^\\d]", "")), totalPrecio);
    }
    @And ("Verificar el numero de productos agregados en el carrito")
    public void verificarNumeroProductos(){
        Assert.assertEquals(carroComPage.cantProdSelec(), cantProduc);
        
    }
    @And ("Verificar la cantidad de cada producto agregado")
    public void verificarCantidadProductos(){
        for(int i=0;i<cantProduc;i++){
            listCantidadCar.add(carroComPage.cantProducto(i+1));
        }
        Collections.sort(listCantidadCar);
        Assert.assertEquals(listCantidadCar, listCantidad);
    }
}
