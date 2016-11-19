/*
* Part of Protocoder http://www.protocoder.org
* A prototyping platform for Android devices 
*
* Copyright (C) 2013 Victor Diaz Barrales victormdb@gmail.com
* 
* Protocoder is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Protocoder is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with Protocoder. If not, see <http://www.gnu.org/licenses/>.
*/

package org.protocoderrunner.api.widgets;

import android.content.Context;
import android.widget.ProgressBar;

public class PProgressBar extends ProgressBar {

    public PProgressBar(Context context) {
        super(context);

        // setProgressDrawable(getResources().getDrawable(R.drawable.ui_seekbar_progress));
    }

    public PProgressBar(Context a, int style) {
        super(a, null, style);

        this.setProgress(0);
    }

    public PProgressBar progress(float val) {
        this.setProgress((int) val);
        return this;
    }
}
