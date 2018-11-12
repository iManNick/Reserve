
package iman.reserve.hotel.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.model.LoginResult;

import iman.reserve.hotel.network.LoginService;
import iman.reserve.hotel.network.ReserveService;
import iman.reserve.hotel.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity {

    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        super.finish();
    }
    private EditText editEmail, editPassword, editName, editPhone;
    private Button btnSignIn, btnRegister,btnBack;
    private TextInputLayout layoutEmail,layoutPhone;
    //String URL = "http://10.0.3.2/hotelPHP/index.php";
    //10.0.3.2
//192.168.1.7
    //JSONParser jsonParser = new JSONParser();
    private LoginService service;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        service = RetrofitInstance.getRetrofitInstance().create(LoginService.class);
        SharedPreferences prefs;
        prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String savedun = prefs.getString("username", "");
        String savedpwd = prefs.getString("password", "");


        setTitle("ورود/ثبت نام");
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        layoutEmail = findViewById(R.id.email_Layout);
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        editPhone = findViewById(R.id.editPhone);
        layoutPhone = findViewById(R.id.phone_Layout);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);

        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        if(!savedun.equals("") && !savedpwd.equals("")){
            Intent intent = new Intent(getApplicationContext(), CityActivity.class);
            //intent.putExtra("USERNAME",editName.getText().toString());
            startActivity(intent);

            /*Snackbar.make(view,"در حال وارد شدن به حساب "+savedun,Snackbar.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), "در حال وارد شدن به حساب "+ savedun, Toast.LENGTH_LONG).show();
            editName.setText(savedun);
            editPassword.setText(savedpwd);
            doLogin(savedun,savedpwd);*/
        }


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (editName.getText().toString().equals("")) {
                    editName.setError("نام کاربری ضروری است");
                    //Snackbar.make(getWindow().getDecorView().getRootView(),"نام کاربری ضروری است",Snackbar.LENGTH_SHORT);
                    //Toast.makeText(getApplicationContext(), "نام کاربری ضروری است", Toast.LENGTH_LONG).show();
                } else if (editPassword.getText().toString().equals("")) {
                    editPassword.setError("رمز عبور ضروری است");
                    //Toast.makeText(getApplicationContext(), "رمز عبور ضروری است", Toast.LENGTH_LONG).show();
                } else {
                    final ProgressDialog pd= new ProgressDialog(LoginActivity.this);
                    pd.setMessage("در حال بررسی");
                    pd.show();

                    //Snackbar.make(view,"در حال بررسی",Snackbar.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "در حال وارد شدن به حساب", Toast.LENGTH_SHORT).show();
                    doLogin(editName.getText().toString(), editPassword.getText().toString(),pd);

                }
            }
        });
        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String email = editEmail.getText().toString();
                if (!checkEmail(email) && !email.equals("")) {
                    editEmail.setError("فرمت ایمیل نادرست است");
                }
            }
        });
        editPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String phone = editPhone.getText().toString();
                if (!checkPhone(phone) && !phone.equals("")) {
                    editPhone.setError("فرمت شماره تلفن نادرست است");
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setText("ثبت نام");
                editEmail.setVisibility(GONE);
                layoutEmail.setVisibility(GONE);
                editPhone.setVisibility(GONE);
                layoutPhone.setVisibility(GONE);
                btnSignIn.setVisibility(VISIBLE);
                btnBack.setVisibility(GONE);
                i=0;
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phone = editPhone.getText().toString();
                        String email = editEmail.getText().toString();
                        String txt = "";
                        if (i == 0) {
                            i = 1;
                            editEmail.setVisibility(VISIBLE);
                            layoutEmail.setVisibility(VISIBLE);
                            editPhone.setVisibility(VISIBLE);
                            layoutPhone.setVisibility(VISIBLE);
                            btnSignIn.setVisibility(GONE);
                            txt = "ایجاد حساب";
                            btnRegister.setText(txt);
                            btnBack.setVisibility(VISIBLE);
                        } else {
                            i = 0;
                            if (editName.getText().toString().equals("")) {
                                editName.setError("نام کاربری ضروری است");
                                //Toast.makeText(getApplicationContext(), "نام کاربری ضروری است", Toast.LENGTH_LONG).show();
                            } else if (editPassword.getText().toString().equals("")) {
                                editPassword.setError("رمز عبور ضروری است");
                                //Toast.makeText(getApplicationContext(), "رمز عبور ضروری است", Toast.LENGTH_LONG).show();
                            } else if (email.equals("") || !checkEmail(email)) {
                                editEmail.setError("لطفا ایمیل را به درستی وارد کنید");
                            }
                            else if (phone.equals("") || !checkPhone(phone)) {
                                    editPhone.setError("لطفا شماره تلفن را به درستی وارد کنید");
                                //Toast.makeText(getApplicationContext(), "ایمیل ضروری است", Toast.LENGTH_LONG).show();
                            } else {

                                final ProgressDialog pd= new ProgressDialog(LoginActivity.this);
                                pd.setMessage("در حال ثبت نام");
                                pd.show();
                                //Snackbar.make(view,"",Snackbar.LENGTH_SHORT).show();
                                //AttemptLogin attemptLogin = new AttemptLogin();
                                //attemptLogin.execute(editName.getText().toString(), editPassword.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString());
                                Call<LoginResult> call = service.getLoginData(editName.getText().toString(), editPassword.getText().toString(), editEmail.getText().toString(), editPhone.getText().toString());

                                Log.wtf("URL Called", call.request().url() + "");
                                call.enqueue(new Callback<LoginResult>() {
                                    @Override
                                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                                        pd.dismiss();

                                        openCityActivity(response.body().getLoginSuccess(), response.body().getLoginMessage());
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResult> call, Throwable t) {
                                        pd.dismiss();
                                        Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }

                    }
                });



    }
    private boolean checkEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean checkPhone(String phone){
        return phone.matches("^[9][0-9]{9}$");

    }

    private void doLogin(String username, String password, final ProgressDialog pd){

        //AttemptLogin attemptLogin = new AttemptLogin();
        //attemptLogin.execute(editName.getText().toString(), editPassword.getText().toString(), "", "");

        Call<LoginResult> call = service.getLoginData(username,password, "", "");
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                pd.dismiss();
                openCityActivity(response.body().getLoginSuccess(), response.body().getLoginMessage());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void openCityActivity(String success, String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        if (success.equals("1")) {
            Intent intent = new Intent(getApplicationContext(), CityActivity.class);
            //intent.putExtra("USERNAME",editName.getText().toString());
            startActivity(intent);

            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", editName.getText().toString());
            editor.putString("password", editPassword.getText().toString());
            editor.apply();
            finish();
        }
    }


}


    /*
    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String phone = args[3];
            String email = args[2];
            String password = args[1];
            String name= args[0];


            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));
            if(phone.length()>0)
                params.add(new BasicNameValuePair("phone",phone));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    if(result.getString("success").equals("1")){
                        Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                        //intent.putExtra("USERNAME",editName.getText().toString());
                        getApplicationContext().startActivity(intent);

                        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("username", editName.getText().toString());
                        editor.putString("password", editPassword.getText().toString());
                        editor.apply();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "عدم دسترسی به سرور", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }*/

