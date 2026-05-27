---
title: "Reporte de Aseguramiento de Calidad - SauceDemo"
subtitle: "Validacion funcional y automatizacion con Selenium WebDriver"
author: "Integrantes: [Completar nombres del equipo]"
date: "26 de mayo de 2026"
lang: es-MX
---

# Portada

**Nombre del proyecto:** Reporte de aseguramiento de calidad para SauceDemo  
**Sistema bajo prueba:** SauceDemo  
**Materia:** Aseguramiento de la Calidad del Software  
**Integrantes del equipo:** [Completar nombres del equipo]  
**Docente:** [Completar nombre del docente]  
**Grupo:** [Completar grupo]  
**Fecha:** 26 de mayo de 2026  

# Introduccion

El presente reporte documenta el proceso de aseguramiento de calidad aplicado a SauceDemo, una aplicacion web demo que simula el flujo principal de una tienda en linea. El trabajo se enfoca en validar funcionalidades criticas como autenticacion, inventario, carrito de compras y checkout, tomando como base una matriz funcional previamente definida y una suite automatizada con Java, Selenium WebDriver y TestNG.

El proyecto parte de un enfoque incremental: primero se reviso la matriz de pruebas, despues se comparo contra los scripts existentes, posteriormente se amplio la automatizacion para cubrir la mayor cantidad posible de escenarios y finalmente se ejecuto la suite completa para obtener evidencia objetiva. Esta forma de trabajo permite mantener trazabilidad entre lo planeado, lo automatizado y lo observado durante la ejecucion.

Aunque SauceDemo es una aplicacion de practica, representa flujos frecuentes en sistemas de comercio electronico. Por ello, validar su comportamiento permite aplicar criterios reales de aseguramiento de calidad: confirmacion de rutas felices, pruebas negativas, manejo de errores, validacion de reglas de negocio y documentacion de defectos.

# Objetivos

## Objetivo general

Disenar, documentar y automatizar un conjunto de pruebas funcionales para validar la calidad de SauceDemo, identificando defectos en modulos clave como autenticacion, inventario, carrito de compras y checkout.

## Objetivos especificos

- Revisar la matriz funcional existente y clasificar los casos por modulo.
- Comparar los casos documentados contra la automatizacion disponible en el repositorio.
- Completar la automatizacion pendiente utilizando Java, Selenium WebDriver, TestNG y Page Object Model.
- Ejecutar la suite completa y registrar resultados de casos aprobados, fallidos y bloqueados.
- Documentar defectos relevantes asociados a usuarios con comportamiento problematico simulado.
- Generar evidencia tecnica que pueda anexarse al reporte y utilizarse posteriormente en una presentacion.

# Justificacion

En una tienda en linea, errores en autenticacion, inventario, carrito o checkout pueden afectar directamente la experiencia del usuario y la confiabilidad del proceso de compra. Por esta razon, resulta necesario validar que los flujos principales funcionen correctamente antes de considerar una funcionalidad como estable.

El uso de pruebas automatizadas aporta repetibilidad y rapidez. En este proyecto se eligio Selenium WebDriver porque permite interactuar con la aplicacion de forma similar a un usuario real, validando elementos visibles, navegacion, formularios, botones y mensajes de error. TestNG permite organizar los casos, ejecutar la suite y generar reportes de resultados.

La matriz funcional funciona como contrato de validacion: define los escenarios esperados, los datos de prueba, los resultados esperados y los estados observados. La automatizacion se alinea con esa matriz para asegurar que cada script tenga un proposito claro y medible.

# Descripcion del sistema

## Nombre del sistema

SauceDemo

## Tipo de sistema

Aplicacion web demo orientada a simular una tienda en linea. Se utiliza comunmente para practicas de pruebas funcionales, automatizacion de UI y validacion de flujos de compra.

## Usuarios considerados

| Usuario | Proposito dentro de las pruebas |
|---|---|
| `standard_user` | Usuario base para validar comportamiento esperado. |
| `locked_out_user` | Usuario bloqueado para pruebas negativas de autenticacion. |
| `problem_user` | Usuario con defectos simulados en inventario, carrito y checkout. |
| `error_user` | Usuario con errores simulados para validar fallos funcionales y manejo de excepciones. |

## Funcionalidades principales

- Inicio de sesion con usuarios validos e invalidos.
- Visualizacion del inventario de productos.
- Ordenamiento de productos por nombre y precio.
- Agregado y remocion de productos del carrito.
- Visualizacion del carrito de compras.
- Captura de datos personales para checkout.
- Confirmacion final de compra.

## Problema que resuelve

SauceDemo permite simular el proceso basico de compra de una tienda digital. Aunque no procesa compras reales, sirve como entorno controlado para comprobar si una aplicacion permite autenticar usuarios, consultar productos, administrar un carrito y finalizar una orden.

## Flujo principal del usuario

1. El usuario ingresa a `https://www.saucedemo.com/`.
2. Captura usuario y contrasena.
3. Accede al inventario.
4. Ordena o revisa productos disponibles.
5. Agrega productos al carrito.
6. Abre el carrito y revisa los productos seleccionados.
7. Inicia checkout.
8. Captura nombre, apellido y codigo postal.
9. Revisa el resumen de compra.
10. Finaliza la compra y visualiza la confirmacion.

# Plan de pruebas

## Alcance

El alcance cubre pruebas funcionales de los modulos de autenticacion, inventario, carrito de compras y checkout. Se consideran escenarios positivos, negativos, de defectos conocidos y de casos bloqueados por precondiciones no cumplidas.

## Modulos probados

| Modulo | Funcionalidades validadas |
|---|---|
| Autenticacion | Login exitoso, password incorrecto y usuario bloqueado. |
| Inventario y filtros | Ordenamientos por precio y nombre con distintos usuarios. |
| Carrito de compras | Agregar, remover y validar contador del carrito. |
| Checkout | Compra completa, campos vacios, captura de datos y errores simulados. |

## Modulos no probados

No se probaron aspectos no disponibles o fuera del alcance de SauceDemo, como registro de usuarios, integracion con pasarelas de pago, persistencia en base de datos, API interna, seguridad avanzada, rendimiento, accesibilidad formal o compatibilidad completa entre navegadores.

## Tipos de prueba aplicados

- Pruebas funcionales positivas.
- Pruebas funcionales negativas.
- Pruebas de validacion de formularios.
- Pruebas de manejo de errores.
- Pruebas de regresion automatizada.
- Pruebas de defectos conocidos.
- Casos bloqueados documentados como `Skipped`.

## Ambiente de pruebas

| Elemento | Valor |
|---|---|
| Sistema bajo prueba | SauceDemo |
| URL | `https://www.saucedemo.com/` |
| Lenguaje | Java |
| Automatizacion | Selenium WebDriver |
| Framework | TestNG |
| Build tool | Maven |
| Patron | Page Object Model |
| Navegador | Chrome 148 |
| Driver | ChromeDriver 148.0.7778.178 |
| Sistema operativo | macOS 15.7.4 |
| JDK | OpenJDK 25.0.2 |

## Herramientas utilizadas

- Java para implementar los scripts de prueba.
- Selenium WebDriver para interactuar con la interfaz web.
- TestNG para organizar y ejecutar la suite.
- Maven para compilar y ejecutar pruebas.
- WebDriverManager para resolver ChromeDriver.
- Page Object Model para separar acciones de pagina y casos de prueba.
- Reportes Surefire/TestNG como evidencia de ejecucion.

# Casos de prueba

La matriz funcional fue revisada y alineada con la suite automatizada. La cobertura final contempla 32 casos automatizados o documentados como bloqueados.

## Resumen por modulo

| Modulo | Total | Pass esperado | Fail esperado | Blocked |
|---|---:|---:|---:|---:|
| Autenticacion | 3 | 3 | 0 | 0 |
| Carrito | 8 | 4 | 4 | 0 |
| Filtros de inventario | 16 | 7 | 7 | 2 |
| Checkout | 5 | 2 | 3 | 0 |
| **Total** | **32** | **16** | **14** | **2** |

## Tabla de trazabilidad matriz vs automatizacion

| ID | Modulo | Estado matriz | Automatizado | Metodo / observacion |
|---|---|---|---|---|
| P-AUTH-01P | Autenticacion | Pass | Si | `AuthTest.pAuth01PLoginWithValidCredentials` |
| P-AUTH-01N | Autenticacion | Pass | Si | `AuthTest.pAuth01NLoginWithWrongPassword` |
| P-AUTH-02N | Autenticacion | Pass | Si | `AuthTest.pAuth02NLoginWithLockedUser` |
| P-CART-05P | Carrito | Pass | Si | `CartTest.pCart05PAddAllProductsWithStandardUser` |
| P-CART-05N | Carrito | Fail | Si | Defecto con `problem_user`; solo agrega 3 de 6 productos. |
| P-CART-01P | Carrito | Pass | Si | `CartTest.pCart01PAddSpecificProduct` |
| P-CART-02P | Carrito | Pass | Si | `CartTest.pCart02PRemoveSpecificProductFromInventory` |
| P-CART-03P | Carrito | Fail | Si | Defecto con `error_user`; no permite agregar cuarto producto. |
| P-CART-04P | Carrito | Fail | Si | Defecto al remover producto desde inventario con `error_user`. |
| P-CART-06P | Carrito | Pass | Si | `CartTest.pCart06PRemoveSpecificProductFromCartWithErrorUser` |
| P-CART-07P | Carrito | Fail | Si | Defecto al remover desde detalle con `error_user`. |
| P-FILTER-01P | Filtros | Pass | Si | Orden precio low to high con `standard_user`. |
| P-FILTER-01N | Filtros | Fail | Si | `problem_user` no ordena por precio ascendente. |
| P-FILTER-02P | Filtros | Pass | Si | Orden precio high to low con `standard_user`. |
| P-FILTER-02N | Filtros | Fail | Si | `problem_user` no ordena por precio descendente. |
| P-FILTER-03P | Filtros | Pass | Si | Orden nombre A to Z con `standard_user`. |
| P-FILTER-03N | Filtros | Fail | Si | `problem_user` no ejecuta ordenamiento A to Z. |
| P-FILTER-04P | Filtros | Pass | Si | Orden nombre Z to A con `standard_user`. |
| P-FILTER-04N | Filtros | Fail | Si | `problem_user` no ejecuta ordenamiento Z to A. |
| P-FILTER-05P | Filtros | Fail | Si | `error_user` no ordena low to high. |
| P-FILTER-05N | Filtros | Pass | Si | El sistema maneja excepcion con alerta controlada. |
| P-FILTER-06P | Filtros | Fail | Si | `error_user` no ordena high to low. |
| P-FILTER-06N | Filtros | Pass | Si | El sistema maneja excepcion con alerta controlada. |
| P-FILTER-07P | Filtros | Blocked | Si | Caso marcado `Skipped` por precondicion no cumplida. |
| P-FILTER-07N | Filtros | Blocked | Si | Caso marcado `Skipped` por precondicion no cumplida. |
| P-FILTER-08P | Filtros | Fail | Si | `error_user` no ordena Z to A. |
| P-FILTER-08N | Filtros | Pass | Si | El sistema maneja excepcion con alerta controlada. |
| P-CHOUT-01P | Checkout | Pass | Si | Checkout completo con `standard_user`. |
| P-CHOUT-02N | Checkout | Pass | Si | Error al intentar continuar con campos vacios. |
| P-CHOUT-03P | Checkout | Fail | Si | `problem_user` altera datos capturados. |
| P-CHOUT-04P | Checkout | Fail | Si | `error_user` no permite capturar apellido correctamente. |
| P-CHOUT-05N | Checkout | Fail | Si | `error_user` permite continuar sin apellido. |

# Explicacion de la automatizacion

La automatizacion fue implementada con Java, Selenium WebDriver, TestNG y Maven. Se utilizo el patron Page Object Model para separar las acciones de cada pagina de las validaciones de los casos de prueba. Esta separacion permite que los tests sean mas claros y que los cambios en localizadores o acciones se mantengan dentro de las clases Page.

## Organizacion por capas

```text
src/test/java
├── Base
│   ├── BasePage.java
│   └── BaseTest.java
├── Pages
│   ├── AuthPage.java
│   ├── CartPage.java
│   ├── CheckoutPage.java
│   └── InventoryPage.java
├── Tests
│   ├── AuthTest.java
│   ├── CartTest.java
│   ├── CheckoutTest.java
│   └── InventoryTest.java
└── Utils
    └── TestData.java
```

## Page Object Model

Cada Page Object representa una pantalla o modulo de SauceDemo:

- `AuthPage`: captura credenciales, ejecuta login y valida errores de autenticacion.
- `InventoryPage`: selecciona filtros, obtiene nombres y precios de productos, y maneja alertas.
- `CartPage`: agrega/remueve productos por nombre, valida contador y revisa productos en carrito.
- `CheckoutPage`: captura datos personales, valida mensajes de error y confirma orden.

## Base de pruebas

`BaseTest` concentra la configuracion comun:

```java
@BeforeMethod(alwaysRun = true)
public void setUp()
{
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(createChromeOptions());
    driver.manage().window().setSize(new Dimension(1366, 900));
    driver.get(TestData.BASE_URL);
}
```

Esta base evita duplicacion y garantiza que cada caso inicie con navegador limpio, lo cual reduce dependencias entre pruebas.

## Esperas explicitas

`BasePage` centraliza esperas con `WebDriverWait`:

```java
protected WebElement findElement(By locator)
{
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}
```

Tambien se agregaron validaciones defensivas para elementos visibles, elementos presentes y alertas. Esto permite que los casos negativos no fallen por errores tecnicos, sino por assertions relacionadas con el resultado esperado.

## Assertions

Las assertions comparan comportamiento esperado contra comportamiento observado. En los casos donde la matriz indica `Fail`, el test mantiene la expectativa funcional correcta; por eso el resultado automatizado falla y confirma el defecto.

Ejemplo:

```java
Assert.assertEquals(cartPage.getCartBadgeCount(), TestData.ALL_PRODUCTS.size());
```

Si `problem_user` solo agrega 3 productos de 6, la assertion falla y confirma el defecto documentado.

## Relacion entre matriz y scripts

Cada metodo de prueba incluye el ID de matriz en un comentario y su nombre resume el escenario. Por ejemplo:

```java
@Test(priority = 2) // P-CART-05N
public void pCart05NAddAllProductsWithProblemUser()
```

Esto permite rastrear cada caso desde la matriz manual hasta su implementacion automatizada y su resultado en el reporte TestNG.

# Evidencia

## Evidencia documental

| Evidencia | Ubicacion |
|---|---|
| Matriz funcional original | `Matriz pruebas funcionales - Hoja 1.pdf` |
| Reporte de preparacion y trazabilidad | `docs/preparacion_qa_saucedemo.md` |
| Reporte HTML TestNG | `target/surefire-reports/index.html` |
| Reporte ejecutable TestNG | `target/surefire-reports/emailable-report.html` |
| Resumen Surefire | `target/surefire-reports/TestSuite.txt` |
| Resultados XML | `target/surefire-reports/testng-results.xml` |

## Resultados de ejecucion

La suite fue ejecutada con:

```bash
mvn test
```

Resultado final:

| Resultado | Total |
|---|---:|
| Casos ejecutados | 32 |
| Aprobados | 16 |
| Fallidos | 14 |
| Bloqueados / omitidos | 2 |
| Errores tecnicos | 0 |
| Tiempo total | 59.35 s |

Los 14 fallos son esperados de acuerdo con la matriz funcional, ya que representan defectos conocidos del sistema bajo prueba. Los 2 casos omitidos corresponden a escenarios bloqueados por precondiciones no cumplidas.

## Fragmentos relevantes de codigo

### Datos centralizados

```java
public static final String STANDARD_USER = "standard_user";
public static final String PROBLEM_USER = "problem_user";
public static final String ERROR_USER = "error_user";
public static final List<String> ALL_PRODUCTS = List.of(...);
```

### Validacion de ordenamiento

```java
List<Double> prices = inventoryPage.getProductPrices();
List<Double> expected = new ArrayList<>(prices);
expected.sort(Comparator.naturalOrder());
Assert.assertEquals(prices, expected);
```

### Caso bloqueado

```java
throw new SkipException("Caso bloqueado: no es posible cumplir la precondicion...");
```

## Capturas y reportes

Para evidencia visual, se debe abrir `target/surefire-reports/index.html` o `target/surefire-reports/emailable-report.html` y anexar capturas de:

- Resumen general de ejecucion.
- Lista de pruebas fallidas.
- Lista de pruebas omitidas.
- Detalle de defectos en checkout, carrito e inventario.

El reporte TestNG generado por la suite constituye la evidencia principal de ejecucion.

# Defectos encontrados

## Carrito de compras

| ID | Usuario | Defecto observado |
|---|---|---|
| P-CART-05N | `problem_user` | No se agregan todos los productos al carrito; solo se reflejan 3 de 6. |
| P-CART-03P | `error_user` | No permite agregar un cuarto producto despues de tener 3 productos. |
| P-CART-04P | `error_user` | No remueve correctamente un producto desde inventario. |
| P-CART-07P | `error_user` | No remueve correctamente un producto desde el detalle. |

## Filtros de inventario

| ID | Usuario | Defecto observado |
|---|---|---|
| P-FILTER-01N | `problem_user` | No ordena precios de menor a mayor. |
| P-FILTER-02N | `problem_user` | No ordena precios de mayor a menor. |
| P-FILTER-03N | `problem_user` | No ejecuta ordenamiento de nombre A a Z. |
| P-FILTER-04N | `problem_user` | No ejecuta ordenamiento de nombre Z a A. |
| P-FILTER-05P | `error_user` | No aplica ordenamiento low to high. |
| P-FILTER-06P | `error_user` | No aplica ordenamiento high to low. |
| P-FILTER-08P | `error_user` | No aplica ordenamiento Z to A. |

## Checkout

| ID | Usuario | Defecto observado |
|---|---|---|
| P-CHOUT-03P | `problem_user` | Al capturar datos personales, el sistema altera el valor del nombre. |
| P-CHOUT-04P | `error_user` | No permite capturar correctamente el apellido. |
| P-CHOUT-05N | `error_user` | Permite continuar el flujo aunque falte el apellido. |

# Casos bloqueados

| ID | Motivo |
|---|---|
| P-FILTER-07P | No es posible colocar el listado en un orden distinto por falla previa del filtro con `error_user`. |
| P-FILTER-07N | No es posible cumplir la precondicion necesaria para validar Name A to Z con `error_user`. |

# Conclusiones

Se valido el flujo funcional principal de SauceDemo en autenticacion, inventario, carrito de compras y checkout. La suite automatizada quedo alineada con la matriz de pruebas, cubriendo 32 casos con trazabilidad directa entre ID de matriz, metodo de prueba y resultado esperado.

La automatizacion confirma que el usuario `standard_user` cumple los flujos principales de autenticacion, carrito, filtros y checkout. Tambien confirma defectos intencionalmente simulados en `problem_user` y `error_user`, principalmente en operaciones de carrito, filtros de inventario y captura de datos personales durante checkout.

El uso de Page Object Model permitio organizar el proyecto por capas y reducir duplicacion. La incorporacion de `BaseTest`, `BasePage` y `TestData` mejoro la mantenibilidad de la suite y facilito que los casos se alinearan con la matriz funcional.

Como actividades pendientes, se recomienda anexar capturas visuales del reporte TestNG en la entrega final, mantener actualizada la trazabilidad cuando cambie la matriz y, si se requiere una ejecucion verde para integracion continua, separar los defectos conocidos en una suite especial o marcarlos con grupos de TestNG.

# Anexos

## Comandos utilizados

```bash
mvn -q -DskipTests test
mvn test
```

## Archivos principales del proyecto

| Archivo | Proposito |
|---|---|
| `src/test/java/Base/BasePage.java` | Utilidades comunes de Selenium y esperas explicitas. |
| `src/test/java/Base/BaseTest.java` | Configuracion comun de WebDriver y flujos reutilizables. |
| `src/test/java/Utils/TestData.java` | Datos centralizados de usuarios, productos y URLs. |
| `src/test/java/Pages/AuthPage.java` | Acciones de autenticacion. |
| `src/test/java/Pages/InventoryPage.java` | Acciones y validaciones de inventario. |
| `src/test/java/Pages/CartPage.java` | Acciones y validaciones de carrito. |
| `src/test/java/Pages/CheckoutPage.java` | Acciones y validaciones de checkout. |
| `src/test/java/Tests/*.java` | Casos automatizados alineados con la matriz. |

## Nota sobre resultados fallidos

El resultado `BUILD FAILURE` de Maven no representa una falla de configuracion del proyecto. En este contexto, se produce porque TestNG reporta como fallidos los casos donde SauceDemo no cumple el resultado esperado definido en la matriz. Dichos fallos son evidencia de defectos funcionales documentados.
