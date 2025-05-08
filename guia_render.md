serverengine/
└── rendering/
├── core/
│ ├── Scene.java // Contiene entidades activas y ciclo de update
│ ├── Engine.java // Motor principal, registra escenas y render loop
│ ├── RenderContext.java // Información de renderizado por tick
├── model/
│ ├── ModelAsset.java // Datos estáticos del modelo (estructura, jerarquía)
│ ├── BoneDefinition.java // Estructura de un hueso del modelo
│ ├── ModelInstance.java // Instancia viva del modelo
│ └── ModelLoader.java // Carga modelos desde archivos
├── component/
│ ├── Transform.java // Posición, rotación, escala
│ ├── BoneRenderer.java // Renderiza un hueso específico
│ └── AnimationState.java // Datos de animación por entidad
├── system/
│ ├── AnimationSystem.java // Aplica animaciones
│ ├── TransformSystem.java // Propaga transformaciones jerárquicas
│ └── RenderSystem.java // Mueve ArmorStands/Displays en el mundo
├── animation/
│ ├── AnimationClip.java // Datos de una animación (keyframes)
│ ├── Keyframe.java
│ └── Interpolation.java
└── util/
└── MathUtils.java
