# README Prinplup

---

## Flujo de Trabajo GitFlow 

Se implementó el ciclo de vida de GitFlow para la versión 2.0:

```mermaid
gitGraph
   commit id: "v1.0.0 (Piplup)"
   branch develop
   checkout develop
   commit id: "setup prinplup"
   branch feature/asignacion-automatica
   checkout feature/asignacion-automatica
   commit id: "feat: AsignadorAutomatico"
   checkout develop
   merge feature/asignacion-automatica
   branch feature/alertas-centro-control
   checkout feature/alertas-centro-control
   commit id: "feat: CentroControlService"
   checkout develop
   merge feature/alertas-centro-control
   branch feature/factory-drones
   checkout feature/factory-drones
   commit id: "feat: FabricaDrones"
   checkout develop
   merge feature/factory-drones
   branch release/v2.0
   checkout release/v2.0
   commit id: "chore: preparar v2.0.0"
   checkout main
   merge release/v2.0 tag: "v2.0.0"
   checkout develop
   merge release/v2.0
   checkout main
   branch hotfix/fix-bateria-critica
   checkout hotfix/fix-bateria-critica
   commit id: "fix: umbral critico"
   checkout main
   merge hotfix/fix-bateria-critica tag: "v2.0.1"
   checkout develop
   merge hotfix/fix-bateria-critica

---

## Principios SOLID 

### 1. Single Responsibility Principle (SRP)
* **Clases:** `FabricaDrones`, `AsignadorAutomatico`, `AnalizadorFlotaAvanzado`.
* **Cómo se aplica:** Cada clase responde a un único actor y motivo de cambio:
  * `FabricaDrones`: Responsable exclusiva de instanciar y parametrizar los tipos especializados de hardware acuático.
  * `AsignadorAutomatico`: Orquesta el flujo de despacho delegando la lógica de selección y la notificación de eventos.
  * `AnalizadorFlotaAvanzado`: Concentra únicamente consultas analíticas y agregaciones funcionales de telemetría.
* **Por qué importa en AquaPort:** Evita que cambios en la topología de la flota o en las reglas de conexión del hardware afecten la lógica de asignación operativa.

### 2. Open/Closed Principle (OCP)
* **Clases / Interfaces:** `EstrategiaSeleccion`, `DroneAcuatico` y sus subclases.
* **Cómo se aplica:**
  * El sistema está abierto a la extensión pero cerrado a la modificación. Para incorporar un nuevo tipo de unidad (por ejemplo, `DroneSumergibleProfundo`), solo se crea la subclase que hereda de `DroneAcuatico`, sin necesidad de alterar `AsignadorAutomatico` ni los servicios de validación.
  * Se pueden añadir nuevas políticas de despacho (como `MenorConsumoEnergeticoStrategy`) implementando la interfaz `EstrategiaSeleccion` sin modificar el código fuente de `AsignadorAutomatico`.
* **Por qué importa en AquaPort:** Permite que el sistema incorpore prototipos experimentales de drones en el campus de la ECI sin riesgo de romper el motor de misiones en producción.

### 3. Liskov Substitution Principle (LSP)
* **Clases:** `DroneSuperficial`, `DroneSemisumergido`, `DroneBuceador` (subclases de `DroneAcuatico`).
* **Cómo se aplica:** Toda subclase respeta los contratos y precondiciones de la clase base `DroneAcuatico`. Ninguna subclase anula métodos para retornar valores nulos imprevistos, restringir artificialmente los rangos de batería ni lanzar excepciones donde el contrato base garantiza respuestas válidas.
* **Por qué importa en AquaPort:** Las colecciones de flota (`List<DroneAcuatico>`) son procesadas uniformemente por los algoritmos de búsqueda y asignación, garantizando que un dron buceador sea intercambiable con uno de superficie sin provocar fallos en tiempo de ejecución.

### 4. Interface Segregation Principle (ISP)
* **Clases / Interfaces:** `EstrategiaSeleccion`, `ObservadorMision`.
* **Cómo se aplica:** En lugar de interfaces monolíticas, se diseñaron contratos cohesivos y de propósito específico:
  * `EstrategiaSeleccion` expone exclusivamente `seleccionar(candidatos, mision)`.
  * `ObservadorMision` contiene únicamente las funciones de notificación de alertas operativas (`notificarFallo`, `notificarFalloAsignacion`).
* **Por qué importa en AquaPort:** Impide que los componentes de toma de decisiones dependan de métodos de infraestructura o I/O que no utilizan, manteniendo desacoplado el núcleo del dominio.

### 5. Dependency Inversion Principle (DIP)
* **Clases / Interfaces:** `AsignadorAutomatico` dependiente de `EstrategiaSeleccion` y `ObservadorMision`.
* **Cómo se aplica:** Los módulos de alto nivel no dependen de implementaciones concretas. `AsignadorAutomatico` interactúa con las abstracciones de estrategia y observador, inyectadas mediante su constructor o setters, sin acoplamiento con `MayorBateriaStrategy`, `CentroControlObserver` o `TecnicoMantenimientoObserver`.
* **Por qué importa en AquaPort:** Facilita el intercambio dinámico de algoritmos en caliente y permite sustituir dependencias reales por simulaciones con Mockito en pruebas automatizadas.