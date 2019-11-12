package br.com.rodrigoamora.desafioandroid.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import br.com.rodrigoamora.desafioandroid.R;
import br.com.rodrigoamora.desafioandroid.model.PullRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PullRequestViewHoler extends RecyclerView.ViewHolder {

    @BindView(R.id.avatar)
    CircleImageView avatar;

    @BindView(R.id.titulo_pullrequest)
    TextView titulo;

    @BindView(R.id.descricao_pullrequest)
    TextView descricao;

    @BindView(R.id.username)
    TextView username;

    public PullRequestViewHoler(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setValues(PullRequest pullRequest) {
        String avatarURL = pullRequest.getUser().getAvatar_url();
        if (avatarURL != null && !avatarURL.isEmpty()) {
            Picasso.get().load(avatarURL).into(avatar);
        } else {
            Picasso.get().load(R.mipmap.ic_launcher_round).into(avatar);
        }

        descricao.setText(pullRequest.getBody());
        titulo.setText(pullRequest.getTitle());
        username.setText(pullRequest.getUser().getLogin());
    }

}
