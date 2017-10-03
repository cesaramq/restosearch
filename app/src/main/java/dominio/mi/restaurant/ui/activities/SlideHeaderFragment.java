package dominio.mi.restaurant.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dominio.mi.restaurant.R;

public class SlideHeaderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_slide_header,
                container, false);

        ImageView imageHeader = (ImageView) rootView.findViewById(R.id.image_header_slide);

        Glide.with(this).load(getArguments().getString("url")).into(imageHeader);

        return rootView;
    }
}
