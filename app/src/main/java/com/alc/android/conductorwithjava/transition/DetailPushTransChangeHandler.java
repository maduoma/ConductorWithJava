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
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.alc.android.conductorwithjava.countrydetail.CountryDetailView;
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandler;

//import androidx.core.view.animation.FastOutSlowInInterpolator;

//import android.support.annotation.Nullable;

//import android.support.annotation.NonNull;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class DetailPushTransChangeHandler extends TransitionChangeHandler {

    private static final String KEY_FLAG_TRANSITION_NAME = "key_flag_transition_name";

    private String flagViewTransitionName;

    public DetailPushTransChangeHandler() {
    }

    public DetailPushTransChangeHandler(String flagViewTransitionName) {
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

        if (to == null || !(to instanceof CountryDetailView)) {
            throw new IllegalArgumentException("The to view must be a CountryDetailView");
        }

        final CountryDetailView detailView = (CountryDetailView) to;

        detailView.flagView.setTransitionName(flagViewTransitionName);

        ChangeTransform changeTransform = new ChangeTransform();

        // Shared elements (the flag view in this case) are drawn in the window's view overlay during the transition by default.
        // That causes the favourite fab being drawn behind the flag when it is scaled up.
        // Setting the change transform not using overlay addresses this issue.
        changeTransform.setReparentWithOverlay(false);

        return new TransitionSet()
                .addTransition(new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeClipBounds())
                        .addTransition(changeTransform)
                        .addTransition(new ChangeImageTransform())
                        .setDuration(300))
                .addTransition(new Slide().addTarget(detailView.detailGroup).setStartDelay(150))
                .addTransition(new Scale().addTarget(detailView.favouriteFab).setStartDelay(300))
                .setInterpolator(new FastOutSlowInInterpolator());
    }
}
