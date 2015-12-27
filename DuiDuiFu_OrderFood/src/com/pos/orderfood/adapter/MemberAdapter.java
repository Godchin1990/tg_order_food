package com.pos.orderfood.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pos.orderfood.R;
import com.pos.orderfood.bean.Member;

public class MemberAdapter extends BaseAdapter{
	private Context context;
	private List<Member>list=new ArrayList<Member>();
	private CallBack callback;
	public MemberAdapter(Context context,List<Member>list,CallBack callback){
		this.context=context;
		this.callback=callback;
		this.list=list;
	}
	public void update(List<Member>list){
		this.list=list;
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
					R.layout.adapter_member, null);
			holder.cb=(CheckBox) convertView.findViewById(R.id.cb);
			holder.detail=(Button) convertView.findViewById(R.id.btn_detail);
			holder.phone=(TextView) convertView.findViewById(R.id.tv_phone);
			holder.grade=(TextView) convertView.findViewById(R.id.tv_grade);
			holder.interge=(TextView) convertView.findViewById(R.id.tv_interge);
			holder.count=(TextView) convertView.findViewById(R.id.tv_count);
			holder.date=(TextView) convertView.findViewById(R.id.tv_date);
			holder.person=(TextView) convertView.findViewById(R.id.tv_person);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.phone.setText(list.get(position).getPhone());
		holder.grade.setText(list.get(position).getGrade());
		holder.interge.setText(list.get(position).getIntegral());
		holder.count.setText(list.get(position).getCount());
		holder.date.setText(list.get(position).getDate());
		holder.person.setText(list.get(position).getPerson());
		holder.detail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.callback();//跳转界面
			}
		});
		return convertView;
	}
	private class ViewHolder {
		TextView phone,grade,interge,count,date,person;
		 
		CheckBox cb;
		Button detail;
	}
	public interface CallBack{
		public void callback();
	}
}
