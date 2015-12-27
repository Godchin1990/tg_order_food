package com.pos.orderfood.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.pos.orderfood.R;
import com.pos.orderfood.adapter.MyFragmentPagerAdapter;
import com.pos.orderfood.widget.UnderlinePageIndicator;

//提现管理
public class AccountFragment2 extends NoBugFragment {
	private Context context;
	private Button reflect;
	

	private RadioButton radio1, radio2, radio3,radio4;

	private RadioGroup group;
	private ViewPager viewPager;
	private UnderlinePageIndicator indicator;
	private MyFragmentPagerAdapter adapter;
	private List<Fragment> fragments;
	OnCheckedChangeListener l_radio = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			switch (arg1) {
			case R.id.rb_account1: {

				viewPager.setCurrentItem(0);

			}
				break;

			case R.id.rb_account2: {

				viewPager.setCurrentItem(1);

			}
				break;
			case R.id.rb_account3: {

				viewPager.setCurrentItem(2);

			}
				break;
			case R.id.rb_account4: {

				viewPager.setCurrentItem(3);

			}
				break;
			}
		}
	};

	OnPageChangeListener l_page = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0: {
				radio1.setChecked(true);

			}
				break;

			case 1: {
				radio2.setChecked(true);
			}
				break;
			case 2: {
				radio3.setChecked(true);
			}
				break;
			case 3: {
				radio4.setChecked(true);
			}
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	};

	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_account2, null);
		context = get_Activity();

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		fragments = new ArrayList<Fragment>();
		InitUI();
	}

	private void InitUI() {
		// TODO Auto-generated method stub
		reflect = (Button) view.findViewById(R.id.btn_reflect);
		reflect.setVisibility(View.INVISIBLE);

		
		group = (RadioGroup) getView().findViewById(R.id.rg_account);
		radio1 = (RadioButton) getView().findViewById(R.id.rb_account1);

		radio2 = (RadioButton) getView().findViewById(R.id.rb_account2);
		radio3 = (RadioButton) getView().findViewById(R.id.rb_account3);
		radio4 = (RadioButton) getView().findViewById(R.id.rb_account4);
		viewPager = (ViewPager) getView().findViewById(R.id.vp_account);
		indicator = (UnderlinePageIndicator) getView().findViewById(
				R.id.indicator_account);

		initViewpager();
		adapter = new MyFragmentPagerAdapter(getActivity()
				.getSupportFragmentManager(), viewPager, fragments);
		viewPager.setAdapter(adapter);

		viewPager.setCurrentItem(0);
		indicator.setViewPager(viewPager);
		indicator.setOnPageChangeListener(l_page);
		group.setOnCheckedChangeListener(l_radio);
	}
	private void initViewpager() {
		AccountFragmentAll frag1 = new AccountFragmentAll();
		AccountFragmentIn frag2 = new AccountFragmentIn();
		AccountFragmentOut frag3 = new AccountFragmentOut();
		AccountFragmentProfit frag4=new AccountFragmentProfit();
		fragments.add(frag1);
		fragments.add(frag2);
		fragments.add(frag3);
		fragments.add(frag4);
	 
	}


}
