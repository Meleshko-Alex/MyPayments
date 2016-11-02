package edu.mypayments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.UUID;

/**
 * Created by Александр on 04.10.2016.
 */
public class MonthPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<Month> monthList;
    private static final String EXTRA_MONTH_ID = "edu.mypayments.month_id";
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_pager);

        UUID monthId = (UUID)getIntent().getSerializableExtra(EXTRA_MONTH_ID);

        mViewPager = (ViewPager)findViewById(R.id.activity_month_view_pager);
        monthList = MonthLab.get(this).getMonthList();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Month month = monthList.get(position);
                return MonthFragment.newInstance(month.getId());
            }

            @Override
            public int getCount() {
                return monthList.size();
            }
        });

        for (int i = 0; i < monthList.size(); i++) {
            if (monthList.get(i).getId().equals(monthId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent (Context packageContext, UUID monthId){
        Intent intent = new Intent(packageContext, MonthPagerActivity.class);
        intent.putExtra(EXTRA_MONTH_ID, monthId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_month, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_month_delete_month){

            //Уничтожаем фотофайл относящийся к этому месяцу
            MonthLab.get(this).getPhotoFile(monthList.get(mViewPager.getCurrentItem())).delete();
            MonthLab.get(this).removeMonth(monthList.get(mViewPager.getCurrentItem()));
            finish();
        }


        if(id == R.id.menu_month_send_meter){
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("option", "send_meter");
            startActivity(intent);
        }

        if(id == R.id.menu_month_new_port_and_ip){
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("option", "new_port_and_ip");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
}
