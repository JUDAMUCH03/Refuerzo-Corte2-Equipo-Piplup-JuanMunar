# Manual de Identidad Visual — AquaPort

## 1. Paleta de Colores

### Colores de Marca
* **Primario (Azul Abisal):** `#0A2540` — Utilizado en barra de navegación superior, títulos y contenedores principales. Transmite sobriedad institucional y entorno marítimo/hídrico.
* **Superficie / Fondo:** `#050914` — Azul noche profundo con capa fotorrealista de embalse al anochecer con fuerte desenfoque de fondo.
* **Superficie de Panel:** Glassmorphism oscuro — `backdrop-blur-xl` + `bg-[#0f1626]/80` con bordes `border-white/10`. Textura metálica mate mediante gradientes internos y sombras `inset`.
* **Acento (Cian Técnico / Emerald):** `#00F5D4` / `#10B981` — Nav activa, indicadores LED, valores operativos y focos de interacción.

### Colores de Estado
* **Disponible / Operativo:** `#10B981` (Verde Esmeralda) — El dron cumple autonomía (≥ 35%) y no tiene misión asignada. LED pulsante (`pulse-dot`) a 2 s.
* **En Misión:** `#3B82F6` (Azul Cobalto) — Dron navegando; botón cambia a `EN MISIÓN — CANCELAR` con LED azul.
* **Recargando:** `#F59E0B` (Ámbar) — Unidad conectada a estación de carga en muelle.
* **Mantenimiento:** `#64748B` (Gris Neutro) — Unidad fuera de servicio por calibración física o sensores.
* **Fallo / Bloqueado:** `#EF4444` (Rojo Carmesí) — Dron inoperativo o con batería crítica (< 35%). Panel dimado a `opacity: 0.4`.

---

## 2. Tipografía

* **Tipografía de Interfaz (UI):** `Inter` o `system-ui` (Sans-serif). Usada en títulos H1, subtítulos y párrafos descriptivos (`Flota Aqua-Ranger`, breadcrumb).
* **Tipografía de Datos Técnicos:** `JetBrains Mono` (monospace). Usada en **todos** los identificadores de hardware (`AR-01`…`AR-04`), porcentajes de batería, coordenadas GPS, timestamps, etiquetas de telemetría, badges de estado y datos HUD.

---

## 3. Componentes Clave Implementados

### `BatteryRing`
Anillo SVG circular con `strokeDasharray` proporcional al nivel de batería. Color dinámico: verde (> 50%), ámbar (30–50%), rojo (< 30%). Efecto `drop-shadow` desactivado en modo crítico.

### `HUDDataStream`
Visualizador de telemetría estilo osciloscupio: 24 barras de altura aleatoria con `Math.sin` para simular señal viva. En modo crítico las barras aplanan (`height: 10–25%`) y cambian a rojo, mostrando `DATA.STR: 18% FAIL`. Overlay `hud-scanline` para línea de barrido CRT.

### `StatusBadge`
Pastilla monoespaciada con punto LED de color + texto. Borde semitransparente del color de estado. Punto animado solo en estado `DISPONIBLE`.

### `TelemetryRow`
Fila etiqueta/valor con separador `border-white/5`. Etiqueta en `text-slate-500`, valor en `text-slate-300` con clase `telemetry-value`.

### `DroneCard`
Tarjeta de flota con panel glassmorphism. En estado crítico: `opacity: 0.4`, línea de acento roja (`#EF4444`), LED parpadeante a 0.8 s, banner de advertencia con icono triangular SVG y botón `[ MISIÓN BLOQUEADA ]` deshabilitado. En estado normal: botón `[ DESPACHAR MISIÓN ]` → toggle a `EN MISIÓN — CANCELAR`.

### `StatTile`
Tile de métrica con borde inferior coloreado (`borderBottomColor`), valor principal con `led-glow` y clase `data-flicker` para efecto de parpadeo digital.

---

## 4. Heurísticas de Nielsen Implementadas

| Heurística | Implementación |
|---|---|
| **H1 — Visibilidad del estado del sistema** | Reloj `HORA SIS` en vivo, badge `EN VIVO` parpadeante, `lastSync` por unidad, `DATA.STR: XX% OK/FAIL` en HUD, LEDs animados |
| **H5 — Prevención de errores** | AR-03 dimado al 40%, botón bloqueado + ícono candado, banner: *"Batería insuficiente (18%). Mínimo operativo: 35%."* |
| **H6 — Reconocimiento en lugar de recuerdo** | Etiquetas en cada fila de telemetría, `StatusBadge` con color + texto + punto, porcentaje numérico en el HUD |
| **H8 — Estética y diseño minimalista** | 4 métricas por dron, `StatTile` con un valor, fondo desenfocado que no compite con los datos |
| **H9 — Diagnóstico y recuperación de errores** | Banner nombra causa y umbral exacto, botón vacío con `RESTABLECER VISTA`, filtro con `[ LIMPIAR FILTRO ] ×` |

---

## 5. Tono de Voz

* **Registro:** Técnico, conciso, preventivo y orientado al monitoreo ambiental.
* **Idioma:** Todo el texto de interfaz en español obligatoriamente.
* **Directriz:** Los mensajes indican directamente la causa y el valor umbral sin ambigüedades operativas (ejemplo: *"Batería insuficiente (18%). Mínimo operativo: 35%"*).
* **Clasificación:** El footer incluye `CLASIFICACIÓN: INTERNO · SOLO LECTURA` para contexto institucional.