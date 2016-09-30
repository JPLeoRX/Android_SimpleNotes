package com.tekleo.simple_notes.frontend.dialogs.note;

import android.view.View;

import com.tekleo.simple_notes.backend.Current;

/**
 * Created by Leo on 01-Feb-16.
 */
public class EditNoteDialogButtonsListener implements View.OnClickListener
{
    // Parent dialog
    private EditNoteDialog parentDialog;

    // Constructor
    public EditNoteDialogButtonsListener(EditNoteDialog parentDialog) {
        this.parentDialog = parentDialog;
    }

    @Override
    public void onClick(View v) {
        if (v == parentDialog.getBtnSave()) {
            // Save the edits
            parentDialog.saveNote();
            Current.saveFile(parentDialog.getContext());

            // Refresh buttons in parent and close the dialog
            parentDialog.activityRefresh(); parentDialog.dismiss();
        }

        else if (v == parentDialog.getBtnCancel()) {
            // Close the dialog
            parentDialog.dismiss();
        }
    }
}
