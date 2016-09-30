package com.tekleo.simple_notes.util.gui;

import android.app.Dialog;

import com.tekleo.simple_notes.frontend.main.MainActivity;

/**
 * Created by Leo on 31-Jan-16.
 */
public abstract class CustomDialog extends Dialog implements HasComponents, CreatesComponents, StylesComponents
{
    protected MainActivity mainActivity;

    public CustomDialog(MainActivity mainActivity) {
        super(mainActivity); this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void activityRefresh() {
        mainActivity.refresh();
    }

    @Override
    public abstract void retrieveComponents();

    @Override
    public abstract void createComponents();

    @Override
    public abstract void styleComponents();
}
