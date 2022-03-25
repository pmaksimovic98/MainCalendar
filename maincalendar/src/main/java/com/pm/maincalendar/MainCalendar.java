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
public class MainCalendar extends ConstraintLayout {
    View rootView;
    public Object dataClass = this;
    public CalendarButton calendarButton;
    public OnMainClickListener onMainClickListener;




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
        initButtons(arrayList);





    }

    public MainCalendar(Context context) {
        super(context);
        init(context);

    }

    public MainCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        dataClass = new CalendarButton<Object>(context, attrs);
    }

    public void initButtons(ArrayList<LinearLayout> layout) {
        System.out.println("TETERETETETE" + layout.size());
        layout.forEach((k -> {
            for (int i = 0; i < k.getChildCount(); i++) {
                View v = k.getChildAt(i);
                if (v instanceof CalendarButton) {
                    HashMap<LocalDate, Object> data = new HashMap();
                    data.put(LocalDate.now(), dataClass);

                    ArrayList<HashMap<LocalDate, Object>> datesWithValues = new ArrayList<>();
                    datesWithValues.add(data);

                    System.out.println("Data  " + k.getChildCount());

                    ((CalendarButton<Object>) v).setData(datesWithValues);
                    ((CalendarButton<Object>) v).setOnDayClickListener(new CalendarButton.OnDayClickListener() {
                        @Override
                        public void onClick(OnClickListener onClickListener, CalendarButton calendarButton) {
                            System.out.println("clicked " + calendarButton);
                            MainCalendar.this.calendarButton = calendarButton;
                            //onMainClickListener.onClick(MainCalendar.this, calendarButton);
                        }
                    });

                    ((CalendarButton<Object>) v).setOnInitListener(new CalendarButton.OnInitDataListener() {
                        @Override
                        public void onInit(ArrayList localDatesWithValues) {
                            System.out.println("TETERETETETE" + localDatesWithValues);
                        }
                    });
                }
            }
        }));

    }




    public void setOnClickListener(OnMainClickListener me) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                me.onClick(MainCalendar.this, calendarButton);

            }
        });
    }



    public interface OnMainClickListener {
        void onClick(MainCalendar mainCalendar, CalendarButton button);
    }


}
