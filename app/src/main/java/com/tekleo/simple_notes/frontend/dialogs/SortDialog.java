package com.tekleo.simple_notes.frontend.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.RadioButton;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.backend.Current;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.helpers.SortHelper;
import com.tekleo.simple_notes.util.gui.CustomDialog;


/**
 * Created by Leo on 31-Jan-16.
 */
public class SortDialog extends CustomDialog
{
    private RadioButton btnSortAZ;
    private RadioButton btnSortZA;
    private RadioButton btnSortNew;
    private RadioButton btnSortOld;

    //----------------------------------------------------------------------------------------------
    //----------------------------------- Constructor ----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public SortDialog(MainActivity mainActivity) {
        super(mainActivity);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Life Cycle -----------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_sort);
        this.retrieveComponents(); this.createComponents(); this.styleComponents();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Getters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public RadioButton getBtnSortAZ() {
        return btnSortAZ;
    }

    public RadioButton getBtnSortZA() {
        return btnSortZA;
    }

    public RadioButton getBtnSortNew() {
        return btnSortNew;
    }

    public RadioButton getBtnSortOld() {
        return btnSortOld;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Utility --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void retrieveComponents() {
        btnSortAZ = (RadioButton) findViewById(R.id.btnSortAZ);
        btnSortZA = (RadioButton) findViewById(R.id.btnSortZA);
        btnSortNew = (RadioButton) findViewById(R.id.btnSortNew);
        btnSortOld = (RadioButton) findViewById(R.id.btnSortOld);

        btnSortAZ.setOnClickListener(new SortDialogListener(this));
        btnSortZA.setOnClickListener(new SortDialogListener(this));
        btnSortNew.setOnClickListener(new SortDialogListener(this));
        btnSortOld.setOnClickListener(new SortDialogListener(this));
    }

    @Override
    public void createComponents() {

    }

    @Override
    public void styleComponents() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (Current.DATA.getSortChoice() == SortHelper.SORT_AZ)
            btnSortAZ.setChecked(true);
        else if (Current.DATA.getSortChoice() == SortHelper.SORT_ZA)
            btnSortZA.setChecked(true);
        else if (Current.DATA.getSortChoice() == SortHelper.SORT_NEW_FIRST)
            btnSortNew.setChecked(true);
        else if (Current.DATA.getSortChoice() == SortHelper.SORT_OLD_FIRST)
            btnSortOld.setChecked(true);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
