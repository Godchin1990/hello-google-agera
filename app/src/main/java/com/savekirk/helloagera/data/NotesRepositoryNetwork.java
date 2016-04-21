package com.savekirk.helloagera.data;

import android.support.annotation.NonNull;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import java.util.ArrayList;
import java.util.List;

public class NotesRepositoryNetwork implements Supplier<Result<List<NoteItem>>> {
    public static final int TOTAL_NOTE = 5;

    public List<NoteItem> getNotes() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return null;
        }

        List<NoteItem> notes = new ArrayList<>();
        for (int i = 0; i < TOTAL_NOTE; i++) {
            long time = System.currentTimeMillis();
            notes.add(new NoteItem(String.valueOf(i), "Server Note " + time , "Server note details" + time));
        }

        return notes;

    }

    @NonNull
    @Override
    public Result<List<NoteItem>> get() {
        List<NoteItem> notes = getNotes();
        if (notes == null) {
           return Result.failure();
        } else {
           return  Result.success(notes);
        }
    }


}
