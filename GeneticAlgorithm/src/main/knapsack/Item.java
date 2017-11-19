package main.knapsack;

public class Item {

    public double getWeight() {
        return weight;
    }

    public double getAmount() {
        return amount;
    }

    public Item(double weight, double amount) {
        this.weight = weight;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("\nItem { weight = %.3f, amount = %.3f }", weight, amount);
    }

    private final double weight;
    private final double amount;
}
