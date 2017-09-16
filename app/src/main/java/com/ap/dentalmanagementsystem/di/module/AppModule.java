package com.ap.dentalmanagementsystem.di.module;

import android.app.Application;

import com.ap.dentalmanagementsystem.model.AppStateModel;
import com.ap.dentalmanagementsystem.network.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    public FirebaseDatabase provideFirbaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    public AppStateModel provideAppStateModel() {
        return new AppStateModel();
    }

    @Singleton
    @Provides
    public FirebaseService provideFirbaseService(FirebaseDatabase firebaseDatabase, FirebaseAuth firebaseAuth, AppStateModel appStateModel) {
        return new FirebaseService(firebaseDatabase, firebaseAuth, appStateModel);
    }
}
