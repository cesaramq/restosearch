package dominio.mi.restaurant.ui.activities;

import android.view.MenuItem;

import dominio.mi.restaurant.ui.MyActivity;

/**
 * Created by Dumevi Cruces on 01/10/17.
 */

public class AlgoActivity extends MyActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuCaseLogin(item);
    }
}
