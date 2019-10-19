package com.alc.android.conductorwithjava.countrygrid;

//import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.alc.android.conductorwithjava.R;
import com.alc.android.conductorwithjava.controller.BaseController;
import com.alc.android.conductorwithjava.countrydetail.CountryDetailController;
import com.alc.android.conductorwithjava.model.Country;
import com.alc.android.conductorwithjava.transition.DetailPopAnimChangeHandler;
import com.alc.android.conductorwithjava.transition.DetailPopTransChangeHandler;
import com.alc.android.conductorwithjava.transition.DetailPushAnimChangeHandler;
import com.alc.android.conductorwithjava.transition.DetailPushTransChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandlerCompat;

public class CountryGridController extends BaseController implements GridEventHandler {

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        CountryGridView view = (CountryGridView) inflater.inflate(R.layout.country_grid, container, false);
        view.setEventHandler(this);
        return view;
    }

    @Override
    public void onCountryClicked(@NonNull Country country) {
        // For demo purposes, use animator change handler for countries with name starting with
        // a character before 'i' in the alphabet. For other countries, use transition change handler if the app is running on
        // API level 21+. Use the mentioned animator change handler otherwise.
        boolean countryNameFirstCharBeforeI = country.getName().toLowerCase().charAt(0) < 'i';

        ControllerChangeHandler pushHandler
                = countryNameFirstCharBeforeI ? new DetailPushAnimChangeHandler()
                : new TransitionChangeHandlerCompat(new DetailPushTransChangeHandler(country.getName()), new DetailPushAnimChangeHandler());

        ControllerChangeHandler popHandler
                = countryNameFirstCharBeforeI ? new DetailPopAnimChangeHandler()
                : new TransitionChangeHandlerCompat(new DetailPopTransChangeHandler(country.getName()), new DetailPopAnimChangeHandler());

        getRouter().pushController(RouterTransaction.with(new CountryDetailController(country))
                .pushChangeHandler(pushHandler)
                .popChangeHandler(popHandler));
    }

    @Override
    protected String getTitle() {
        return getActivity().getString(R.string.countries);
    }
}
