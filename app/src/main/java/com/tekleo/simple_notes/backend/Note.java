package com.tekleo.simple_notes.backend;

import com.tekleo.simple_notes.helpers.SortHelper;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Leo on 31-Jan-16.
 */
public class Note implements Serializable, Comparable
{
    private String title;
    private String text;
    private Date date;

    private int previewSize = 12;
    private int titleSize = 20;
    private int textSize = 18;

    //----------------------------------------------------------------------------------------------
    //--------------------------------- Constructors -----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Note(String title, String text) {
        Calendar calendar = Calendar.getInstance();

        this.date = calendar.getTime();
        this.title = title;
        this.text = text;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Getters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getPreviewSize() {
        return previewSize;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Getters --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setPreviewSize(int previewSize) {
        this.previewSize = previewSize;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Utility --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public int compareTo(Object another) {
        if (SortHelper.SORT_CURRENT == SortHelper.SORT_AZ)
            return title.compareToIgnoreCase(((Note) another).getTitle());
        else if (SortHelper.SORT_CURRENT == SortHelper.SORT_ZA)
            return (-1 * title.compareToIgnoreCase(((Note) another).getTitle()));
        else if (SortHelper.SORT_CURRENT == SortHelper.SORT_NEW_FIRST)
            return (-1 * date.compareTo(((Note) another).getDate()));
        else if (SortHelper.SORT_CURRENT == SortHelper.SORT_OLD_FIRST)
            return date.compareTo(((Note) another).getDate());
        else
            return 0;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
