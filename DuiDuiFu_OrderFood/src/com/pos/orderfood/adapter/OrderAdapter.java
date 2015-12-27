package com.pos.orderfood.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pos.orderfood.R;
import com.pos.orderfood.bean.Order;

public class OrderAdapter extends BaseAdapter{
	private Context context;
	private List<Order>list=new ArrayList<Order>();
	private int index;
	private CallBack callback;
	public interface CallBack{
		public void callback(int postion);
	}
	public OrderAdapter(Context context,List<Order>list,int index,CallBack callBack){
		this.context=context;
		this.callback=callBack;
		this.list=list;
		this.index=index;
	}
	public void update(List<Order>list,int index){
		this.list=list;
		this.index=index;
		notifyDataSetChanged();
	}
	public void update(int index){
		this.index=index;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		if(list.size()>10)
			return list.size();
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.adapter_order, null);
			holder.orderid=(TextView) convertView.findViewById(R.id.tv_orderid);
			holder.time=(TextView) convertView.findViewById(R.id.tv_time);
			holder.person=(TextView) convertView.findViewById(R.id.tv_person);
			holder.statue=(TextView) convertView.findViewById(R.id.tv_statue);
			holder.ll=(LinearLayout) convertView.findViewById(R.id.ll_item);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		if(position<list.size()){
			holder.orderid.setText(list.get(position).getOrderid());
			holder.time.setText(list.get(position).getData());
			holder.person.setText(list.get(position).getPerson());
			holder.statue.setText(list.get(position).getStatue());
			if(index==position){
				holder.ll.setBackgroundColor(context.getResources().getColor(R.color.order_item));
				callback.callback(position);
				//holder.line.setVisibility(View.VISIBLE);
			}else{
				holder.ll.setBackgroundColor(context.getResources().getColor(R.color.white));
				//holder.line.setVisibility(View.INVISIBLE);
			}
		}
		
		 return convertView;
	}
	private class ViewHolder {
		TextView orderid,statue,person,time;
		LinearLayout ll;
	}

}
