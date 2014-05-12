package com.larry.projectx;

/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		
		if(searchView == null)
		{Log.e("SearchView","Fail to get Search View."); return true ; }
		
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_about) {
			return true;
		}
		else if (id == R.id.action_search)
		{
			onSearchRequested();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private ListView datalist;
		// private String tag;
		//
		// // 生成一个SpinnerAdapter
		// @Override
		// public void onAttach(Activity activity) {
		// // TODO Auto-generated method stub
		// super.onAttach(activity);
		// tag = getTag();
		// }
		// SpinnerAdapter adapter =
		// ArrayAdapter.createFromResource(getActivity(), R.array.horses,
		// android.R.layout.simple_spinner_dropdown_item);
		// // 得到ActionBar
		// android.app.ActionBar actionBar = getActivity().getActionBar();
		// // 将ActionBar的操作模型设置为NAVIGATION_MODE_LIST
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// // 为ActionBar设置下拉菜单和监听器
		// actionBar.setListNavigationCallbacks(adapter, new
		// DropDownListenser());

		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));

			int ArgumentNum = getArguments().getInt(ARG_SECTION_NUMBER);
			if ((ArgumentNum == 1) || (ArgumentNum == 2)) {
				datalist = (ListView) rootView.findViewById(R.id.vlist);
				datalist.setDividerHeight(0);// Make Listview divider line
												// invisible
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						getData(), R.layout.vlist, new String[] { "title",
								"info", "img" }, new int[] { R.id.title,
								R.id.info, R.id.img });
				datalist.setAdapter(adapter);
			} else if (ArgumentNum == 3) {
				rootView = inflater.inflate(R.layout.fragment_mine, container,
						false);

				// datalist = (ListView)rootView.findViewById(R.id.vlist2);
				// SimpleAdapter adapter = new
				// SimpleAdapter(getActivity(),getData2(),R.layout.vlist2,
				// new String[]{"title","img"},
				// new int[]{R.id.title,R.id.img});
				// datalist.setAdapter(adapter);

			}
			// Toast.makeText(getActivity(),
			// "it's"+Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)),
			// Toast.LENGTH_LONG).show();
			// TextView textViewSpinner= new TextView(getActivity());
			// textViewSpinner.setText(tag);

			return rootView;

		}

		public List<Map<String, Object>> getData() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "汗血宝马    ￥19.8");
			map.put("info", "1597人已买");
			map.put("img", R.drawable.hanxuebaoma);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "汗血宝马    ￥19.8");
			map.put("info", "1597人已买");
			map.put("img", R.drawable.hanxuebaoma);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "汗血宝马    ￥19.8");
			map.put("info", "1597人已买");
			map.put("img", R.drawable.hanxuebaoma);
			list.add(map);

			return list;
		}

		// public List<Map<String, Object>> getData2() {
		// List<Map<String, Object>> list = new ArrayList<Map<String,
		// Object>>();
		//
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("title", "汗血宝马    ￥19.8");
		// map.put("img", R.drawable.hanxuebaoma);
		// list.add(map);
		//
		// map = new HashMap<String, Object>();
		// map.put("title", "汗血宝马    ￥19.8");
		// map.put("img", R.drawable.hanxuebaoma);
		// list.add(map);
		//
		// map = new HashMap<String, Object>();
		// map.put("title", "汗血宝马    ￥19.8");
		// map.put("img", R.drawable.hanxuebaoma);
		// list.add(map);
		//
		// return list;
		// }

	}

	/**
	 * 实现 ActionBar.OnNavigationListener接口
	 */
	// class DropDownListenser implements OnNavigationListener
	// {
	// // 得到和SpinnerAdapter里一致的字符数组
	// String[] listNames = getResources().getStringArray(R.array.horses);
	//
	// /* 当选择下拉菜单项的时候，将Activity中的内容置换为对应的Fragment */
	// public boolean onNavigationItemSelected(int itemPosition, long itemId)
	// {
	// // 生成自定的Fragment
	// PlaceholderFragment phf = new PlaceholderFragment();
	// android.app.FragmentManager manager = getFragmentManager();
	// android.app.FragmentTransaction transaction = manager.beginTransaction();
	// // 将Activity中的内容替换成对应选择的Fragment
	// transaction.replace(R.id.action_bar, phf, listNames[itemPosition]);
	// transaction.commit();
	// return true;
	// }
	// }

}
