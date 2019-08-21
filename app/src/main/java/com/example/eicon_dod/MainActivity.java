package com.example.eicon_dod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.eicon_dod.Database.AppDatabase;
import com.example.eicon_dod.Database.Data;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnChartGestureListener, OnChartValueSelectedListener {
    private static final String TAG = "MainActivity";

    private LineChart mChart;

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureStart: X: " + me.getX() + "Y: " + me.getY());

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureEnd: " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG, "onChartLongPressed: ");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG, "onChartDoubleTapped: ");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG, "onChartSingleTapped: ");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "onChartFling: veloX: " + velocityX + "veloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale: ScaleX: " + scaleX + "scaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate: dX: " + dX + "dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onValueSelected: " + e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "DoD Keyboard Initialized", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("shared_profile", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "John Doe");

        TextView greeting = findViewById(R.id.greeting);
        greeting.setText(getString(R.string.greeting, name));

        mChart = (LineChart) findViewById(R.id.Linechart);

        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(25f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);


        mChart.getAxisRight().setEnabled(false);


        ArrayList<Entry> yValues = new ArrayList<>();

        AppDatabase db = AppDatabase.getInstance(this);
        List<Data> dbData = db.dataDAO().getDataList();
        List<Data> filteredData = Helper.filterDate(dbData);

        yValues.add(new Entry(1, Helper.countOccurrence(filteredData, true, "Sun")));
        yValues.add(new Entry(2, Helper.countOccurrence(filteredData, true, "Mon")));
        yValues.add(new Entry(3, Helper.countOccurrence(filteredData, true, "Tue")));
        yValues.add(new Entry(4, Helper.countOccurrence(filteredData, true, "Wed")));
        yValues.add(new Entry(5, Helper.countOccurrence(filteredData, true, "Thu")));
        yValues.add(new Entry(6, Helper.countOccurrence(filteredData, true, "Fri")));
        yValues.add(new Entry(7, Helper.countOccurrence(filteredData, true, "Sat")));

        LineDataSet set1 = new LineDataSet(yValues, "Bad word");

        set1.setFillAlpha(110);

        set1.setColor(Color.BLUE);
        set1.setLineWidth(3f);
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.BLUE);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.setData(data);

        String[] values = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(values));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.n_profile:
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                break;
            case R.id.n_keyboard:
                Intent keyboardIntent = new Intent(getApplicationContext(), KeyboardActivation.class);
                startActivity(keyboardIntent);
                break;
            case R.id.n_view_graph:
                Intent graphIntent = new Intent(getApplicationContext(), GraphActivity.class);
                startActivity(graphIntent);
                break;
            case R.id.n_setting:
                Intent settingIntent = new Intent(getApplicationContext(), KeyboardModification.class);
                startActivity(settingIntent);
                break;
            case R.id.n_send:
                break;
            case R.id.n_logout:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value - 1];
        }
    }
}
