package com.tekleo.simple_notes.frontend.listeners;

import android.view.View;

import com.tekleo.simple_notes.frontend.dialogs.PopupMenuDialog;
import com.tekleo.simple_notes.frontend.dialogs.note.ViewNoteDialog;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.frontend.main.NoteButton;


/**
 * Created by Leo on 01-Feb-16.
 */
public class NoteButtonListener implements View.OnClickListener, View.OnLongClickListener
{
    private MainActivity mainActivity;

    public NoteButtonListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        NoteButton noteButton = (NoteButton) v;

        ViewNoteDialog viewNoteDialog = new ViewNoteDialog(noteButton.getNote(), mainActivity);
        viewNoteDialog.show();
    }

    @Override
    public boolean onLongClick(View v) {
        PopupMenuDialog dialog = new PopupMenuDialog(mainActivity, (NoteButton) v);
        dialog.show();

        return true;
    }
}
