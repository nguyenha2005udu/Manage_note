package com.example.manage_note;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {
    EditText edtTitle, edtContent;
    Button btnSave;
    int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", -1);
        edtTitle.setText(intent.getStringExtra("title"));
        edtContent.setText(intent.getStringExtra("content"));

        btnSave.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("title", edtTitle.getText().toString());
            result.putExtra("content", edtContent.getText().toString());
            result.putExtra("pos", pos);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}

