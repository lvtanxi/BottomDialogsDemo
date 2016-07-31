package com.lv.bottomdialogsdemo.selectpop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.Space;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lv.bottomdialogsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 吕勇
 * Date: 2015-11-17
 * Time: 11:51
 * Description:选择PopupWindow(后期如果item太多了，可换成ListView处理)
 */

public class SelectPopupWindow<T extends ExtendItem> extends PopupWindow implements OnClickListener {

    private Context mContext;
    private SelectPopupBack mPopupBack;

    private LinearLayout mPopLayout;
    private LinearLayout mSelectPopPanent;
    private TextView mBtnTakeTitle;
    private Button mBtnCancel;
    private ScrollView mScrollView;
    private Space mItemView;

    private View assignViews() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.select_pop, null, false);
        mPopLayout = (LinearLayout) view.findViewById(R.id.pop_layout);
        mSelectPopPanent = (LinearLayout) view.findViewById(R.id.select_pop_panent);
        mBtnTakeTitle = (TextView) view.findViewById(R.id.btn_take_title);
        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        mScrollView = (ScrollView) view.findViewById(R.id.select_pop_scrollView);
        //取消按钮
        mBtnCancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
                //                SettingActivity.isClick = false;
            }
        });
        return view;
    }

    public SelectPopupWindow setTitle(String title) {
        if (null != mBtnTakeTitle && null != title) {
            mBtnTakeTitle.setVisibility(View.VISIBLE);
            mBtnTakeTitle.setText(title);
        }
        return this;
    }

    public SelectPopupWindow setPopupBack(SelectPopupBack popupBack) {
        this.mPopupBack = popupBack;
        return this;
    }

    public SelectPopupWindow(Context context) {
        super(context);
        mContext = context;
        View popup = assignViews();
        //设置SelectPicPopupWindow的View
        this.setContentView(popup);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.ActionSheetAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        popup.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mPopLayout.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public SelectPopupWindow addItem(List<ExtendItem> spinnerItems) {
        if (null != spinnerItems) {
            if (spinnerItems.size() >= 7) {
                DisplayMetrics dm = new DisplayMetrics();
                ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mScrollView.getLayoutParams();
                params.height = dm.heightPixels / 2;
                mScrollView.setLayoutParams(params);
            }
            int dividerMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48.0f, mContext.getResources().getDisplayMetrics());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, dividerMargin);
            LinearLayout.LayoutParams paramsLine = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1);
            int color = ContextCompat.getColor(mContext, R.color.colorPrimary);
            Button itemBtn;
            ExtendItem item;
            for (int index = 0; index < spinnerItems.size(); index++) {
                item = spinnerItems.get(index);
                itemBtn = new Button(mContext);
                itemBtn.setTextSize(15.0f);
                itemBtn.setTextColor(color);
                itemBtn.setLayoutParams(params);
                itemBtn.setText(item.getValue());
                itemBtn.setTag(item);
                itemBtn.setOnClickListener(this);
                itemBtn.setAllCaps(false);
                if (0 == index) {
                    if (mBtnTakeTitle.getVisibility() == View.VISIBLE) {
                        itemBtn.setBackgroundResource(spinnerItems.size() == 1 ? R.drawable.btn_bottom : R.drawable.border_top_bottom_gray_selector);
                    } else {
                        itemBtn.setBackgroundResource(spinnerItems.size() == 1 ? R.drawable.btn_dialog_selector : R.drawable.btn_top);
                    }
                } else if (index == spinnerItems.size() - 1) {
                    if (spinnerItems.size() == 2 && mBtnTakeTitle.getVisibility() != View.VISIBLE) {
                        mItemView = new Space(mContext);
                        mItemView.setLayoutParams(paramsLine);
                        mSelectPopPanent.addView(mItemView);
                    }
                    itemBtn.setBackgroundResource(R.drawable.btn_bottom);
                } else {
                    if (mBtnTakeTitle.getVisibility() != View.VISIBLE && index == 1) {
                        itemBtn.setBackgroundResource(R.drawable.border_top_bottom_gray_selector);
                    } else {
                        itemBtn.setBackgroundResource(R.drawable.background_border_bottom_gray_selector);
                    }
                }
                mSelectPopPanent.addView(itemBtn);
            }
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null && null != mPopupBack)
            mPopupBack.selectPopupBack((ExtendItem) v.getTag());
        dismiss();
    }

    public interface SelectPopupBack {
        void selectPopupBack(ExtendItem item);
    }

    public void show(View view) {
        showAtLocation(view, Gravity.CENTER_VERTICAL, 0, 0);
    }


    /**
     * 获取默认的item选项
     *
     * @param items item的字符串
     */
    public SelectPopupWindow addDefExtendItems(String... items) {
        List<ExtendItem> itemList = new ArrayList<>();
        ExtendItem item;
        for (int i = 0; i < items.length; i++) {
            item = new DefExtendItem(i, items[i]);
            itemList.add(item);
        }
        return addItem(itemList);
    }

    public void onDestroy() {
        mContext = null;
        mPopupBack = null;
        mPopLayout = null;
        mSelectPopPanent = null;
        mBtnTakeTitle = null;
        mBtnCancel = null;
        mScrollView = null;
        mItemView = null;
    }


}  