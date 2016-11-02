package edu.mypayments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.mypayments.client.ClientModel;

/**
 * Created by Александр on 13.10.2016.
 */
public class NewServerConectFragment extends Fragment {
    private EditText et_port;
    private EditText et_ip;
    private Button btn_conect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_server_fragment, container, false);

        et_port = (EditText)v.findViewById(R.id.et_port);
        et_ip = (EditText)v.findViewById(R.id.et_ip);


        btn_conect = (Button)v.findViewById(R.id.btn_conect);
        btn_conect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_port.getText().toString().equals(""))et_port.setText("0");
                int port = Integer.parseInt(et_port.getText().toString());
                String ip = et_ip.getText().toString();
                ClientModel.iP = ip;
                ClientModel.port = port;
                MonthListActivity.conect();
                getActivity().finish();
            }
        });
        return v;
    }
}
