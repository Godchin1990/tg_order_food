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
import com.pos.orderfood.adapter.AccountAdapter2;
import com.pos.orderfood.bean.account2;
import com.pos.orderfood.dialog.BankDialog;
import com.pos.orderfood.dialog.InputPasswordDialog;

public class AccountFragmentOut extends NoBugFragment implements OnClickListener{
	private Context context;
	private AccountAdapter2 adapter1;//提现，账户管理
	private List<account2>list1;//提现，账户管理
	private ListView listview;
 
	private BankDialog bankdialog;
	//private BankSelectDialog selectdialog;
	private InputPasswordDialog passdialog;
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_accountall, null);
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
 
		list1=new ArrayList<account2>();
	 
 
	 
		listview=(ListView) view.findViewById(R.id.lv_retail);
		adapter1=new AccountAdapter2(context, list1);
		listview.setAdapter(adapter1);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
 
	}

	
}
