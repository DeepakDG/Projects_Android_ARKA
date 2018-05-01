package com.arkaapps.puttaraj.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkaapps.puttaraj.DataItemModel;
import com.arkaapps.puttaraj.Listeners.RecyclerItemClickListener;
import com.arkaapps.puttaraj.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by CSC on 3/25/2018.
 */

public class DashboardListFragment extends Fragment {

    ArrayList<DataItemModel> mListItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private View mview;
    String mListDashboardName[] = {"ಜೀವನಚರಿತ್ರೆ", "ಮಠ ಸ್ಥಳ", "ಪುಸ್ತಕಗಳು", "Tools", "ಚಿತ್ರಗಳು", "ಪ್ರಶಸ್ತಿಗಳು ಮತ್ತು ಮನ್ನಣೆಗಳು"};
    int mListDashboardImage[] = {R.drawable.puttaraj_1, R.drawable.puttaraj_2, R.drawable.puttaraj_3, R.drawable.puttaraj_4, R.drawable.puttaraj_5, R.drawable.puttaraj_6};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragmentdashboard, container, false);
        mRecyclerView = (RecyclerView) mview.findViewById(R.id.listRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        linearLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(new DashboardAdapter(mListItems));
        }
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                navigate(position);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return mview;
    }

    private void navigate(int position) {
        Fragment mFragment = new Fragment();
        if (position == 0) {
            mFragment = new BioFragment();
        } else if (position == 1) {
            mFragment = new AwardsFragment();
        } else if (position == 2) {
            mFragment = new CarouselWallpaperFragment();
        } else if (position == 3) {
            Intent firstpage = new Intent(getActivity(), DirectionFragment.class);
            getActivity().startActivity(firstpage);
        }
        FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        mFragmentTransaction.replace(R.id.fragmentContainer, mFragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    private void init() {
        mListItems.clear();

        for (int i = 0; i < 6; i++) {
            DataItemModel mDataModel = new DataItemModel();
            mDataModel.setmStrName(mListDashboardName[i]);
            mDataModel.setmImage(mListDashboardImage[i]);
            mListItems.add(mDataModel);
        }
    }

    public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {
        private ArrayList<DataItemModel> list;

        public DashboardAdapter(ArrayList<DataItemModel> mListItems) {
            list = mListItems;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_items, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.titleTextView.setText(list.get(position).getmStrName());
            holder.coverImageView.setImageResource(list.get(position).getmImage());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView titleTextView;
            public ImageView coverImageView;

            public MyViewHolder(View itemView) {
                super(itemView);

                titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
                coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);
            }
        }
    }
}
