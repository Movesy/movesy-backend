# Backend haladási napló
# 4. hét
Mostantól itt dokumentáljuk a backenndel kapcsolatos haladást.
Azt beszéltük meg, hogy adatbázisnak a MongoDB-t fogjuk használni, mivel a usereket szereptől függetlenül fogjuk egy adatbázisban tárolni, így megspórolunk csomó null értéket.
Csütörtökön létrehoztuk a projektet, majd elkezdtünk azzal foglalkozni, hogy létrehozzuk az endpointokat és összekössük a MongoDB-vel az alkalmazást
(Ehhez létrehoztunk egy Atlas-os clustert hogy ne lokálisan tárolódjanak az adatok).
Ez utóbbival rendesen elakadtunk, kb 5 óránk ment el azzal, hogy az application.properties-ben a Mongo URI-ját idézőjellel együtt írtuk meg, pedig anélkül kellett volna.
Pénteken erre rájöttünk és folytattuk a munkát az endpointok megírásával. Ezek teszteléséhez a Postman-t használtuk.
Miután megvoltunk a nagyjával, elkezdtünk azzal foglalkozni, hogy hostoljuk a Spring alkalmazást [Heroku](https://www.callicoder.com/deploy-host-spring-boot-apps-on-heroku/) segítségével. Ez elérhető [itt](https://movesy.herokuapp.com/) és tesztelhető is Postmannel, vagy a [linkre](https://movesy.herokuapp.com/diary) kattintva.

# 5. hét
A feladatunk az volt erre a hétre, hogy jwt tokenes beléptetéssel bővítsük az alkalmazást, viszont sajnos nem jártunk sikerrel, mert nem sikerült teljesen megérteni hogy hogyan kellene ezt implementálnunk. Megnéztünk cikkeket a témában, meg több tutorial sorozatot is követtünk, de mindig egy ponton elakadtunk.
Ezekből itt van néhány:
- [java-config-spring-security](https://www.baeldung.com/java-config-spring-security)
- [Spring Boot Security - RomanianCoder](https://www.youtube.com/playlist?list=PLVApX3evDwJ1d0lKKHssPQvzv2Ao3e__Q)
- [Spring Boot Authorization Tutorial: Secure an API (Java)](https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/)
- [Spring Boot and Spring Security with JWT including Access and Refresh Tokens 🔑 - Amigoscode](https://www.youtube.com/watch?v=VVn9OG9nfH0)
- [Spring Security: Authentication and Authorization In-Depth](https://www.marcobehler.com/guides/spring-security)
- [Spring Boot JWT bootcamp 2021 | Best tutorial in the universe](https://i.kym-cdn.com/entries/icons/facebook/000/034/772/Untitled-1.jpg)
