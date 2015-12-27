package com.pos.orderfood.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.pos.orderfood.R;
import com.pos.orderfood.adapter.AccountAdapter3;
import com.pos.orderfood.bean.Profit;

public class AccountFragmentProfit extends NoBugFragment implements OnClickListener{
	private Context context;
	private AccountAdapter3 adapter1;//提现，账户管理
	private List<Profit>list1;//提现，账户管理
	private ListView listview;
  
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_profit, null);
		context = get_Activity();

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		InitUI();
	}

	private void InitUI() {
		// TODO Auto-generated method stub
 
		list1=new ArrayList<Profit>();
		for(int i=0;i<10;i++){
 
			Profit a=new Profit();
			a.setId(""+(i+1));
			a.setProfitid("201510300000"+i);
			a.setMemname("罗订单");
			a.setMoney("1254.21");
			a.setData("2015-11-11 11:24:2"+i);
			list1.add(a);
		}
 
	 
		listview=(ListView) view.findViewById(R.id.lv_retail);
		adapter1=new AccountAdapter3(context, list1);
		listview.setAdapter(adapter1);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
 
	}

	
}
