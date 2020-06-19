package com.pccontroll.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import java.lang.reflect.Field;

/**
 * @author Sergey Ulizko
 * @company UnitedThinkers
 * @since 2020/06/04
 */
public class UIUtil {

	public static final String SOURCE_SUN_PRO_ITALIC = "fonts/sourcesanspro/italic.ttf";
	public static final String SOURCE_SUN_PRO_BOLD = "fonts/sourcesanspro/bold.otf";
	private static final String SOURCE_SUN_PRO_DEFAULT = "fonts/sourcesanspro/default.ttf";

	public static final String FONT_AWESOME_DEFAULT = "fonts/fontawesome/default.ttf";
	public static final String FONT_AWESOME_BRANDS = "fonts/fontawesome/brands.ttf";

	private static final String BASE_FONT_STYLE = "SERIF";

	public static Typeface getTypeface(Context context, String pathToFont) {
		return Typeface.createFromAsset(context.getAssets(),
				pathToFont);
	}

	public static void overrideFont(Context context) throws NoSuchFieldException, IllegalAccessException {
		final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), SOURCE_SUN_PRO_DEFAULT);
		final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(BASE_FONT_STYLE);
		defaultFontTypefaceField.setAccessible(true);
		defaultFontTypefaceField.set(null, customFontTypeface);
	}

	public static void hideAndroidSystemButtons(View view) {
		view.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_FULLSCREEN
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}
}
