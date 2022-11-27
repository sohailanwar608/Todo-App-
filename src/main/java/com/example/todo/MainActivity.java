package com.example.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.todo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    // create object of viewModel for passing data
    private static final String TAG = "MyActivity";
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

                // intialize it
                noteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                        .get(NoteViewModel.class);

               // go to next activity and move back with  some data.
                binding.floatingActionButton3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                        intent.putExtra("type" , "addMode");
                        startActivityForResult(intent, 1);
                    }
                });


        // show data in recyclerView
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setHasFixedSize(true);
        RVAdapter adapter =new RVAdapter();
        binding.Rv.setAdapter(adapter);

        // fetch data from noteViewModel for showing in recyclerView
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>()
        {
            @Override
            public void onChanged(List<Note> notes)
            {
                adapter.submitList(notes);
            }
        });


        // delete and update by swipe it toward left or right
       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT)
       {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
           {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

               if(direction == ItemTouchHelper.RIGHT)
               {
                   noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                   Toast.makeText(MainActivity.this,"Data delete Successfully",Toast.LENGTH_SHORT).show();
               }
               else
               {
                     //noteViewModel.update(adapter.getNote(viewHolder.getAdapterPosition()));
                   Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                   intent.putExtra("type" , "update");
                   intent.putExtra("title", adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                   intent.putExtra("dicp", adapter.getNote(viewHolder.getAdapterPosition()).getDisp());
                   intent.putExtra("id", adapter.getNote(viewHolder.getAdapterPosition()).getId());
                   //Toast.makeText(MainActivity.this,"Data update Successfully",Toast.LENGTH_SHORT).show();
                   startActivityForResult(intent,2);
               }

           }
       }).attachToRecyclerView(binding.Rv);


    }

    // receive data here coming from other activity by click on floating_action_button
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.i(TAG, "get item number " );
        if (requestCode == 1)

        {
            //Log.i(TAG, "get item number " );
            String title = data.getStringExtra("title");
            String discp = data.getStringExtra("discp");
            // Log.i(TAG, "get item number " );
            Note note = new Note(title, discp);
            Log.i(TAG, "get item number ");
            noteViewModel.insert(note);
            Toast.makeText(MainActivity.this, "Note  Added", Toast.LENGTH_SHORT).show();

        }

        else if(requestCode == 2)
        {

            String title = data.getStringExtra("title");
            String discp = data.getStringExtra("discp");
            // Log.i(TAG, "get item number " );
            Note note = new Note(title, discp);
            note.setId(data.getIntExtra("id" , 0));
            noteViewModel.update(note);
            Toast.makeText(MainActivity.this, "note  update", Toast.LENGTH_SHORT).show();
        }

    }
}


