package com.arturoguillen.flightsearch.view.search;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.di.component.FlightComponent;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;
import com.arturoguillen.flightsearch.entities.client.Search;
import com.arturoguillen.flightsearch.presenter.SearchPresenter;
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
    EditText inbound;

    @BindView(R.id.et_outbound)
    EditText outbound;

    @BindView(R.id.sp_origin)
    Spinner spOrigin;

    @BindView(R.id.sp_destination)
    Spinner spDestination;

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
            /*showErrorInDestination();*/
        } else if (TextUtils.isEmpty(origin)) {
            /*showErrorInOrigin();*/
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

    @OnClick(R.id.et_outbound)
    public void onClickEtOutbound(final EditText editText) {
        setListenerToDatePicker(editText);
    }

    @OnClick(R.id.et_inbound)
    public void onClickEtInbound(final EditText editText) {
        setListenerToDatePicker(editText);
    }

    private void setListenerToDatePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

                editText.setText(sdf.format(calendar.getTime()));
            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(@StringRes int stringId) {

    }

    @Override
    public void hideMessage() {

    }

    @Override
    public void goToResultActivity(FlightsResult flightsResult) {
        startActivity(ResultActivity.createIntent(SearchActivity.this, flightsResult));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.sp_origin:
                origin = (String) spOrigin.getAdapter().getItem(position);
                break;
            case R.id.sp_destination:
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
