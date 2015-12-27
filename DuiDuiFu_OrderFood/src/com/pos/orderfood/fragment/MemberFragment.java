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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.pos.orderfood.R;
import com.pos.orderfood.adapter.MyFragmentPagerAdapter;
import com.pos.orderfood.widget.UnderlinePageIndicator;

public class MemberFragment extends NoBugFragment{
private Context context;
private RadioButton radio1, radio2, radio3, radio4;

private RadioGroup group;
private ViewPager viewPager;
private UnderlinePageIndicator indicator;
private MyFragmentPagerAdapter adapter;
private List<Fragment> fragments;
private CallBack callback;
OnCheckedChangeListener l_radio = new OnCheckedChangeListener() {

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch (arg1) {
		case R.id.rb_member1: {

			viewPager.setCurrentItem(0);

		}
			break;

		case R.id.rb_member2: {

			viewPager.setCurrentItem(1);

		}
			break;
		case R.id.rb_member3: {

			viewPager.setCurrentItem(2);

		}
			break;
		case R.id.rb_member4: {

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
	view = View.inflate(get_Activity(), R.layout.fragment_member, null);
	context = get_Activity();
	return view;
}
public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	getActivity().getWindow().setSoftInputMode(
			WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	fragments = new ArrayList<Fragment>();
	initView();
}
private void initView() {
	group = (RadioGroup) getView().findViewById(R.id.rg_member);
	radio1 = (RadioButton) getView().findViewById(R.id.rb_member1);

	radio2 = (RadioButton) getView().findViewById(R.id.rb_member2);
	radio3 = (RadioButton) getView().findViewById(R.id.rb_member3);
	radio4 = (RadioButton) getView().findViewById(R.id.rb_member4);
	viewPager = (ViewPager) getView().findViewById(R.id.vp_member);
	indicator = (UnderlinePageIndicator) getView().findViewById(
			R.id.indicator_member);

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
	MemberFragment1 frag1 = new MemberFragment1();
	MemberFragment2 frag2 = new MemberFragment2();
	MemberFragment3 frag3 = new MemberFragment3();
	MemberFragment4 frag4 = new MemberFragment4();
	fragments.add(frag1);
	fragments.add(frag2);
	fragments.add(frag3);
	fragments.add(frag4);
	frag1.setCallback(new MemberFragment1.Callback() {
		
		@Override
		public void callback() {
			// TODO Auto-generated method stub
			callback.callback();
		}
	});
}
public interface CallBack{
	public void callback();
}
public void setCallback(CallBack callback) {
	this.callback = callback;
}
 
}
