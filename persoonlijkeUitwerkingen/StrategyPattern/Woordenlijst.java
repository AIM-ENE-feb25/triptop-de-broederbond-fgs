package persoonlijkeUitwerkingen.StrategyPattern;

public class Woordenlijst {
    private String[] woorden = {"hond", "beer", "leeuw", "kat", "aap", "tijger", "olifant"};
    private ISorteerStrategie soorteerStrategie;

    public void print() {
        for (int i = 0; i < woorden.length; i++) {
            System.out.print(woorden[i] + " ");
        }
        System.out.println();
    }

    public void sorteer() {
        soorteerStrategie.sorteer(woorden);
    }

    public void setSorteerStrategie(ISorteerStrategie strategie) {
        this.soorteerStrategie = strategie;
    }
}