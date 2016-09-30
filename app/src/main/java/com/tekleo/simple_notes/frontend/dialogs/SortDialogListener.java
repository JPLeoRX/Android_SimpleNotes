package com.tekleo.simple_notes.frontend.dialogs;

import android.view.View;

import com.tekleo.simple_notes.backend.Current;
import com.tekleo.simple_notes.helpers.SortHelper;


/**
 * Created by Leo on 02-Feb-16.
 */
public class SortDialogListener implements View.OnClickListener
{
    private SortDialog parentDialog;

    public SortDialogListener(SortDialog parentDialog) {
        this.parentDialog = parentDialog;
    }

    @Override
    public void onClick(View v) {
        if (v == parentDialog.getBtnSortAZ()) {
            SortHelper.SORT_CURRENT = SortHelper.SORT_AZ;
            Current.DATA.setSortChoice(SortHelper.SORT_AZ);
            parentDialog.activityRefresh();
            Current.saveFile(parentDialog.getContext());
            //parentDialog.dismiss();
        }

        else if (v == parentDialog.getBtnSortZA()) {
            SortHelper.SORT_CURRENT = SortHelper.SORT_ZA;
            Current.DATA.setSortChoice(SortHelper.SORT_ZA);
            parentDialog.activityRefresh();
            Current.saveFile(parentDialog.getContext());
            //parentDialog.dismiss();
        }

        else if (v == parentDialog.getBtnSortNew()) {
            SortHelper.SORT_CURRENT = SortHelper.SORT_NEW_FIRST;
            Current.DATA.setSortChoice(SortHelper.SORT_NEW_FIRST);
            parentDialog.activityRefresh();
            Current.saveFile(parentDialog.getContext());
            //parentDialog.dismiss();
        }

        else if (v == parentDialog.getBtnSortOld()) {
            SortHelper.SORT_CURRENT = SortHelper.SORT_OLD_FIRST;
            Current.DATA.setSortChoice(SortHelper.SORT_OLD_FIRST);
            parentDialog.activityRefresh();
            Current.saveFile(parentDialog.getContext());
            //parentDialog.dismiss();
        }
    }
}
