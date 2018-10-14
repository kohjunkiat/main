package seedu.address.model.statistic;

public class Statistic {

    private static Statistic instance = null;
    private final Revenue revenue;
    private final Expense expense;
    private final String month;
    private final Volume volume;


    public Statistic(Revenue revenue, String month, Volume volume, Expense expense) {
        this.revenue = revenue;
        this.month = month;
        this.volume = volume;
        this.expense = expense;
    }

    public Statistic getInstance() {
            if (instance == null) {
                instance = new Statistic(this.revenue, month, volume, expense);
            }
            return instance;
    }

    public Revenue getRevenue() {return revenue;}

    public String getMonth() {return month;}

    public Volume getVolume() {return volume;}

    public Expense getExpense() {return expense;}
}
