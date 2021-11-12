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

# 6. & 7. hét
Mivel a múlt héten nem sikerült a feladatunkat rendesen elvégezni, ezért ezen a héten is ugyan azt kell csinálnunk, ehhez pedig kaptunk egy [tutorialt](https://www.javainuse.com/spring/boot-jwt) segítségül. Ezt végigcsinálva nem működött még az authentikáció, viszont rövid keresgélés után rájöttünk, hogy a jjwt dependency  outdated, ezért kicseréltük frissekre az [alábbi](https://github.com/jwtk/jjwt#install-jdk-maven) README alapján. Ezek után olyan hibába ütlöztünk, hogy néhány függvény deprecated lett azóta, ami miatt a secret-et nem az application.propertiesbe tettük hard codeolva, hanem generáltattunk egy megfelelőt a ```SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);``` kód segítségével. Így már működött is az authentikáció, viszont egyelőre csak egy beégetett felhasználó volt engedélyezett az oldalon, csak azzal a felhasználónévvel és jelszóval lehetett bármit csinálni az oldalon.
Folytattuk tovább a tutorial sorozatot [ezen a linken](https://www.javainuse.com/spring/boot-jwt-mysql), hogy már ne csak egy, hanem az összes, az adatbázisunkban szereplő felhasználó be tudjon lépni az alkalmazásunkba.
A felhasználók role-jait a jwt tokenbe tettük bele, hogy a későbbiekben le lehessen ellenőrizni, hogy bizonyos felhasználóknak milyen függvények hívásához van jogosultsága.

# 8. hét
Megírtuk az authorization részt, így most minden felhasználó csak azokat az endpointokat használhatja, amihez engedéle van a role-ja alapján. Ehhez a Spring [hivatalos dokumentációjában](https://docs.spring.io/spring-security/site/docs/5.2.11.RELEASE/reference/html/authorization.html) találtunk segítséget.
Pontosítottuk az alkalmazás OpenAPI dokumentációját és summary-kkel láttuk el, hogy könnyebben el lehessen rajta igazodni a frontendeseknek.
Megpróbáltuk megoldani, hogy a MongoDB jelszavunk ne legyen kint githubon ahol bárki megtalálhatja, de hosszas próbálkozás után sajnos nem jártunk sikerrel.
Arra jutottunk, hogy a Jasypt toollal titkosítani lehet bármit, amit el akarunk rejteni mások elől, majd egy előre megadott kulcs segítségével visszafejteni az eredeti Stringet, a kulcsot meg lokálisan kell tárolni a biztonságosság érdekében.
Azért hagytuk abba a próbálkozást, mert rájöttünk, hogy ha ki akarjuk tenni Herokura az appot, akkor ott nem fog működni ez, hiszen nem tudunk a Heroku szerverekre kulcsokat pakolni. (Meg egyébként sincs akkora veszélynek kitéve az alkalmazásunk hogy erre nagy szükség legyen)
