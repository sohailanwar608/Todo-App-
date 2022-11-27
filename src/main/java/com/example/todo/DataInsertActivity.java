package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todo.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity
{
   ActivityDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // update and add data
        String type = getIntent().getStringExtra("type");

        if(type.equals("update"))

        {
            setTitle("update");
            binding.title.setText(getIntent().getStringExtra("title"));
            binding.discp.setText(getIntent().getStringExtra("discp"));
            int id = getIntent().getIntExtra("id", 0);
            binding.add.setText("update note");

            //when on update button
            binding.add.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("discp", binding.discp.getText().toString());
                    intent.putExtra("id", id);
                    // move to back to previous activity with data
                    setResult(RESULT_OK, intent);

                    finish();

                }
            });
        }
        else

        {
            setTitle("Add Mode");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("discp", binding.discp.getText().toString());
                    setResult(RESULT_OK, intent);

                    finish();

                }
            });
        }
    }

    // Move back to previous activity
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
    }
}