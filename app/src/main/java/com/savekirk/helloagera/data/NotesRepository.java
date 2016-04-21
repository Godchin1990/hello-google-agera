package com.savekirk.helloagera.data;

import android.support.annotation.NonNull;

import com.google.android.agera.BaseObservable;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;


public class NotesRepository extends BaseObservable implements Supplier<List<NoteItem>>, Updatable {

    /**
     * An array of sample (Note) items.
     */
    private List<NoteItem> NOTES = new ArrayList<>();

    private static final int COUNT = 25;


    private Repository<Result<List<NoteItem>>> notesRepoNetwork;
    private ExecutorService networkExecutor;

    public NotesRepository() {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createNoteItem(i));
        }
        setUpNetworkRepository();
    }

    private void addItem(NoteItem item) {
        NOTES.add(item);
    }

    private static NoteItem createNoteItem(int position) {
        return new NoteItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    @NonNull
    @Override
    public List<NoteItem> get() {
        return NOTES;
    }

    @Override
    public void update() {
        if (notesRepoNetwork.get().isPresent()) {
            NOTES = notesRepoNetwork.get().get();
            dispatchUpdate();
        }
    }



    @Override
    protected void observableActivated() {
        super.observableActivated();
        notesRepoNetwork.addUpdatable(this);
        dispatchUpdate();
    }

    @Override
    protected void observableDeactivated() {
        super.observableDeactivated();
        notesRepoNetwork.removeUpdatable(this);
    }

    private void setUpNetworkRepository() {
        networkExecutor = newSingleThreadExecutor();
        notesRepoNetwork = Repositories
                .repositoryWithInitialValue(Result.<List<NoteItem>>absent())
                .observe()
                .onUpdatesPerLoop()
                .goTo(networkExecutor)
                .thenGetFrom(new NotesRepositoryNetwork())
                .compile();
    }

}
