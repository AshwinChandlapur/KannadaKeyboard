/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package my.typekannada.ashwin.customkeyboard.blackcj.customkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;

import java.util.List;

public class LatinKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;

    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        /*} else if (key.codes[0] == 113) {

            return true; */
        } else {
            //Log.d("LatinKeyboardView", "KEY: " + key.codes[0]);
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
        //keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(28);
        paint.setColor(Color.LTGRAY);

        List<Key> keys = getKeyboard().getKeys();
        for(Key key: keys) {
            if(key.label != null) {
                if (key.label.equals("q")) {
                    canvas.drawText("1", key.x + (key.width - 25), key.y + 40, paint);
                } else if (key.label.equals("w")) {
                    canvas.drawText("2", key.x + (key.width - 25), key.y + 40, paint);
                } else if (key.label.equals("e")) {
                    canvas.drawText("3", key.x + (key.width - 25), key.y + 40, paint);
                } else if (key.label.equals("r")) {
                    canvas.drawText("4", key.x + (key.width - 25), key.y + 40, paint);
                } else if (key.label.equals("t")) {
                    canvas.drawText("5", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಅ")) {
                    canvas.drawText("್", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಆ")) {
                    canvas.drawText("ಾ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಇ")) {
                    canvas.drawText("ಿ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಈ")) {
                    canvas.drawText("ೀ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಉ")) {
                    canvas.drawText("ು", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಊ")) {
                    canvas.drawText("ೂ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಋ")) {
                    canvas.drawText("ೃ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಎ")) {
                    canvas.drawText("ೆ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಏ")) {
                    canvas.drawText("ೇ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಐ")) {
                    canvas.drawText("ೈ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಒ")) {
                    canvas.drawText("ೊ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಓ")) {
                    canvas.drawText("ೋ", key.x + (key.width - 25), key.y + 40, paint);
                }else if (key.label.equals("ಔ")) {
                    canvas.drawText("ೌ", key.x + (key.width - 25), key.y + 40, paint);
                }
            }

        }
    }
}
