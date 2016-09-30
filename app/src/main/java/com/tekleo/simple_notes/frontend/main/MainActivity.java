package com.tekleo.simple_notes.frontend.main;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tekleo.simple_notes.R;
import com.tekleo.simple_notes.backend.Current;
import com.tekleo.simple_notes.backend.Data;
import com.tekleo.simple_notes.frontend.listeners.NoteButtonListener;
import com.tekleo.simple_notes.helpers.ScreenHelper;
import com.tekleo.simple_notes.util.gui.CustomActivity;

public class MainActivity extends CustomActivity
{
    // Retrieved components
    private ImageButton btnAdd;
    private ImageButton btnSort;
    private ImageButton btnSearch;
    private ImageButton btnInfo;
    private EditText fieldSearch;
    private RelativeLayout relativeLayout;
    private LinearLayout verticalLayout;

    //----------------------------------------------------------------------------------------------
    //----------------------------------- Getters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public ImageButton getBtnAdd() {
        return btnAdd;
    }

    public ImageButton getBtnSort() {
        return btnSort;
    }

    public ImageButton getBtnSearch() {
        return btnSearch;
    }

    public ImageButton getBtnInfo() {
        return btnInfo;
    }

    public EditText getFieldSearch() {
        return fieldSearch;
    }

    public LinearLayout getVerticalLayout() {
        return verticalLayout;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Life Cycle -----------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Super call
        super.onCreate(savedInstanceState);

        // Set XML layout
        this.setContentView(R.layout.activity_main);

        // Read, create, style
        this.retrieveComponents(); this.createComponents(); this.styleComponents();
    }

    @Override
    public void onBackPressed() {
        // If not searching - exit normally
        if (!this.isSearching())
            super.onBackPressed();

        // Else - finish search, dont exit
        else
            this.finishSearch();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------
    //----------------------------------- Utility --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void retrieveComponents()
    {
        // Read
        verticalLayout = (LinearLayout) findViewById(R.id.linearLayout);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnSort = (ImageButton) findViewById(R.id.btnSort);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        btnInfo = (ImageButton) findViewById(R.id.btnInfo);
        fieldSearch = (EditText) findViewById(R.id.txtSearch);

        // Set listeners
        btnAdd.setOnClickListener(new MainActivityListener(this));
        btnSort.setOnClickListener(new MainActivityListener(this));
        btnSearch.setOnClickListener(new MainActivityListener(this));
        btnInfo.setOnClickListener(new MainActivityListener(this));
        fieldSearch.addTextChangedListener(new MainActivityListener(this));
    }


    @Override
    public void createComponents() {
        // If we have data
        if (Current.isReadyData()) {
            // If not searching
            if (!this.isSearching()) {
                if (ScreenHelper.isPortrait(this))
                    createButtons(3, Current.DATA);
                else if (ScreenHelper.isLandscape(this))
                    createButtons(5, Current.DATA);
            }

            // If searching
            else {
                // If search completed
                if (Current.isReadySearch()) {
                    if (ScreenHelper.isPortrait(this))
                        createButtons(3, Current.SEARCH);
                    else if (ScreenHelper.isLandscape(this))
                        createButtons(5, Current.SEARCH);
                }
            }
        }
    }

    @Override
    public void styleComponents() {
        // Hide title bar of the window
        this.getSupportActionBar().hide();

        // Prevent focusing on invisible text and keyboard opening
        this.finishSearch();
    }

    private void createButtons(int n, Data data)
    {
        // Clear the view
        verticalLayout.removeAllViews();

        // Sort the notes
        data.sort();

        if (data.size() < n) {
            relativeLayout.setGravity(Gravity.LEFT);
        }

        else {
            relativeLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        // For each row
        for (int i = 0; i < (data.size() / n + 1) * n; i += n) {
            // Create new horizontal layout
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            // For each note in row
            for (int j = 0; j < n; j++) {
                // If a valid index
                if (i + j < data.size()) {
                    // Create and add a button
                    NoteButton noteButton = new NoteButton(data.get(i + j), this);
                    noteButton.setOnClickListener(new NoteButtonListener(this));
                    noteButton.setOnLongClickListener(new NoteButtonListener(this));
                    horizontalLayout.addView(noteButton);
                }
            }

            // Add a row
            verticalLayout.addView(horizontalLayout);
        }
    }

    public void refresh() {
        this.createComponents();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Searching ------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public boolean isSearching() {
        return fieldSearch.getHeight() != 0;
    }

    public void startSearch() {
        // Create new layout parameters
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        // Apply layout changes
        fieldSearch.setLayoutParams(layoutParams); fieldSearch.setGravity(Gravity.CENTER_HORIZONTAL);

        // Reset button
        btnSearch.setImageResource(R.drawable.btn_cancel);
    }

    public void finishSearch() {
        // Create new layout parameters
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 0);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        // Remove keyboard
        InputMethodManager imm = (InputMethodManager)(getSystemService(Context.INPUT_METHOD_SERVICE));
        imm.hideSoftInputFromWindow(fieldSearch.getWindowToken(), 0);

        // Apply layout changes
        fieldSearch.setLayoutParams(layoutParams); fieldSearch.setGravity(Gravity.CENTER_HORIZONTAL); fieldSearch.setText("");

        // Reset button
        btnSearch.setImageResource(R.drawable.btn_search);

        // Reset search and refresh view
        Current.searchStart(""); this.createComponents();
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
