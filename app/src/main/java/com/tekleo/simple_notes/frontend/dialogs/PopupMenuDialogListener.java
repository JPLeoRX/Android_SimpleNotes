package com.tekleo.simple_notes.frontend.dialogs;

import android.view.View;

import com.tekleo.simple_notes.backend.Current;
import com.tekleo.simple_notes.frontend.dialogs.note.EditNoteDialog;

/**
 * Created by Leo on 02-Feb-16.
 */
public class PopupMenuDialogListener implements View.OnClickListener
{
    private PopupMenuDialog popupMenuDialog;

    public PopupMenuDialogListener(PopupMenuDialog popupMenuDialog) {
        this.popupMenuDialog = popupMenuDialog;
    }

    @Override
    public void onClick(View v) {
        if (v == popupMenuDialog.getBtnDelete()) {
            // Remove from data
            Current.DATA.remove(popupMenuDialog.getNote());

            // Refresh view
            popupMenuDialog.activityRefresh();

            // Save changes
            Current.saveFile(popupMenuDialog.getContext());

            // Close dialog
            popupMenuDialog.dismiss();
        }

        if (v == popupMenuDialog.getBtnEdit()) {
            // Close dialog
            popupMenuDialog.dismiss();

            // Create and open editor
            EditNoteDialog editNoteDialog = new EditNoteDialog(popupMenuDialog.getNote(), popupMenuDialog.getMainActivity());
            editNoteDialog.show();
        }

        if (v == popupMenuDialog.getBtnSettings()) {
            // Close dialog
            popupMenuDialog.dismiss();

            // Create and open settings
            SettingsDialog settingsDialog = new SettingsDialog(popupMenuDialog.getMainActivity(), popupMenuDialog.getNote());
            settingsDialog.show();
        }
    }
}
