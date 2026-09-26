# Backlog Ágil — AquaPort MVP (Nivel Piplup)

## Épica
* **Código / Título:** Aqua Port

## Feature
* **Título:** FEAT-01: Gestión de la flota de drones acuáticos del campus

---

## Historias de Usuario

### HU-01: Visualización y filtrado de flota operativa
* Como operador hídrico, quiero ver qué drones están disponibles y su nivel de batería ordenados de mayor a menor, para identificar rápidamente las unidades aptas para operar en el campus.

### HU-02: Asignación y registro de misión de transporte
* Como operador hídrico, quiero asignar un drone disponible a una misión ingresando ruta y tipo de carga, para despachar el transporte de muestras ambientales dentro del campus.
* **Criterio de Aceptación 1:** Dado un dron con batería >= 35% y disponible, cuando se registre la misión, el sistema debe confirmarla en estado PENDIENTE y guardarla en el repositorio.
* **Criterio de Aceptación 2:** Dado un dron con batería < 35%, cuando se intente asignar, el sistema debe impedir la creación lanzando IllegalStateException.
* **Subtareas Técnicas:**
  1. Implementar validación de batería (>=35%) y compatibilidad de zona en ValidadorMision.
  2. Configurar construcción inmutable y validaciones de campos obligatorios en Mision.Builder.
  3. Implementar método guardar(mision) en RegistradorMisiones integrando RepositorioMisiones.

### HU-03: Cancelación de misión en estado pendiente
* **Narrativa:** Como operador hídrico, quiero cancelar una misión que se encuentre en estado PENDIENTE, para liberar inmediatamente el dron asignado ante contingencias climáticas o errores de solicitud.