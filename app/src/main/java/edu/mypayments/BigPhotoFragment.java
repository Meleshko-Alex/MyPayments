package edu.mypayments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Александр on 07.10.2016.
 */
public class BigPhotoFragment extends Fragment {
    private Button btn_delete_photo;
    private Button btn_back;
    private ImageView iv_big_photo;
    private File mPhotoFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_big_photo, container,false);

        iv_big_photo = (ImageView)v.findViewById(R.id.iv_big_photo);
        btn_delete_photo = (Button)v.findViewById(R.id.btn_delete_photo);
        btn_delete_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoFile.delete();
                try {
                } catch (Exception e){
                    e.printStackTrace();
                }

                getActivity().finish();
            }
        });
        btn_back = (Button)v.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getActivity().getIntent();
        mPhotoFile = (File)intent.getSerializableExtra("photoFile");
        updatePhotoView();
    }

    private void updatePhotoView(){
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            iv_big_photo.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            iv_big_photo.setImageBitmap(bitmap);
        }
    }
}
