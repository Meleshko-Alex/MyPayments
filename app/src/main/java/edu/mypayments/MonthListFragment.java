package edu.mypayments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Александр on 04.10.2016.
 */
public class MonthListFragment extends Fragment {
    private RecyclerView mMonthRecyclerView;
    private MonthAdapter mMonthAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_month_list, container, false);
        mMonthRecyclerView = (RecyclerView)v.findViewById(R.id.month_recycler_view);
        mMonthRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    private void updateUI() {
        MonthLab monthLab = MonthLab.get(getActivity());
        List<Month> monthList = monthLab.getMonthList();
        if (mMonthAdapter == null) {
            mMonthAdapter = new MonthAdapter(monthList);
            mMonthRecyclerView.setAdapter(mMonthAdapter);
        } else {
            mMonthAdapter.setMonth(monthList);
            mMonthAdapter.notifyDataSetChanged();
        }

    }

    private class MonthHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_title_text;
        private TextView tv_list_item_ask;
        private TextView tv_list_item_sum_money;
        private CheckBox chb_closed;
        private Month mMonth;

        public MonthHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_title_text = (TextView)itemView.findViewById(R.id.tv_list_item_title);
            tv_list_item_ask = (TextView)itemView.findViewById(R.id.tv_list_item_ask);
            tv_list_item_sum_money = (TextView)itemView.findViewById(R.id.tv_list_item_sum_money);
            //tv_date_text = (TextView)itemView.findViewById(R.id.tv_list_item_date);
            chb_closed = (CheckBox)itemView.findViewById(R.id.chb_list_item_month);
        }

        public void bindMonth (Month month){
            mMonth = month;
            tv_title_text.setText(mMonth.getTitle());
            //tv_date_text.setText(mMonth.getDate().toString());
            tv_list_item_ask.setText(getString(R.string.month_closed_label));
            String summa = mMonth.getTotal();
            if (summa == null) summa = "0 грн.";
            tv_list_item_sum_money.setText(getString(R.string.summa) + "  " + summa);
            chb_closed.setChecked(mMonth.isMonthClosed());
        }

        @Override
        public void onClick(View v) {
            Intent intent = MonthPagerActivity.newIntent(getActivity(), mMonth.getId());
            startActivity(intent);
        }
    }

    private class MonthAdapter extends  RecyclerView.Adapter<MonthHolder> {
        private List<Month> mMonthList;

        public MonthAdapter(List<Month> monthList) {
            mMonthList = monthList;
        }

        public void setMonth(List<Month> month){
            mMonthList = month;
        }

        @Override
        public MonthHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_month, parent, false);
            return new MonthHolder(v);
        }

        @Override
        public void onBindViewHolder(MonthHolder holder, int position) {
            Month month = mMonthList.get(position);
            holder.bindMonth(month);
        }

        @Override
        public int getItemCount() {
            return mMonthList.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_month:

                Month month = new Month();
                MonthLab.get(getActivity()).addMonth(month);
                Intent intent = MonthPagerActivity.newIntent(getActivity(), month.getId());
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
