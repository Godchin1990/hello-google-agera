package com.savekirk.helloagera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.savekirk.helloagera.data.NoteItem;

public class MainActivity extends AppCompatActivity implements NoteFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onListFragmentInteraction(NoteItem item) {
        Toast.makeText(MainActivity.this, item.details, Toast.LENGTH_LONG).show();
    }
}
