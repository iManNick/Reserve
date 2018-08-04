package iman.reserve.hotel.network;

import iman.reserve.hotel.model.EmployeeList;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReserveService {
    @POST("retrofit-demo.php")
    Call<EmployeeList> getEmployeeData(@Query("company_no") int companyNo);
}