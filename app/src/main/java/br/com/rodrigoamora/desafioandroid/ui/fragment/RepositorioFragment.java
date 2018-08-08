package br.com.rodrigoamora.desafioandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.rodrigoamora.desafioandroid.R;
import br.com.rodrigoamora.desafioandroid.app.MyApplication;
import br.com.rodrigoamora.desafioandroid.callback.RepositorioCallback;
import br.com.rodrigoamora.desafioandroid.component.RepositorioComponent;
import br.com.rodrigoamora.desafioandroid.delegate.Delegate;
import br.com.rodrigoamora.desafioandroid.model.Repositorio;
import br.com.rodrigoamora.desafioandroid.model.callback.ListaRepositorios;
import br.com.rodrigoamora.desafioandroid.service.RepositorioService;
import br.com.rodrigoamora.desafioandroid.ui.activity.MainActivity;
import br.com.rodrigoamora.desafioandroid.ui.adapter.RepositorioAdapter;
import br.com.rodrigoamora.desafioandroid.ui.listener.OnItemClickListener;
import br.com.rodrigoamora.desafioandroid.ui.listener.PaginateListener;
import br.com.rodrigoamora.desafioandroid.util.FragmentUtil;
import br.com.rodrigoamora.desafioandroid.util.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

public class RepositorioFragment extends android.app.Fragment implements Delegate<List<Repositorio>> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    RepositorioService service;

    private Call<ListaRepositorios> call;
    private List<Repositorio> repositorios;
    private Integer page;
    private String linguagem;
    private Unbinder unbinder;

    private RepositorioAdapter adapter;
    private RepositorioCallback callback;
    private MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        callback = new RepositorioCallback(this);
        repositorios = new ArrayList();
        linguagem = "language:Java";
        page = 1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repositorio, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMaintActivity();
        configureRecyclerView();
        getComponents();
        getRepositorios();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }

    private void getComponents() {
        MyApplication myApplication = (MyApplication) activity.getApplication();
        RepositorioComponent component = myApplication.getRepositorioComponent();
        component.inject(this);
    }

    public void getMaintActivity() {
        activity = (MainActivity) getActivity();
    }

    private void configureRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(activity);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setOnScrollListener(new PaginateListener(linearLayout) {
            @Override
            public void onLoadMore(int current_page) {
                page++;
                getRepositorios();
            }
        });
    }

    private void populateRecyclerView() {
        if (adapter == null) {
            adapter = new RepositorioAdapter(activity, repositorios);
            recyclerView.setAdapter(adapter);
            adapter.setListener(new OnItemClickListener<Repositorio>() {
                @Override
                public void onItemClick(Repositorio repositorio) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("owner", repositorio.getOwner().getLogin());
                    bundle.putString("repositorio", repositorio.getName());
                    FragmentUtil.changeFragment(R.id.conatiner, new PullRequestFramgnet(), activity.getFragmentManager(), true, bundle);
                }
            });
        } else {
            recyclerView.refreshDrawableState();
        }
    }

    private void getRepositorios() {
        if (NetworkUtil.checkConnection(activity)) {
            call = service.listarRepositorios(linguagem, "start", page);
            call.enqueue(callback);
        } else {
            Toast.makeText(activity, getString(R.string.alert_no_internet), Toast.LENGTH_LONG).show();
        }
    }

    private void atualizarRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error() {
        Toast.makeText(activity, getString(R.string.error_consult_repositories), Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(List<Repositorio> repositorios) {
        if (!repositorios.isEmpty()) {
            this.repositorios.addAll(repositorios);
            if (page == 1) {
                populateRecyclerView();
            } else {
                atualizarRecyclerView();
            }
        } else {
            Toast.makeText(activity, getString(R.string.no_result), Toast.LENGTH_LONG).show();
        }
    }

}
