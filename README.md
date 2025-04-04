Autentisering (Öppen)

Metod	Endpoint	                    Beskrivning
----    -------                         ----------
POST	/api/auth/login	                Logga in och få en JWT-token

POST	/api/auth/register	            Registrera en ny användare


GET     /api/activities                 Hämtar alla aktiviteter
GET     /api/activities/{id}            Hämtar specifik aktivitet
POST    /api/activities/create          Registrerar ny aktivitet
PUT     /api/activities/{id}            Uppdaterar specifik aktivitet
DELETE  /api/activities/{id}            Raderar specifik aktivitet

GET     /api/categories                 Hämtar alla kategorier
GET     /api/categories/{id}            Hämtar specifik kategori
POST    /api/categories/create          Registrerar ny kategori
PUT     /api/categories/{id}            Uppdaterar specifik kategori
DELETE  /api/categories/{id}            Raderar specifik kategori

GET     /api/users                       Hämtar alla användare
GET     /api/users/{id}                 Hämtar specifik användare
POST    /api/users/create               Registrerar ny användare
PUT     /api/users/{id}                 Uppdaterar specifik användare
DELETE  /api/users/{id}                 Raderar specifik användare

------------------------------------------------------

Användarhantering (Kräver autentisering)

Metod	Endpoint	             Behörighet	Beskrivning
-----   -------                  ---------   -----------
GET	    /api/user/profile	    ROLE_USER	Hämta användarens profil

PUT	    /api/user/update	    ROLE_USER	Uppdatera användarens info

DELETE	/api/user/delete	    ROLE_USER	Radera användaren

GET     /api/users/info         ROLE_USER   Hämtar info om registrerade användare