package edu.uc.lunchr.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

import edu.uc.lunchr.R;
import edu.uc.lunchr.dto.PreviousDates;
import edu.uc.lunchr.ui.adapters.RecyclerAdapter;
import edu.uc.lunchr.ui.utils.RecyclerViewScrollListener;
import edu.uc.lunchr.ui.utils.RecyclerViewTouchListener;
import edu.uc.lunchr.ui.widgets.ControllableAppBarLayout;
import edu.uc.lunchr.ui.widgets.Fab;
import edu.uc.lunchr.ui.widgets.MaterialSheetFab;

/**
 * Created by Aaron on 7/1/2015.
 *
 * Homepage activity
 */
public class MainActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ControllableAppBarLayout controllableAppBarLayout;
    private MaterialSheetFab materialSheetFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFab();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        controllableAppBarLayout = (ControllableAppBarLayout) findViewById(R.id.appBar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Dates");
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setOnTouchListener(new RecyclerViewTouchListener(materialSheetFab));

        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnTouchListener(new RecyclerViewTouchListener(materialSheetFab));
        recyclerView.addOnScrollListener(new RecyclerViewScrollListener(controllableAppBarLayout));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<String> listData = new ArrayList<String>();
        int ct = 0;
        for (int i = 0; i < PreviousDates.data.length; i++) {
            listData.add(PreviousDates.data[ct]);
            ct++;
            if (ct == PreviousDates.data.length) {
                ct = 0;
            }
        }

        if (recyclerAdapter == null) {
            recyclerAdapter = new RecyclerAdapter(this, listData);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (materialSheetFab.isSheetVisible()) {
            materialSheetFab.hideSheet();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Sets up the Floating action button.
     */
    private void setupFab() {

        Fab fab = (Fab) findViewById(R.id.fab);
        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.accent);
        int fabColor = getResources().getColor(R.color.theme_accent);

        // Create material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);
    }

    public void onCreateDateClick(View v) {
        materialSheetFab.hideSheet();
        Intent createDateIntent = new Intent(this, CreateDateActivity.class);
        startActivity(createDateIntent);
    }

    private int getStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getWindow().getStatusBarColor();
        }
        return 0;
    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbar.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
    }
}