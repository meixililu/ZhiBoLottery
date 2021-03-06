package com.zhibo.lotterystore;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zhibo.lotterystore.adapter.OftenUseAppGridViewAdapter;
import com.zhibo.lotterystore.util.PackageInfoUtil;


public class OftenFragment extends BaseFragment implements OnClickListener {
	
	public static OftenFragment mBaseFragment;
	private GridView gridview;
	private OftenUseAppGridViewAdapter mAdapter;
	
	public static BaseFragment getInstance(){
		if(mBaseFragment == null){
			mBaseFragment = new OftenFragment();
		}
		return mBaseFragment;
	}

	@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.often_fragment, null);
		init(inflater);
		return view;
	}
	
	private void init(LayoutInflater inflater) {
		gridview = (GridView) view.findViewById(R.id.gridview);
		mAdapter = new OftenUseAppGridViewAdapter(getActivity(),inflater,PackageInfoUtil.mAppInfoList);
		gridview.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		
	}
}
