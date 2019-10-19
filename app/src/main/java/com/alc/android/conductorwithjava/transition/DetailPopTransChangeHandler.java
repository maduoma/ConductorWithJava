package com.alc.android.conductorwithjava.transition;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alc.android.conductorwithjava.countrydetail.CountryDetailView;
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandler;

//import android.support.annotation.Nullable;

//import android.support.annotation.NonNull;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class DetailPopTransChangeHandler extends TransitionChangeHandler {

    private static final String KEY_FLAG_TRANSITION_NAME = "key_flag_transition_name";

    private String flagViewTransitionName;

    public DetailPopTransChangeHandler() {
        this.flagViewTransitionName = null;
    }

    public DetailPopTransChangeHandler(String flagViewTransitionName) {
        this.flagViewTransitionName = flagViewTransitionName;
    }

    @Override
    public void saveToBundle(@NonNull Bundle bundle) {
        bundle.putString(KEY_FLAG_TRANSITION_NAME, flagViewTransitionName);
    }

    @Override
    public void restoreFromBundle(@NonNull Bundle bundle) {
        flagViewTransitionName = bundle.getString(KEY_FLAG_TRANSITION_NAME);
    }

    @NonNull
    @Override
    protected Transition getTransition(@NonNull ViewGroup container,
                                       @Nullable View from,
                                       @Nullable View to,
                                       boolean isPush) {
        if (from == null || !(from instanceof CountryDetailView))
            throw new IllegalArgumentException("The from view must be a CountryDetailView");

        CountryDetailView detailView = (CountryDetailView) from;
        detailView.flagView.setTransitionName(flagViewTransitionName);

        return new TransitionSet()
                .addTransition(new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeClipBounds())
                        .addTransition(new ChangeTransform())
                        .addTransition(new ChangeImageTransform()))
                .addTransition(new Slide().addTarget(detailView.detailGroup))
                .addTransition(new Scale().addTarget(detailView.favouriteFab));
    }
}
