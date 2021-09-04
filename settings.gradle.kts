enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "microservice-template"
include("application")
include("persistence")
include("persistence:persistance-mongo")
include("communication")
include("shared")
include("bussiness")
include("convensions-plugins")
include("persistence:migrations")
findProject(":persistence:migrations")?.name = "migrations"
include("persistence:migrations:mongo")
findProject(":persistence:migrations:mongo")?.name = "mongo"
include("shared:web")
findProject(":shared:web")?.name = "web"
include("shared:web:dto")
findProject(":shared:web:dto")?.name = "dto"
include("communication:rest-controller")
findProject(":communication:rest-controller")?.name = "rest-controller"
