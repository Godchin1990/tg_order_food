package com.pos.orderfood.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pos.orderfood.R;
import com.pos.orderfood.fragment.AccountFragment;
import com.pos.orderfood.fragment.CashFragment;
import com.pos.orderfood.fragment.FunctionFragment;
import com.pos.orderfood.fragment.GoodsManageFragment;
import com.pos.orderfood.fragment.HomeFragment;
import com.pos.orderfood.fragment.KitchenFragment;
import com.pos.orderfood.fragment.MemberFragmentNull;
import com.pos.orderfood.fragment.OrderFragment;
import com.pos.orderfood.push.MyApplication;
import com.pos.orderfood.service.HeartService.HeartSyncCallback;
import com.pos.orderfood.service.WindowService;
import com.pos.orderfood.util.StringUtil;
import com.wizarpos.cardinfolink.CloudPosPaymentClient.aidl.ICloudPay;

/**
 * 
 * @ClassName: MainActivity.java
 * 
 * @Description: 主界面
 * 
 * 
 * @date 2015-5-12 上午18:06:09
 * 
 */
public class MainActivity extends FragmentActivity implements HeartSyncCallback {
	private Context context;
	private HomeFragment fra_home;// 1首页
	private CashFragment fra_cash;// 2收银台
	private OrderFragment fra_order;// 3订单功能
	private GoodsManageFragment fra_goods;// 4商品管理
	private MemberFragmentNull fra_member;// 5会员管理
	private FunctionFragment fra_function;// 6功能管理
	private AccountFragment fra_account;// 7账户管理
	private KitchenFragment fra_kitchen;// 8厨房端
	private boolean flag = false;
	private Button home, cash, order, goods, member, function, account,
			kitchen;
	private TextView tv_frag;
	private ImageView tv_back;
	private String userId, loginSign;
	private String cashtag = "", goodstag = "";// 判断是从哪个fragment传递过来的
	FragmentManager fm;
	private int pre_index = 1;// 上一个fragment的索引
	public final static int PAYRESULTREQUESTCODE = 8;
	public final static int PAYRESULRESULTTCODE = 8;
	private ICloudPay mCupService;
	private RadioGroup group;
	private LinearLayout typeadd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		context = this;
		((MyApplication) getApplication()).addActivity(this);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_main);

		userId = getIntent().getStringExtra("userId");
		loginSign = getIntent().getStringExtra("loginSign");
		pre_index = getIntent().getIntExtra("index", 1);
		fm = getSupportFragmentManager();

		initView();
		initFragment();

		// Intent intent = new Intent();
		// intent.setClass(this, WindowService.class);
		// this.startService(intent);
		// pos机设备初始化
	}

	private void initView() {

		group = (RadioGroup) findViewById(R.id.rg_account);

		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rb1: {
					Intent intent = new Intent();
					intent.setAction("com.pos.orderfood.fragment.AccountFragment");
					intent.putExtra("tag", "AccountFragment1");
					context.sendBroadcast(intent);
				}
					break;
				case R.id.rb2: {
					Intent intent = new Intent();
					intent.setAction("com.pos.orderfood.fragment.AccountFragment");
					intent.putExtra("tag", "AccountFragment2");
					context.sendBroadcast(intent);
				}
					break;
				default:
					break;
				}
			}
		});
		typeadd = (LinearLayout) findViewById(R.id.ll_typeadd);

		/*
		 * rb1=(RadioButton) findViewById(R.id.rb1); rb2=(RadioButton)
		 * findViewById(R.id.rb1);
		 */
		home = (Button) findViewById(R.id.btn_main_home);
		cash = (Button) findViewById(R.id.btn_main_cash);
		order = (Button) findViewById(R.id.btn_main_order);
		member = (Button) findViewById(R.id.btn_main_member);
		goods = (Button) findViewById(R.id.btn_main_goods);
		account = (Button) findViewById(R.id.btn_main_account);
		function = (Button) findViewById(R.id.btn_main_function);
		kitchen = (Button) findViewById(R.id.btn_main_kitchen);
		typeadd.setOnClickListener(l);
		home.setOnClickListener(l);
		cash.setOnClickListener(l);
		account.setOnClickListener(l);
		member.setOnClickListener(l);
		kitchen.setOnClickListener(l);
		order.setOnClickListener(l);
		goods.setOnClickListener(l);
		function.setOnClickListener(l);

		tv_back = (ImageView) findViewById(R.id.tv_back);
		tv_back.setClickable(false);
		tv_back.setOnClickListener(l);
		tv_frag = (TextView) findViewById(R.id.tv_frag);
	}

	private OnClickListener l = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			switch (arg0.getId()) {
			case R.id.btn_main_home: {
				// setTab(0);
				Intent intent = new Intent(context, HomeActivity.class);
				startActivity(intent);
			}
				break;
			case R.id.btn_main_cash: {

				setTab(1);
			}
				break;
			case R.id.btn_main_order: {

				setTab(2);
			}
				break;
			case R.id.btn_main_goods: {

				setTab(3);
			}
				break;
			case R.id.btn_main_member: {

				setTab(4);
			}
				break;
			case R.id.btn_main_function: {

				setTab(5);
			}
				break;
			case R.id.btn_main_account: {

				setTab(6);
			}
				break;
			case R.id.btn_main_kitchen: {

				setTab(7);
			}
				break;
			case R.id.tv_back: {
				if (cashtag.equals("CashFragment1") && pre_index == 1) {
					cashtag = "";
					tv_back.setClickable(false);
					tv_back.setImageResource(R.drawable.logo1);
					fra_cash.setTab(0);
				} else {
					goodstag = "";
					tv_back.setClickable(false);
					tv_back.setImageResource(R.drawable.logo1);
					typeadd.setVisibility(View.GONE);
					fra_goods.setTab(0);
				}
			}
				break;
			case R.id.ll_typeadd: {
					fra_goods.getClssifyfrag();
			}
				break;
			}
		}
	};

	private void initFragment() {
		fra_account = new AccountFragment();
		fra_cash = new CashFragment();
		fra_cash.setCallback(new CashFragment.Callback() {

			@Override
			public void callback(String arg0) {
				if (arg0.equals("CashFragment1")) {
					tv_back.setClickable(true);
					tv_back.setImageResource(R.drawable.back_normal);

					cashtag = "CashFragment1";
				}
			}
		});
		fra_home = new HomeFragment();
		fra_member = new MemberFragmentNull();
		fra_goods = new GoodsManageFragment();
		fra_goods.setCallback(new GoodsManageFragment.CallBack() {

			@Override
			public void callback() {
				
				goodstag = "GoodsFragment";
				typeadd.setVisibility(View.VISIBLE);
				
				tv_back.setClickable(true);
				tv_back.setImageResource(R.drawable.back_normal);
			}
		});
		fra_order = new OrderFragment();
		fra_function = new FunctionFragment();
		fra_function.setCallback(new FunctionFragment.CallBack() {
			
			@Override
			public void callback() {
				// TODO Auto-generated method stub
				if(kitchen.getVisibility()==View.INVISIBLE){
					kitchen.setVisibility(View.VISIBLE);
				}else{
					kitchen.setVisibility(View.INVISIBLE);
				}
			}
		});
		fra_kitchen = new KitchenFragment();

		if (pre_index == 1) {
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("收银台");
			fm.beginTransaction().add(R.id.fl_main_content, fra_cash).commit();
			cash.setBackgroundResource(R.drawable.icon_manu_cashier_pre);
		} else if (pre_index == 2) {
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("订单管理");
			fm.beginTransaction().add(R.id.fl_main_content, fra_order).commit();
			order.setBackgroundResource(R.drawable.icon_manu_order_pre);
		} else if (pre_index == 3) {
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("商品管理");
			fm.beginTransaction().add(R.id.fl_main_content, fra_goods).commit();
			goods.setBackgroundResource(R.drawable.icon_manu_goods_pre);
		} else if (pre_index == 4) {
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("会员管理");
			fm.beginTransaction().add(R.id.fl_main_content, fra_member)
					.commit();
			member.setBackgroundResource(R.drawable.icon_manu_member_pre);
		} else if (pre_index == 5) {
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("功能管理");
			fm.beginTransaction().add(R.id.fl_main_content, fra_function)
					.commit();
			function.setBackgroundResource(R.drawable.icon_manu_function_pre);
		} else if (pre_index == 6) {
			// 后期写
			tv_frag.setVisibility(View.GONE);
			group.setVisibility(View.VISIBLE);

			fm.beginTransaction().add(R.id.fl_main_content, fra_account)
					.commit();
			account.setBackgroundResource(R.drawable.icon_manu_account_pre);
		} else if (pre_index == 7) {
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("厨房端");
			fm.beginTransaction().add(R.id.fl_main_content, fra_kitchen)
					.commit();
			kitchen.setBackgroundResource(R.drawable.icon_manu_cook_pre);
		}
	}

	private void setTab(int position) {
		home.setBackgroundResource(R.drawable.icon_manu_home);
		cash.setBackgroundResource(R.drawable.icon_manu_cashier);
		order.setBackgroundResource(R.drawable.icon_manu_order);
		goods.setBackgroundResource(R.drawable.icon_manu_goods);
		member.setBackgroundResource(R.drawable.icon_manu_member);
		function.setBackgroundResource(R.drawable.icon_manu_function);
		account.setBackgroundResource(R.drawable.icon_manu_account);
		kitchen.setBackgroundResource(R.drawable.icon_manu_cook);
		hideTab();

		// HideKeyboard();
		switch (position) {
		case 0: {
			typeadd.setVisibility(View.GONE);
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			home.setBackgroundResource(R.drawable.icon_manu_home_pre);
			if (fra_home.isAdded()) {
				fm.beginTransaction().show(fra_home).commit();
			} else {
				fm.beginTransaction().add(R.id.fl_main_content, fra_home)
						.commit();
			}

		}
			break;
		case 1: {
			typeadd.setVisibility(View.GONE);
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("收银台");
			if (!StringUtil.isEmpty(cashtag)) {
				tv_back.setClickable(true);
				tv_back.setImageResource(R.drawable.back_normal);
			} else {
				tv_back.setClickable(false);
				tv_back.setImageResource(R.drawable.logo1);
			}
			cash.setBackgroundResource(R.drawable.icon_manu_cashier_pre);
			if (fra_cash.isAdded()) {
				fm.beginTransaction().show(fra_cash).commit();
			} else {
				fm.beginTransaction().replace(R.id.fl_main_content, fra_cash)
						.commit();
				// fm.beginTransaction().show(fra_cash).commit();
			}

		}
			break;
		case 2: {
			typeadd.setVisibility(View.GONE);
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
		 
			tv_frag.setText("订单管理");
			tv_back.setImageResource(R.drawable.logo1);
			tv_back.setClickable(false);
			order.setBackgroundResource(R.drawable.icon_manu_order_pre);
			if (fra_order.isAdded()) {
				fm.beginTransaction().show(fra_order).commit();
			} else {
				fm.beginTransaction().add(R.id.fl_main_content, fra_order)
						.commit();
				// fm.beginTransaction().show(fra_order).commit();
			}

		}
			break;
		case 3: {
			
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("商品管理");
			if (!StringUtil.isEmpty(goodstag)) {
				tv_back.setImageResource(R.drawable.back_normal);
				tv_back.setClickable(true);
				typeadd.setVisibility(View.VISIBLE);
			} else {
				tv_back.setImageResource(R.drawable.logo1);
				tv_back.setClickable(false);
				typeadd.setVisibility(View.GONE);
			}
			goods.setBackgroundResource(R.drawable.icon_manu_goods_pre);
			if (fra_goods.isAdded()) {
				fm.beginTransaction().show(fra_goods).commit();
			} else {
				fm.beginTransaction().add(R.id.fl_main_content, fra_goods)
						.commit();
			}

		}
			break;
		case 4: {
			typeadd.setVisibility(View.GONE);
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("会员管理");
			tv_back.setImageResource(R.drawable.logo1);
			tv_back.setClickable(false);
			member.setBackgroundResource(R.drawable.icon_manu_member_pre);
			if (fra_member.isAdded()) {
				fm.beginTransaction().show(fra_member).commit();
			} else {
				fm.beginTransaction().add(R.id.fl_main_content, fra_member)
						.commit();
			}

		}
			break;
		case 5: {
			typeadd.setVisibility(View.GONE);
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("功能管理");
			tv_back.setImageResource(R.drawable.logo1);
			tv_back.setClickable(false);
			function.setBackgroundResource(R.drawable.icon_manu_function_pre);
			if (fra_function.isAdded()) {
				fm.beginTransaction().show(fra_function).commit();
			} else {
				fm.beginTransaction().add(R.id.fl_main_content, fra_function)
						.commit();
			}

		}
			break;
		case 6: {
			typeadd.setVisibility(View.GONE);
			group.setVisibility(View.VISIBLE);
			tv_frag.setVisibility(View.GONE);
			tv_frag.setText("账户管理");
			tv_back.setImageResource(R.drawable.logo1);
			tv_back.setClickable(false);
			account.setBackgroundResource(R.drawable.icon_manu_account_pre);
			if (fra_account.isAdded()) {

				fm.beginTransaction().show(fra_account).commit();
			} else {
				fm.beginTransaction().add(R.id.fl_main_content, fra_account)
						.commit();
			}

		}
			break;
		case 7: {
			typeadd.setVisibility(View.GONE);
			group.setVisibility(View.GONE);
			tv_frag.setVisibility(View.VISIBLE);
			tv_frag.setText("厨房端");
			tv_back.setImageResource(R.drawable.logo1);
			tv_back.setClickable(false);
			kitchen.setBackgroundResource(R.drawable.icon_manu_cook_pre);
			if (fra_kitchen.isAdded()) {
				fm.beginTransaction().show(fra_kitchen).commit();
			} else {
				fm.beginTransaction().add(R.id.fl_main_content, fra_kitchen)
						.commit();
			}

		}
			break;
		}

		fm.beginTransaction().commit();
		pre_index = position;
	}

	// 隐藏上一个Fragment
	private void hideTab() {
		switch (pre_index) {
		case 0: {
			fm.beginTransaction().hide(fra_home).commit();
		}
			break;
		case 1: {
			fm.beginTransaction().hide(fra_cash).commit();
		}
			break;
		case 2: {
			fm.beginTransaction().hide(fra_order).commit();
		}
			break;
		case 3: {
			fm.beginTransaction().hide(fra_goods).commit();
		}
			break;
		case 4: {
			fm.beginTransaction().hide(fra_member).commit();
		}
			break;
		case 5: {
			fm.beginTransaction().hide(fra_function).commit();
		}
			break;
		case 6: {
			fm.beginTransaction().hide(fra_account).commit();
		}
			break;
		case 7: {
			fm.beginTransaction().hide(fra_kitchen).commit();
		}
			break;
		}
	}

	// public void showPayResultActivity(String CardNum,String payAmount,String
	// memo,String result,boolean isUnBundler){
	// Intent intent=new Intent(this,PayResultActivity.class);
	// intent.putExtra("CardNum", CardNum);
	// intent.putExtra("payAmount", payAmount);
	// intent.putExtra("memo", memo);
	// intent.putExtra("result", result);
	// this.startActivityForResult(intent, PAYRESULTREQUESTCODE);
	//
	//
	// }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		// ((IndexFragment)mIndexFragment).loadReadCount();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_HOME) {

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		// try {
		// Intent intent = new Intent();
		// intent.setAction("com.duiduifu.retail.sync.close");//发出自定义广播
		// this.sendBroadcast(intent);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// this.finish();
	}

	@Override
	protected void onDestroy() {

		System.out.println("===========MainActivity-Destroy!	");

		try {
			// this.unbindService(conn);

			// ((MyApplication)getApplication()).removeOnly(MainActivity.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Intent intent = new Intent();
			intent.setClass(this, WindowService.class);
			this.stopService(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.onDestroy();
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		/*
		 * if (requestCode == IndexFragment.MESSAGE_LIST_REQUEST && resultCode
		 * == MessageListActivity.MESSAGE_LIST_RESULT) { //
		 * if(mIndexFragment!=null){ // //
		 * ((IndexFragment)mIndexFragment).setMessageHint(); // } }
		 */
	};

	// ============= Service回调 ==============

	@Override
	public void setHintNumber(int messageNum, int orderNum) {
		// if(mIndexFragment!=null){
		// ((IndexFragment)mIndexFragment).setHint(orderNum, messageNum);
		// }
	}

	private void HideKeyboard() {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (((Activity) context).getCurrentFocus() != null)
			imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
					.getWindowToken(), 0);
	}

}
