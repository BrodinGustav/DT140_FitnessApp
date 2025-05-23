Metod	Endpoint	                    Beskrivning
----    -------                         ----------
POST	/api/auth/login	                Logga in och få en JWT-token

POST	/api/auth/register	            Registrera en ny användare


GET     /api/activities                 Hämtar alla aktiviteter

GET     /api/users                       Hämtar alla användare
GET     /api/users/{me}                 Hämtar specifik användare
POST    /api/users/create               Registrerar ny användare
PUT     /api/users/update                 Uppdaterar specifik användare
DELETE  /api/users/{me}                 Raderar specifik användare

//UserActity 
GET     /api/useractivities                      Hämtar alla användaraktiviteter
GET     /api/useractivities/{id}                 Hämtar specifik användaraktivitet
POST    /api/useractivities/create               Registrerar ny användaraktivitet
PUT     /api/useractivities/{id}                 Uppdaterar specifik användaraktivitet
DELETE  /api/useractivities/{id}                 Raderar specifik användaraktivitet

------------------------------------------------------

