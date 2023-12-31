package co.bongga.touristeando.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import co.bongga.touristeando.R;
import co.bongga.touristeando.models.GalleryItem;

/**
 * Created by bongga on 3/15/17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {
    private List<GalleryItem> imageList;
    private Context context;

    public GalleryAdapter(Context context, List<GalleryItem> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public GalleryAdapter.GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_thumb, parent, false);

        return new GalleryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.GalleryHolder holder, int position) {
        GalleryItem image = imageList.get(position);

        if(image.getUrlLarge() != null){
            Glide.with(context).load(image.getUrlLarge())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumb);
        }
        else if(image.getBitmap() != null){
            holder.thumb.setImageBitmap(image.getBitmap());
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class GalleryHolder extends RecyclerView.ViewHolder {
        public ImageView thumb;

        public GalleryHolder(View view){
            super(view);
            thumb = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }
}
