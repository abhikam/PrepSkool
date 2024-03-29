package in.prepskool.prepskoolacademy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.prepskool.prepskoolacademy.utils.IntentData;
import in.prepskool.prepskoolacademy.R;
import in.prepskool.prepskoolacademy.utils.RecyclerTouchListener;
import in.prepskool.prepskoolacademy.utils.RecyclerViewType;
import in.prepskool.prepskoolacademy.activities.StandardActivity;
import in.prepskool.prepskoolacademy.model.SectionHome;

public class HomeSectionRecyclerViewAdapter
        extends RecyclerView.Adapter<HomeSectionRecyclerViewAdapter.HomeSectionViewHolder> {

    class HomeSectionViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionLabel;
        private RecyclerView itemRecyclerView;

        HomeSectionViewHolder(View itemView) {
            super(itemView);
            sectionLabel = itemView.findViewById(R.id.home_section_label);
            itemRecyclerView = itemView.findViewById(R.id.home_item_recycler_view);
        }
    }

    private Context context;
    private RecyclerViewType recyclerViewType;
    private ArrayList<SectionHome> sectionHomeArrayList;

    public HomeSectionRecyclerViewAdapter(Context context, RecyclerViewType recyclerViewType,
                               ArrayList<SectionHome> sectionHomeArrayList) {
        this.context = context;
        this.recyclerViewType = recyclerViewType;
        this.sectionHomeArrayList = sectionHomeArrayList;
    }

    @NonNull
    @Override
    public HomeSectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home_section,
                parent, false);
        return new HomeSectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSectionViewHolder holder, int position) {
        final SectionHome sectionHome = sectionHomeArrayList.get(position);
        holder.sectionLabel.setText(sectionHome.getSectionLabel());

        //recycler view for items
        holder.itemRecyclerView.setHasFixedSize(true);
        holder.itemRecyclerView.setNestedScrollingEnabled(false);

        /* set layout manager on basis of recyclerview enum type */
        switch (recyclerViewType) {
            case LINEAR_VERTICAL:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case LINEAR_HORIZONTAL:
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager1);
                break;
            case GRID:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                holder.itemRecyclerView.setLayoutManager(gridLayoutManager);
                break;
        }

        HomeItemRecyclerViewAdapter adapter = new HomeItemRecyclerViewAdapter(context,
                sectionHome.getItemArrayList());
        holder.itemRecyclerView.setAdapter(adapter);

        holder.itemRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(context,
                holder.itemRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                IntentData.CATEGORY_HOME = sectionHome.getSectionLabel();
                IntentData.SUBCATEGORY_HOME = sectionHome.getItemArrayList().get(position).getLabel();
                IntentData.SUBCATEGORY_HOME = IntentData.SUBCATEGORY_HOME.replace("NCERT ", "");

                switch (IntentData.CATEGORY_HOME) {

                    case "SCHOOL BOARDS": {

                        Intent intent = new Intent(context, StandardActivity.class);
                        intent.putExtra("CATEGORY_HOME", IntentData.CATEGORY_HOME);
                        intent.putExtra("SUBCATEGORY_HOME", IntentData.SUBCATEGORY_HOME + " BOARD");
                        intent.putExtra("type", "0");
                        context.startActivity(intent);
                        break;
                    }
                    case "NCERT": {

                        Intent intent = new Intent(context, StandardActivity.class);
                        intent.putExtra("CATEGORY_HOME", IntentData.CATEGORY_HOME);
                        intent.putExtra("SUBCATEGORY_HOME", IntentData.SUBCATEGORY_HOME);
                        intent.putExtra("type", "0");
                        context.startActivity(intent);
                        break;
                    }

                    case "CBSE PRACTICE PAPERS":  {

                        Intent intent = new Intent(context, StandardActivity.class);
                        intent.putExtra("CATEGORY_HOME", IntentData.CATEGORY_HOME);
                        intent.putExtra("SUBCATEGORY_HOME", IntentData.SUBCATEGORY_HOME);
                        intent.putExtra("BOARD", "cbse board");
                        intent.putExtra("type", "1");
                        context.startActivity(intent);
                        break;
                    }
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public int getItemCount() {
        return sectionHomeArrayList.size();
    }
}

