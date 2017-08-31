package com.ap.dentalmanagementsystem.network.API;


import com.ap.dentalmanagementsystem.data.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IFirebaseRestService {
    @GET("patients/-KqqTCoEdPNtFH4vLkH5.json")
    Call<JsonObject> getPatientById();

    @GET("users/CLmsJAhTcVRB9m6FztkHue1mhdW2")
    Call<User> getUserById();

    @GET("/patients.json")
    Call<JsonObject> getPatients();

}
