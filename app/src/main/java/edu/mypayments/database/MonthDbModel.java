package edu.mypayments.database;

/**
 * Created by Александр on 06.10.2016.
 */
public class MonthDbModel {
    public  static final class MonthTable {
        public static final String NAME = "allMonth";


        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            //public static final String DATE = "date";
            public static final String CLOSED = "closed";
            ///////////////////////////////////////////////////////
            public static final String LAST_MONTH_1 = "last_month_1";
            public static final String LAST_MONTH_2 = "last_month_2";
            public static final String LAST_MONTH_3 = "last_month_3";
            public static final String LAST_MONTH_4 = "last_month_4";
            public static final String THIS_MONTH_1 = "this_month_1";
            public static final String THIS_MONTH_2 = "this_month_2";
            public static final String THIS_MONTH_3 = "this_month_3";
            public static final String THIS_MONTH_4 = "this_month_4";
            public static final String TOTAL_1 = "total_1";
            public static final String TOTAL_2 = "total_2";
            public static final String TOTAL_3 = "total_3";
            public static final String TOTAL_4 = "total_4";
            public static final String TARIFF_POWER = "tariff_power";
            public static final String TARIFF_GAS = "tariff_gas";
            public static final String TARIFF_COOL_WATER = "tariff_cool_water";
            public static final String TARIFF_HOT_WATER = "tariff_hot_water";
            public static final String HOUSE_PAY = "house_pay";
            public static final String GARBAGE = "garbage";
            public static final String HEATING = "heating";
            public static final String EXTRA_PAY = "extra_pay";
            public static final String TOTAL = "total";
        }
    }
}
