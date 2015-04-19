package com.example.rex.homework6_1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;




public class MainActivity extends ActionBarActivity {
    private static TextView txtView;
    private Spinner spinner;
    private static int year, month, day, hour, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviews();
    }

    protected void findviews() {
        txtView = (TextView) findViewById(R.id.txtView);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection(0, true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = parent.getItemAtPosition(position).toString();

                    if (select.equals("DatePicker")) {
                        onDateClick(view);
                    } else if (select.equals("TimePicker")) {
                        onTimeClick(view);
                    }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onDateClick(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        FragmentManager fm = getSupportFragmentManager();
        datePickerFragment.show(fm, "datePicker");
    }

    public void onTimeClick(View view) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        android.app.FragmentManager fm = getFragmentManager();
        timePickerFragment.show(fm, "timePicker");
    }

    private void showInfo() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DATE);
        hour = c.get(Calendar.HOUR);
        min = c.get(Calendar.MINUTE);
        updateinfo();
    }

    public static void updateinfo() {
        txtView.setText(new StringBuilder()
                .append(year).append("-").append(parseNum(month)).append("-").append(parseNum(day))
                .append("-").append(hour).append(":").append(min));

    }


    private static String parseNum(int day) {
        if (day >= 10)
            return String.valueOf(day);
        else
            return "0" + String.valueOf(day);
    }

    public static class DatePickerFragment extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // 建立DatePickerDialog物件
            // this為OnDateSetListener物件
            // year、month、day會成為日期挑選器預選的年月日
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getActivity(),this,year,month,day
            );
            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            year=y;
            month=m+1;
            day=d;
            updateinfo();
        }
    }

    public static class TimePickerFragment extends android.app.DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    getActivity(),this,hour,min,true
            );
            return timePickerDialog;
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int shour, int sminute) {
            hour=shour;
            min=sminute;
            updateinfo();
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
