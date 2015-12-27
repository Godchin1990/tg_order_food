package com.pos.orderfood.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.pos.orderfood.R;

public class MemberFragmentNull extends NoBugFragment{
	private int pre_index = 0;
	private boolean flag;//判断是否需要隐藏
	private FragmentManager fm;
	private Context context;
	private FrameLayout frame;
	private MemberFragment member_frag;
	private MemberDetailFragment memdetail_frag;
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_membernull, null);
		
		
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		context = get_Activity();
		fm = ((FragmentActivity) context).getSupportFragmentManager();
		flag=false;
		initFragment();
		InitUI();
	}

	private void InitUI() {
		// TODO Auto-generated method stub
		frame=(FrameLayout) view.findViewById(R.id.frame);
		setTab(pre_index);
	}
	private void initFragment() {
		member_frag=new MemberFragment();
		member_frag.setCallback(new MemberFragment.CallBack() {
			
			@Override
			public void callback() {
				// TODO Auto-generated method stub
				setTab(1);
			}
		});
		memdetail_frag=new MemberDetailFragment();
		memdetail_frag.setCallback(new MemberDetailFragment.Callback() {
			
			@Override
			public void callback() {
				// TODO Auto-generated method stub
				setTab(0);
			}
		});
	}
	public void setTab(int position) {
		if(flag)
			hideTab();

		switch (position) {
		case 0: {

			if (member_frag.isAdded()) {
				fm.beginTransaction().show(member_frag).commit();
			} else {
				fm.beginTransaction().add(R.id.frame, member_frag).commit();
				//fm.beginTransaction().replace(R.id.frame, member_frag).commit();
			}
			flag=true;
			setPre_index(0);
		}
			break;
		case 1: {

			if (memdetail_frag.isAdded()) {
				fm.beginTransaction().show(memdetail_frag).commit();
			} else {
				fm.beginTransaction().add(R.id.frame, memdetail_frag).commit();
				//fm.beginTransaction().replace(R.id.frame, memdetail_frag).commit();
			}
			setPre_index(1);
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
				fm.beginTransaction().hide(member_frag).commit();
			}
				break;
			case 1: {
				fm.beginTransaction().hide(memdetail_frag).commit();
			}
				break;

			}
		}
		public void setPre_index(int pre_index) {
			this.pre_index = pre_index;
		}
}
