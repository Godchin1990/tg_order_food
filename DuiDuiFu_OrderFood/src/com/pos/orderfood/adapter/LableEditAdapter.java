package com.pos.orderfood.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pos.orderfood.R;



public class LableEditAdapter extends BaseAdapter {
	private Context context;
	private List<String> items;
	public LableEditAdapter(Context context,List<String> items) {
		this.context = context;
		this.items=items;
	}
    public void refresh(List<String> items){
    	this.items=items;
    	notifyDataSetChanged();
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Cache cache=null;
		if(view==null){
			LayoutInflater inflater=LayoutInflater.from(context);
			view=inflater.inflate(R.layout.adapter_lableedit, null);	
			cache=new Cache();
			cache.name=(TextView) view.findViewById(R.id.tv_name);
 
			cache.root=(RelativeLayout) view.findViewById(R.id.ll_root);
			cache.line=view.findViewById(R.id.line);
			view.setTag(cache);
		}else{
			cache=(Cache) view.getTag();
		}
		/*if(position<4){
			cache.name.setTextColor(context.getResources().getColor(R.color.red));
		}else{
			cache.name.setTextColor(context.getResources().getColor(R.color.gray));
		}
		if(position==4){
			cache.line.setVisibility(View.VISIBLE);
		}else{
			cache.line.setVisibility(View.GONE);
		}*/
		cache.root.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		cache.name.setText(items.get(position));
	 
		return view;
	}
	private class Cache{
		private TextView name;
 
		private View line;
		private RelativeLayout root;
		public Cache(){
			
		}
	}
	

}
