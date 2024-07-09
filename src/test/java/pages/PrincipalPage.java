package pages;
 
public class PrincipalPage extends BasePage {
 
    private String menuButton="//button[@aria-label='Collapse menu']";
    private String cetMenuButton="//p[text()='%s']/parent::div";
    private String subCatMenuButton="//a[text()='%s']";

 

    public PrincipalPage() {
        super(driver);
    }
 
    public void navigateToExitoPage(String linkPage) {
        navigateTo(linkPage);
        
    }

    public void clickOnMenuButton(){
        clickElement(menuButton);
    }

    public void clicOnCatMenuButton(String categoriaMenu){
        String xpathSection = String.format(cetMenuButton, categoriaMenu);
        clickElement(xpathSection);
    }

    public void clicOnSubCatMenuButton(String subCategoriaMenu){
        String xpathSection = String.format(subCatMenuButton, subCategoriaMenu);
        clickElement(xpathSection);
    }

}