## Flujo de Trabajo GitFlow — Versión 2.0 (Prinplup)

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