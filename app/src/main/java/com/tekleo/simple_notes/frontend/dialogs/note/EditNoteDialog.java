package com.tekleo.simple_notes.frontend.dialogs.note;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.backend.Note;
import com.tekleo.simple_notes.frontend.dialogs.CloseWarningDialog;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.util.gui.CustomDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Leo on 01-Feb-16.
 */
public class EditNoteDialog extends CustomDialog
{
    // Note
    private Note note;

    // Retrieved components
    private TextView textViewDate;
    private EditText editTextTitle;
    private EditText editTextText;
    private ImageButton btnSave;
    private ImageButton btnCancel;

    //----------------------------------------------------------------------------------------------
    //----------------------------------- Constructors ---------------------------------------------
    //----------------------------------------------------------------------------------------------
    public EditNoteDialog(Note note, MainActivity mainActivity) {
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
        this.setContentView(R.layout.dialog_note);
        this.retrieveComponents(); this.createComponents(); this.styleComponents();
    }

    @Override
    public void onBackPressed() {
        if (this.getNoteText().equals(note.getText()) && this.getNoteTitle().equals(note.getTitle()))
            super.onBackPressed();
        else {
            CloseWarningDialog dialog = new CloseWarningDialog(this);
            dialog.show();
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Getters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public ImageButton getBtnSave() {
        return btnSave;
    }

    public ImageButton getBtnCancel() {
        return btnCancel;
    }

    public String getNoteTitle() {
        return editTextTitle.getText().toString();
    }

    public String getNoteText() {
        return editTextText.getText().toString();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Setters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void setNoteTitle(String title) {
        editTextTitle.setText(title);
    }

    public void setNoteText(String text) {
        editTextText.setText(text);
    }

    public void setDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(date.getTime());
        textViewDate.setText(formattedDate);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    @Override
    public void retrieveComponents() {
        textViewDate = (TextView) findViewById(R.id.vtxtDate);

        editTextTitle = (EditText) findViewById(R.id.etxtTitle);
        editTextText = (EditText) findViewById(R.id.etxtText);

        btnSave = (ImageButton) findViewById(R.id.btnSave);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new EditNoteDialogButtonsListener(this));
        btnCancel.setOnClickListener(new EditNoteDialogButtonsListener(this));
    }

    @Override
    public void createComponents() {

    }

    @Override
    public void styleComponents() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.setNoteTitle(note.getTitle());
        this.setNoteText(note.getText());
        this.setDate(note.getDate());

        editTextTitle.setTextSize(note.getTitleSize());
        editTextText.setTextSize(note.getTextSize());
    }



    public void saveNote() {
        this.note.setTitle(this.getNoteTitle());
        this.note.setText(this.getNoteText());
    }
}
