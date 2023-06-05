# Projektbeskrivning

Projektet är en MongoDB-fasadimplementation och har utförts hara jag Abdale.

## Vem har gjort vad

- Medlem: Abdale

## Vad har gjorts

- Skapade en MongoDBFacade-klass för att ansluta till och interagera med en MongoDB-databas.
- Implementerade metoder för att skapa, uppdatera, ta bort och hämta personer från MongoDB-samlingarna.
- Hanterade anslutningssträng, skapande av databas och samlingar vid behov.
- Använde MongoDB Java Driver och loggning med SLF4J.

## Vad som var svårt

- Att hantera anslutningen till MongoDB och konfigurera klientinställningarna.
- Att implementera CRUD-operationer för både kunder och anställda.
- Att hämta och konvertera dokument från databasen till personobjekt.

## Lösningar och byten

- Använde MongoDB Java Driver och konfigurerade klientinställningar med hjälp av `MongoClientSettings`.
- Använde `MongoCollection` för att interagera med samlingarna och `Document` för att representera data i MongoDB.
- Använde SLF4J för loggning av händelser och fel.

## Slutsatser

- Det gick bra att skapa en fungerande MongoDB-fasad med de implementerade funktionerna.
- Svårigheter uppstod vid hanteringen av anslutningen och datakonverteringar.
- Med den kunskap som erhållits under kursen skulle jag ha kunnat implementera fler avancerade funktioner och förbättra prestanda och skalbarhet.
- I framtida projekt skulle jag vara mer medveten om optimering och användning av indexering i MongoDB för att förbättra frågeprestanda.
