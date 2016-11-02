package edu.mypayments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Александр on 13.10.2016.
 */
public class SendLetterFragment extends Fragment {
    private EditText et_whom;
    private EditText et_theme_name;
    private EditText et_account_number;
    private EditText et_client_full_name;
    private EditText et_client_full_adress;
    private EditText et_client_testimony;
    private EditText et_date_test;
    private EditText et_client_phone;
    private Button btn_send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_send_letter, container, false);

        et_whom = (EditText)v.findViewById(R.id.et_whom);
        et_theme_name = (EditText)v.findViewById(R.id.et_theme_name);
        et_account_number = (EditText)v.findViewById(R.id.et_account_number);
        et_client_full_name = (EditText)v.findViewById(R.id.et_client_full_name);
        et_client_full_adress = (EditText)v.findViewById(R.id.et_client_full_adress);
        et_client_testimony = (EditText)v.findViewById(R.id.et_client_testimony);
        et_date_test = (EditText)v.findViewById(R.id.et_date_test);
        et_client_phone = (EditText)v.findViewById(R.id.et_client_phone);
        btn_send = (Button)v.findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();
                builder.append("л/с ").append(et_account_number.getText().toString()).append("\n");
                builder.append(et_client_full_name.getText().toString()).append("\n");
                builder.append(et_client_full_adress.getText().toString()).append("\n");
                builder.append("показания счетчика: ").append(et_client_testimony.getText().toString()).append("\n");
                builder.append("дата снятия показаний: ").append(et_date_test.getText().toString()).append("\n");
                builder.append("тел. для связи: ").append(et_client_phone.getText().toString()).append("\n");

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{et_whom.getText().toString()});
                email.putExtra(Intent.EXTRA_SUBJECT, et_theme_name.getText().toString());
                email.putExtra(Intent.EXTRA_TEXT, builder.toString());
                email.setType("plain/text");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                getActivity().finish();
            }
        });
        return v;
    }
}
