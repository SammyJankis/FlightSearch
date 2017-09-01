package com.arturoguillen.flightsearch.view.search;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.di.component.FlightComponent;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;
import com.arturoguillen.flightsearch.entities.client.Search;
import com.arturoguillen.flightsearch.presenter.SearchPresenter;
import com.arturoguillen.flightsearch.utils.ui.LoadingButton;
import com.arturoguillen.flightsearch.view.InjectedActivity;
import com.arturoguillen.flightsearch.view.result.ResultActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class SearchActivity extends InjectedActivity implements SearchView, AdapterView.OnItemSelectedListener {

    @Inject
    SearchPresenter presenter;

    @BindView(R.id.et_adults)
    EditText adults;

    @BindView(R.id.et_children)
    EditText children;

    @BindView(R.id.et_infants)
    EditText infants;

    @BindView(R.id.et_inbound)
    TextView inbound;

    @BindView(R.id.et_outbound)
    TextView outbound;

    @BindView(R.id.sp_origin)
    Spinner spOrigin;

    @BindView(R.id.sp_destination)
    Spinner spDestination;

    @BindView(R.id.cl_search)
    CoordinatorLayout clSearch;

    @BindView(R.id.btn_search)
    LoadingButton searchButton;

    private Snackbar snackbar;

    String destination = null;
    String origin = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        ArrayAdapter<CharSequence> adapterDestination = ArrayAdapter.createFromResource(this,
                R.array.airports_array, android.R.layout.simple_spinner_item);
        adapterDestination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDestination.setAdapter(adapterDestination);
        spDestination.setSelection(0);
        destination = String.valueOf(adapterDestination.getItem(0));
        ArrayAdapter<CharSequence> adapterOrigin = ArrayAdapter.createFromResource(this,
                R.array.airports_array, android.R.layout.simple_spinner_item);
        adapterOrigin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrigin.setAdapter(adapterOrigin);
        spOrigin.setSelection(1);
        origin = String.valueOf(adapterOrigin.getItem(1));
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void injectComponent(FlightComponent component) {
        component.inject(this);
    }

    @OnClick(R.id.btn_search)
    public void onClickBtnSearch(View view) {

        if (TextUtils.isEmpty(destination)) {
            showErrorInDestination();
        } else if (TextUtils.isEmpty(origin)) {
            showErrorInOrigin();
        } else if (!destination.equals(origin)) {
            showErrorSameDestinationOrigin();
        } else {
            Search search = new Search();
            search.setOriginPlace(origin);
            search.setDestinationPlace(destination);
            search.setOutboundDate(String.valueOf(outbound.getText()));
            search.setInbounddate(String.valueOf(inbound.getText()));
            search.setAdults(Integer.parseInt(String.valueOf(adults.getText())));
            search.setChildren(Integer.parseInt(String.valueOf(children.getText())));
            search.setInfants(Integer.parseInt(String.valueOf(infants.getText())));

            presenter.search(search);
        }

    }

    private void showErrorSameDestinationOrigin() {
        showMessage(R.string.please_select_diferent_origin_destination);
    }

    private void showErrorInOrigin() {
        showMessage(R.string.please_select_origin);
    }

    private void showErrorInDestination() {
        showMessage(R.string.please_select_destination);
    }

    @OnClick(R.id.et_outbound)
    public void onClickAtOutbound(final TextView textView) {
        setListenerToDatePicker(textView);
    }

    @OnClick(R.id.et_inbound)
    public void onClickAtInbound(final TextView textView) {
        setListenerToDatePicker(textView);
    }

    private void setListenerToDatePicker(final TextView textView) {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                textView.setText(sdf.format(calendar.getTime()));
            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void showProgress() {
        searchButton.enableLoadingState();
    }

    @Override
    public void hideProgress() {
        searchButton.disableLoadingState();
    }

    @Override
    public void showMessage(@StringRes int stringId) {
        snackbar = Snackbar.make(clSearch, stringId, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    @Override
    public void hideMessage() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void goToResultActivity(FlightsResult flightsResult) {
        startActivity(ResultActivity.createIntent(SearchActivity.this, flightsResult));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.sp_origin:
                hideMessage();
                origin = (String) spOrigin.getAdapter().getItem(position);
                break;
            case R.id.sp_destination:
                hideMessage();
                destination = (String) spDestination.getAdapter().getItem(position);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
