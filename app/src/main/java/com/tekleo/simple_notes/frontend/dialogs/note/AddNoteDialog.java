package com.tekleo.simple_notes.frontend.dialogs.note;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.frontend.dialogs.CloseWarningDialog;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.util.gui.CustomDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Leo on 31-Jan-16.
 */
public class AddNoteDialog extends CustomDialog
{
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
    public AddNoteDialog(MainActivity mainActivity) {
        super(mainActivity); this.mainActivity = mainActivity;
        this.initializeLayout(); this.retrieveComponents(); this.createComponents(); this.styleComponents();
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





    //----------------------------------------------------------------------------------------------
    //----------------------------------- Utility --------------------------------------------------
    //----------------------------------------------------------------------------------------------
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

        btnSave.setOnClickListener(new AddNoteDialogButtonsListener(this));
        btnCancel.setOnClickListener(new AddNoteDialogButtonsListener(this));
    }

    @Override
    public void createComponents() {

    }

    @Override
    public void styleComponents() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        textViewDate.setText(formattedDate);

        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onBackPressed() {

        if (getNoteTitle().equals("") && getNoteText().equals("")) {
            super.onBackPressed();
        }

        else {
            CloseWarningDialog dialog = new CloseWarningDialog(this);
            dialog.show();
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    public void refreshParentButtons() {
        mainActivity.createComponents();
    }
}
