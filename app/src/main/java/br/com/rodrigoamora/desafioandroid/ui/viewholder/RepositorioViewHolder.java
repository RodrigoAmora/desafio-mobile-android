package br.com.rodrigoamora.desafioandroid.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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

    @BindView(R.id.desc_repository)
    TextView descRepository;

    @BindView(R.id.stars)
    TextView stars;

    public RepositorioViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setValues(Repositorio repositorio) {
        String avatarURL = repositorio.getOwner().getAvatar_url();
        if (avatarURL != null && !avatarURL.isEmpty()) {
            Picasso.get().load(avatarURL).into(avatar);
        } else {
            Picasso.get().load(R.mipmap.ic_launcher_round).into(avatar);
        }

        descRepository.setText(repositorio.getDescription());
        forks.setText(repositorio.getForks().toString());
        nameOwner.setText(repositorio.getOwner().getLogin());
        nameRepository.setText(repositorio.getName());
        stars.setText(repositorio.getStargazers_count().toString());
    }

    public CoordinatorLayout getRepositorioLayout() {
        return repositorioLayout;
    }

}
