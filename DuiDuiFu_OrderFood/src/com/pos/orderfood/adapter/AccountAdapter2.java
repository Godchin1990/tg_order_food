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
import com.pos.orderfood.bean.account2;

public class AccountAdapter2 extends BaseAdapter{
	private Context context;
	private List<account2>list=new ArrayList<account2>();
	public AccountAdapter2(Context context,List<account2>list){
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
					R.layout.adapter_account2_list, null);
			holder.item=(LinearLayout) convertView.findViewById(R.id.ll_item);
			holder.id=(TextView) convertView.findViewById(R.id.tv_pro_id);
			holder.card=(TextView) convertView.findViewById(R.id.tv_pro_card);
			holder.staues=(TextView) convertView.findViewById(R.id.tv_pro_staues);
			holder.price=(TextView) convertView.findViewById(R.id.tv_pro_price);
			holder.discount=(TextView) convertView.findViewById(R.id.tv_pro_discount);
			holder.date=(TextView) convertView.findViewById(R.id.tv_pro_date);
			holder.person=(TextView) convertView.findViewById(R.id.tv_pro_person);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(position<list.size()){
			holder.id.setText(list.get(position).getAccountid());
			holder.card.setText(list.get(position).getOrderid());
			holder.staues.setText(list.get(position).getDate());
			holder.price.setText(list.get(position).getType());
			holder.discount.setText(list.get(position).getPaytype());
			holder.date.setText(list.get(position).getExpenditure());
			holder.person.setText(list.get(position).getBalance());
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
		TextView id,card,staues,price,discount,date,person;
		LinearLayout item;
 
	}

}