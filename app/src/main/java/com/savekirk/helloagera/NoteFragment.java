package com.savekirk.helloagera;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.agera.Updatable;
import com.savekirk.helloagera.data.NoteItem;
import com.savekirk.helloagera.data.NotesRepository;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NoteFragment extends Fragment implements Updatable {

    private OnListFragmentInteractionListener mListener;
    private NotesRepository notesRepository;
    private RecyclerView recyclerView;
    private OnRefreshObservable refreshObservable;

    public NoteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }

        refreshObservable = new OnRefreshObservable();
        notesRepository = new NotesRepository();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        notesRepository.addUpdatable(this);
        refreshObservable.addUpdatable(notesRepository);

    }

    @Override
    public void onPause() {
        super.onPause();
        notesRepository.removeUpdatable(this);
        refreshObservable.removeUpdatable(notesRepository);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        menu.getItem(0).setOnMenuItemClickListener(refreshObservable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void update() {
        Toast.makeText(getContext(), "List updated", Toast.LENGTH_LONG).show();
        recyclerView.setAdapter(new NoteRecyclerViewAdapter(notesRepository.get(), mListener));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(NoteItem item);
    }

}
