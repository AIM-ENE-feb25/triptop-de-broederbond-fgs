# API’s ADR

Datum: 21-03-2025

## Status

Accepted

## Context

Er zijn verschillende Rapid API’s onderzocht die gebruikt kunnen worden in de TripTop applicatie.

## Considered Options

| Forces | TripAdvisor | Aerobox | Booking |
| --- | --- | --- | --- |
| Snelheid | - | ++ | + |
| Overbodige data | - | - -  | -  |
| Benodigde data | - - | - -  | ++ |
| Prijs | ++ | ++ | ++ |

## Decision

Er is besloten om de Booking API wel te gebruiken, maar voor de andere twee API’s een andere te zoeken.

## Consequences

Er kan nu één API gebruikt worden, maar moeten er nog minstens twee andere API’s onderzocht worden om verder te gaan met de applicatie.