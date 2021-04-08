# talk-intro-reactive-java
Este repositorio está asociado a la charla introductoria a la programación reactiva en Java para el Chapter Backend de ADL.

![Logo vikingos](./doc/assets/logo_vikingos.jpeg)

## Contenido

- [Introducción](#Introducción)
- [Motivación](#Motivación)
- [Contexto](#Contexto)
- [Tutorial](#Tutorial)
- [Recursos adicionales](#Recursos-adicionales)
- [Referencias](#Referencias)

## 1. Introducción

En este repositorio hay un pequeño demo en Java útil para introducirse a los conceptos básicos de la programación reactiva, particularmente en Java.

- La presentación la puede encontrar aquí: [TODO]()
- El video de la presentación lo puede encontrar aquí: [TODO]()

La charla tiene una duración de ~50 min.

  - Conceptos básicos: ~15 min
  - Tutorial: ~20 min
  - Buenas prácticas y recomendaciones: ~15 min
  - Quiz: ~5 min (solo presentación en vivo)

- En este repo encontrará:
  - Instalación y configuración del proyecto
  - como ejecutar los ejemplos

## 2. Motivación

Revisar la presentación.

## 3. Contexto

Revisar la presentación.

## 4. Tutorial

Los conceptos que se revisarán en esta sección son transversales a la mayoria de bibliotecas que proveen capacidades y/o habilitan el uso de _Streams_ reactivos. 

Una funcionalidad interesantes es la de poder "encadenar" métodos, permitiendo así la conversión compleja de de flujos de datos [1].

### 4.1 Instalación y configuración

Para este tutorial se recomienda tener instalado:


- Java 8: se trabajará con Java 8+ por lo tanto es esencial tenerlo instalado. Si requiere tener varias versiones del JDK en su maquina puede usar [JENV](https://github.com/jenv/jenv).

```sh
$ java -version
java version "1.8.0_101"
Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.101-b13, mixed mode)
```

- Un IDE para Java. Se recomienda IntelliJ IDEA.

#### Pasos para ejecutar este proyecto:

- Clone este proyecto (si todavia no lo ha hecho)

```sh
git clone https://github.com/alejandro56664-adl/talk-intro-reactive-java.git
```

- Importe el proyecto ```demo_reactor```en InteliJ IDEA como un proyecto tipo Gradle.

- Ejecute la prueba ```ReactorTest.java```



### 4.2 Revisión de los operadores

## 5. Recursos adicionales

## 6. Referencias

1. [Adam L. Davis,  Reactive Streams in Java, 2019](https://learning.oreilly.com/library/view/reactive-streams-in/9781484241769/), https://doi.org/10.1007/978-1-4842-4176-9

2. [Tomasz Nurkiewicz, Ben Christensen, Reactive Programming with RxJava, 2016](https://learning.oreilly.com/library/view/reactive-programming-with/9781491931646/)
