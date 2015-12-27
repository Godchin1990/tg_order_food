package com.pos.orderfood.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.markmao.pulltorefresh.widget.XListView;
import com.markmao.pulltorefresh.widget.XListView.IXListViewListener;
import com.pos.orderfood.R;
import com.pos.orderfood.adapter.OrderAdapter;
import com.pos.orderfood.bean.Order;

public class OrderFragment extends NoBugFragment implements
		OnCheckedChangeListener {
	private Context context;
	private List<Order> list = new ArrayList<Order>();
	private OrderAdapter adapter;
	private XListView xlistview;
	private boolean flag = false;
	private int index = 0;
	private RelativeLayout rl1,rl2;
	private CheckBox app, pos;
	private Button btn_reminder, btn_getorder, btn_print;
	private TextView tv_statue, tv_orderid, tv_ordertime, tv_memid, tv_memname,
			tv_memdevel, tv_appointment, tv_packtype, tv_address,
			tv_ordermoney, tv_memdiscount, tv_integral, tv_vouchers,
			tv_factmoney, tv_change, tv_paytype, tv_overtime;

	@Override
	public View oncreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(get_Activity(), R.layout.fragment_order, null);
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
		//rl1,rl2,rl3
		rl1=(RelativeLayout) view.findViewById(R.id.rl1);
		rl2=(RelativeLayout) view.findViewById(R.id.rl2);
 
		rl1.setBackgroundColor(context.getResources().getColor(R.color.midgray));
		rl2.setBackgroundColor(context.getResources().getColor(R.color.midgray));
		tv_statue = (TextView) view.findViewById(R.id.tv_statue);
		tv_orderid = (TextView) view.findViewById(R.id.tv_orderid);
		tv_ordertime = (TextView) view.findViewById(R.id.tv_ordertime);
		tv_memid = (TextView) view.findViewById(R.id.tv_memid);
		tv_memname = (TextView) view.findViewById(R.id.tv_memname);
		tv_memdevel = (TextView) view.findViewById(R.id.tv_memdevel);
		tv_appointment = (TextView) view.findViewById(R.id.tv_appointment);
		tv_packtype = (TextView) view.findViewById(R.id.tv_packtype);
		tv_address = (TextView) view.findViewById(R.id.tv_address);
		tv_ordermoney = (TextView) view.findViewById(R.id.tv_ordermoney);
		tv_memdiscount = (TextView) view.findViewById(R.id.tv_memdiscount);
		tv_integral = (TextView) view.findViewById(R.id.tv_integral);
		tv_vouchers = (TextView) view.findViewById(R.id.tv_vouchers);
		tv_factmoney = (TextView) view.findViewById(R.id.tv_factmoney);
		tv_change = (TextView) view.findViewById(R.id.tv_change);
		tv_paytype = (TextView) view.findViewById(R.id.tv_paytype);
		tv_overtime = (TextView) view.findViewById(R.id.tv_overtime);

		btn_getorder = (Button) view.findViewById(R.id.btn_getorder);
		btn_reminder = (Button) view.findViewById(R.id.btn_reminder);
		btn_print = (Button) view.findViewById(R.id.btn_print);
		btn_reminder.setVisibility(View.GONE);
		app = (CheckBox) view.findViewById(R.id.cb_app);
		pos = (CheckBox) view.findViewById(R.id.cb_pos);
		app.setChecked(true);
		app.setOnCheckedChangeListener(this);
		pos.setOnCheckedChangeListener(this);
		xlistview = (XListView) view.findViewById(R.id.xlv_list);
		for (int i = 0; i < 10; i++) {
			Order order = new Order();
			order.setOrderid("201511110000" + i);
			order.setPerson("张凡先生");
			order.setData("12:0" + i);
			order.setStatue("待接单");
			order.setType("app");
			list.add(order);
		}
		adapter = new OrderAdapter(context, list, index,new OrderAdapter.CallBack() {
			
			@Override
			public void callback(int postion) {
				// TODO Auto-generated method stub
				if(list.get(postion).getType().equals("app")){
					btn_reminder.setVisibility(View.GONE);
					btn_getorder.setVisibility(View.VISIBLE);
					btn_print.setVisibility(View.VISIBLE);
					rl1.setBackgroundColor(context.getResources().getColor(R.color.midgray));
					rl2.setBackgroundColor(context.getResources().getColor(R.color.midgray));
				}else{
					btn_print.setVisibility(View.VISIBLE);
					btn_reminder.setVisibility(View.VISIBLE);
					btn_getorder.setVisibility(View.GONE);
					rl1.setBackgroundColor(context.getResources().getColor(R.color.darkyellow));
					rl2.setBackgroundColor(context.getResources().getColor(R.color.darkgreen));
				}
			}
		});
		xlistview.setAdapter(adapter);
		xlistview.setPullLoadEnable(true);// 下拉刷新 /滚动
		xlistview.setAutoLoadEnable(true);// 成功

		xlistview.setXListViewListener(new IXListViewListener() {

			// 下拉: 显示第一页，清空集合，重新加载第一页
			@Override
			public void onRefresh() {
				if (!flag) {
					flag = !flag;
					stopTopOrButtom();

				}
			}

			// 滚动：添加下一页，刷新列表
			@Override
			public void onLoadMore() {

				if (!flag) {
					flag = !flag;

				}

				stopTopOrButtom();
			}
		});

		xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (list.size() >= position) {
					if (position != (1 + index)) {
						index = position - 1;
						adapter.update(index);
						btn_print.setVisibility(View.VISIBLE);
						if (list.get(position - 1).getStatue().equals("制作中")) {
							tv_statue.setText("制作中");
							
							btn_reminder.setVisibility(View.VISIBLE);
							btn_getorder.setVisibility(View.GONE);
							rl1.setBackgroundColor(context.getResources().getColor(R.color.darkyellow));
							rl2.setBackgroundColor(context.getResources().getColor(R.color.darkgreen));
						} else {
							tv_statue.setText("待接单");
							btn_reminder.setVisibility(View.GONE);
							btn_getorder.setVisibility(View.VISIBLE);
							rl1.setBackgroundColor(context.getResources().getColor(R.color.midgray));
							rl2.setBackgroundColor(context.getResources().getColor(R.color.midgray));
						}
					}
				}
			}
		});
	}

	private void stopTopOrButtom() {
		xlistview.stopRefresh();// 隐藏顶部等待视图
		xlistview.stopLoadMore();// 隐藏底部等待视图
		xlistview.setRefreshTime(getTime());
		flag = false;
	}

	private String getTime() {
		Date date = new Date();
		SimpleDateFormat formate = new SimpleDateFormat("MM月dd日 HH:mm");
		return formate.format(date);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.cb_app: {
			if (isChecked) {
				for (int i = 0; i < 10; i++) {
					Order order = new Order();
					order.setOrderid("201511110000" + i);
					order.setPerson("罗明先生");
					order.setData("12:0" + i);
					order.setStatue("待接单");
					order.setType("app");
					list.add(order);
				}
				adapter.update(list, 0);
				SetData(1);
			} else {
				list.removeAll(list);
				if (pos.isChecked()) {
					for (int i = 0; i < 10; i++) {
						Order order = new Order();
						order.setOrderid("201511110000" + i);
						order.setPerson("张凡先生");
						order.setData("12:0" + i);
						order.setStatue("制作中");
						order.setType("pos");
						list.add(order);
					}
					adapter.update(list, 0);
					SetData(1);
				} else {
					adapter.update(list, -1);
					xlistview.setAdapter(adapter);
					SetData(-1);
				}
			}
		}
			break;
		case R.id.cb_pos: {
			if (isChecked) {

				for (int i = 0; i < 10; i++) {
					Order order = new Order();
					order.setOrderid("201511110000" + i);
					order.setPerson("罗明先生");
					order.setData("12:0" + i);
					order.setStatue("制作中");
					order.setType("pos");
					list.add(order);
				}
				adapter.update(list, 0);
				SetData(1);
			} else {
				list.removeAll(list);
				if (app.isChecked()) {
					for (int i = 0; i < 10; i++) {
						Order order = new Order();
						order.setOrderid("201511110000" + i);
						order.setPerson("张凡先生");
						order.setData("12:0" + i);
						order.setStatue("待接单");
						order.setType("app");
						list.add(order);
					}
					adapter.update(list, 0);
					SetData(1);
				} else {
					adapter.update(list, -1);
					xlistview.setAdapter(adapter);
					SetData(-1);
				}
			}
		}
			break;
		default:
			break;
		}

	}

	private void SetData(int temp) {
		// TODO Auto-generated method stub

		if (temp == -1) {// 没有订单
			tv_statue.setText("");
			tv_orderid.setText("订单编号：");
			tv_ordertime.setText("下单时间：");
			tv_memid.setText("会员账号：");
			tv_memname.setText("会员姓名：");
			tv_memdevel.setText("会员等级：");
			tv_appointment.setText("预约时间：");
			tv_packtype.setText("包装：");
			tv_address.setText("地址：");
			tv_ordermoney.setText("订单金额：");
			tv_memdiscount.setText("会员优惠：");
			tv_integral.setText("积分抵扣：");
			tv_vouchers.setText("代金卷：");
			tv_factmoney.setText("实收金额：");
			tv_change.setText("找零金额：");
			tv_paytype.setText("支付方式：");
			tv_overtime.setText("完成时间：");

			btn_reminder.setVisibility(View.GONE);
			btn_getorder.setVisibility(View.GONE);
			btn_print.setVisibility(View.GONE);
		} else if (temp == 1) {

			tv_orderid.setText("订单编号：2015103000006");
			tv_ordertime.setText("下单时间：2015-10-30 12:05:03");
			tv_memid.setText("会员账号：13879618942");
			tv_memname.setText("会员姓名：张飞（先生）");
			tv_memdevel.setText("会员等级：普通会员");
			tv_appointment.setText("预约时间：即时");
			tv_packtype.setText("包装：堂食");
			tv_address.setText("地址：店内");
			tv_ordermoney.setText("订单金额：110.00");
			tv_memdiscount.setText("会员优惠：2.00元");
			tv_integral.setText("积分抵扣：10.00元（1000）");
			tv_vouchers.setText("代金卷：10.00元（6411323256）");
			tv_factmoney.setText("实收金额：100.00元");
			tv_change.setText("找零金额：12.00元");
			tv_paytype.setText("支付方式：现金");
			tv_overtime.setText("完成时间：2015-10-30 12:08:15");
		}
	}
}
