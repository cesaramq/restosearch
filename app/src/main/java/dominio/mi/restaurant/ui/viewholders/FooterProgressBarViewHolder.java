package dominio.mi.restaurant.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import dominio.mi.restaurant.R;

/**
 * Created by Dumevi Cruces on 06/09/17.
 */

public class FooterProgressBarViewHolder extends RecyclerView.ViewHolder {
    private ProgressBar progressBar;
    private TextView textView;

    public FooterProgressBarViewHolder(View itemView) {
        super(itemView);

        progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_footer);
        textView = (TextView) itemView.findViewById(R.id.footer_no_matches);
    }

    public void setFooter(int listSize) {
        if (listSize == 0) {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

    }

}
