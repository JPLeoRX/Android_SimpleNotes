package com.tekleo.simple_notes.frontend.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageButton;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.util.gui.CustomDialog;

/**
 * Created by Leo on 02-Feb-16.
 */
public class CloseWarningDialog extends CustomDialog
{
    private CustomDialog parentDialog;

    private ImageButton btnCancel;
    private ImageButton btnExit;

    public CloseWarningDialog(CustomDialog parentDialog) {
        super(parentDialog.getMainActivity()); this.parentDialog = parentDialog;
        this.initializeLayout(); this.retrieveComponents(); this.createComponents(); this.styleComponents();
    }

    public void initializeLayout() {
        this.setContentView(R.layout.dialog_close_warning);
    }

    @Override
    public void retrieveComponents() {
        btnCancel = (ImageButton) findViewById(R.id.btnCloseCancel);
        btnExit = (ImageButton) findViewById(R.id.btnCloseExit);

        btnCancel.setOnClickListener(new CloseWarningDialogButtonsListener(this, parentDialog));
        btnExit.setOnClickListener(new CloseWarningDialogButtonsListener(this, parentDialog));
    }

    @Override
    public void createComponents() {

    }

    @Override
    public void styleComponents() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public ImageButton getBtnCancel() {
        return btnCancel;
    }

    public ImageButton getBtnExit() {
        return btnExit;
    }
}
