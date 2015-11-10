package com.trending.foursquare;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.trending.foursquare.app.AppController;

import java.util.List;
import java.util.Map;



public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TrendViewHolder>{




    List<Map> trendlist;

    Context context;
    int loaderimg = R.drawable.ic_launcher;
    private RequestQueue requestQueue;


    public RVAdapter(List dv, Context c)
    {
         trendlist =dv;
         context=c;
         Log.d("list in rv adapter", trendlist.toString());


    }


    public class TrendViewHolder extends RecyclerView.ViewHolder {

		FrameLayout frameLayout ;
		TextView tvName, tvRestaurant , tvDesc, tvloc;
		ImageView imgmain;
        TrendViewHolder(View itemView) {
            super(itemView);

			tvName = (TextView) itemView.findViewById(R.id.name);
			tvRestaurant = (TextView) itemView.findViewById(R.id.restaurants);
			tvDesc = (TextView) itemView.findViewById(R.id.description);
			tvloc = (TextView) itemView.findViewById(R.id.loc);
			imgmain = (ImageView) itemView.findViewById(R.id.imageViewmain);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.framemain);

        }
    }





    @Override
    public TrendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachtab, parent, false);
        TrendViewHolder cvh = new TrendViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(final TrendViewHolder holder, final int position) {

        final String name = trendlist.get(position).get("name").toString();
        final String restaurant = trendlist.get(position).get("restaurant").toString();
      //  final String id = trendlist.get(position).get("id").toString();
      //  final String desc = trendlist.get(position).get("description").toString();
       // final String imgurl = trendlist.get(position).get("imgurl").toString();
		final String location = trendlist.get(position).get("location").toString();




		holder.tvName.setText(name);
		holder.tvRestaurant.setText(restaurant);
		//holder.tvDesc.setText(desc);
		holder.tvloc.setText(location);


		/*if (imgurl != null)


        {



            ImageLoader imageLoader = AppController.getInstance().getImageLoader();

            // If you are using normal ImageView
            // put the image url  here
            imageLoader.get("domain" + imgurl, new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("tag_image", "Image Load Error: " + error.getMessage());
                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                    if (response.getBitmap() != null) {
                        // load image into imageview
                        Drawable d = new BitmapDrawable(context.getResources(), response.getBitmap());
						holder.imgmain.setBackground(d);

                    }
                }
            });

         //   makeImageRequest("http://www.feedboard.in/api/media/images/" + imgurl, holder.imgMain, holder.tempImg);
        }*/

        holder.frameLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context , Description.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trendlist.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



}