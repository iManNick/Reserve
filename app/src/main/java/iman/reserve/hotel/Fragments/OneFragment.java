package iman.reserve.hotel.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import iman.reserve.hotel.R;


public class OneFragment extends android.support.v4.app.Fragment implements com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    private EditText checkInDate,checkOutDate;
    private int inYear,inMonth,inDay;
    private String selectedDate;
    private boolean checkInFlag = false;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        selectedDate = year + "/" + (monthOfYear+1) + "/" + dayOfMonth;
        if (checkInFlag) {
            checkInDate.setText(selectedDate);
        }
        else{
            checkOutDate.setText(selectedDate);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);


        // Inflate the layout for this fragment
        checkInDate = (EditText) view.findViewById(R.id.checkIn);
        checkInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersianCalendar persianCalendar = new PersianCalendar();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        OneFragment.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );
                datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
                checkInFlag = true;
                //checkInDate.setText(selectedDate);
            }
        });
        checkOutDate = (EditText) view.findViewById(R.id.checkOut);
        checkOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersianCalendar persianCalendar = new PersianCalendar();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        OneFragment.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()+1
                );
                datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
                checkInFlag = false;
                //checkOutDate.setText(selectedDate);
            }
        });
        //checkOutDate.setOnClickListener();
/*        checkOutDate.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // To show current date in the datepicker
                        PersianCalendar initDate = new PersianCalendar();
                        initDate.setPersianDate(PersianDatePickerDialog.THIS_YEAR,PersianDatePickerDialog.THIS_MONTH,PersianDatePickerDialog.THIS_DAY);
                        PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
                                .setPositiveButtonString("باشه")
                                .setNegativeButton("بیخیال")
                                .setTodayButton("امروز")
                                .setTodayButtonVisible(true)
                                .setInitDate(initDate)
                                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                                .setMinYear(1300)
                                .setActionTextColor(R.color.colorPrimary);
                                .setTypeFace(typeface)
                                .setListener(new Listener() {
                                    @Override
                                    public void onDateSelected(PersianCalendar persianCalendar) {
                                        checkOutDate.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                                    }

                                    @Override
                                    public void onDismissed() {

                                    }
                                });

                        picker.show();
                    }
                });
*/

        return view;

    }
}