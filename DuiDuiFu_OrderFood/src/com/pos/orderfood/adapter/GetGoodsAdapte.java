package com.pos.orderfood.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pos.orderfood.R;

public class GetGoodsAdapte extends BaseAdapter{
	private Context context;
	private List<String>list=new ArrayList<String>();
	private int count;
	public GetGoodsAdapte(Context context,String name){
		this.context=context;
		if(name!=null )
			list.add(name);
	}
	public void update(String name){
		list.add(name);
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(getCount()>0)
			return list.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.adapter_getgoods, null);
			holder.name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.minus=(ImageView) convertView.findViewById(R.id.iv_minus);
			holder.add=(ImageView) convertView.findViewById(R.id.iv_add);
			holder.num=(TextView) convertView.findViewById(R.id.tv_num);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(list.get(position)!=null){
			holder.num.setText( "1");
			 
			holder.name.setText((position+1)+"."+list.get(position) );
			holder.add.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					count=Integer.parseInt((String) holder.num.getText());
					count++;
					holder.num.setText(count+"");
				}
			});
			holder.minus.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(count>1){
						count=Integer.parseInt((String) holder.num.getText());
						count--;
						holder.num.setText(count+"");
					
					}
				}
			});
		}
 
		return convertView;
	}
	private class ViewHolder {
		private TextView name,num;
		private ImageView minus,add;
	}

}
