package edu.mypayments;

import android.support.v4.app.Fragment;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import edu.mypayments.client.ClientModel;

/**
 * Created by Александр on 08.10.2016.
 */
public class MonthListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MonthListFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        conect();
    }

    public static void conect(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    Socket mSocket = new Socket(ClientModel.iP, ClientModel.port);
                    if (mSocket != null) {
                        Scanner sc = new Scanner(mSocket.getInputStream());
                        String answerFromServer = sc.nextLine() + "\n";
                        int separator = answerFromServer.indexOf("#");

                        //парсим строку
                        String tariffEnergy = answerFromServer.substring(0, separator);
                        String tariffGas = answerFromServer.substring(separator + 1, answerFromServer.length());
                        ClientModel.TARIFF_ENERGY = Double.parseDouble(tariffEnergy);
                        ClientModel.TARIFF_GAS = Double.parseDouble(tariffGas);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
