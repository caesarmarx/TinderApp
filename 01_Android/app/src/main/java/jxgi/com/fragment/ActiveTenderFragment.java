package jxgi.com.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jxgi.com.R;
import jxgi.com.adapter.TenderAdapter;
import jxgi.com.model.TenderItem;
import jxgi.com.util.DividerItemDecoration;
import jxgi.com.util.SharedPrefManager;

/**
 * Created by Caesar on 5/26/2017.
 */

public class ActiveTenderFragment extends Fragment {

    @Bind(R.id.tender_list)
    RecyclerView tenderList;

    private ProgressDialog progressDialog;

    private TenderAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    private Paint p = new Paint();

    public static ActiveTenderFragment newInstance() {
        ActiveTenderFragment fragment = new ActiveTenderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_tender, container, false);
        ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        tenderList.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tenderList.setLayoutManager(mLinearLayoutManager);
        tenderList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        adapter = new TenderAdapter(getActivity());

        ArrayList<TenderItem> items = new ArrayList<>();
        items.add(new TenderItem("Argentina", "Banking", "Printing", "Eric", ""));
        items.add(new TenderItem("Brazil", "Banking", "Printing", "Eric", ""));
        items.add(new TenderItem("Canada", "Banking", "Printing", "Eric", ""));

        adapter.addItems(items);

        tenderList.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
    }


}