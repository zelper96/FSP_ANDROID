package com.nexgrid_fsp.myapplication.list;



import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nexgrid_fsp.myapplication.R;
import com.nexgrid_fsp.myapplication.vo.ListViewItemVO;

import java.util.ArrayList;
import java.util.List;

public class setListUiDataItemVH extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<ListViewItemVO> items;



    public setListUiDataItemVH(Context context, ArrayList<ListViewItemVO> items) {
        this.context = context;
        this.items = items;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_view_item,viewGroup,false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        ListViewItemVO view_item = items.get(position);
        

        ViewHolder vh = (ViewHolder) holder;
        vh.num.setText((view_item.getNum()));
        vh.name.setText(view_item.getName());
        vh.resident_all.setText(view_item.getResident_all());
        vh.start_date.setText(view_item.getstart_date());
        vh.end_date.setText(view_item.getend_date());
        vh.zhang_ji.setText(view_item.getZhang_ji());
        vh.back_color.setBackgroundColor(Color.parseColor(view_item.getBack_color()));

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView num;
        TextView name;
        TextView resident_all;
        TextView start_date;
        TextView end_date;
        TextView zhang_ji;
        LinearLayout back_color;

        public ViewHolder(View itemView) {
            super(itemView);

            num = itemView.findViewById(R.id.num);
            name = itemView.findViewById(R.id.name);
            resident_all = itemView.findViewById(R.id.resident_all);
            start_date = itemView.findViewById(R.id.start_date);
            end_date = itemView.findViewById(R.id.end_date);
            zhang_ji = itemView.findViewById(R.id.zhang_ji);
            back_color = itemView.findViewById(R.id.back_color);


        }
    }

}
