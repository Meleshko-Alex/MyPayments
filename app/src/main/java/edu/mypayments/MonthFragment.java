package edu.mypayments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.UUID;

/**
 * Created by Александр on 03.10.2016.
 */
public class MonthFragment extends Fragment{
    private Month mMonth;
    private File mPhotoFile;
    private EditText et_month_title;
    //private Button btn_month_date;
    private CheckBox chb_month_closed;
    private static final String ARG_MONTH_ID = "month_id";
    //private static final String DIALOG_DATE = "DialogDate";
    //private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 2;
    private ImageButton im_btn_camera;
    private ImageView iv_check_foto;
    private boolean isHasPhoto;
    private EditText et_last_month_1, et_this_month_1, et_tariff_power, et_total_1;
    private EditText et_last_month_2, et_this_month_2, et_tariff_gas, et_total_2;
    private EditText et_last_month_3, et_this_month_3, et_tariff_cool_water, et_total_3;
    private EditText et_last_month_4, et_this_month_4, et_tariff_hot_water, et_total_4;
    private EditText et_house_pay, et_garbage, et_heating, et_extra_pay;
    private TextView et_summa;
    private Button btn_calculate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID monthId = (UUID)getArguments().getSerializable(ARG_MONTH_ID);
        mMonth = MonthLab.get(getActivity()).getMonth(monthId);
        mPhotoFile = MonthLab.get(getActivity()).getPhotoFile(mMonth);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_month, container, false);

        et_month_title = (EditText)v.findViewById(R.id.et_month_title);
        et_month_title.setText(mMonth.getTitle());
        et_month_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMonth.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        chb_month_closed = (CheckBox)v.findViewById(R.id.chb_month_closed);
        chb_month_closed.setChecked(mMonth.isMonthClosed());
        chb_month_closed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMonth.setMonthClosed(isChecked);
            }
        });

        iv_check_foto = (ImageView)v.findViewById(R.id.iv_check_foto);
        updatePhotoView();

        iv_check_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHasPhoto) {
                    Intent intent = new Intent(getActivity(), BigPhotoActivity.class);
                    intent.putExtra("photoFile",mPhotoFile);
                    startActivity(intent);
                }
            }
        });

        im_btn_camera =(ImageButton)v.findViewById(R.id.im_btn_camera);

        PackageManager packageManager = getActivity().getPackageManager();

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        im_btn_camera.setEnabled(canTakePhoto);
        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        im_btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        et_last_month_1 = (EditText)v.findViewById(R.id.et_last_month_1);
        et_last_month_1.setText(mMonth.getLast_month_1());
        et_last_month_2 = (EditText)v.findViewById(R.id.et_last_month_2);
        et_last_month_2.setText(mMonth.getLast_month_2());
        et_last_month_3 = (EditText)v.findViewById(R.id.et_last_month_3);
        et_last_month_3.setText(mMonth.getLast_month_3());
        et_last_month_4 = (EditText)v.findViewById(R.id.et_last_month_4);
        et_last_month_4.setText(mMonth.getLast_month_4());
        et_this_month_1 = (EditText)v.findViewById(R.id.et_this_month_1);
        et_this_month_1.setText(mMonth.getThis_month_1());
        et_this_month_2 = (EditText)v.findViewById(R.id.et_this_month_2);
        et_this_month_2.setText(mMonth.getThis_month_2());
        et_this_month_3 = (EditText)v.findViewById(R.id.et_this_month_3);
        et_this_month_3.setText(mMonth.getThis_month_3());
        et_this_month_4 = (EditText)v.findViewById(R.id.et_this_month_4);
        et_this_month_4.setText(mMonth.getThis_month_4());
        et_total_1 = (EditText)v.findViewById(R.id.et_total_1);
        et_total_1.setText(mMonth.getTotal_1());
        et_total_2 = (EditText)v.findViewById(R.id.et_total_2);
        et_total_2.setText(mMonth.getTotal_2());
        et_total_3 = (EditText)v.findViewById(R.id.et_total_3);
        et_total_3.setText(mMonth.getTotal_3());
        et_total_4 = (EditText)v.findViewById(R.id.et_total_4);
        et_total_4.setText(mMonth.getTotal_4());
        et_tariff_power = (EditText)v.findViewById(R.id.et_tariff_power);
        et_tariff_power.setText(mMonth.getTariff_power());
        et_tariff_gas = (EditText)v.findViewById(R.id.et_tariff_gas);
        et_tariff_gas.setText(mMonth.getTariff_gas());
        et_tariff_cool_water = (EditText)v.findViewById(R.id.et_tariff_cool_water);
        et_tariff_cool_water.setText(mMonth.getTariff_cool_water());
        et_tariff_hot_water = (EditText)v.findViewById(R.id.et_tariff_hot_water);
        et_tariff_hot_water.setText(mMonth.getTariff_hot_water());
        et_house_pay = (EditText)v.findViewById(R.id.et_house_pay);
        et_house_pay.setText(mMonth.getHouse_pay());
        et_garbage = (EditText)v.findViewById(R.id.et_garbage);
        et_garbage.setText(mMonth.getGarbage());
        et_heating = (EditText)v.findViewById(R.id.et_heating);
        et_heating.setText(mMonth.getHeating());
        et_extra_pay = (EditText)v.findViewById(R.id.et_extra_pay);
        et_extra_pay.setText(mMonth.getExtra_pay());
        et_summa = (TextView) v.findViewById(R.id.et_summa);
        et_summa.setText(mMonth.getTotal());
        btn_calculate = (Button)v.findViewById(R.id.btn_calculate);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                //MonthLab.getMonthLab().getMonth(mMonth.getId());
                bindMonth(mMonth);
                MonthLab.get(getActivity())
                        .updateMonth(mMonth);
            }
        });

        return v;
    }

    private void calculate() {
        double total_1;
        double total_2;
        double total_3;
        double total_4;

        if (et_last_month_1.getText().toString().equals("")
                || et_this_month_1.getText().toString().equals("")
                || et_tariff_power.getText().toString().equals("")){
            if (et_total_1.getText().toString().equals(""))et_total_1.setText("0");
            total_1 = Double.parseDouble(et_total_1.getText().toString());
        }else{
            total_1 = (getIntData(et_this_month_1) - getIntData(et_last_month_1))*getDoubleData(et_tariff_power);
            if (total_1 < 0 )total_1 = 0;
        }
        et_total_1.setText(Double.toString(total_1));

        if (et_last_month_2.getText().toString().equals("")
                || et_this_month_2.getText().toString().equals("")
                || et_tariff_gas.getText().toString().equals("")){
            if (et_total_2.getText().toString().equals(""))et_total_2.setText("0");
            total_2 = Double.parseDouble(et_total_2.getText().toString());
        }else{
            total_2 = (getIntData(et_this_month_2) - getIntData(et_last_month_2))*getDoubleData(et_tariff_gas);
            if (total_2 < 0 )total_2 = 0;
        }
        et_total_2.setText(Double.toString(total_2));

        if (et_last_month_3.getText().toString().equals("")
                || et_this_month_3.getText().toString().equals("")
                || et_tariff_cool_water.getText().toString().equals("")){
            if (et_total_3.getText().toString().equals(""))et_total_3.setText("0");
            total_3 = Double.parseDouble(et_total_3.getText().toString());
        }else{
            total_3 = (getIntData(et_this_month_3) - getIntData(et_last_month_3))*getDoubleData(et_tariff_cool_water);
            if (total_3 < 0 )total_3 = 0;
        }
        et_total_3.setText(Double.toString(total_3));

        if (et_last_month_4.getText().toString().equals("")
                || et_this_month_4.getText().toString().equals("")
                || et_tariff_hot_water.getText().toString().equals("")){
            if (et_total_4.getText().toString().equals(""))et_total_4.setText("0");
            total_4 = Double.parseDouble(et_total_4.getText().toString());
        }else{
            total_4 = (getIntData(et_this_month_4) - getIntData(et_last_month_4))*getDoubleData(et_tariff_hot_water);
            if (total_4 < 0 )total_4 = 0;
        }
        et_total_4.setText(Double.toString(total_4));

        double house_pay = getDoubleData(et_house_pay);
        double garbage = getDoubleData(et_garbage);
        double heating = getDoubleData(et_heating);
        double extra_pay = getDoubleData(et_extra_pay);

        double summa = total_1 + total_2 + total_3 + total_4
                + house_pay + garbage + heating + extra_pay;
        summa = Math.round(summa * 100.0) / 100.0;

        et_summa.setText(summa + " грн.");
    }
    private  int getIntData (EditText v){
        String text = v.getText().toString();
        if (text.equals("")) text = "0";
        int intData = Integer.parseInt(text);
        return  intData;
    }
    private  double getDoubleData (EditText v){
        String text = v.getText().toString();
        if (text.equals("")) text = "0";
        double doubleData = Double.parseDouble(text);
        return  doubleData;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static MonthFragment newInstance (UUID monthId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_MONTH_ID, monthId);
        MonthFragment fragment = new MonthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        MonthLab.get(getActivity())
                .updateMonth(mMonth);
    }

    public  void updatePhotoView(){
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            iv_check_foto.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            iv_check_foto.setImageBitmap(bitmap);
            isHasPhoto = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updatePhotoView();
    }

    public void bindMonth (Month month){
        mMonth = month;
        mMonth.setLast_month_1(et_last_month_1.getText().toString());
        mMonth.setLast_month_2(et_last_month_2.getText().toString());
        mMonth.setLast_month_3(et_last_month_3.getText().toString());
        mMonth.setLast_month_4(et_last_month_4.getText().toString());
        mMonth.setThis_month_1(et_this_month_1.getText().toString());
        mMonth.setThis_month_2(et_this_month_2.getText().toString());
        mMonth.setThis_month_3(et_this_month_3.getText().toString());
        mMonth.setThis_month_4(et_this_month_4.getText().toString());
        mMonth.setTotal_1(et_total_1.getText().toString());
        mMonth.setTotal_2(et_total_2.getText().toString());
        mMonth.setTotal_3(et_total_3.getText().toString());
        mMonth.setTotal_4(et_total_4.getText().toString());
        mMonth.setTariff_power(et_tariff_power.getText().toString());
        mMonth.setTariff_gas(et_tariff_gas.getText().toString());
        mMonth.setTariff_cool_water(et_tariff_cool_water.getText().toString());
        mMonth.setTariff_hot_water(et_tariff_hot_water.getText().toString());
        mMonth.setHouse_pay(et_house_pay.getText().toString());
        mMonth.setGarbage(et_garbage.getText().toString());
        mMonth.setHeating(et_heating.getText().toString());
        mMonth.setExtra_pay(et_extra_pay.getText().toString());
        mMonth.setTotal(et_summa.getText().toString());
    }
}
