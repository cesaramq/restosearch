package dominio.mi.restaurant.ui;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Dumevi Cruces on 21/09/17.
 */

public class ProgressBarUtil {
    private ProgressBar progressBar;
    private View overlay;

    public ProgressBarUtil(ProgressBar progressBar, View overlay) {
        this.progressBar = progressBar;
        this.overlay = overlay;
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
        overlay.setVisibility(View.GONE);
    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
        overlay.setVisibility(View.VISIBLE);
    }
}
