Autentisering (Öppen)

Metod	Endpoint	Beskrivning
----    -------     ----------
POST	/api/auth/login	Logga in och få en JWT-token

POST	/api/auth/register	Registrera en ny användare

------------------------------------------------------

Användarhantering (Kräver autentisering)

Metod	Endpoint	             Behörighet	Beskrivning
-----   -------                  ---------   -----------
GET	    /api/user/profile	    ROLE_USER	Hämta användarens profil

PUT	    /api/user/update	    ROLE_USER	Uppdatera användarens info

DELETE	/api/user/delete	    ROLE_USER	Radera användaren

GET     /api/users/info         ROLE_USER   Hämtar info om registrerade användare