# GatlingPojectOne-tutorial

Aqui se realizaran pruebas de rendimiento con Gatling.io.

El propósito de este tutorial es conocer los conceptos básicos de la herramienta Gatling.io y su funcionalidad R&R. Le mostraré cómo configurar un entorno, la herramienta y le explicaré los conceptos básicos sobre el escenario grabado.


## Instalaciones

Se requieren instalaciones necesarias para comenzar a trabajar con Gatling::

	1. Firefox web browser (o otros, Gatling tambien admite otros navegadores).
	2. Clona o descarga el repositorio en una carpeta de tu elección (preferiblemente C:\).
	3. Install OpenJDK última versión.
	4. Scala última versión.

__Configurar certificados en Gatling recorder__

Mientras trabaja con la grabadora, es importante decirle a su navegador que Gatling no es una amenaza para la seguridad. Más sobre esto [Aquí](https://gatling.io/docs/current/http/recorder/#https-mode). Yo uso la Autoridad de Certificación:

	1. Vaya al paquete Gatling: %GATLING_HOME%\bin\recorder.bat.
	2. Configure el modo HTTPS: Autoridad de certificación.
	3. Generar CA.
	4. Guarde archivos.
	5. En Certificado CA: busque y configure: gatlingCA.cert.pem.
	6. Conjunto de CA PK de Unser: gatlingCA.key.pem.

__Configurar el navegador Firefox__

Y ahora, configuremos los certificados y el proxy en el navegador. El siguiente ejemplo se refiere a Firefox, pero se pueden realizar pasos similares en otros navegadores:

	1. Navegue a Opciones > Certificados > Ver certificados.
    2. Vaya a la pestaña Autoridades.
    3. Importe gatlingCA.cert.pem y gatlingCA.key.pem.
    4. Navegue a Opciones > Red > Configuración.
    5. Configúrelo en "Configuración manual de proxy".

__Configuración de variables de entorno del sistema__

Para ejecutar Gatling, se requiere configurar las variables de entorno del sistema:

	1. Navegue a Propiedades del sistema > Avanzado > Variables de entorno.
    2. En Variables del sistema, seleccione Nuevo.
    3. Agregue lo siguiente:
        Nombre de variable: JAVA_HOME.
        Valor de variable: {ruta a jdk}.

## Recorder

Lea este capítulo para comprender cómo se empareja Gatling con el navegador y cómo imita sus pasos:

[Link to Gatling/recorder](https://gatling.io/docs/3.3/http/recorder)

Ejecutamos el recorder y procedemos con la configuración    .

Ejecute %GATLING_HOME%\bin\recorder.bat

Luego verá la siguiente ventana:

![Image of recorder](img/recorder.png)

