package com.pos.orderfood.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.pos.orderfood.R;

//账户管理
public class AccountFragment extends NoBugFragment {
	private Context context;
	private FragmentManager fm;
	private AccountFragment1 account1;
	private AccountFragment2 account2;
	private ReceiveBroadCast receiveBroadCast;
	private int pre_index = 0;
	private boolean flag = false;// 判断是否需要隐藏

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		receiveBroadCast = new ReceiveBroadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.pos.orderfood.fragment.AccountFragment"); // 只有持有相同的action的接受者才能接收此广播
		context.registerReceiver(receiveBroadCast, filter);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		context.unregisterReceiver(receiveBroadCast);
	}

	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_account, null);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		context = get_Activity();
		fm = ((FragmentActivity) context).getSupportFragmentManager();
		InitFragment();
		setTab(pre_index);
	}

	private void InitFragment() {
		// TODO Auto-generated method stub
		account1 = new AccountFragment1();
		account2 = new AccountFragment2();
	}

	public void setTab(int position) {
		if (flag)
			hideTab();

		switch (position) {
		case 0: {

			if (account1.isAdded()) {
				fm.beginTransaction().show(account1).commit();
			} else {
				fm.beginTransaction().add(R.id.fra_account, account1).commit();
				// fm.beginTransaction().replace(R.id.fra_account,
				// account1).commit();
			}
			flag = true;
			setPre_index(0);
		}
			break;
		case 1: {

			if (account2.isAdded()) {
				fm.beginTransaction().show(account2).commit();
			} else {
				fm.beginTransaction().add(R.id.fra_account, account2).commit();
				// fm.beginTransaction().replace(R.id.fra_account,
				// account2).commit();
			}
			setPre_index(1);
		}
			break;
		}
		fm.beginTransaction().commit();

	}

	// 隐藏上一个Fragment
	private void hideTab() {
		switch (pre_index) {
		case 0: {
			fm.beginTransaction().hide(account1).commit();
		}
			break;
		case 1: {
			fm.beginTransaction().hide(account2).commit();
		}
			break;

		}
	}

	public void setPre_index(int pre_index) {
		this.pre_index = pre_index;
	}

	class ReceiveBroadCast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String tag = intent.getExtras().getString("tag");
			if (tag.equals("AccountFragment1")) {// 账户管理
				setTab(0);
			} else {// 提现管理
				setTab(1);
			}
		}
	}
}
