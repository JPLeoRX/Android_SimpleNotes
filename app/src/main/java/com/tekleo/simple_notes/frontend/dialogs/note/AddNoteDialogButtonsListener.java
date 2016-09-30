package com.tekleo.simple_notes.frontend.dialogs.note;

import android.view.View;

import com.tekleo.simple_notes.backend.Current;
import com.tekleo.simple_notes.backend.Note;


/**
 * Created by Leo on 31-Jan-16.
 */
public class AddNoteDialogButtonsListener implements View.OnClickListener
{
    // Parent dialog
    private AddNoteDialog parentDialog;

    // Constructor
    public AddNoteDialogButtonsListener(AddNoteDialog parentDialog) {
        this.parentDialog = parentDialog;
    }

    @Override
    public void onClick(View v) {
        if (v == parentDialog.getBtnSave()) {
            // Save new note
            Current.DATA.add(new Note(parentDialog.getNoteTitle(), parentDialog.getNoteText()));
            Current.saveFile(parentDialog.getContext());

            // Refresh buttons in parent and close the dialog
            parentDialog.refreshParentButtons(); parentDialog.dismiss();
        }

        else if (v == parentDialog.getBtnCancel()) {
            // Close the dialog
            parentDialog.dismiss();
        }
    }
}
