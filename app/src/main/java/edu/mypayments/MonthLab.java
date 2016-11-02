package edu.mypayments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.mypayments.database.MonthBaseHelper;
import edu.mypayments.database.MonthCursorWrapper;
import edu.mypayments.database.MonthDbModel.MonthTable;

/**
 * Created by Александр on 03.10.2016.
 */
public class MonthLab {
    private static MonthLab sMonthLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MonthLab get(Context context){
        if (sMonthLab == null) {
            sMonthLab = new MonthLab(context);
        }
        return sMonthLab;
    }

    private MonthLab (Context context){
        mContext = context.getApplicationContext();
        mDatabase = new MonthBaseHelper(mContext).getWritableDatabase();
        //mMonthList = new ArrayList<>();
    }

    public List<Month> getMonthList(){
        List<Month> monthList = new ArrayList<>();
        MonthCursorWrapper cursor = queryMonth(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                monthList.add(cursor.getMonth());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return monthList;
    }

    public Month getMonth (UUID id){
        MonthCursorWrapper cursor = queryMonth(
                MonthTable.Columns.UUID + " = ?",
                new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMonth();
        }finally {
            cursor.close();
        }
    }

    public  void addMonth (Month m){
        ContentValues values = getContentValues(m);
        mDatabase.insert(MonthTable.NAME, null, values);
    }

    public void removeMonth (Month m){
        mDatabase.delete(MonthTable.NAME,
                MonthTable.Columns.UUID + " = ?",
                new String[]{m.getId().toString()});
    }

    private static ContentValues getContentValues(Month month){
        ContentValues values = new ContentValues();
        values.put(MonthTable.Columns.UUID, month.getId().toString());
        values.put(MonthTable.Columns.TITLE, month.getTitle());
        //values.put(MonthTable.Columns.DATE, month.getDate().getTime());
        values.put(MonthTable.Columns.CLOSED, month.isMonthClosed() ? 1 : 0);
        values.put(MonthTable.Columns.LAST_MONTH_1, month.getLast_month_1());
        values.put(MonthTable.Columns.LAST_MONTH_2, month.getLast_month_2());
        values.put(MonthTable.Columns.LAST_MONTH_3, month.getLast_month_3());
        values.put(MonthTable.Columns.LAST_MONTH_4, month.getLast_month_4());
        values.put(MonthTable.Columns.THIS_MONTH_1, month.getThis_month_1());
        values.put(MonthTable.Columns.THIS_MONTH_2, month.getThis_month_2());
        values.put(MonthTable.Columns.THIS_MONTH_3, month.getThis_month_3());
        values.put(MonthTable.Columns.THIS_MONTH_4, month.getThis_month_4());
        values.put(MonthTable.Columns.TOTAL_1, month.getTotal_1());
        values.put(MonthTable.Columns.TOTAL_2, month.getTotal_2());
        values.put(MonthTable.Columns.TOTAL_3, month.getTotal_3());
        values.put(MonthTable.Columns.TOTAL_4, month.getTotal_4());
        values.put(MonthTable.Columns.TARIFF_POWER, month.getTariff_power());
        values.put(MonthTable.Columns.TARIFF_GAS, month.getTariff_gas());
        values.put(MonthTable.Columns.TARIFF_COOL_WATER, month.getTariff_cool_water());
        values.put(MonthTable.Columns.TARIFF_HOT_WATER, month.getTariff_hot_water());
        values.put(MonthTable.Columns.HOUSE_PAY, month.getHouse_pay());
        values.put(MonthTable.Columns.GARBAGE, month.getGarbage());
        values.put(MonthTable.Columns.HEATING, month.getHeating());
        values.put(MonthTable.Columns.EXTRA_PAY, month.getExtra_pay());
        values.put(MonthTable.Columns.TOTAL, month.getTotal());
        return values;
    }

    public void updateMonth(Month month){
        String uuidString = month.getId().toString();
        ContentValues values = getContentValues(month);

        mDatabase.update(MonthTable.NAME, values, MonthTable.Columns.UUID + " = ?",
                new String[]{uuidString});
    }

    private MonthCursorWrapper queryMonth (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                MonthTable.NAME,
                null, //все столбцы
                whereClause,
                whereArgs,
                null,null,null
        );
        return new MonthCursorWrapper(cursor);
    }

    public File getPhotoFile(Month month){
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return  new File(externalFilesDir, month.getPhotoFileName());
    }

    public static MonthLab getMonthLab() {
        return sMonthLab;
    }
}
