package com.pm.maincalendar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainCalendar<DataClass> extends ConstraintLayout {
    View rootView;
    public CalendarButton calendarButton;
    public OnMainClickListener onMainClickListener;

    private DataClass dataClass = null;
    private ArrayList<CalendarButton<DataClass>> mainCalendarButtons = new ArrayList();





    void init(Context context) {
        rootView = inflate(context, R.layout.main_calendar_layout, this);
        ArrayList<LinearLayout> arrayList;
        arrayList = new ArrayList<LinearLayout>();
        arrayList.add(this.findViewById(R.id.week_2));
        arrayList.add(this.findViewById(R.id.week_3));
        arrayList.add(this.findViewById(R.id.week_4));
        arrayList.add(this.findViewById(R.id.week_5));
        arrayList.add(this.findViewById(R.id.week_6));
        arrayList.add(this.findViewById(R.id.week_7));

  /*      for (int i = 1; i < this.getChildCount(); i++) {

            arrayList.add((LinearLayout) this.getChildAt(i));
        }*/
        initLayout(arrayList);


    }

    public MainCalendar(Context context) {
        super(context);
        init(context);

    }

    public MainCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        //dataClass = new CalendarButton<DataClass>(context, attrs);
    }

    public void setDataClass(DataClass dataClass) {
        this.dataClass = dataClass;
    }

    public CalendarButton<DataClass> getButton(LocalDate localDate){
        for(byte i = 0; i< mainCalendarButtons.size(); i++){
           if(mainCalendarButtons.get(i).publicKey.getDayOfMonth() == localDate.getDayOfMonth()){
               return mainCalendarButtons.get(i);
           }
        }
        return null;
    }

    public ArrayList<CalendarButton<DataClass>> getAllButtons(){
        return this.mainCalendarButtons;
    }


    private void initLayout(ArrayList<LinearLayout> layout) {
        layout.forEach(this::initButtons);
    }

    private void initButtons(LinearLayout k) {
        //short i;
        for (byte i = 0; i < k.getChildCount(); i++) {
            View v = k.getChildAt(i);
            if (v instanceof CalendarButton) {
                ((CalendarButton<?>) v).publicKey = LocalDate.of(2022, 3, 1).plusDays(i);
                mainCalendarButtons.add((CalendarButton) v);
                HashMap<LocalDate, DataClass> data = new HashMap();
                data.put(LocalDate.now(), dataClass);
                System.out.println("Data  " + k.getChildCount());
                ((CalendarButton<DataClass>) v).setData(
                        data.get(((CalendarButton<?>) v).publicKey));
                ((CalendarButton) v).setText(String.valueOf(i));


                ((CalendarButton<DataClass>) v).setOnInitListener(new CalendarButton.OnInitDataListener() {
                    @Override
                    public void onInit(HashMap localDatesWithValues) {
                        System.out.println("TETERETETETE" + localDatesWithValues);
                    }
                });
            }
        }
    }


    public interface OnMainClickListener {
        void onClick(MainCalendar mainCalendar, CalendarButton button);
    }


}
