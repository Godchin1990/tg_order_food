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
import android.widget.Button;
import android.widget.ListView;

import com.pos.orderfood.R;
import com.pos.orderfood.adapter.AccountAdapter;
import com.pos.orderfood.bean.account;
import com.pos.orderfood.dialog.BankDialog;
import com.pos.orderfood.dialog.InputPasswordDialog;

//账户管理
public class AccountFragment1 extends NoBugFragment implements OnClickListener{
	private Context context;
	private AccountAdapter adapter1, adapter2;//提现，账户管理
	private List<account>list1,list2;//提现，账户管理
	private ListView listview;
	private Button reflect;
	private BankDialog bankdialog;
	private InputPasswordDialog passdialog;
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_account1, null);
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
		reflect=(Button) view.findViewById(R.id.btn_reflect);
		reflect.setOnClickListener(this);
		list1=new ArrayList<account>();
		for(int i=0;i<3;i++){
			account a=new account();
			a.setId("2015111118222"+i);
			a.setCardid("20158691263"+i);
			a.setStatue("处理中");
			a.setMoney("5214"+i);
			a.setFee("-5.00");
			a.setData("2015-11-11 11:24:2"+i);
			a.setPerson("罗先生");
			list1.add(a);
		}
		list2=new ArrayList<account>();
		for(int i=0;i<3;i++){
			account a=new account();
			a.setId("2015111118222"+i);
			a.setCardid("20158691263"+i);
			a.setStatue("处理中");
			a.setMoney("5204"+i);
			a.setFee("-5.00");
			a.setData("2015-11-11 14:24:2"+i);
			a.setPerson("罗先生");
			list2.add(a);
		}
		listview=(ListView) view.findViewById(R.id.lv_retail);
		adapter1=new AccountAdapter(context, list1);
		listview.setAdapter(adapter1);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_reflect: {
			bankdialog = new BankDialog(context, new BankDialog.CallBack() {

				@Override
				public void callback(String money) {
					// TODO Auto-generated method stub
					passdialog = new InputPasswordDialog(context, money);
					passdialog.show();
				}
			});
			bankdialog.show();

		}
			break;

		default:
			break;
		}
	}
}
