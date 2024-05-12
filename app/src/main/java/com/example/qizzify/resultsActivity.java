package com.example.qizzify;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class resultsActivity extends Activity {

    private SQLiteDatabase db;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        findViewById(R.id.image_back_2).setOnClickListener(
                a-> finish()

        );


        tableLayout = findViewById(R.id.tableLayout);

        // Open your SQLite database
        //db = openOrCreateDatabase("scoredb.db", MODE_PRIVATE, null);
        DBHandler dbHelper = new DBHandler(this);
        db = dbHelper.getReadableDatabase();


        // Add header row
        TableRow headerRow = new TableRow(this);
        addTextToRowWithStyle(headerRow, "Attempt No.");
        addTextToRowWithStyle(headerRow, "Date & Time");
        addTextToRowWithStyle(headerRow, "Score");
        tableLayout.addView(headerRow);

        // Add data rows
        Cursor cursor = db.rawQuery(" SELECT * FROM userscores ", null);
        if (cursor.moveToFirst()) {
            do {
                // Create a new row to be added.
                TableRow row = new TableRow(this);
                // Add columns to each row
                addTextToRowWithStyle(row, cursor.getString(0)); // Column 1
                addTextToRowWithStyle(row, cursor.getString(1)); // Column 2
                addTextToRowWithStyle(row, cursor.getString(2)); // Column 3
                // Add row to TableLayout.
                tableLayout.addView(row);
            } while (cursor.moveToNext());
        }
        else {
            Toast.makeText(this, "No data found⚠️", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }

    // Helper method to add text to row with style
    private void addTextToRowWithStyle(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(5, 5, 5, 5);
        textView.setBackgroundResource(R.drawable.cell_shape); // Create a background resource for cells
        row.addView(textView);
    }
}