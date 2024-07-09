@Shopping
Feature: Shopping cart

    Scenario: Los productos se agregan correctamente al carrito de compras
        Given I navigate to "https://www.exito.com/"
        And Realizo clic en el Menu
        And Realiza clic en la categoria "Deportes"
        And Realiza clic en la subcategoria "Carpas"
        When Realizo clic en el boton Agregar 
        And Agrego mas cantidades a cada producto
        Then Ingreso al carrito de compras
        And Verifico los nombres de los productos
        And Verifico el precio total de los productos
        And Verificar el numero de productos agregados en el carrito
        And Verificar la cantidad de cada producto agregado