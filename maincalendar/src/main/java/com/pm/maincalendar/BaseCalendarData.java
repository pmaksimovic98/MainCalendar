package com.pm.maincalendar;



import androidx.annotation.NonNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseCalendarData<DataClass> {
    //abstraktna klasa koja bi trebala da uzme public/data klasu namjenju za upravljanje podacima prema datumu i zbog rada ne moze da bude null !
    protected HashMap<LocalDate, DataClass> baseCalendarData;
/*
*     public OnChangeDataListener<DataClass> onChangeDataListener = new OnChangeDataListener<DataClass>() {
        @Override
        public void onChange(HashMap<LocalDate, DataClass> oldValue, HashMap<LocalDate, DataClass> newValue) {

        }
    };
    public OnInitDataListener<DataClass> onInitDataListener = new OnInitDataListener<DataClass>() {
        @Override
        public void onInit(ArrayList<HashMap<LocalDate, DataClass>> localDatesWithValues) {
            System.out.println("Init   ");

        }
    };
* */

    public BaseCalendarData(
            HashMap<LocalDate, DataClass> localDatesWithValues) {
        this.baseCalendarData = localDatesWithValues;
        System.out.println("Local data valiues "+ localDatesWithValues);
        //onInitDataListener.onInit(localDatesWithValues);
    }

    HashMap<LocalDate, DataClass>
    getBaseCalendarData() {
        return this.baseCalendarData;
    }

    public void setBaseCalendarData(@NonNull HashMap<LocalDate, DataClass> localDatesWithValues) {
        this.baseCalendarData = localDatesWithValues;
    }

    public void changeValueOfCalendarData(
            @NonNull LocalDate key,
            @NonNull DataClass newValue
    ) {
        this.baseCalendarData.replace(key, newValue);

    }



}




