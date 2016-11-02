package edu.mypayments.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.mypayments.database.MonthDbModel.MonthTable;

/**
 * Created by Александр on 06.10.2016.
 */
public class MonthBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASSE_NAME = "monthDB";

    public MonthBaseHelper(Context context) {
        super(context, DATABASSE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MonthTable.NAME + "(" +
        "_id integer primary key autoincrement, " +
        MonthTable.Columns.UUID + ", " +
        MonthTable.Columns.TITLE + ", " +
        //MonthTable.Columns.DATE + ", " +
        MonthTable.Columns.CLOSED + ", " +

        /////////////////////////////////////////////////
                MonthTable.Columns.LAST_MONTH_1 + ", " +
                MonthTable.Columns.LAST_MONTH_2 + ", " +
                MonthTable.Columns.LAST_MONTH_3 + ", " +
                MonthTable.Columns.LAST_MONTH_4 + ", " +
                MonthTable.Columns.THIS_MONTH_1 + ", " +
                MonthTable.Columns.THIS_MONTH_2 + ", " +
                MonthTable.Columns.THIS_MONTH_3 + ", " +
                MonthTable.Columns.THIS_MONTH_4 + ", " +
                MonthTable.Columns.TOTAL_1 + ", " +
                MonthTable.Columns.TOTAL_2 + ", " +
                MonthTable.Columns.TOTAL_3 + ", " +
                MonthTable.Columns.TOTAL_4 + ", " +
                MonthTable.Columns.TARIFF_POWER + ", " +
                MonthTable.Columns.TARIFF_GAS + ", " +
                MonthTable.Columns.TARIFF_COOL_WATER + ", " +
                MonthTable.Columns.TARIFF_HOT_WATER + ", " +
                MonthTable.Columns.HOUSE_PAY + ", " +
                MonthTable.Columns.GARBAGE + ", " +
                MonthTable.Columns.HEATING + ", " +
                MonthTable.Columns.EXTRA_PAY + ", " +
                MonthTable.Columns.TOTAL + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
