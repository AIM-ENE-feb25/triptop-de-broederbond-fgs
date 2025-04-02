package persoonlijkeUitwerkingen.StrategyPattern;

public class Main {
    public static void main(String[] args) {
        Woordenlijst woordenlijstBubble = new Woordenlijst();
        woordenlijstBubble.print();
        woordenlijstBubble.setSorteerStrategie(new BubbleSort());
        woordenlijstBubble.sorteer();
        woordenlijstBubble.print();

        System.out.println();

        Woordenlijst woordenlijstSelection = new Woordenlijst();
        woordenlijstSelection.print();
        woordenlijstSelection.setSorteerStrategie(new SelectionSort());
        woordenlijstSelection.sorteer();
        woordenlijstSelection.print();
    }
}
