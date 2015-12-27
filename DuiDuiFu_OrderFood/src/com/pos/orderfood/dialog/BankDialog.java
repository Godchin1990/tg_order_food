package com.pos.orderfood.dialog;

import com.duiduifu.view.TipsToast;
import com.pos.orderfood.R;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class BankDialog extends BaseDialog implements android.view.View.OnClickListener{
	private Context context;
	private View view;
	private EditText moneny;
	private Button income;
	private CallBack callback;
	public BankDialog(Context context,CallBack callback) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.callback=callback;
		InitUI();
	}

	private void InitUI() {
		// TODO Auto-generated method stub
		view=getLayoutInflater().inflate(R.layout.dialog_bank, null);	
		moneny=(EditText) view.findViewById(R.id.et_account2_moneny);
		income=(Button) view.findViewById(R.id.bt_account2_income);
		income.setOnClickListener(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);	
		this.addContentView(view, lp);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bt_account2_income){
			 if(moneny.getText().toString().equals("")){
				 TipsToast.makeText(context, "请输入提现金额", 1).show();
			 }else if(Double.parseDouble(moneny.getText().toString())<=0){
				 TipsToast.makeText(context, "输入不合法", 1).show();
			 }else{
				 callback.callback(moneny.getText().toString());
				 BankDialog.this.dismiss();
			 }
		}
	}
	public interface CallBack{
		public void callback(String money);
	}
}
