package com.tekleo.simple_notes.frontend.main;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.backend.Note;
import com.tekleo.simple_notes.helpers.ScreenHelper;

import java.util.Date;

/**
 * Created by Leo on 31-Jan-16.
 */
public class NoteButton extends Button
{
    private Note note;

    //----------------------------------------------------------------------------------------------
    //--------------------------------- Constructors -----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public NoteButton(Note note, Context context) {
        super(context); this.note = note; this.initialize();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Wrapper --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Note getNote() {
        return note;
    }

    public String getNoteTitle() {
        return note.getTitle();
    }

    public String getNoteText() {
        return note.getText();
    }

    public Date getNoteDate() {
        return note.getDate();
    }

    public int getNoteTitleSize() {
        return note.getTitleSize();
    }

    public int getNoteTextSize() {
        return note.getTextSize();
    }

    public int getNotePreviewSize() {
        return note.getPreviewSize();
    }

    public void setNoteTitle(String title) {
        note.setTitle(title);
    }

    public void setNoteText(String text) {
        note.setText(text);
    }

    public void setNoteDate(Date date) {
        note.setDate(date);
    }

    public void setNoteTitleSize(int titleSize) {
        note.setTitleSize(titleSize);
    }

    public void setNoteTextSize(int textSize) {
        note.setTextSize(textSize);
    }

    public void setNotePreviewSize(int previewSize) {
        note.setPreviewSize(previewSize);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //------------------------------- Initialization -----------------------------------------------
    //----------------------------------------------------------------------------------------------
    private void initialize() {
        this.setBackgroundResource(R.drawable.note_button);
        this.initializeText();
        this.initializeLayout();
    }

    private void initializeText() {
        this.setText(note.getTitle());
        this.setTextSize(note.getPreviewSize());
    }

    private void initializeLayout() {
        // Show text in mixed case instead of upper case
        this.setTransformationMethod(null);

        // Align the text to top
        this.setGravity(Gravity.TOP);

        // Compute and apply position in layout
        int a = (int) (0.28 * ScreenHelper.WIDTH);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        layoutParams.setMargins(10,10,10,10);
        this.setLayoutParams(layoutParams);
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
