package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Woordenlijst lijst = new Woordenlijst();

        System.out.println("Oorspronkelijke lijst:");
        lijst.print();

        // Gebruik BubbleSort
        lijst.setSorteerStrategie(new BubbleSort());
        lijst.sorteer();
        System.out.println("Gesorteerde lijst met BubbleSort:");
        lijst.print();

        // Reset de lijst
        lijst = new Woordenlijst();

        // Gebruik SelectionSort
        lijst.setSorteerStrategie(new SelectionSort());
        lijst.sorteer();
        System.out.println("Gesorteerde lijst met SelectionSort:");
        lijst.print();
    }
}
