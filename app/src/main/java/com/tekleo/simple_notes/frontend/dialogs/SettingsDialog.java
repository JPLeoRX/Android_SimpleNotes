package com.tekleo.simple_notes.frontend.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.SeekBar;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.backend.Note;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.util.gui.CustomDialog;


/**
 * Created by Leo on 03-Feb-16.
 */
public class SettingsDialog extends CustomDialog
{
    private Note note;
    private SeekBar seekBarTitleSize;
    private SeekBar seekBarTextSize;

    //----------------------------------------------------------------------------------------------
    //----------------------------------- Constructor ----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public SettingsDialog(MainActivity mainActivity, Note note) {
        super(mainActivity); this.note = note;
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
        this.setContentView(R.layout.dialog_settings);
        this.retrieveComponents(); this.createComponents(); this.styleComponents();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Getters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public SeekBar getSeekBarTextSize() {
        return seekBarTextSize;
    }

    public SeekBar getSeekBarTitleSize() {
        return seekBarTitleSize;
    }

    public Note getNote() {
        return note;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Utility --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void retrieveComponents() {
        seekBarTitleSize = (SeekBar) findViewById(R.id.seekTitle);
        seekBarTextSize = (SeekBar) findViewById(R.id.seekText);

        seekBarTitleSize.setMax(100);
        seekBarTextSize.setMax(100);

        seekBarTitleSize.setProgress((int) (note.getTitleSize() / 40.0 * 100.0));
        seekBarTextSize.setProgress((int) (note.getTextSize() / 40.0 * 100.0));

        seekBarTitleSize.setOnSeekBarChangeListener(new SettingsDialogListener(this));
        seekBarTextSize.setOnSeekBarChangeListener(new SettingsDialogListener(this));
    }

    @Override
    public void createComponents() {

    }

    @Override
    public void styleComponents() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
