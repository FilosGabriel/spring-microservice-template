enableFeaturePreview("VERSION_CATALOGS")
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
