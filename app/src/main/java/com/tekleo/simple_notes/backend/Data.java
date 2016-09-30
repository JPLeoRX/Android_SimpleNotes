package com.tekleo.simple_notes.backend;

import android.content.Context;

import com.tekleo.simple_notes.helpers.SortHelper;
import com.tekleo.simple_notes.util.data_structures.BinarySearchTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Leo on 31-Jan-16.
 */
public class Data implements Serializable
{
    private int sortChoice = SortHelper.SORT_NEW_FIRST;
    private ArrayList<Note> notesArray = new ArrayList<>();
    private BinarySearchTree<Note> notesTree = new BinarySearchTree<>();

    //----------------------------------------------------------------------------------------------
    //--------------------------------- Constructors -----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Data() {

    }

    private Data(ArrayList<Note> notesArray, BinarySearchTree<Note> notesTree) {
        this.notesArray = notesArray;
        this.notesTree = notesTree;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    //----------------------------------- Wrappers -------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void add(Note object) {
        notesArray.add(object);
    }

    public Note get(int index) {
        return notesArray.get(index);
    }

    public int size() {
        return notesArray.size();
    }

    public void clear() {
        notesTree.clear(); notesArray.clear();
    }

    public void remove(Note object) {
        notesArray.remove(object);
    }

    public int getSortChoice() {
        return sortChoice;
    }

    public void setSortChoice(int sortChoice) {
        this.sortChoice = sortChoice;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //--------------------------------- Search & Sort ----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Data search(String title) {
        ArrayList<Note> searchArray = new ArrayList<>();
        BinarySearchTree<Note> searchTree = new BinarySearchTree<>();

        for (Note note : notesArray)
            if (note.getTitle().toLowerCase().contains(title.toLowerCase()))
                searchTree.add(note);

        for (Note note : searchTree)
            searchArray.add(note);

        return new Data(searchArray, searchTree);
    }

    public void sort() {
        notesTree.clear();
        for (Note note : notesArray) {
            notesTree.add(note);
        }

        notesArray.clear();
        for (Note note : notesTree) {
            notesArray.add(note);
        }
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    //----------------------------------------------------------------------------------------------
    //----------------------------------- Utility --------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public static void saveToFile(Context context, Data data, String filename) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Data loadFromFile(Context context, String filename) {
        Data data = null;

        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (Data) ois.readObject();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
}
