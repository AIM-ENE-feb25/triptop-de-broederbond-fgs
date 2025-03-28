Datum: 28-3-2025

## Status

Pending

## Context

Voor de volgende onderzoeksvraag: Hoe kunnen we verschillende identity providers met verschillende interfaces integreren voor het gehele systeem?
Gaan we een prototype maken dat een of meerdere design patterns implementeert om dit probleem op te lossen. Vervolgens zullen we testen of deze aanpak de juiste oplossing biedt.
De design patterns waaruit we kunnen kiezen, zijn: State Pattern, Strategy Pattern, Adapter Pattern, Facade Pattern en Factory Method Pattern.

## Considered Options

| Forces | State Pattern | Strategy Pattern | Adapter Pattern | Facade Pattern | Factory Method Pattern |
| --- | --- | --- | --- | --- | --- |
| Complexiteit | - | 0 | 0 | 0 | 0 |
| Herbruikbaarheid | - | ++ | ++ | + | ++ |
| Onderhoudbaarheid | - | + | ++ | + | ++ |
| Testbaarheid | 0 | ++ | ++ | + | ++ |
| Passend | - | + | ++ | + | ++ |

## Decision

Er is gekozen voor het gebruik van het Adapter en Factory Pattern. Deze keuze is gebaseerd op de criteria uit de tabel, waaruit blijkt dat deze twee patterns het beste passen bij dit probleem. Na het maken van het prototype kunnen we met zekerheid zeggen of deze conclusie klopt.

## Consequences

Volgens de criteria uit de tabel zal het gebruik van deze patterns een positief effect hebben op de herbruikbaarheid, onderhoudbaarheid en testbaarheid van het systeem. Dit komt doordat de code beter gestructureerd wordt, afhankelijkheden worden verminderd en de afzonderlijke componenten eenvoudiger te testen en hergebruiken zijn.