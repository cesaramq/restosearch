package dominio.mi.restaurant.ui.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dumevi Cruces on 09/07/17.
 */

public class RestaurantsScrollListener extends RecyclerView.OnScrollListener {
    private boolean start = false;
    private int lastItems = 0;
    private OnRestaurantScrollListenerActions onRestaurantScrollListenerActions;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int listItems = recyclerView.getAdapter().getItemCount();
        int lastPosition = listItems - 1;
        View lastItemVisible = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
        int lastItemAdapter = recyclerView.getChildAdapterPosition(lastItemVisible);

        if (lastPosition == lastItemAdapter && !start) {
            lastItems = listItems;
            if (onRestaurantScrollListenerActions != null && lastItemAdapter == lastItems - 1) {
                onRestaurantScrollListenerActions.loadMore();
                start = true;
            }
        } else {
            if (listItems > lastItems) {
                start = false;
            }
        }
    }

    public void setOnRestaurantScrollListenerActions(OnRestaurantScrollListenerActions onRestaurantScrollListenerActions) {
        this.onRestaurantScrollListenerActions = onRestaurantScrollListenerActions;
    }

    public interface OnRestaurantScrollListenerActions {
        void loadMore();
    }

    public void setLastItems(int lastItems) {
        this.lastItems = lastItems;
    }
}
