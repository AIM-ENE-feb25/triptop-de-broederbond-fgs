# Vervoer Design Pattern ADR

Datum: 28-03-2025

## Status

Pending

## Context

De onderzoeksvraag is: “Hoe kunnen we verschillende externe vervoersdiensten integreren zonder afhankelijk te worden van hun specifieke implementaties?” Hiervoor moet een design pattern gekozen worden dat flexibiliteit biedt en het toevoegen van nieuwe vervoersystemen eenvoudig maakt.

## Considered Options

| Forces | State Pattern | Strategy Pattern | Adapter Pattern | Facade Pattern | Factory Method Pattern |
| --- | --- | --- | --- | --- | --- |
| Complexiteit | + | 0 | 0 | 0 | 0 |
| Herbruikbaarheid | 0 | ++ | + | + | ++ |
| Onderhoudbaarheid | + | + | ++ | + | + |
| Testbaarheid | + | + | + | + | + |
| Passend | - | + | ++ | + | + |

## Decision

Er is gekozen voor het Factory Pattern en het Adapter Pattern.

## Consequences

Met het Factory Pattern en Adapter Pattern kunnen we eenvoudig nieuwe vervoerssystemen toevoegen zonder bestaande code aan te passen. Het Factory Pattern kiest automatisch de juiste adapter voor de vervoerder, en het Adapter Pattern zorgt ervoor dat we op dezelfde manier met verschillende vervoerders kunnen werken. Dit maakt het systeem flexibel, makkelijk uit te breiden en goed te onderhouden, omdat we gewoon nieuwe adapters kunnen toevoegen zonder veel extra werk.