package com.ap.dentalmanagementsystem;

import android.app.Application;

import com.ap.dentalmanagementsystem.di.component.AppComponent;
import com.ap.dentalmanagementsystem.di.componentFactory.AppComponentFactory;

public class DMSApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentGraph();
    }

    private void buildComponentGraph() {
        appComponent = AppComponentFactory.create(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
