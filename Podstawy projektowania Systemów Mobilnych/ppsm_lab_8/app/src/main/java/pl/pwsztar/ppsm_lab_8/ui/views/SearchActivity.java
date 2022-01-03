package pl.pwsztar.ppsm_lab_8.ui.views;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Observable;
import java.util.Observer;


import pl.pwsztar.ppsm_lab_8.R;
import pl.pwsztar.ppsm_lab_8.ui.adapter.CityListAdapter;
import pl.pwsztar.ppsm_lab_8.ui.databinding.SearchActivityDataBinding;
import pl.pwsztar.ppsm_lab_8.ui.viewmodel.SearchActivityViewModel;
import pl.pwsztar.ppsm_lab_8.utils.NetworkUtils;


public class SearchActivity extends AppCompatActivity implements Observer {
    private SearchActivityViewModel viewModel;
    private SearchActivityDataBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.R)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        binding = new SearchActivityDataBinding(getWindow().getDecorView().getRootView());
        viewModel = new SearchActivityViewModel(getApplicationContext(), this);

        if( !NetworkUtils.isNetworkAvailable( getApplicationContext()) ) {
            NetworkUtils.buildNetworkAlert(this);
        }

        setupToolbar();
        setUpSearchView();
        setupObserver(viewModel);
        setupListCities(binding.recyclerCities);
    }

    public void setupToolbar() {
        setTitle(getString(R.string.search_city));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void setUpSearchView() {
        binding.searchView.requestFocus();
        binding.searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.searchView.performClick();
                viewModel.checkCityInRepo(binding.searchView.getText().toString());
                return true;
            }
            return false;
        });

        binding.searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.cityListAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //binding.searchView.
    }

    private void setupListCities(RecyclerView recyclerBusStop) {
        binding.cityListAdapter = new CityListAdapter(this);
        recyclerBusStop.setAdapter(binding.cityListAdapter);
        recyclerBusStop.setLayoutManager(new LinearLayoutManager(this));
        recyclerBusStop.setHasFixedSize(true);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.reset();
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof SearchActivityViewModel) {
            CityListAdapter busStopListAdapter = (CityListAdapter) binding.recyclerCities.getAdapter();
            SearchActivityViewModel busStopListViewModel = (SearchActivityViewModel) observable;
            if (busStopListAdapter != null) {
                busStopListAdapter.update(busStopListViewModel.getCitiesList());
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
