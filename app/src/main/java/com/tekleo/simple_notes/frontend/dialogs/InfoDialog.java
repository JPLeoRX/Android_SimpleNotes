package com.tekleo.simple_notes.frontend.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.frontend.main.MainActivity;
import com.tekleo.simple_notes.util.gui.CustomDialog;

/**
 * Created by Leo on 04-Feb-16.
 */
public class InfoDialog extends CustomDialog
{
    public InfoDialog(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_info);
        this.retrieveComponents(); this.createComponents(); this.styleComponents();
    }

    @Override
    public void createComponents() {

    }

    @Override
    public void retrieveComponents() {

    }

    @Override
    public void styleComponents() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
