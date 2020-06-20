package com.techasylum.my_architecture_mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.techasylum.my_architecture_mvvm.R;
import com.techasylum.my_architecture_mvvm.room.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {


    private Context mContext;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private final AsyncListDiffer<Note> mDiffer = new AsyncListDiffer(this, DIFF_CALLBACK);
    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }
    public void submitList(List<Note> list) {
        mDiffer.submitList(list);
    }
    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull Note oldUser, @NonNull Note newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldUser.getId() == newUser.getId();
        }
        @Override
        public boolean areContentsTheSame(
                @NonNull Note oldUser, @NonNull Note newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.getTitle().equals(newUser.getTitle()) &&
                    oldUser.getDescription().equals(newUser.getDescription()) &&
                    oldUser.getDate().equals(newUser.getDate());
        }
    };



    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, final int position) {
        final Note currentNote =mDiffer.getCurrentList().get(position);


        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(currentNote.getDate());

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "item click successfully", Toast.LENGTH_SHORT).show();

                if ( position != RecyclerView.NO_POSITION) {
                    Note temp = notes.get(position);
                    Intent intent = new Intent(mContext, AddNoteActivity.class);
                    intent.putExtra(AddNoteActivity.EXTRA_ID, temp.getId());
                    intent.putExtra(AddNoteActivity.EXTRA_TITLE, temp.getTitle());
                    intent.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, temp.getDescription());
                    intent.putExtra(AddNoteActivity.EXTRA_PRIORITY, temp.getDate());
                    intent.putExtra("ED_NOTE_REQUEST", 2);
                    mContext.startActivity(intent);
                }



            }
        });*/
    }




    public Note getNoteAt(int position) {
        return mDiffer.getCurrentList().get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                        //Toast.makeText(mContext, "djjdjddk", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
