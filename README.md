# BancoECI

### Pagina Web 
* [BancoECI](https://proyecto-cvds-banco.herokuapp.com/login.xhtml)

[![CircleCI](https://circleci.com/gh/CAndresRa/Laboratorio8-CVDS.svg?style=svg)](https://circleci.com/gh/CAndresRa/Laboratorio8-CVDS)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9f948df073824e4dbf0787fab38b9207)](https://www.codacy.com/gh/BancoIniciativasECI/BancoECI?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=BancoIniciativasECI/BancoECI&amp;utm_campaign=Badge_Grade)

## Integrantes 
### CVDS 2020-1
* Daniel Alfonso, Team.
* Nicolas Aguilera, Team.
* Ernesto Camacho, Team.
* Carlos Andrés Ramírez, Scrum Master.
* Oscar David Ospin, Product Owner.
* Julian Mauricio Briceño, Product Owner.

# Descripcion Del Producto

## Descripcion General
El **Banco de Iniciativas de Proyectos** es una herramienta desarrollada con la comunidad de la Escuela Colombiana de ingenieria en mente, la cual tiene el objetivo de facilitar el registro y desarrollo de iniciativas de proyectos para la comunidad. La herramienta facilita la participación, de la comunidad de la escuela, para el proceso de retroalimentacion y desarrollo del proyecto; adicional a eso, el perosnal Academico puede integrar estudiantes de diferentes ambitos, para el desarrollo de Iniciativas.

## Manual de Usuario
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/VistaLogin.PNG)
La Aplicacion maneja 3 tipos de usuario y una vista publica, los tipos de usuario son:
* Administrador, el cual cuenta con los siguientes servicios:
  - Modificar Roles de Otros Usuarios 
  - Consulta de Iniciativas 
  - Registrar Usuarios
  - Asociar Iniciativas
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/InicioAdmin.PNG)
Daremos inicio con el usuario del tipo Administrador, despues de hacer inicio de sesión la aplicacion lleva al administrador a esta pagina.
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
En esta vista estan los servicios que la aplicacion ofrece a los administradores, la tabla que se observa contiene los usuairos de la aplicacion.
Para Actualizar el rol de un usuario primero lo seleccionamos, y posterior a eso damos click en el boton de "actualizar Rol"
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/SeleccionUsuario.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/actualizarRol.png)
Para hacer un Registro de usuario damos Click en el boton "registrar Usuario"
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/registrarUsuario.PNG)
Para consultar el reporte de Iniciativas registradas, damos click en el botón "Consultar reporte de iniciativas", posteriormente observamos las iniciativas y un grafico donde nos muestra un reporte de iniciativas por area
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/consultarReporteIniciativasAdmin.PNG)
Para asociar iniciativas, damos click en el botón Asociar Iniciativas, nos llevara a una vista donde tendremos la posibilidad de seleccionar varias iniciativas y darle en el boton Asociar para relacionarlas o regresar a la vista anterior 
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/asociarIniciativas.PNG)
* Proponente, el cual cuenta con los siguientes servicios: 
  - Registrar iniciativas.
  - Consultar reporte de iniciativas. 
  
  La vista de proponente, nos dirige al registro de iniciativas 
 ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/registrarIniciativas.PNG)
 Podemos consultar las iniciativas propuestas dando click en el boton "Consultar reporte de iniciativas" 
 ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/consultarReporteIniciativasPMO.PNG)
 * PMO, el cual cuenta con los siguientes servicios:
  - Consultar iniciativas 
  - Cambiar estado de iniciativas 
  
  La vista PMO nos dirige a una pagina con la tabla de iniciativas propuestas, para cambiar el estado de una iniciativa, la seleccionamos y damos click en "Cambiar Estado". Tambien podemos consultar el reporte iniciativas, dando click en el boton "Consultar reporte de iniciativas"
  ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/cambioEstadoIniciativa.PNG)
  ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/cambioEstado.png)
  
  * Publico, el cual cuenta con los siguientes servicios: 
    - Consultar iniciativas
    - Comentar las iniciativas 
    - Busqueda de iniciativas por palabras clave
    
    Para acceder a la vista publica, en el inicio de sesión damos click en el botón "Consultar Iniciativas" el cual nos dirige a una vista donde podremos filtrar las iniciativas buscando por palabras clave.
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/VistaLogin.PNG)
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/publicoConsultarIniciativasPalabras.PNG)
      
      En el campo de busqueda ingresamos las palabras claves separadas por coma 
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/busquedaPorPalabras.PNG)
      
      Damos click en "Buscar" y obtenemos el resultado. 
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/resultadoBusquedaPalabras.PNG)
      
      Tambien podemos seleccionar una iniciativa para comentarla o consultar los comentarios de una iniciativa. Seleccionamos la iniciativa, damos click en "Comentar iniciativa" 
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/publicoConsultarIniciativasPalabras.PNG)
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/comentariosIniciativa.PNG)
     
     Podemos consultar los comentarios de las iniciativas, dando click en "Consultar comentarios" 
     ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/consultarComentarios.PNG)
     
