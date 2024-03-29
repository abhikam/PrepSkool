package in.prepskool.prepskoolacademy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import in.prepskool.prepskoolacademy.R;
import in.prepskool.prepskoolacademy.activities.DownloadActivity;
import in.prepskool.prepskoolacademy.activities.PdfLoaderActivity;
import in.prepskool.prepskoolacademy.model.Pdf;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView itemLabel;
        private TextView itemVieww;
        private TextView itemDown;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemLabel = (TextView) itemView.findViewById(R.id.item_label);

            itemVieww = (TextView) itemView.findViewById(R.id.item_view);

            itemDown = (TextView) itemView.findViewById(R.id.item_down);

        }
    }

    private Context context;
    private ArrayList<Pdf> arrayList;
    private String title;

    ItemRecyclerViewAdapter(Context context, ArrayList<Pdf> arrayList, String title) {
        this.context = context;
        this.arrayList = arrayList;
        this.title = title;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_custom_item,
                parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {

        holder.itemLabel.setText(arrayList.get(position).getName());

        holder.itemVieww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, PdfLoaderActivity.class);
                i.putExtra("name", arrayList.get(holder.getAdapterPosition()).getName());
                i.putExtra("link", arrayList.get(holder.getAdapterPosition()).getLink());
                i.putExtra("slug", arrayList.get(holder.getAdapterPosition()).getSlug());
                i.putExtra("title", title);
                context.startActivity(i);
            }
        });

        holder.itemDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, DownloadActivity.class);
                i.putExtra("name", arrayList.get(holder.getAdapterPosition()).getName());
                i.putExtra("link", arrayList.get(holder.getAdapterPosition()).getLink());
                i.putExtra("slug", arrayList.get(holder.getAdapterPosition()).getSlug());
                i.putExtra("title", title);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
