package com.tekleo.simple_notes.frontend.dialogs;

import android.widget.SeekBar;

import com.tekleo.simple_notes.backend.Current;


/**
 * Created by Leo on 03-Feb-16.
 */
public class SettingsDialogListener implements SeekBar.OnSeekBarChangeListener
{
    private SettingsDialog settingsDialog;

    public SettingsDialogListener(SettingsDialog settingsDialog) {
        this.settingsDialog = settingsDialog;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int size = (int) ((progress / 100.0) * 40);

        if (seekBar == settingsDialog.getSeekBarTitleSize()) {
            if (size > 6)
                settingsDialog.getNote().setTitleSize(size);
            else
                settingsDialog.getNote().setTitleSize(6);
        }

        else if (seekBar == settingsDialog.getSeekBarTextSize()) {
            if (size > 6)
                settingsDialog.getNote().setTextSize(size);
            else
                settingsDialog.getNote().setTextSize(6);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Current.saveFile(settingsDialog.getContext());
    }
}
