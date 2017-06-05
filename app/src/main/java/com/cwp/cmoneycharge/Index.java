package com.cwp.cmoneycharge;

import java.util.ArrayList;
import java.util.List;

import com.cwp.cmoneycharge.R;

import cwp.moneycharge.dao.AccountDAO;
import cwp.moneycharge.dao.DBOpenHelper;
import cwp.moneycharge.model.ActivityManager;
import cwp.moneycharge.model.CustomDialog;
import cwp.moneycharge.model.Tb_account;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Index extends Activity {
	GridView gvInfo;// ??GridView??
	int userid;
	int returnflag;
	// ??????????????
	String[] titles = new String[] { "????", "????", "????", "????", "????",
			"????",  "??" };
	// ??int????????????
	int[] images = new int[] { R.drawable.pay,
			R.drawable.income, R.drawable.note,
			R.drawable.data, R.drawable.account, R.drawable.setting,
			R.drawable.exit };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		ActivityManager.getInstance().addActivity(this); //??Activity,?????????
		gvInfo = (GridView) findViewById(R.id.gvInfo);// ????????gvInfo??
		pictureAdapter adapter = new pictureAdapter(titles, images, this);// ??pictureAdapter??
		gvInfo.setAdapter(adapter);// ?GridView?????
	}
	@Override
	protected void onStart(){
		super.onStart();
		Intent intentr=getIntent();
		userid=intentr.getIntExtra("cwp.id", 100000001);//????
		returnflag=0;
		gvInfo.setOnItemClickListener(new OnItemClickListener() {// ?GridView???????
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				Intent intent = null;// ??Intent??
				switch (arg2) {
					case 0://????
						intent = new Intent(Index.this, Pay.class);// ??AddOutaccount?????Intent
						intent.putExtra("cwp.id", userid);
						startActivity(intent);
						break;
					case 1://????
						intent = new Intent(Index.this, Income.class);
						intent.putExtra("cwp.id", userid);
						startActivity(intent);
						break;
					case 2://????
						intent = new Intent(Index.this, Note.class);
						intent.putExtra("cwp.id", userid);
						startActivity(intent);
						break;
					case 3://????
						intent = new Intent(Index.this, Data.class);
						intent.putExtra("cwp.id", userid);
						startActivity(intent);
						break;
					case 4://????
						intent = new Intent(Index.this, Account.class);
						intent.putExtra("cwp.id", userid);
						startActivity(intent);
						break;
					case 5://????
						intent = new Intent(Index.this, Setting.class);
						intent.putExtra("cwp.id", userid);
						startActivity(intent);
						break;
					case 6: //??

						exitDialog();// ????Activity
				}
			}
		});
	}
	/**
	 * Build the desired Dialog
	 * CUSTOM or DEFAULT
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}
	class ViewHolder// ??ViewHolder?
	{
		public TextView title;// ??TextView??
		public ImageView image;// ??ImageView??
	}
	public void quit() {
		ActivityManager.getInstance().exit();
	}
	private void exitDialog(){  //???????
		Dialog dialog =null;

		CustomDialog.Builder customBuilder = new  CustomDialog.Builder(Index.this);


		customBuilder.setTitle("????")  // ????

				.setMessage("????????")    //????????

				.setPositiveButton("??", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						quit();
					}

				}).setNegativeButton("??",new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		dialog=customBuilder.create();//?????
		dialog.show();  //?????

	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) { //??/??/?????
			if(returnflag==0){
				Toast.makeText(Index.this,"?????????", Toast.LENGTH_SHORT).show();
				returnflag=1;
			}else{
				quit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
class pictureAdapter extends BaseAdapter// ????BaseAdapter???
{
	private LayoutInflater inflater;// ??LayoutInflater??
	private List<Picture> pictures;// ??List????

	// ????????
	public pictureAdapter(String[] titles, int[] images, Context context) {
		super();
		pictures = new ArrayList<Picture>();// ?????????
		inflater = LayoutInflater.from(context);// ???LayoutInflater??
		for (int i = 0; i < images.length; i++)// ??????
		{
			Picture picture = new Picture(titles[i], images[i]);// ?????????Picture??
			pictures.add(picture);// ?Picture??????????
		}
	}

	@Override
	public int getCount() {// ?????????
		if (null != pictures) {// ?????????
			return pictures.size();// ??????
		} else {
			return 0;// ??0
		}
	}

	@Override
	public Object getItem(int arg0) {
		return pictures.get(arg0);// ?????????????
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;// ?????????
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder viewHolder;// ??ViewHolder??
		if (arg1 == null)// ??????????
		{
			arg1 = inflater.inflate(R.layout.gvitem, null);// ??????
			viewHolder = new ViewHolder();// ???ViewHolder??
			viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);// ??????
			viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);// ?????????
			arg1.setTag(viewHolder);// ????
		} else {
			viewHolder = (ViewHolder) arg1.getTag();// ????
		}
		viewHolder.title.setText(pictures.get(arg0).getTitle());// ??????
		viewHolder.image.setImageResource(pictures.get(arg0).getImageId());// ?????????
		return arg1;// ??????
	}

}

class ViewHolder// ??ViewHolder?
{
	public TextView title;// ??TextView??
	public ImageView image;// ??ImageView??
}
class Picture// ??Picture?
{
	private String title;// ????????????
	private int imageId;// ??int????????????

	public Picture()// ??????
	{
		super();
	}

	public Picture(String title, int imageId)// ????????
	{
		super();
		this.title = title;// ???????
		this.imageId = imageId;// ??????????
	}

	public String getTitle() {// ???????????
		return title;
	}

	public void setTitle(String title) {// ???????????
		this.title = title;
	}

	public int getImageId() {// ?????????????
		return imageId;
	}

	public void setimageId(int imageId) {// ?????????????
		this.imageId = imageId;
	}

}