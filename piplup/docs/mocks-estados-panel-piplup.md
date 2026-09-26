# Mocks del Panel de Monitoreo

## 1. Prompt usado

Actúa como Diseñador UX/UI Senior de sistemas de monitoreo y telemetría hídrica industrial.

SISTEMA: AquaPort — Centro de Comando y Flota de Drones Acuáticos de Superficie (Campus ECI).
PANTALLA: Panel de Monitoreo de Flota (Vista Principal del Operador Hídrico) mostrando TRES ESTADOS DE LA INTERFAZ secuenciales o en mosaico de pantalla:

1. ESTÉTICA E IDENTIDAD VISUAL:
- Fondo: Azul noche ultra profundo (#050914) con sutil efecto de mapa batimétrico/topográfico hídrico al 15% de opacidad.
- Paneles: Glassmorphism oscuro (backdrop-blur, fondo #0F1626 con 80% de opacidad y borde fino border-white/10).
- Colores de Marca y Telemetría: Cian técnico (#00F5D4), Verde esmeralda (#10B981) para DISPONIBLE, Azul cobalto (#3B82F6) para EN MISIÓN, Ámbar (#F59E0B) para RECARGANDO, Carmesí (#EF4444) para FALLO/CRÍTICO.
- Tipografía: 'Inter' para títulos y botones; 'JetBrains Mono' estricta para IDs de hardware (AR-01 a AR-04), coordenadas, porcentajes numéricos y reloj del sistema (HORA SIS 20:30).
- Idioma: TODO el texto de la interfaz debe estar 100% en ESPAÑOL técnico y corporativo.

2. GENERAR LOS TRES ESTADOS ESPECÍFICOS:

ESTADO 1: OPERACIÓN NORMAL
- Cuadrícula con los 4 drones Aqua-Ranger 100:
  * AR-01: DISPONIBLE (92% batería, verde brillante), Embalse Norte. Botón activo: "[ Asignar Misión ]".
  * AR-02: EN MISIÓN (65% batería, azul cobalto), Canal Central. Botón alterno: "[ En Misión - Ver Ruta ]".
  * AR-03: RECARGANDO (40% batería, ámbar), Muelle Laguna Sur. Botón deshabilitado: "[ Estación de Carga ]".
  * AR-04: DISPONIBLE (73% batería, verde brillante), Punto Ribereño Este. Botón activo: "[ Asignar Misión ]".

ESTADO 2: ALERTA DE FALLO OPERATIVO
- Banner de notificación superior rojo intenso: "ALERTA: Dron AR-03 reporta fallo crítico en Laguna Sur. Telemetría fuera de rango."
- La tarjeta del dron AR-03 se resalta con resplandor rojo (#EF4444), badge "FALLO", batería en 12%, datos en alerta roja "DATA.STR: CRITICAL", botón bloqueado con candado "[ BLOQUEADO / ACCIÓN REQUERIDA ]". Los demás drones permanecen atenuados en segundo plano.

ESTADO 3: FLOTA SATURADA / ESTADO VACÍO FUNCIONAL
- Los 4 drones (AR-01, AR-02, AR-03, AR-04) aparecen en estado "EN MISIÓN" (azul cobalto).
- Ningún botón de "[ Asignar Misión ]" está disponible.
- Se presenta un banner central informativo translúcido: "Flota 100% Comprometida. Cero drones disponibles para despacho inmediato. Próxima liberación estimada: AR-02 en 18 min". Incluye botón "[ Notificar al Liberar Unidad ]".

3. PRINCIPIOS DE NIELSEN REFLEJADOS:
- H1 (Visibilidad del estado): Anillos radiales de energía y badges semánticos visibles al instante.
- H5 (Prevención de errores): Deshabilitación absoluta de botones de despacho en unidades en fallo o no disponibles.
- H8 (Diseño minimalista): Sin sobrecarga visual, limitándose a ID, Modelo, Batería, Zona y Estado de misión.

---

## 2. Heurísticas de Nielsen Cumplidas

* **#1 Visibilidad del estado del sistema:** El operador reconoce de un vistazo la condición de la flota mediante los anillos de batería, los badges de estado con LEDs y el reloj del sistema en vivo.
* **#5 Prevención de errores:** En los estados de Alerta y Flota Saturada, el sistema deshabilita visualmente cualquier intento de asignación antes de consultar al backend, protegiendo las reglas de negocio del MVP.
* **#8 Diseño estético y minimalista:** Cada tarjeta restringe su densidad de información a los 5 parámetros indispensables del RF AP-01: identificador, modelo, porcentaje de carga, zona hídrica y disponibilidad.
* **#9 Reconocimiento y diagnóstico de errores:** En el Estado de Alerta, el sistema explicita la causa del problema (*"Fallo crítico / Batería insuficiente: 12%"*), orientando la recuperación inmediata en muelle.