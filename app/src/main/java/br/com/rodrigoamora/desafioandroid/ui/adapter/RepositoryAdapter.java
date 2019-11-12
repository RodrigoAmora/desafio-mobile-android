package br.com.rodrigoamora.desafioandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.rodrigoamora.desafioandroid.R;
import br.com.rodrigoamora.desafioandroid.model.Repository;
import br.com.rodrigoamora.desafioandroid.ui.listener.OnItemClickListener;
import br.com.rodrigoamora.desafioandroid.ui.viewholder.RepositoryViewHolder;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {

    private Context context;
    private List<Repository> repositories;

    private OnItemClickListener listener;

    public RepositoryAdapter(Context context, List<Repository> repositories) {
        this.context = context;
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_repository, parent,false);
        RepositoryViewHolder viewHolder = new RepositoryViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        holder.setValues(repositories.get(position));
        holder.getRepositorioLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(repositories.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
