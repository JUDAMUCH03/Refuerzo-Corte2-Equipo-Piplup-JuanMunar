# Requerimientos del Sistema — AquaPort MVP (Nivel Piplup)

## 1. Requerimientos Funcionales (RF)

* **RF-01: Consulta de drones operativos**
  * **Actor:** Operador Hídrico.
  * **Acción:** Solicita la lista de drones acuáticos aptos para operación en el sistema.
  * **Resultado observable:** El sistema retorna exclusivamente los drones con estado disponible y batería $\ge$ 35%, ordenados descendentemente por nivel de carga.

* **RF-02: Registro y asignación de misión**
  * **Actor:** Operador Hídrico.
  * **Acción:** Ingresa los datos de una misión (identificador, dron asignado, punto de partida, punto de llegada y tipo de carga) y confirma el registro.
  * **Resultado observable:** El sistema valida las restricciones de dominio, almacena la misión en estado `PENDIENTE` en el repositorio en memoria y retorna el identificador de la misión registrada.

* **RF-03: Filtrado de flota por zona hídrica**
  * **Actor:** Operador Hídrico.
  * **Acción:** Solicita consultar los drones disponibles filtrando por una zona hídrica específica (ej. "Embalse Norte").
  * **Resultado observable:** El sistema retorna únicamente las unidades disponibles cuya zona actual coincide con el criterio ingresado, o una lista vacía si ninguna unidad se encuentra en ese punto.

---

## 2. Requerimientos No Funcionales (RNF)

* **RNF-01: Rendimiento en consultas de flota**
  * **Métrica:** El filtrado y ordenamiento de una flota de hasta 10 drones en memoria debe completarse en un tiempo inferior a **200 ms**, verificado mediante `assertTimeoutPreemptively` en JUnit 5.

* **RNF-02: Cobertura de pruebas unitarias**
  * **Métrica:** Las clases del módulo `piplup` (`ConsultorFlota`, `Mision`, `ValidadorMision` y `RegistradorMisiones`) deben mantener una cobertura de código mínima del **80%** en líneas e instrucciones, auditada por el plugin JaCoCo durante la ejecución de `mvn test`.

* **RNF-03: Robustez e integridad ante datos inválidos**
  * **Métrica:** El sistema debe rechazar el **100%** de los intentos de construcción o registro de misiones con atributos nulos, cadenas en blanco o drones no disponibles, lanzando de forma determinista una excepción (`IllegalStateException` o `IllegalArgumentException`) en menos de **50 ms**.

---

## 3. Priorización MoSCoW

| Código | Tipo | Nombre | Categoría MoSCoW | Justificación |
| :--- | :--- | :--- | :--- | :--- |
| **RF-01** | Funcional | Consulta de drones operativos | **Must Have** | Es indispensable para que el operador conozca los recursos viables y evite despachar unidades sin autonomía suficiente. |
| **RF-02** | Funcional | Registro de misión | **Must Have** | Constituye el flujo central de valor del MVP, sin el cual el software no cumple su propósito operativo de despacho. |
| **RF-03** | Funcional | Filtrado por zona hídrica | **Should Have** | Agiliza significativamente la asignación al evitar traslados innecesarios entre embalses, aunque el operador podría revisar manualmente la lista general. |
| **RNF-01** | No Funcional | Rendimiento en consultas | **Must Have** | Garantiza una respuesta interactiva inmediata al trabajar sobre estructuras en memoria dentro de la arquitectura base. |
| **RNF-02** | No Funcional | Cobertura JaCoCo $\ge$ 80% | **Must Have** | Asegura el estándar de calidad y la ausencia de regresiones exigidos en DOSW antes de evolucionar el proyecto al nivel Prinplup. |
| **RNF-03** | No Funcional | Rechazo de entradas inválidas | **Should Have** | Protege el estado del dominio frente a corrupciones en memoria, complementando la operación manual controlada del MVP. |