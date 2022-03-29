package com.pm.maincalendar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.time.LocalDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.HashMap;


@RequiresApi(api = Build.VERSION_CODES.O)
public class CalendarButton<DataClass>
        extends AppCompatTextView {

    public BaseCalendarData<DataClass> data;
    public LocalDate publicKey;

    private OnChangeDataListener<DataClass> onChangeDataListener;

    public CalendarButton(Context context) {
        super(context);
        init();
    }

    public CalendarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setData(@NonNull DataClass localDatesWithValues){
        HashMap<LocalDate, DataClass> hashMap = new HashMap<> ();
        hashMap.put(publicKey, localDatesWithValues);
        data = new BaseCalendarData<>(hashMap);
        data.setBaseCalendarData(hashMap);
    }

    public void changeValueOfCalendarData(
            LocalDate key,
            DataClass newValue
    ) {
        DataClass changedData;
        DataClass oldData;
        oldData = data.getBaseCalendarData().get(key);
        data.changeValueOfCalendarData(key, newValue);
        changedData = newValue;
        onChangeDataListener.onChange(oldData, changedData);
    }

    public BaseCalendarData<DataClass> getCalendarData(){
        return this.data;
    }

    public void setOnChangedDataListener(OnChangeDataListener<DataClass> e){
        onChangeDataListener = e;
    }

    public void setOnInitListener(OnInitDataListener<DataClass> e){
        e.onInit(data.baseCalendarData);
    }

    public void setOnDayClickListener(OnDayClickListener e){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                e.onClick(this, CalendarButton.this);
            }
        });
    }

    public interface OnChangeDataListener<DataClass> {
        void onChange(DataClass oldValue,
                      DataClass newValue);
    }

    public interface OnInitDataListener<DataClass> {
        void onInit(HashMap<LocalDate, DataClass> localDatesWithValues);
    }

    public interface OnDayClickListener{
        void onClick(OnClickListener onClickListener, CalendarButton calendarButton);
    }


    private void init(){

    }

}

