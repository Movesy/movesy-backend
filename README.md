# Backend haladási napló
# 5. hét
Mostantól itt dokumentáljuk a backenndel kapcsolatos haladást.
Azt beszéltük meg, hogy adatbázisnak a MongoDB-t fogjuk használni, mivel a usereket szereptől függetlenül fogjuk egy adatbázisban tárolni, így megspórolunk csomó null értéket.
Csütörtökön létrehoztuk a projektet, majd elkezdtünk azzal foglalkozni, hogy létrehozzuk az endpointokat és összekössük a MongoDB-vel az alkalmazást
(Ehhez létrehoztunk egy Atlas-os clustert hogy ne lokálisan tárolódjanak az adatok).
Ez utóbbival rendesen elakadtunk, kb 5 óránk ment el azzal, hogy az application.properties-ben a Mongo URI-ját idézőjellel együtt írtuk meg, pedig anélkül kellett volna.
Pénteken erre rájöttünk és folytattuk a munkát az endpointok megírásával. Ezek teszteléséhez a Postman-t használtuk.
Miután megvoltunk a nagyjával, elkezdtünk azzal foglalkozni, hogy hostoljuk a Spring alkalmazást [Heroku](https://www.callicoder.com/deploy-host-spring-boot-apps-on-heroku/) segítségével. Ez elérhető [itt](https://movesy.herokuapp.com/) és tesztelhető is Postmannel, vagy a [linkre](https://movesy.herokuapp.com/diary) kattintva.
