package com.tekleo.simple_notes.frontend.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.backend.Note;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.frontend.main.NoteButton;
import com.tekleo.simple_notes.util.gui.CustomDialog;


/**
 * Created by Leo on 02-Feb-16.
 */
public class PopupMenuDialog extends CustomDialog
{
    private NoteButton noteButton;
    private LinearLayout btnEdit;
    private LinearLayout btnSettings;
    private LinearLayout btnDelete;

    //----------------------------------------------------------------------------------------------
    //----------------------------------- Constructor ----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public PopupMenuDialog(MainActivity mainActivity, NoteButton noteButton) {
        super(mainActivity); this.noteButton = noteButton;
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
        this.setContentView(R.layout.menu_note);
        this.retrieveComponents(); this.createComponents(); this.styleComponents();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Getters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public NoteButton getNoteButton() {
        return noteButton;
    }

    public Note getNote() {
        return noteButton.getNote();
    }

    public LinearLayout getBtnEdit() {
        return btnEdit;
    }

    public LinearLayout getBtnSettings() {
        return btnSettings;
    }

    public LinearLayout getBtnDelete() {
        return btnDelete;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Utility --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void retrieveComponents() {
        btnEdit = (LinearLayout) findViewById(R.id.menuBtnEdit);
        btnSettings = (LinearLayout) findViewById(R.id.menuBtnSettings);
        btnDelete = (LinearLayout) findViewById(R.id.menuBtnDelete);

        btnEdit.setOnClickListener(new PopupMenuDialogListener(this));
        btnSettings.setOnClickListener(new PopupMenuDialogListener(this));
        btnDelete.setOnClickListener(new PopupMenuDialogListener(this));
    }

    @Override
    public void createComponents() {

    }

    @Override
    public void styleComponents() {
        int[] l = new int[2];
        noteButton.getLocationOnScreen(l);

        WindowManager.LayoutParams wmlp = getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = (int) (l[0] - noteButton.getWidth() * 0.13);
        wmlp.y = l[1];

        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
