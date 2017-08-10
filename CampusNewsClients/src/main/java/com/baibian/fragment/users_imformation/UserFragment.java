package com.baibian.fragment.users_imformation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baibian.R;
import com.baibian.bean.HisUserContent;
import com.baibian.tool.DataTools;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;
import com.baibian.tool.SpaceItemDecoration;
import com.baibian.view.RevealFollowButton;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class UserFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UserFragment newInstance(int columnCount) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new CommonAdapter<HisUserContent.User>(getContext(), R.layout.fragment_user, HisUserContent.USERS){
                @Override
                public void convert(ViewHolder holder, HisUserContent.User user) {
                    //TODO
                    CircleImageView userPortrait = (CircleImageView) holder.getItemView().findViewById(R.id.user_portrait);
                    TextView userName = (TextView) holder.getItemView().findViewById(R.id.user_name);
                    TextView userPersonalSignature = (TextView) holder.getItemView().findViewById(R.id.user_personal_signal);
                    RevealFollowButton followButton = (RevealFollowButton) holder.getItemView().findViewById(R.id.focus_action);

                    Glide.with(getContext()).load("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=8694cad471899e516c8332572aceb346/0eb30f2442a7d9337bfbfd5aa74bd11373f00143.jpg").crossFade().into(userPortrait);
                }
            });
            recyclerView.addItemDecoration(new SpaceItemDecoration(DataTools.dip2px(getContext(), 4), false));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnListFragmentInteractionListener<T> {
        // TODO: Update argument type and name
        void onListFragmentInteraction(T t);
    }
*/
}
