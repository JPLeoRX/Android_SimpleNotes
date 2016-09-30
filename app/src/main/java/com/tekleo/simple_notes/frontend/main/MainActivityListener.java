package com.tekleo.simple_notes.frontend.main;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.tekleo.simple_notes.backend.Current;
import com.tekleo.simple_notes.frontend.dialogs.InfoDialog;
import com.tekleo.simple_notes.frontend.dialogs.SortDialog;
import com.tekleo.simple_notes.frontend.dialogs.note.AddNoteDialog;


/**
 * Created by Leo on 31-Jan-16.
 */
public class MainActivityListener implements View.OnClickListener, TextWatcher
{
    private MainActivity mainActivity;

    //----------------------------------------------------------------------------------------------
    //----------------------------------- Constructors ---------------------------------------------
    //----------------------------------------------------------------------------------------------
    public MainActivityListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Buttons --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        if (v == mainActivity.getBtnAdd())
            this.respondToAdd();

        else if (v == mainActivity.getBtnSort())
            this.respondToSort();

        else if (v == mainActivity.getBtnSearch())
            this.respondToSearch();

        else if (v == mainActivity.getBtnInfo())
            this.respondToInfo();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //---------------------------------- Edit Text -------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Current.searchStart(s.toString());
        mainActivity.refresh();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Responses ------------------------------------------------
    //----------------------------------------------------------------------------------------------
    private void respondToAdd() {
        AddNoteDialog dialog = new AddNoteDialog(mainActivity);
        dialog.show();
    }


    private void respondToSearch() {
        if (!mainActivity.isSearching())
            mainActivity.startSearch();
        else
            mainActivity.finishSearch();
    }

    private void respondToSort() {
        SortDialog dialog = new SortDialog(mainActivity);
        dialog.show();
    }

    private void respondToInfo() {
        InfoDialog dialog = new InfoDialog(mainActivity);
        dialog.show();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
