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
public class GoodsManageFragment extends NoBugFragment {
	private Context context;
	private FrameLayout frame;
	private int pre_index = 0;
	private boolean flag;// 判断是否需要隐藏
	private FragmentManager fm;
	private GoodsFragment goodfrag;
 	
	private GoodsClassifyFragment clssifyfrag;
	private CallBack callback;
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View
				.inflate(get_Activity(), R.layout.fragment_goodsmanage, null);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		context = get_Activity();
		fm = ((FragmentActivity) context).getSupportFragmentManager();
		flag = false;
		initFragment();
		InitUI();
	}

	private void InitUI() {
 
		frame=(FrameLayout) view.findViewById(R.id.goods_frame);
		setTab(pre_index);
	}

	private void initFragment() {
 
 
		goodfrag=new GoodsFragment();
 
		clssifyfrag=new GoodsClassifyFragment();
		goodfrag.setCallback(new GoodsFragment.CallBack() {
			@Override
			public void callback() {
				setTab(1);
				callback.callback();
			}
		});
	}
	public void setTab(int position) {
		if (flag)
			hideTab();
		switch (position) {
		case 0: {
			if (goodfrag.isAdded()) {
				fm.beginTransaction().show(goodfrag).commit();
			} else {
				fm.beginTransaction().add(R.id.goods_frame, goodfrag).commit();
			}
			flag = true;
			setPre_index(0);
		}
			break;
		case 1:{
			if (clssifyfrag.isAdded()) {
				fm.beginTransaction().show(clssifyfrag).commit();
			} else {
				fm.beginTransaction().add(R.id.goods_frame, clssifyfrag).commit();
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
			fm.beginTransaction().hide(goodfrag).commit();
		}
			break;
 
		case 1: {
			fm.beginTransaction().hide(clssifyfrag).commit();
		}
			break;
		}
	}

	public void setPre_index(int pre_index) {
		this.pre_index = pre_index;
	}
	public interface CallBack{
		public void callback();
	}
	public void setCallback(CallBack callback) {
		this.callback = callback;
	}
	public GoodsClassifyFragment getClssifyfrag() {
		return clssifyfrag;
	}
}
