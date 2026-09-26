# Especificación de Requerimiento Funcional — AquaPort MVP

| Campo | Detalle |
| :--- | :--- |
| **Código** | AP-01 |
| **Nombre** | Registrar misión de transporte de muestra |
| **Actor** | Operador Hídrico |
| **Precondiciones** | 1. El Operador Hídrico debe haber iniciado sesión en el sistema.<br>2. Debe existir al menos un drone acuático registrado en el sistema con estado `disponible == true` y nivel de `bateria >= 35`. |

---

### Datos de Entrada

* `idMision`: `String` (identificador alfanumérico único para la misión).
* `drone`: `DroneAcuatico(id: String, nombre: String, bateria: int, disponible: boolean, zona: String)`.
* `puntoPartida`: `String` (nombre del muelle o punto de embarque inicial).
* `puntoLlegada`: `String` (nombre del punto de entrega o boya de destino).
* `tipoCarga`: `Enum(MUESTRA_AGUA, SENSOR, PAQUETE_LIGERO)`.

---

### Datos de Salida

* `codigoMision`: `String` (identificador único asignado y confirmado por el sistema).
* `estado`: `Enum(PENDIENTE)` (estado inicial asignado automáticamente a la misión).

---

### Flujo Básico

1. El Operador Hídrico solicita registrar una nueva misión suministrando el `idMision`, el `drone` asignado, el `puntoPartida`, el `puntoLlegada` y el `tipoCarga`.
2. El sistema valida que los campos de texto no sean nulos ni vacíos (`!isBlank()`) y que las referencias de objetos (`drone`, `tipoCarga`) sean válidas.
3. El sistema verifica el cumplimiento de las reglas de negocio de asignación: evalúa que el drone tenga batería suficiente ($\ge$ 35%) y que esté asignado a la zona del punto de partida.
4. El sistema construye la entidad `Mision` en memoria mediante el patrón Builder, establece su estado en `EstadoMision.PENDIENTE` y persiste el registro en el `RepositorioMisiones`.
5. El sistema retorna el `codigoMision` generado y notifica al Operador Hídrico la confirmación de la asignación.

---

### Flujos Alternos

* **3a. Drone con batería insuficiente:**
  1. Si en el paso 3 el sistema detecta que el drone asignado tiene una batería menor al 35%, aborta la construcción de la misión.
  2. El sistema lanza una excepción de negocio (`IllegalStateException`) con el mensaje: *"El drone no tiene batería suficiente para asignar la misión."*
  3. No se persiste ningún cambio y el caso de uso finaliza en estado de error.

* **3b. Zona de destino o partida no compatible:**
  1. Si en el paso 3 el sistema detecta que la zona del drone no coincide con el `puntoPartida` ingresado, aborta la operación.
  2. El sistema lanza una excepción de negocio (`IllegalStateException`) con el mensaje: *"El drone no puede operar en la zona de salida especificada."*
  3. No se persiste la misión y el flujo finaliza.

---

### Reglas de Negocio

* **RN-01 (Exclusividad operativa de asignación):** Un drone acuático solo puede tener asignada una única misión a la vez; al momento de ser enlazado a una nueva misión, su propiedad `disponible` debe evaluarse en `true`.
* **RN-02 (Umbral de reserva energética mínima):** Todo drone acuático asignado a una misión de transporte debe mantener una carga de batería igual o superior al 35% al momento del registro para prevenir detenciones en el cuerpo hídrico.