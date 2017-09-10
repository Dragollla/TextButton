package dragollla.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * {@link TextButton} is a {@link android.widget.TextView}
 * which changes its text color depending on whether it's pressed or not.
 * <p>
 * Colors can be set via xml attributes:
 * <ul>
 *     <li> Default color - {@link android.R.attr#textColor}.
 *     <li> Active (pressed) color - {@link dragollla.views.R.styleable#TextButton_dr_active_color}
 * </ul>
 * <p>
 * Or in code:
 * <ul>
 *     <li> Default color - {@link #setDefaultColor(int)} and {@link #setDefaultColorRes(int)}
 *     <li> Active color - {@link #setActiveColor(int)} and {@link #setActiveColorRes(int)}
 * </ul>
 *
 * @author Dragollla
 * @version 1.0
 */

public class TextButton extends android.support.v7.widget.AppCompatTextView {

    private int mDefaultColor;
    private int mActiveColor;

    public TextButton(Context context) {
        super(context);
    }
    public TextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }
    public TextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }
    /**
     * Obtains default and active text colors from xml attributes if they are present.
     * <p> Both color default to {@link Color#BLACK}.
     */
    private void init(Context context, AttributeSet attrsSet, int defStyleAttr, int defStyleRes) {
        TypedArray baseAttrs = context.getTheme().obtainStyledAttributes(attrsSet, new int[] { android.R.attr.textColor }, defStyleAttr, defStyleRes);
        try {
            mDefaultColor = baseAttrs.getColor(0, Color.BLACK);
        } finally {
            baseAttrs.recycle();
        }
        TypedArray attrs = context.getTheme().obtainStyledAttributes(attrsSet, R.styleable.TextButton, defStyleAttr, defStyleRes);
        try {
            mActiveColor = attrs.getColor(R.styleable.TextButton_dr_active_color, mDefaultColor);
        } finally {
            attrs.recycle();
        }
    }

    /**
     * Switches between active and default text
     * colors depending on whether button is pressed or not.
     * @see #setDefaultColor(int)
     * @see #setActiveColor(int)
     * @see android.view.View#onTouchEvent(MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setTextColor(mActiveColor);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setTextColor(mDefaultColor);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * Sets text color resource for button default state (not pressed).
     * @param colorId text color resource for default button state.
     * @see #setDefaultColor(int)
     * @see #getDefaultColor()
     */
    public void setDefaultColorRes(@android.support.annotation.ColorRes int colorId) {
        setDefaultColor(ContextCompat.getColor(getContext(), colorId));
    }
    /**
     * Sets text color for button default state (not pressed).
     * @param color text color for default button state.
     * @see #setDefaultColorRes(int)
     * @see #getDefaultColor()
     */
    public void setDefaultColor(int color) {
        mDefaultColor = color;
        setTextColor(color);
    }
    /**
     * @return text color for default button state.
     * @see #setDefaultColor(int)
     */
    public int getDefaultColor() {
        return mDefaultColor;
    }
    /**
     * Sets text color for button pressed state.
     * @param color text color for button pressed state.
     * @see #setActiveColorRes(int)
     * @see #getActiveColor()
     */
    public void setActiveColor(int color) {
        mActiveColor = color;
    }
    /**
     * Sets text color for button pressed state.
     * @param colorId text color resource for button pressed state.
     * @see #setActiveColor(int)
     * @see #getActiveColor()
     */
    public void setActiveColorRes(@android.support.annotation.ColorRes int colorId) {
        setActiveColor(ContextCompat.getColor(getContext(), colorId));
    }
    /**
     * @return text color for button pressed state.
     * @see #setActiveColor(int)
     */
    public int getActiveColor() {
        return mActiveColor;
    }

}
