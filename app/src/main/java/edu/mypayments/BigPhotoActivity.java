package edu.mypayments;

import android.support.v4.app.Fragment;

/**
 * Created by Александр on 08.10.2016.
 */
public class BigPhotoActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BigPhotoFragment();
    }
}
