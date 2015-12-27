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

public class AccountFragmentAll extends NoBugFragment implements OnClickListener{
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
		for(int i=0;i<10;i++){
			/*private String accountid;//流水号
			private String orderid;
			private String date;
			private String type;
			private String paytype;
			private String expenditure;//收支类型
			private String Balance;//余额
*/
			account2 a=new account2();
			a.setAccountid("20151025"+i);
			a.setOrderid("201510300000"+i);
			a.setDate("2015-11-11 11:24:2"+i);
			a.setType("订单金额");
			a.setPaytype("微信支付");
			a.setExpenditure("+500.0"+i);
			a.setBalance("234.0"+i);
			
			list1.add(a);
		}
 
	 
		listview=(ListView) view.findViewById(R.id.lv_retail);
		adapter1=new AccountAdapter2(context, list1);
		listview.setAdapter(adapter1);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
 
	}

	
}
