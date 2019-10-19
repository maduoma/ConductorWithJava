package com.alc.android.conductorwithjava.controller;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alc.android.conductorwithjava.activity.MainActivity;
import com.bluelinelabs.conductor.Controller;

//import android.support.annotation.NonNull;

public abstract class BaseController extends Controller {

    protected BaseController() { }
    protected BaseController(Bundle args) {
        super(args);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);

        // Quick way to access the toolbar for demo purposes. Production app needs to have this done properly
        MainActivity activity = (MainActivity) getActivity();

        // Activity should have already been set after the conductor is attached.
        assert activity != null;

        activity.setToolBarTitle(getTitle());
        activity.enableUpArrow(getRouter().getBackstackSize() > 1);
    }

    protected abstract String getTitle();
}