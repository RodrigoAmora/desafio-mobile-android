package br.com.rodrigoamora.desafioandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rodrigoamora.desafioandroid.R;
import br.com.rodrigoamora.desafioandroid.model.PullRequest;
import br.com.rodrigoamora.desafioandroid.ui.viewholder.PullRequestViewHoler;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestViewHoler> {

    private Context context;
    private List<PullRequest> pullRequests;

    public PullRequestAdapter(Context context, List<PullRequest> pullRequests) {
        this.context = context;
        this.pullRequests = pullRequests;
    }

    @NonNull
    @Override
    public PullRequestViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_pullrequest, parent,false);
        PullRequestViewHoler viewHolder = new PullRequestViewHoler(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PullRequestViewHoler holder, int position) {
        holder.setValues(pullRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

}
