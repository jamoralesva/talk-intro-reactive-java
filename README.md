# talk-intro-reactive-java
Este repositorio está asociado a la charla introductoria a la programación reactiva en Java para el Chapter Backend de ADL.

![Logo vikingos](./doc/assets/logo_vikingos.jpeg)

<img src="./doc/assets/logo_reactor.png" alt="Logo Reactor" width="100"/>

## Contenido

- [Introducción](#Introducción)
- [Motivación](#Motivación)
- [Contexto](#Contexto)
- [Tutorial](#Tutorial)
- [Recursos adicionales](#Recursos-adicionales)
- [Referencias](#Referencias)

## 1. Introducción

En este repositorio hay un pequeño demo en Java útil para introducirse a los conceptos básicos de la programación reactiva, particularmente en Java.

- La presentación la puede encontrar [aquí](https://drive.google.com/file/d/1Bae9VhLq6N49GKLRRBHnf0PjbP9wNPno/view?usp=sharing).
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



### 4.2 Revisión de los componentes básicos

Ahora vamos a revisar los operadores, las porciones de código que se describe en esta sección pertenecen al archivo ```BasicsTest.java```.

 **Flux (Flujo)**

```Flux <T>``` es el principal punto de entrada para los flujos reactivos de _Reactor_ y es similar al ```Observable``` de _RxJava_.

 ```Mono <T>``` es como un ```Flux``` pero de cero a un elemento. Tanto ```Mono``` como ```Flux``` implementan 
 ```org.reactivestreams.Publisher```.

Al igual que en RxJava, Reactor usa _Schedulers_ para decidir qué hilo ejecutar.

Se crea un rango y se publica en ```Schedulers.parallel()``` que proporciona una caché de subprocesos para ejecutar en paralelo:

```java
Flux.range(1, 100)
    .publishOn(Schedulers.parallel())
    .subscribe(v -> System.out.println(v));
```

Para manejar erroes se usan los siguientes métodos

- ```onErrorResume(Function)``` : Acepta la excepción y devuelve un publicador diferente como flujo alternativo o secundario.

- ```onErrorMap(Function)``` : acepta la excepción y le permite modificarla o devolver una excepción completamente nueva si lo prefiere.

- ```onErrorReturn(T)``` : Proporciona un valor predeterminado para usar cuando surge un error.

- ```doOnError(Consumer<? super Throwable>)``` : Permite manejar el error sin afectar la transmisión subyacente de ninguna manera.

 **Mono (Único)**

Es como el ```Flux```, pero solo para uno o cero elementos. Es la versión reactiva de la clase ```Optional``` . 

Métodos:

- ```justOrEmpty (T)```: toma un valor que acepta valores NULL y se convierte en Mono. Si es nulo, el resultado es el mismo que ```Mono.empty()```.

- ```justOrEmpty (Optional)```: Toma un Optional y se convierte en Mono directamente.

A diferencia del ```Optional``` de Java, ```Mono``` puede manejar errores de la misma manera que un ```Flux``` (usando onErrorResume, onErrorMap u onErrorReturn).

Ejemplos: 

```java
Flux<String> flux1 = Flux.just("a", "b", "foobar"); 
List<String> iterable = Arrays.asList("a", "b", "foobar");
Flux<String> flux2 = Flux.fromIterable(iterable);
Flux<Integer> numbers = Flux.range(1, 64);

Mono<String> noData = Mono.empty();
Mono<String> data = Mono.just("foo"); 
```

**Flux API**

La API principal maneja los siguientes conceptos:

- **Value**: se refiere a los valores generados por el Publicador/Observable.

- **Completion**: Se refiere a una terminación normal del flujo.

- **Error**: Se refiere a una terminación erronea del flujo.

Esto lleva la existencia de los siguientes tipos de flujo:

- **Infinite stream**: Un Publicador solo genera eventos con Valores (Value) y no eventos terminales (completion and error).

- **Infinite empty stream**: Un flujo que no genera ni valores ni eventos terminales.

- **Finite stream**: Un Publicador que genera N eventos y luego un evento terminal.

- **Empty stream**: Un Publicador que no genera eventos con valores, solo un evento terminal.

**Schedulers**

- ```Schedulers.immediate()``` : El hilo actual.

- ```Schedulers.single()``` : En un hilo único y reusable. Este método reusa el mismo hilo para todos los métodos que lo invoquen, hasta que el Scheduler sea desechado. 

- ```Schedulers.newSingle()``` : Crea un nuevo hilo cada vez que es usado.

- ```Schedulers.elastic()``` : Crea un _thread pool_ elastico. Crea nuevos _worker pools_ a medida que es necesario y reusa los hilos ociosos. Cuando los _Worker pools_ estan ociosos por mucho tiempo son desechados. Esta una buena elección para manejar operaciones de lectura o escritura bloqueantes dando a estos su propio hilo.

- ```Schedulers.parallel()``` : Un _pool of workers_ de tamaño fijo, crea tantos _workers_ como núcleos tenga la CPU.

- ```Schedulers.fromExecutor(Executor)``` : Crea un _Scheduler_ indicandole que use un _Executor_ dado.

Ejemplo:

```java
List<Integer> squares = new ArrayList<>();
Flux.range(1, 64).flatMap(v -> 
  Mono.just(v)
      .subscribeOn(Schedulers.newSingle("comp"))
      .map(w -> w * w))
    .doOnError(ex -> ex.printStackTrace())
    .doOnComplete(() -> System.out.println("Completed"))
    .subscribeOn(Schedulers.immediate())
    .subscribe(squares::add); 
```

**Manejo de la contrapresión**

- ```onBackpressureBuffer()``` : Almacena todos los elementos hasta que pueda aguantar la maquina.

- ```onBackpressureBuffer(maxSize)``` : Almacena elementos hasta llegar a maxSize.

- ```onBackpressureBuffer(maxSize, BufferOverflowStrategy)``` : Almacena elementos en el búfer hasta llegar al _maxSize_ y le permite especificar la estrategia a utilizar cuando y si el búfer está lleno.   
  - ```BufferOverflowStrategy``` es una enumeración que tiene tres valores: 
    - DROP_OLDEST: que elimina los elementos más antiguos en el búfer.
    - DROP_LATEST: que elimina los elementos más nuevos.
    - ERROR, que terminaría el flujo con un error.

- ```onBackpressureLatest()``` : Similar a mantener un búfer de solo el último elemento agregado. Si el flujo descendente no se mantiene al día con el flujo ascendente, solo se proporcionará el último elemento descendente.

- ```onBackpressureError()``` : Finaliza el flujo con un error (llamando al onError del suscriptor en sentido descendente) con una ```IllegalStateException``` de ```Exceptions.failWithOverflow ()``` si se produjeron más elementos en sentido ascendente que los solicitados en sentido descendente.

- ```onBackpressureDrop()``` : Elimina cualquier artículo producido por encima de lo solicitado. Esto sería útil, por ejemplo, en el código de la interfaz de usuario para eliminar la entrada del usuario que no se puede manejar de inmediato.

- ```onBackpressureDrop(Consumer)``` : Elimina cualquier artículo producido por encima de lo solicitado y llama al Consumidor dado por cada artículo eliminado.

Puede revisar los ejemplos un poco mas complejos del archivo ```ReactorTest.java```.



### 4.3 Revisión de los Operadores básicos

TODO

## 5. Recursos adicionales

## 6. Referencias

1. [Adam L. Davis,  Reactive Streams in Java, 2019](https://learning.oreilly.com/library/view/reactive-streams-in/9781484241769/), https://doi.org/10.1007/978-1-4842-4176-9

2. [Rahul Sharma, Hands-On Reactive Programming with Reactor, 2018](https://learning.oreilly.com/library/view/hands-on-reactive-programming/9781789135794/)

3. [Tomasz Nurkiewicz, Ben Christensen, Reactive Programming with RxJava, 2016](https://learning.oreilly.com/library/view/reactive-programming-with/9781491931646/)
