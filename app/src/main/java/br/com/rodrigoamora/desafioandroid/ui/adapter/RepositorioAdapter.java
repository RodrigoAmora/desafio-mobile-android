package br.com.rodrigoamora.desafioandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.rodrigoamora.desafioandroid.R;
import br.com.rodrigoamora.desafioandroid.model.Repositorio;
import br.com.rodrigoamora.desafioandroid.ui.listener.OnItemClickListener;
import br.com.rodrigoamora.desafioandroid.ui.viewholder.RepositorioViewHolder;

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioViewHolder> {

    private Context context;
    private List<Repositorio> repositorios;

    private OnItemClickListener listener;

    public RepositorioAdapter(Context context, List<Repositorio> repositorios) {
        this.context = context;
        this.repositorios = repositorios;
    }

    @NonNull
    @Override
    public RepositorioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_repository, parent,false);
        RepositorioViewHolder viewHolder = new RepositorioViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepositorioViewHolder holder, int position) {
        holder.setValues(repositorios.get(position));
        holder.getRepositorioLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(repositorios.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
