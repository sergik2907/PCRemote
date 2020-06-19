package com.pccontroll.ui.viewmodel;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.databinding.BindingAdapter;
import com.pccontroll.integration.WebSocketConnector;
import com.pccontroll.ui.AbstractForm;
import com.pccontroll.ui.AbstractViewModel;
import com.pccontroll.model.Field;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/06
 */
public class MouseFormVM extends AbstractViewModel {

	private GestureDetector detectTouch;

	class Point {
		public int x;
		public int y;

	}

	Point m_startPt;
	Point m_endPt;

	public MouseFormVM(AbstractForm form) {
		super(form);
		handleGestures();
		m_startPt = new Point();
		m_endPt = new Point();
	}

	public void onLeftClick() {
		new WebSocketConnector(getApplication()).send(Field.Mouse.getApiKey() + "=" + "left");
	}

	public void onRightClick() {
		new WebSocketConnector(getApplication()).send(Field.Mouse.getApiKey() + "=" + "right");
	}

	@BindingAdapter("onTouch")
	public static void setOnTouchListener(View view, View.OnTouchListener listener) {
		view.setOnTouchListener(listener);
	}

	public View.OnTouchListener getOnTouch() {
		return new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				boolean flag = detectTouch.onTouchEvent(event);
				Log.d("ON TOUCH DOWN : ", "Touch is coming ... ");
				if (event.getAction() == MotionEvent.ACTION_MOVE && !flag) {
					int dx = (int) event.getX() - m_startPt.x;
					int dy = (int) event.getY() - m_startPt.y;
					m_startPt.x = (int) event.getX();
					m_startPt.y = (int) event.getY();
					Log.d("ON D SCROLL : ", " x : " + dx + " y : " + dy);
					new WebSocketConnector(getApplication()).send(Field.Mouse.getApiKey() + "=" + dx + "x" + dy);
				}
				return true;
			}
		};
	}

	public void handleGestures() {
		detectTouch = new GestureDetector(getForm().getActivity(),
				new GestureDetector.OnGestureListener() {

					@Override
					public boolean onSingleTapUp(MotionEvent e) {
						onLeftClick();
						return true;
					}

					@Override
					public void onShowPress(MotionEvent e) {
					}

					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
											float distanceY) {
						int dx = (int) e2.getX() - m_startPt.x;
						int dy = (int) e2.getY() - m_startPt.y;
						m_startPt.x = (int) e2.getX();
						m_startPt.y = (int) e2.getY();
						new WebSocketConnector(getApplication()).send(Field.Mouse.getApiKey() + "=" + dx + "x" + dy);
						return true;
					}

					@Override
					public void onLongPress(MotionEvent e) {
					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
										   float velocityY) {
						return true;
					}

					@Override
					public boolean onDown(MotionEvent e) {
						m_startPt.x = (int) e.getX();
						m_startPt.y = (int) e.getY();
						Log.d("ON D DOWN : ", " x : " + m_startPt.x + " y : " + m_startPt.y);
						return false;
					}
				});
	}
}
