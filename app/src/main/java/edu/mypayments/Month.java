package edu.mypayments;

import java.util.UUID;

import edu.mypayments.client.ClientModel;

/**
 * Created by Александр on 03.10.2016.
 */
public class Month {
    private UUID mId;
    private String mTitle;
    private boolean mMonthClosed;
    ///////////////////////////////////////
    private String last_month_1;
    private String last_month_2;
    private String last_month_3;
    private String last_month_4;
    private String this_month_1;
    private String this_month_2;
    private String this_month_3;
    private String this_month_4;
    private String total_1;
    private String total_2;
    private String total_3;
    private String total_4;
    private String tariff_power;
    private String tariff_gas;
    private String tariff_cool_water;
    private String tariff_hot_water;
    private String house_pay;
    private String garbage;
    private String heating;
    private String extra_pay;
    private String total;


    public Month() {
        this(UUID.randomUUID());
        tariff_power = Double.toString(ClientModel.TARIFF_ENERGY);
        tariff_gas = Double.toString(ClientModel.TARIFF_GAS);
    }

    public Month(UUID id){
        mId = id;

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean isMonthClosed() {
        return mMonthClosed;
    }

    public void setMonthClosed(boolean monthClosed) {
        mMonthClosed = monthClosed;
    }

    public String getLast_month_1() {
        return last_month_1;
    }

    public void setLast_month_1(String last_month_1) {
        this.last_month_1 = last_month_1;
    }

    public String getLast_month_2() {
        return last_month_2;
    }

    public void setLast_month_2(String last_month_2) {
        this.last_month_2 = last_month_2;
    }

    public String getLast_month_3() {
        return last_month_3;
    }

    public void setLast_month_3(String last_month_3) {
        this.last_month_3 = last_month_3;
    }

    public String getLast_month_4() {
        return last_month_4;
    }

    public void setLast_month_4(String last_month_4) {
        this.last_month_4 = last_month_4;
    }

    public String getThis_month_1() {
        return this_month_1;
    }

    public void setThis_month_1(String this_month_1) {
        this.this_month_1 = this_month_1;
    }

    public String getThis_month_2() {
        return this_month_2;
    }

    public void setThis_month_2(String this_month_2) {
        this.this_month_2 = this_month_2;
    }

    public String getThis_month_3() {
        return this_month_3;
    }

    public void setThis_month_3(String this_month_3) {
        this.this_month_3 = this_month_3;
    }

    public String getThis_month_4() {
        return this_month_4;
    }

    public void setThis_month_4(String this_month_4) {
        this.this_month_4 = this_month_4;
    }

    public String getTotal_1() {
        return total_1;
    }

    public void setTotal_1(String total_1) {
        this.total_1 = total_1;
    }

    public String getTotal_2() {
        return total_2;
    }

    public void setTotal_2(String total_2) {
        this.total_2 = total_2;
    }

    public String getTotal_3() {
        return total_3;
    }

    public void setTotal_3(String total_3) {
        this.total_3 = total_3;
    }

    public String getTotal_4() {
        return total_4;
    }

    public void setTotal_4(String total_4) {
        this.total_4 = total_4;
    }

    public String getTariff_power() {
        return tariff_power;
    }

    public void setTariff_power(String tariff_power) {
        this.tariff_power = tariff_power;
    }

    public String getTariff_gas() {
        return tariff_gas;
    }

    public void setTariff_gas(String tariff_gas) {
        this.tariff_gas = tariff_gas;
    }

    public String getTariff_cool_water() {
        return tariff_cool_water;
    }

    public void setTariff_cool_water(String tariff_cool_water) {
        this.tariff_cool_water = tariff_cool_water;
    }

    public String getTariff_hot_water() {
        return tariff_hot_water;
    }

    public void setTariff_hot_water(String tariff_hot_water) {
        this.tariff_hot_water = tariff_hot_water;
    }

    public String getHouse_pay() {
        return house_pay;
    }

    public void setHouse_pay(String house_pay) {
        this.house_pay = house_pay;
    }

    public String getGarbage() {
        return garbage;
    }

    public void setGarbage(String garbage) {
        this.garbage = garbage;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public String getExtra_pay() {
        return extra_pay;
    }

    public void setExtra_pay(String extra_pay) {
        this.extra_pay = extra_pay;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPhotoFileName(){
        return  "IMG_" + getId().toString() + ".jpg";
    }
}
