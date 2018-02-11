package com.coolkids.todo.getTogether;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.*;

import java.util.ArrayList;

/**
 * Created by colton on 2/10/18.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private ArrayList<PlannedEvent> events;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, date;
        public RelativeLayout event_row;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            date = view.findViewById(R.id.date);
            event_row = view.findViewById(R.id.event_row);

        }
    }

    public TodoAdapter(ArrayList<PlannedEvent> events) {
        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PlannedEvent event = events.get(position);
        holder.title.setText(event.getName());
        holder.description.setText(event.description);
        holder.date.setText(event.getEventDate());


        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(v.getContext(), EventActivity.class);
              intent.putExtra("EVENT_ID", event.eventID);
              v.getContext().startActivity(intent);
            }
        };
        holder.event_row.setOnClickListener(buttonListener);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
