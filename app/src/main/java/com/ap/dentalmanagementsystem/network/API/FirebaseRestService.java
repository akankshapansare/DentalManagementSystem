package com.ap.dentalmanagementsystem.network.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseRestService {

    private static IFirebaseRestService firebaseRestService;

    private FirebaseRestService() {

    }

    public static synchronized IFirebaseRestService getInstance() {
        if (firebaseRestService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://dental-management-system.firebaseio.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            firebaseRestService = retrofit.create(IFirebaseRestService.class);
        }
        return firebaseRestService;

    }
}
