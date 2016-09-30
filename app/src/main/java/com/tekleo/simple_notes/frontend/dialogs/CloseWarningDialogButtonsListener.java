package com.tekleo.simple_notes.frontend.dialogs;

import android.app.Dialog;
import android.view.View;

/**
 * Created by Leo on 02-Feb-16.
 */
public class CloseWarningDialogButtonsListener implements View.OnClickListener
{
    private CloseWarningDialog parent;
    private Dialog parentsParent;

    public CloseWarningDialogButtonsListener(CloseWarningDialog parent, Dialog parentsParent) {
        this.parent = parent;
        this.parentsParent = parentsParent;
    }

    @Override
    public void onClick(View v) {
        if (v == parent.getBtnCancel())
            parent.dismiss();

        if (v == parent.getBtnExit())
        {
            parentsParent.dismiss();
            parent.dismiss();
        }
    }
}
