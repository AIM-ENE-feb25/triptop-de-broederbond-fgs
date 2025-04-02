# Betaal Design Pattern ADR

Datum: 28-03-2025

## Status

Pending

## Context

Er moet een design pattern gekozen worden voor het implementer van verschillende betaalmethodes in TripTop.

## Considered Options

| Forces | State Pattern | Strategy Pattern | Adapter Pattern | Facade Pattern | Factory Method Pattern |
| --- | --- | --- | --- | --- | --- |
| Complexiteit | - | 0 | 0 | 0 | 0 |
| Herbruikbaarheid | 0 | + | + | + | + |
| Onderhoudbaarheid | + | + | 0 | + | + |
| Testbaarheid | + | + | + | 0 | + |
| Passend | 0 | + | + | 0 | + |
| Gebruikt | 0 | 0 | + | 0 | + |

## Decision

Er is besloten om hier te kiezen voor de strategy pattern. De overweging was tussen de strategy pattern, de adapter pattern en de factory method pattern. Na een goede discussie, is er uiteindelijk besloten om te kiezen voor de strategy pattern, omdat deze de meeste bokjes afvinkte die nog niet ergens anders gekozen was.

## Consequences

Bij het maken van het prototype voor het betalen, gaat er gebruik worden gemaakt van de strategy pattern.