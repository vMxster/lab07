package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private static final int SHORT_MONTH = 28;
    private static final int USUAL_MONTH = 30;
    private static final int LONG_MONTH = 31;

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(final String firstMonth, final String secondMonth) {
                return Integer.compare(Month.fromString(firstMonth).getDays(),Month.fromString(secondMonth).getDays());
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(final String firstMonth, final String secondMonth) {
                return Month.fromString(firstMonth).compareTo(Month.fromString(secondMonth));
            }
        };
    }

    public enum Month {
        January(LONG_MONTH), 
        February(SHORT_MONTH), 
        March(LONG_MONTH), 
        April(USUAL_MONTH), 
        May(LONG_MONTH), 
        June(USUAL_MONTH), 
        July(LONG_MONTH), 
        August(LONG_MONTH), 
        September(USUAL_MONTH), 
        October(LONG_MONTH),
        November(USUAL_MONTH), 
        December(LONG_MONTH);

        private final int days;

        private Month(final int days) {
            this.days = days;
        }

        public static Month fromString(final String nameMonth) {
            if (nameMonth.equals("JU") || nameMonth.equals("J") || nameMonth.equals("A")
                    || nameMonth.equals("M") || nameMonth.equals("MA")) {
                throw new IllegalArgumentException("Ambiguity");
            }
            for (final Month month : values()) {
                if (month.name().equals(nameMonth) || month.name().toUpperCase().equals(nameMonth)  // Mese Completo
                        || month.name().toLowerCase().equals(nameMonth)) {
                    return month;
                }
                if (month.name().startsWith(nameMonth) || month.name().toUpperCase().startsWith(nameMonth)  // Mese Abbreviato
                        || month.name().toLowerCase().startsWith(nameMonth)) {
                    return month;
                }
            }
            throw new IllegalArgumentException("Not a Month");
        }

        public int getDays() {
            return this.days;
        }

    }
}
