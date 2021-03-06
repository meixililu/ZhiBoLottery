package com.zhibo.lotterystore;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;

import com.zhibo.lotterystore.adapter.CategoryGridViewAdapter;
import com.zhibo.lotterystore.dao.Category;
import com.zhibo.lotterystore.db.DataBaseUtil;


public class CategoryFragment extends BaseFragment implements OnClickListener {
	
	public static CategoryFragment mBaseFragment;
	private GridView gridview;
	private CategoryGridViewAdapter mAdapter;
	ArrayList<Category> mCategoryList;
	private Long test;
	
	public static BaseFragment getInstance(){
		if(mBaseFragment == null){
			mBaseFragment = new CategoryFragment();
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
		mCategoryList = (ArrayList<Category>) DataBaseUtil.getInstance().getDataListCategory();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.category_fragment, null);
		init(inflater);
		return view;
	}
	
	private void init(LayoutInflater inflater) {
	}

	@Override
	public void onClick(View v) {
		
	}

	
}
