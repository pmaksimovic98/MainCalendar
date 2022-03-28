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
    LocalDate publicKey;
    //= LocalDate.of(2022, 3, 28);
    private  DataClass changedData;
    private  DataClass oldData;

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

    private void init(){

    }

    public CalendarButton getButton() {
        return this;
    }
    public void setData(@NonNull DataClass localDatesWithValues){
        System.out.println("Dates with values" + localDatesWithValues);
        HashMap<LocalDate, DataClass> hashMap = new HashMap<LocalDate, DataClass> ();
        hashMap.put(publicKey, localDatesWithValues);

        data = new BaseCalendarData<DataClass>(hashMap);
        data.setBaseCalendarData(hashMap);
    }

    public void changeValueOfCalendarData(
            LocalDate key,
            DataClass newValue
    ) {
        System.out.println(key);
        oldData = data.getBaseCalendarData().get(key);
        data.changeValueOfCalendarData(key, newValue);
        changedData = newValue;
        onChangeDataListener.onChange(oldData, changedData);
        System.out.println("Data changed!2");
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

}

