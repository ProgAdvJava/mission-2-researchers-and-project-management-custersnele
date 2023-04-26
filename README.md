### Mission 2: Research projects management tool

#### 1. Genereer de mysql database

Genereer de mysql database die beschreven is in het bestand docker-compose.yml.
Voeg alle properties toe in application.properties zodat je spring boot applicatie verbinding kan
maken met de mysql databank.

#### 2. Enable sql logging

Zorg dat alle sql-statements in de log-file (console) worden getoond.

#### 3. Researchers

Vervolledig de entity-klasse Researcher. You need a one-to-one relationship between Researcher and ContactInformation.
Maak de ResearcherRepository aan. Vervolledig de methoden in de ResearcherService zoals gevraagd.
De SetupController bevat een endpoint om Researchers aan te maken in de databank.
Zorg ervoor dat je de contactinformatie van een researcher kunt aanpassen met het endpoint:
```
PUT http://localhost:<port>/<context-path>/researchers/<researcher-id>/<contact-type>

{
    "value" : "value-comes-here"
}
```

De nieuwe waarde voor het contact-type (bijv. phone), wordt meegegeven in json-format in de request body.

Zorg er verder voor de een researcher verwijderd kan worden. Worden ook de bijhorende contactgegevens van de researcher verwijderd?

#### 4. Researchers en projecten


Vervolledig de entity-klasse Project. Je kan een researcher aan een project toekennen en van een project halen.
Hiervoor worden de volgende twee endpoints ontwikkeld.
```
http://localhost:<port>/<context-path>/researchers/<researcher-id>/join
http://localhost:<port>/<context-path>/researchers/<researcher-id>/leave
```
Het project wordt meegegeven in json-formaat in de request-body.
```
{
    "project": "A secret project"
}
```
Je moet wel rekening houden met de volgende richtlijnen. Bij het toekennen van een researcher aan
een project wordt het project opgezocht aan de hand van de naam van het project.
Indien het project nog niet bestaat wordt het aangemaakt (met huidige datum en status INITIALIZING).
Indien het project al bestaat, mag de researcher enkel toegevoegd worden als het project nog niet CLOSED is,
en als hij nog geen deel uitmaakt van het projectteam.

Een researcher mag enkel een project verlaten, als het project nog niet afgesloten (CLOSED) is.

Zorg dat je alle business-regels test met behulp van unit testen.

#### 5. Status van een project aanpassen

Ontwikkel een endpoint om de status van een project aan te passen. Je mag zelf beslissen hoe dit endpoint eruit ziet.


#### 6. Researcher opzoeken aan de hand van een id

Voorzie een endpoint om alle gegevens van een researcher (incl. zijn projecten - startdatum, naam en status) op te zoeken adhv de id van de researcher.

#### 7. Projecten opzoeken

* Voorzie een endpoint om alle gegevens van een project (incl de researchers met hun email-adres) op te zoeken aan de hand van de naam van het project.
* Voorzie een endpoint alle namen van projecten terug te geven die een opgegeven status hebben. De status kan als parameter in de URL worden meegegeven.

Zorg voor unit testen voor alle queries die je toevoegt in de repositories.

#### Optional: SonarQube report

Configureer SonarQube en bekijk het rapport mbt de kwaliteit van je oplossing.