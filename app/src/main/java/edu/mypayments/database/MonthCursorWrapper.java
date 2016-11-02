package edu.mypayments.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import edu.mypayments.Month;
import edu.mypayments.database.MonthDbModel.MonthTable;

/**
 * Created by Александр on 06.10.2016.
 */
public class MonthCursorWrapper extends CursorWrapper {
    public MonthCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Month getMonth(){
        String uuidString = getString(getColumnIndex(MonthTable.Columns.UUID));
        String title = getString(getColumnIndex(MonthTable.Columns.TITLE));
        //long date = getLong(getColumnIndex(MonthTable.Columns.DATE));
        int isClosed = getInt(getColumnIndex(MonthTable.Columns.CLOSED));
        /////////////////////////////////////////////////////////////////////////
        String last_month_1 = getString(getColumnIndex(MonthTable.Columns.LAST_MONTH_1));
        String last_month_2 = getString(getColumnIndex(MonthTable.Columns.LAST_MONTH_2));
        String last_month_3 = getString(getColumnIndex(MonthTable.Columns.LAST_MONTH_3));
        String last_month_4 = getString(getColumnIndex(MonthTable.Columns.LAST_MONTH_4));
        String this_month_1 = getString(getColumnIndex(MonthTable.Columns.THIS_MONTH_1));
        String this_month_2 = getString(getColumnIndex(MonthTable.Columns.THIS_MONTH_2));
        String this_month_3 = getString(getColumnIndex(MonthTable.Columns.THIS_MONTH_3));
        String this_month_4 = getString(getColumnIndex(MonthTable.Columns.THIS_MONTH_4));
        String total_1 = getString(getColumnIndex(MonthTable.Columns.TOTAL_1));
        String total_2 = getString(getColumnIndex(MonthTable.Columns.TOTAL_2));
        String total_3 = getString(getColumnIndex(MonthTable.Columns.TOTAL_3));
        String total_4 = getString(getColumnIndex(MonthTable.Columns.TOTAL_4));
        String tariff_power = getString(getColumnIndex(MonthTable.Columns.TARIFF_POWER));
        String tariff_gas = getString(getColumnIndex(MonthTable.Columns.TARIFF_GAS));
        String tariff_cool_water = getString(getColumnIndex(MonthTable.Columns.TARIFF_COOL_WATER));
        String tariff_hot_water = getString(getColumnIndex(MonthTable.Columns.TARIFF_HOT_WATER));
        String house_pay = getString(getColumnIndex(MonthTable.Columns.HOUSE_PAY));
        String garbage = getString(getColumnIndex(MonthTable.Columns.GARBAGE));
        String heating = getString(getColumnIndex(MonthTable.Columns.HEATING));
        String extra_pay = getString(getColumnIndex(MonthTable.Columns.EXTRA_PAY));
        String total = getString(getColumnIndex(MonthTable.Columns.TOTAL));

        Month month = new Month(UUID.fromString(uuidString));
        month.setTitle(title);
        //month.setDate(new Date(date));
        month.setMonthClosed(isClosed != 0);

        ///////////////////////////////////////////////////

        month.setLast_month_1(last_month_1);
        month.setLast_month_2(last_month_2);
        month.setLast_month_3(last_month_3);
        month.setLast_month_4(last_month_4);
        month.setThis_month_1(this_month_1);
        month.setThis_month_2(this_month_2);
        month.setThis_month_3(this_month_3);
        month.setThis_month_4(this_month_4);
        month.setTotal_1(total_1);
        month.setTotal_2(total_2);
        month.setTotal_3(total_3);
        month.setTotal_4(total_4);
        month.setTariff_power(tariff_power);
        month.setTariff_gas(tariff_gas);
        month.setTariff_cool_water(tariff_cool_water);
        month.setTariff_hot_water(tariff_hot_water);
        month.setHouse_pay(house_pay);
        month.setGarbage(garbage);
        month.setHeating(heating);
        month.setExtra_pay(extra_pay);
        month.setTotal(total);


        return  month;
    }
}
