package iman.reserve.hotel.activity.Fragments;




import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.adapter.RoomAdapter;
import iman.reserve.hotel.model.Message;

import iman.reserve.hotel.model.Room;
import iman.reserve.hotel.model.RoomList;
import iman.reserve.hotel.network.GetRoomDataService;
import iman.reserve.hotel.network.ReserveService;
import iman.reserve.hotel.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class OneFragment extends android.support.v4.app.Fragment implements com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener , AdapterView.OnItemSelectedListener {
    private EditText checkInDate,checkOutDate;
    private int inYear,inMonth,inDay;
    private String selectedDate,selhot,username,selroom,noroom;
    private EditText noroomEditText;
    private boolean checkInFlag = false;
    private Button reserve;
    private RoomAdapter adapter;
    private RecyclerView recyclerView;
    private View view;
    private SharedPreferences prefs;
    private Spinner spinner;


    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_one, container, false);
        prefs = getContext().getSharedPreferences("UserData", MODE_PRIVATE);
        username = prefs.getString("username","");
        selhot = prefs.getString("selectedhotel","");


        view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        spinner = view.findViewById(R.id.spinner);
        noroomEditText = view.findViewById(R.id.select_number_of_rooms_editText);
        noroomEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });
        Integer[] arraySpinner = new Integer[99];
        for(int i = 1 ; i <= 99 ; i++)
            arraySpinner[i-1] = i;

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        persianDate();
        doReserve();
        roomView();
        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // An item was selected. You can retrieve the selected item using
        noroom = parent.getItemAtPosition(pos).toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    void doReserve(){
        //noroom = view.findViewById(R.id.editText6);
        //type = view.findViewById(R.id.editText10);
        reserve = view.findViewById(R.id.rezfinal);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inString = checkInDate.getText().toString();
                String outString = checkOutDate.getText().toString();
                if(inString.equals("سال/ماه/روز")){
                    Snackbar.make(view,"انتخاب تاریخ ورود ضروری است",Snackbar.LENGTH_LONG).show();
                    checkInDate.setError("انتخاب تاریخ ورود ضروری است");
                }
                else if(outString.equals("سال/ماه/روز")){

                    Snackbar.make(view,"انتخاب تاریخ ورود ضروری است",Snackbar.LENGTH_LONG).show();
                    checkOutDate.setError("انتخاب تاریخ خروج ضروری است");
                }
                else if( Integer.parseInt(inString.replace('/','0')) > Integer.parseInt(outString.replace('/','0'))){
                    Snackbar.make(view,"تاریخ ورود نمیتواند بعد از تاریخ خروج باشد!",Snackbar.LENGTH_LONG);
                    //Toast.makeText(getContext(), "تاریخ ورود نمیتواند بعد از تاریخ خروج باشد!", Toast.LENGTH_SHORT).show();
                }
                else if( Integer.parseInt(noroom) > 50 ) { //TODO: REMOVE THIS TO REMOVE RESERVATION LIMIT
                    Snackbar.make(view, "حداکثر 50 اتاق قابل رزرو میباشد", Snackbar.LENGTH_LONG).show();

                }else {
                    final ProgressDialog pd= new ProgressDialog(getActivity());
                    pd.setMessage("لطفا منتظر بمانید");
                    pd.show();
                    ReserveService service = RetrofitInstance.getRetrofitInstance().create(ReserveService.class);
                    selroom = prefs.getString("selroom", "");
                    Call<Message> call = service.getReserveData(checkInDate.getText().toString(), checkOutDate.getText().toString(), Integer.parseInt(noroom), selroom, selhot, username);
                    Log.wtf("URL Called", call.request().url() + "");
                    call.enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {
                            pd.dismiss();
                            String result = response.body().getMessage();
                            //Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getContext());

                            dlgAlert.setMessage(result);
                            dlgAlert.setTitle("نتیجه رزرو");
                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dismiss the dialog
                                        }
                                    });
                            //dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            pd.dismiss();
                            Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

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

    void persianDate(){
        // Inflate the layout for this fragment
        final PersianCalendar persianCalendar = new PersianCalendar();
        checkInDate = (EditText) view.findViewById(R.id.checkIn);

        checkInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        OneFragment.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );
                datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
                checkInFlag = true;
                checkInDate.setError(null);
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
                checkOutDate.setError(null);
                //checkOutDate.setText(selectedDate);
            }
        });


    }


    void roomView(){
        /*Create handle for the RetrofitInstance interface*/
        GetRoomDataService service = RetrofitInstance.getRetrofitInstance().create(GetRoomDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<RoomList> call = service.getRoomData(selhot);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<RoomList>() {
            @Override
            public void onResponse(Call<RoomList> call, Response<RoomList> response) {
                generateEmployeeList(response.body().getRoomArrayList());

            }

            @Override
            public void onFailure(Call<RoomList> call, Throwable t) {

                Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<Room> empDataList) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_room_list);

        adapter = new RoomAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(adapter);
    }


}