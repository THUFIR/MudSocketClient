package reports;

public class Ratio {

    private int numerator;
    private int denominator;

    private Ratio() {
    }

    public Ratio(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String toString() {


        return numerator + "/" + denominator;
    }
}