package com.example.manage_note;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> notes;
    NoteAdapter adapter;
    ListView listView;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        notes = new ArrayList<>();
        adapter = new NoteAdapter(this, notes);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
            startActivityForResult(intent, 1);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
            intent.putExtra("pos", position);
            intent.putExtra("title", notes.get(position).getTitle());
            intent.putExtra("content", notes.get(position).getContent());
            startActivityForResult(intent, 2);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            notes.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");

            if (requestCode == 1) {
                notes.add(new Note(title, content));
            } else if (requestCode == 2) {
                int pos = data.getIntExtra("pos", -1);
                if (pos >= 0) {
                    notes.set(pos, new Note(title, content));
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}
