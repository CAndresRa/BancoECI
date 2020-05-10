# BancoECI

### Pagina Web 
* [BancoECI](https://proyecto-cvds-banco.herokuapp.com/login.xhtml)

[![CircleCI](https://circleci.com/gh/CAndresRa/Laboratorio8-CVDS.svg?style=svg)](https://circleci.com/gh/CAndresRa/Laboratorio8-CVDS)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9f948df073824e4dbf0787fab38b9207)](https://www.codacy.com/gh/BancoIniciativasECI/BancoECI?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=BancoIniciativasECI/BancoECI&amp;utm_campaign=Badge_Grade)

## Integrantes 
### CVDS 2020-1
* Daniel Alfonso, Team.
* Nicolás Aguilera, Team.
* Ernesto Camacho, Team.
* Carlos Andrés Ramírez, Scrum Master.
* Oscar David Ospina, Product Owner.
* Julián Mauricio Velasco, Product Owner.

# Descripción Del Producto

## Descripción General
El **Banco de Iniciativas de Proyectos** es una herramienta desarrollada con la comunidad de la Escuela Colombiana de ingeniería en mente, la cual tiene el objetivo de facilitar el registro y desarrollo de iniciativas de proyectos para la comunidad. La herramienta facilita la participación, de la comunidad de la escuela, para el proceso de retroalimentación y desarrollo del proyecto; adicional a eso, el personal Académico puede integrar estudiantes de diferentes ámbitos, para el desarrollo de Iniciativas.

## Manual de Usuario
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/VistaLogin.PNG)
La Aplicación maneja 3 tipos de usuario y una vista pública, los tipos de usuario son:
* Administrador, el cual cuenta con los siguientes servicios:
  - Modificar Roles de Otros Usuarios 
  - Consulta de Iniciativas
  
    - Realizar un comentario a una iniciativa
    - Votar con me gusta  o no me gusta a una iniciativa
    
  - Registrar Usuarios
  - Asociar Iniciativas
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/InicioAdmin.PNG)
Daremos inicio con el usuario del tipo Administrador, después de hacer inicio de sesión la aplicación lleva al administrador a esta página. 
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
En esta vista están los servicios que la aplicación ofrece a los administradores, la tabla que se observa contiene los usuarios de la aplicación. También podremos exportar la tabla de usuarios a formato PDF y Excel para mayor facilidad.
Para Actualizar el rol de un usuario primero lo seleccionamos, y posterior a eso damos click en el botón de "actualizar Rol"
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/SeleccionUsuario.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/actualizarRol.png)
Para hacer un Registro de usuario damos Click en el botón "registrar Usuario"
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/registrarUsuario.PNG)
Para consultar el reporte de Iniciativas registradas, damos click en el botón "Consultar reporte de iniciativas", posteriormente observamos las iniciativas y un gráfico donde nos muestra un reporte de iniciativas por área
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/consultarReporteIniciativasAdmin.PNG)
Para asociar iniciativas, damos click en el botón Asociar Iniciativas, nos llevara a una vista donde tendremos la posibilidad de seleccionar varias iniciativas y darle en el botón Asociar para relacionarlas o regresar a la vista anterior 
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaAdmin.PNG)
![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/asociarIniciativas.PNG)
* Proponente, el cual cuenta con los siguientes servicios: 
  - Registrar iniciativas.
  - Consultar reporte de iniciativas. 
  - Modificar sus iniciativas. 
  - Visualizar sus inicitivas. 
  - Comentar Iniciativa 
  - Dar me gusta o no me gusta a iniciativa (Votar) 
  
  La vista de proponente, nos dirige a visualizar sus iniciativas 
 ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/vistaProponente.PNG)
 
  Podremos registrar una nueva iniciativa dando click en el boton "Registrar iniciativa" nos llevara a la siguiente pagina 
 ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/registrarIniciativas.PNG)
  Para modificar una iniciativa podremos seleccionarla primero como se muestra y despues dar click en modificar iniciativa, llenamos los  campos que queremos modificar y damos click a modificar, podremos modificar nombre, descripción o palabras clave (No es obligatorio modificar todo) 
  ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/seleccionIniciativa.PNG)
  ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/modificarIniciatia.PNG)
 Podemos consultar las iniciativas propuestas dando click en el botón "Consultar reporte de iniciativas" 
 ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/consultarReporteIniciativasPMO.PNG)
 
 * PMO, el cual cuenta con los siguientes servicios:
  - Consultar iniciativas 
  - Cambiar estado de iniciativas 
  - Comentar Iniciativa 
  - Dar me gusta o no me gusta a iniciativa (Votar) 
  
  La vista PMO nos dirige a una página con la tabla de iniciativas propuestas la cual podremos descargar igualmente, para cambiar el estado de una iniciativa, la seleccionamos y damos click en "Cambiar estado de iniciativa". También podemos consultar el reporte iniciativas, dando click en el botón "Consultar reporte de iniciativas"
  ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/cambioEstadoIniciativa.PNG)
  ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/cambioEstado.png)
  
  Adicionalmente como mencionamos los usuarios anteriores desde la pagina Consultar Reporte de inicitivas, podran selecciona cualquiera de estas iniciativas y expresar su afinidad. Podran dar me gusta, comentar la iniciativa y ver los detalles de dicha iniciativa. 
   ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/consultarReporteIniciativasPMO.PNG)
   Seleccionamos la iniciativa para comentar 
    ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/seleccionIniciativaComentar.PNG)
    Damos click en "Comentar iniciativa", aqui podremos darle me gusta quitar este, hacer un comentario y damos click en "Aportar a iniciativa" o podemos regresar
     ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/aportarIniciativaUsuario.PNG)
     Damos click en "Ver detalle y podremos ver la iniciativa, el numero de votos que esta tiene, los comentarios realizados y las iniciativas relacionadas 
     ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/verDetalleUsuarios.PNG)
  
  
  
  * Publico, el cual cuenta con los siguientes servicios: 
    - Consultar iniciativas
    - Comentar las iniciativas 
    - Búsqueda de iniciativas por palabras clave
    
    Para acceder a la vista pública, en el inicio de sesión damos click en el botón "Consultar Iniciativas" el cual nos dirige a una vista donde podremos filtrar las iniciativas buscando por palabras clave.
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/VistaLogin.PNG)
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/publicoConsultarIniciativasPalabras.PNG)
      
      En el campo de búsqueda ingresamos las palabras claves separadas por coma 
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/buscarPorPalabras.PNG)
      
      Damos click en "Buscar" y obtenemos el resultado. 
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/resultadoBusquedaPalabras.PNG)
      
      También podemos seleccionar una iniciativa para comentarla o consultar los comentarios de una iniciativa. Seleccionamos la iniciativa, damos click en "Comentar iniciativa" 
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/publicoConsultarIniciativasPalabras.PNG)
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/seleccionarIniciativaPublico.PNG)
      ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/comentarIniciativaPublico.PNG)
     
     Podemos consultar los comentarios de las iniciativas, dando click en "Consultar comentarios" 
     ![](https://github.com/BancoIniciativasECI/BancoECI/blob/master/modelos/Recursos/verDetallePublicos.PNG)
     
