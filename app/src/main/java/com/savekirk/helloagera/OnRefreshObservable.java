package com.savekirk.helloagera;

import android.view.MenuItem;

import com.google.android.agera.BaseObservable;

public class OnRefreshObservable extends BaseObservable implements MenuItem.OnMenuItemClickListener {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            dispatchUpdate();
        }
        return true;
    }
}
