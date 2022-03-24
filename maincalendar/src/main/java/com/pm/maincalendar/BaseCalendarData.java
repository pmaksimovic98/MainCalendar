package com.pm.maincalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;

class BaseCalendarData<DataClass> {
    //abstraktna klasa koja bi trebala da uzme public/data klasu namjenju za upravljanje podacima prema datumu i zbog rada ne moze da bude null !
    protected ArrayList<HashMap<LocalDate, DataClass>> baseCalendarData;
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
            ArrayList<HashMap<LocalDate, DataClass>> localDatesWithValues) {
        this.baseCalendarData = localDatesWithValues;
        System.out.println("Local data valiues "+ localDatesWithValues);
        //onInitDataListener.onInit(localDatesWithValues);
    }

    ArrayList<HashMap<LocalDate, DataClass>>
    getBaseCalendarData() {
        return this.baseCalendarData;
    }

    public void setBaseCalendarData(@NonNull ArrayList<HashMap<LocalDate, DataClass>> localDatesWithValues) {
        this.baseCalendarData = localDatesWithValues;
    }

    public void changeValueOfCalendarData(
            HashMap<LocalDate, DataClass> oldValue,
            HashMap<LocalDate, DataClass> newValue
    ) {
        this.baseCalendarData.set(this.baseCalendarData.indexOf(oldValue), newValue);
        //onChangeDataListener.onChange(oldValue, newValue);
    }




}




