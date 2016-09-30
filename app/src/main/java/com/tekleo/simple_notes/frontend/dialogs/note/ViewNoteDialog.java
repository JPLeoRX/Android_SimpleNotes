package com.tekleo.simple_notes.frontend.dialogs.note;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.backend.Note;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.util.gui.CustomDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Leo on 03-Feb-16.
 */
public class ViewNoteDialog extends CustomDialog
{
    // Note
    private Note note;

    // Parent
    private MainActivity mainActivity;

    // Retrieved components
    private TextView textViewDate;
    private EditText editTextTitle;
    private EditText editTextText;
    private ImageButton btnSave;
    private ImageButton btnCancel;

    //----------------------------------------------------------------------------------------------
    //----------------------------------- Constructors ---------------------------------------------
    //----------------------------------------------------------------------------------------------
    public ViewNoteDialog(Note note, MainActivity mainActivity) {
        super(mainActivity); this.note = note; this.mainActivity = mainActivity;
        this.initializeLayout(); this.retrieveComponents(); this.createComponents(); this.styleComponents();
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
    public void createComponents() {

    }


    public void initializeLayout() {
        this.setContentView(R.layout.dialog_note);
    }

    @Override
    public void retrieveComponents() {
        textViewDate = (TextView) findViewById(R.id.vtxtDate);

        editTextTitle = (EditText) findViewById(R.id.etxtTitle);
        editTextText = (EditText) findViewById(R.id.etxtText);

        btnSave = (ImageButton) findViewById(R.id.btnSave);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        
        btnSave.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);

        editTextTitle.setKeyListener(null);
        editTextTitle.setTextIsSelectable(true);

        editTextText.setKeyListener(null);
        editTextText.setTextIsSelectable(true);


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
}
