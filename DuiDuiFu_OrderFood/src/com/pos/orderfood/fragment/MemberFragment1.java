package com.pos.orderfood.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;

import com.markmao.pulltorefresh.widget.XListView;
import com.markmao.pulltorefresh.widget.XListView.IXListViewListener;
import com.pos.orderfood.R;
import com.pos.orderfood.adapter.MemberAdapter;
import com.pos.orderfood.bean.Member;
import com.pos.orderfood.dialog.MemberDialog;

public class MemberFragment1 extends NoBugFragment implements OnClickListener{
	private Context context;
	private XListView xlistview;
	private List<Member>list=new ArrayList<Member>();
	private MemberAdapter adapter;
	private boolean flag=false;
	private Callback callback;
	private Button add;
	private MemberDialog dialog;
 
	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_member1, null);
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
		add=(Button) view.findViewById(R.id.btn_add);
		add.setOnClickListener(this);
		xlistview = (XListView) view.findViewById(R.id.xlv_member);
		for(int i=0;i<10;i++){
			Member m=new Member();
			m.setPhone("1587961894"+i);
			m.setGrade("金牌会员");
			m.setIntegral("1323"+i);
			m.setCount("7"+i);
			m.setDate("2015-11-11 11:2"+i);
			m.setPerson("王五");
			list.add(m);
			
		}
		adapter=new MemberAdapter(context, list,new MemberAdapter.CallBack() {
			
			@Override
			public void callback() {
				// TODO Auto-generated method stub
				callback.callback();
			}
		});
		xlistview.setAdapter(adapter);
		xlistview.setPullLoadEnable(true);// 下拉刷新 /滚动
		xlistview.setAutoLoadEnable(true);// 成功
		
		xlistview.setXListViewListener(new IXListViewListener() {

			// 下拉: 显示第一页，清空集合，重新加载第一页
			@Override
			public void onRefresh() {
				if(!flag){
					flag=!flag;
					stopTopOrButtom();
					 
				}
			}

			// 滚动：添加下一页，刷新列表
			@Override
			public void onLoadMore() {
				if(!flag){
					flag=!flag;
					for(int i=0;i<10;i++){
						Member m=new Member();
						m.setPhone("1587961894"+i);
						m.setGrade("金牌会员");
						m.setIntegral("1323"+i);
						m.setCount("7"+i);
						m.setDate("2015-11-11 11:2"+i);
						m.setPerson("王五");
						list.add(m);				
					}
					adapter.update(list);
					stopTopOrButtom();
				}
			}
		});
		xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}
	private void stopTopOrButtom() {
		xlistview.stopRefresh();// 隐藏顶部等待视图
		xlistview.stopLoadMore();// 隐藏底部等待视图
		xlistview.setRefreshTime(getTime());
		flag=false;
	}
	private String getTime() {
		Date date = new Date();
		SimpleDateFormat formate = new SimpleDateFormat("MM月dd日 HH:mm");
		return formate.format(date);
	}
	public interface Callback{
		public void callback();
	}
	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add:{
			dialog=new MemberDialog(context);
			dialog.show();
		}
			break;

		default:
			break;
		}
	}
}
