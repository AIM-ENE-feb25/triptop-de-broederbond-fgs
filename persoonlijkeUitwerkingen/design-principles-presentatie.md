## Composition over inheritance

Composition over inheritance wilt dat je liever bestaande objecten gebruikt in een klasse in plaats van overerving.

## Consequentie

Leesbaarder, omdat je nu niet dingen overerft van andere klasse. Leg je nu zelf een relatie met een ander object en kan hier methodes en variabele op aanroepen waardoor je precies weet bij welk object welke methode wordt aangeroepen.

Minder afhankelijk, omdat je niet meer afhankelijk bent van een parent klasse. Heeft een andere klasse minder invloed op de huidige klasse. Je roept nu een andere klasse aan en een specifieke methode, dus enige aanpassingen aan deze klasse is irrelevant.

Testbaarheid, omdat je nu refereert naar een andere klasse in plaats van deze overerft. Kan je nu bij bijvoorbeeld Java gebruik maken van mocking. Je kan een overerving niet mocken, maar een referentie wel. Dit verhoogt de testbaarheid.

## **Design Properties:**

- Coupling, het wilt coupling verlagen zodat aanpassingen andere dingen minimaal beinvloedt
- Extensibility, code is simpeler en omdat minder samenhang is kan je makkelijker dingen toevoegen

## Inheritance

```java
public class Example extends CustomObject {
	public int doSomething(){
		int variable = weirdMethod();
		return variable;
	}
	
public class BadExample extends Example {
	// kan niet doSomething genoemd worden door parent
	public int doSomethingToo() {
		// Deze kan ook deze methode aanroepen
		// wat in een grote codebase complex wordt.
		int variable = weirdMethod();
		int variable2 = doSomething();
	}
}
```

## Composition

```java
public class Example {
	public int doSomething(){
		int variable = weirdMethod();
		return variable;
	}
}

public class GoodExample {
	Example example;
	
	public GoodExample(Example example){
		this.example = example;
	}
	
	public void doSomething(){
		int variable = example.doSomething();
	}
}
```