package com.ap.dentalmanagementsystem.di.componentFactory;

import android.app.Application;

import com.ap.dentalmanagementsystem.di.component.AppComponent;
import com.ap.dentalmanagementsystem.di.component.DaggerAppComponent;
import com.ap.dentalmanagementsystem.di.module.AppModule;

public final class AppComponentFactory {

    private AppComponentFactory() {

    }

    public static AppComponent create(Application app) {
        return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
    }
}
