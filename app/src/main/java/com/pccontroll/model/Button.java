package com.pccontroll.model;

import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.pccontroll.ui.components.GamePadButton;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/08
 */

@Entity
public class Button {

	@PrimaryKey
	private long id;
	private String name;
	private int width;
	private int height;
	private int x;
	private int y;
	private int color;
	private int bindChar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getBindChar() {
		return bindChar;
	}

	public void setBindChar(int bindChar) {
		this.bindChar = bindChar;
	}

	public void from(GamePadButton button) {
		id = button.getId();
		name = (String) button.getText();
		width = button.getWidth();
		height = button.getHeight();
		x = button.getxMargin();
		y = button.getyMargin();
		color = button.getBackgroundTintList().getDefaultColor();
		if (button.getBindChar() != null) {
			bindChar = button.getBindChar();
		}
	}

	public void to(GamePadButton button) {
		button.setId((int) id);
		ViewGroup.LayoutParams params = new ConstraintLayout.LayoutParams(width, height);
		button.setLayoutParams(params);
		button.setText(name);
		ConstraintLayout layout = (ConstraintLayout) button.getParent();
		ConstraintSet constraintSet = new ConstraintSet();
		constraintSet.clone(layout);
		constraintSet.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, x);
		button.setxMargin(x);
		button.setyMargin(y);
		constraintSet.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, y);
		constraintSet.applyTo(layout);
		button.setBindChar(bindChar);
	}
}
