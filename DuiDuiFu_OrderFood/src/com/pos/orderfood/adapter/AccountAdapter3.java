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
import com.pos.orderfood.bean.Profit;

public class AccountAdapter3 extends BaseAdapter{
	private Context context;
	private List<Profit>list=new ArrayList<Profit>();
	public AccountAdapter3(Context context,List<Profit>list){
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		if(list.size()>10)
			return list.size();
		return 10;
	}

	@Override
	public Object getItem(int position) {
		
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.adapter_account2_profit, null);
			holder.item=(LinearLayout) convertView.findViewById(R.id.ll_item);
			holder.id=(TextView) convertView.findViewById(R.id.tv_pro_id);
			holder.card=(TextView) convertView.findViewById(R.id.tv_pro_card);
			holder.staues=(TextView) convertView.findViewById(R.id.tv_pro_staues);
			holder.price=(TextView) convertView.findViewById(R.id.tv_pro_price);
			holder.discount=(TextView) convertView.findViewById(R.id.tv_pro_discount);
			 
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(position<list.size()){
			holder.id.setText(list.get(position).getId());
			holder.card.setText(list.get(position).getProfitid());
			holder.staues.setText(list.get(position).getMemname());
			holder.price.setText(list.get(position).getMoney());
			holder.discount.setText(list.get(position).getData());
 
		}
		if (position % 2 != 0) {
			holder.item.setBackgroundColor(context.getResources().getColor(
					R.color.white_gray));
		} else {
			holder.item.setBackgroundColor(context.getResources().getColor(
					R.color.white));
		}
		return convertView;
	}
	private class ViewHolder {
		TextView id,card,staues,price,discount;
		LinearLayout item;
 
	}

} 
