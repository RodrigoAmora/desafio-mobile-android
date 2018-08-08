package br.com.rodrigoamora.desafioandroid.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.rodrigoamora.desafioandroid.R;
import br.com.rodrigoamora.desafioandroid.app.MyApplication;
import br.com.rodrigoamora.desafioandroid.callback.PullRequestCallback;
import br.com.rodrigoamora.desafioandroid.component.PullRequestComponent;
import br.com.rodrigoamora.desafioandroid.delegate.Delegate;
import br.com.rodrigoamora.desafioandroid.model.PullRequest;
import br.com.rodrigoamora.desafioandroid.service.PullRequestService;
import br.com.rodrigoamora.desafioandroid.ui.activity.MainActivity;
import br.com.rodrigoamora.desafioandroid.ui.adapter.PullRequestAdapter;
import br.com.rodrigoamora.desafioandroid.util.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

public class PullRequestFramgnet extends Fragment implements Delegate<List<PullRequest>> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.closed)
    TextView closed;
    @BindView(R.id.opened)
    TextView opened;
    @Inject
    PullRequestService service;

    private Call<List<PullRequest>> call;
    private List<PullRequest> pullRequests;
    private String owner, repo;
    private Unbinder unbinder;

    private PullRequestAdapter adapter;
    private PullRequestCallback callback;
    private MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        callback = new PullRequestCallback(this);
        pullRequests = new ArrayList();
        repo = getArguments().getString("repositorio");
        owner = getArguments().getString("owner");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmrnt_pullrequest, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMaintActivity();
        configureRecyclerView();
        getComponents();
        getPullRequests();
    }

    @Override
    public void error() {
        Toast.makeText(activity, getString(R.string.error_get_repositories), Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(List<PullRequest> pullRequests) {
        if (!pullRequests.isEmpty()) {
            this.pullRequests.addAll(pullRequests);
            populateRecyclerView();
            contarPullRequestsAbertos();
        } else {
            Toast.makeText(activity, getString(R.string.no_result), Toast.LENGTH_LONG).show();
        }
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
        PullRequestComponent component = myApplication.getPullRequestComponent();
        component.inject(this);
    }

    public void getMaintActivity() {
        activity = (MainActivity) getActivity();
        activity.setTitle(repo);
    }

    private void configureRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(activity);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void populateRecyclerView() {
        if (adapter == null) {
            adapter = new PullRequestAdapter(activity, pullRequests);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.refreshDrawableState();
        }
    }

    private void contarPullRequestsAbertos() {
        Integer abertos = 0;
        Integer fechados = 0;
        for (PullRequest pullRequest : pullRequests) {
            if (pullRequest.getState().equalsIgnoreCase("open")) {
                abertos++;
            } else {
                fechados++;
            }
        }
        opened.setText(abertos.toString());
        closed.setText(fechados.toString());
    }

    private void getPullRequests() {
        if (NetworkUtil.checkConnection(activity)) {
            call = service.listarPullRequests(owner, repo);
            call.enqueue(callback);
        } else {
            Toast.makeText(activity, getString(R.string.alert_no_internet), Toast.LENGTH_LONG).show();
        }
    }

}
