package br.com.rodrigoamora.desafioandroid.ui.viewHolder;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.rodrigoamora.desafioandroid.R;
import br.com.rodrigoamora.desafioandroid.model.Repositorio;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositorioViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.layout_repositorio)
    CoordinatorLayout repositorioLayout;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name_owner)
    TextView nameOwner;
    @BindView(R.id.name_repository)
    TextView nameRepository;
    @BindView(R.id.forks)
    TextView forks;
    @BindView(R.id.url_clone)
    TextView urlClone;
    @BindView(R.id.stars)
    TextView tarts;

    public RepositorioViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setValues(Repositorio repositorio) {
        nameRepository.setText(repositorio.getName());

    }

    public CoordinatorLayout getRepositorioLayout() {
        return repositorioLayout;
    }

}
