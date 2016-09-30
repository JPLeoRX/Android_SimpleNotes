package com.tekleo.simple_notes.util.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tekleo.simple_notes.backend.Current;
import com.tekleo.simple_notes.backend.Data;
import com.tekleo.simple_notes.helpers.ScreenHelper;
import com.tekleo.simple_notes.helpers.SortHelper;

/**
 * Created by Leo on 03-Feb-16.
 */
public abstract class CustomActivity extends AppCompatActivity implements HasComponents, CreatesComponents, StylesComponents
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!ScreenHelper.loaded) {
            ScreenHelper.initialize(this);
        }

        if (!Current.isReadyData()) {
            Current.loadFile(this);
            if (!Current.isReadyData()) {
                Current.DATA = new Data();
            }
        }

        SortHelper.SORT_CURRENT = Current.DATA.getSortChoice();
    }

    @Override
    public abstract void retrieveComponents();

    @Override
    public abstract void createComponents();

    @Override
    public abstract void styleComponents();
}
