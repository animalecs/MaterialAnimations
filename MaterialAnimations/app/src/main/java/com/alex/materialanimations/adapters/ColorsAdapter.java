package com.alex.materialanimations.adapters;

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.materialanimations.R;
import com.alex.materialanimations.models.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 19/02/2018.
 */

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorViewHolder> {

    List<Color> colors = new ArrayList<>();

    public ColorsAdapter(List<Color> colors)
    {
        this.colors = colors;
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_item, parent, false);
        return new ColorViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        Color color = colors.get(position);
        holder.txtColor.setText(color.getColorName());
        holder.view.setBackgroundTintList(color.getColorValue());
        //by default the card is collapsed
        holder.imgArrow.setRotation(0);
        holder.txtColor.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return colors != null ? colors.size() : 0;
    }

    public  class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgArrow;
        public TextView txtColor;
        public View view;
        boolean isCollapsed = true;
        private int originalViewHeight = 0;

        public ColorViewHolder(View itemView) {

            super(itemView);
            imgArrow = (ImageView) itemView.findViewById(R.id.imgArrow);
            txtColor = (TextView) itemView.findViewById(R.id.txtColor);
            view = itemView;
            view.setOnClickListener(this);
            imgArrow.setOnClickListener(this);


        }

        @Override
        public void onClick(final View view) {

            Color color = colors.get(getAdapterPosition());

            switch(view.getId())
            {
                case R.id.card:

                    break;

                case R.id.imgArrow:
                    // If the originalHeight is 0 then find the height of the View being used
                    // This would be the height of the cardview

                    if (originalViewHeight == 0) {
                        originalViewHeight = view.getHeight();
                    }
                    int additionalHeight = 30;

                    // Declare a ValueAnimator object
                    ValueAnimator valueAnimator;
                    if (isCollapsed) {
                        txtColor.setVisibility(View.VISIBLE);
                        txtColor.setEnabled(true);
                        isCollapsed = false;
                        valueAnimator = ValueAnimator.ofInt(originalViewHeight, originalViewHeight + additionalHeight); // These values in this method can be changed to expand however much you like
                    } else {
                        isCollapsed = true;
                        valueAnimator = ValueAnimator.ofInt(originalViewHeight + additionalHeight, originalViewHeight);

                        Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                        a.setDuration(200);
                        // Set a listener to the animation and configure onAnimationEnd
                        a.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                txtColor.setVisibility(View.INVISIBLE);
                                txtColor.setEnabled(false);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        // Set the animation on the custom view
                        txtColor.startAnimation(a);
                    }
                    valueAnimator.setDuration(200);
                    valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            view.getLayoutParams().height = value.intValue();
                            view.requestLayout();
                        }
                    });
                    valueAnimator.start();
                    break;
            }
        }
    }

}
