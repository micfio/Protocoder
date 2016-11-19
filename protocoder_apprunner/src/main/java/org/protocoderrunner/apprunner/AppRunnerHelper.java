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

package org.protocoderrunner.apprunner;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.protocoderrunner.base.utils.FileIO;
import org.protocoderrunner.base.utils.MLog;
import org.protocoderrunner.models.Project;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AppRunnerHelper {

    protected static final String TAG = AppRunnerHelper.class.getSimpleName();

    public static String MAIN_FILENAME = "main.js";
    private Project currentProject;

    public static String getProjectPath(Project p) {
        return AppRunnerSettings.getFolderPath(p.getSandboxPath());
    }

    // Get code from sdcard
    public static String getCode(Project p) {
        return getCode(p, AppRunnerSettings.MAIN_FILENAME);
    }

    public static String getCode(Project p, String name) {
        String path = p.getFullPath() + File.separator + name;

        return FileIO.loadCodeFromFile(path);
    }

    public static Map<String, Object> readProjectProperties(Context c, Project p) {

        Map<String, Object> map = null;

        String json = getCode(p, "app.conf");
        if (json != null) {
            Gson gson = new Gson();
            Type stringStringMap = new TypeToken<Map<String, Object>>() {}.getType();
            map = gson.fromJson(json, stringStringMap);
        } else {
            map = new HashMap<String, Object>();
        }

        // fill with default properties
        if (!map.containsKey("orientation")) map.put("orientation", "portrait");
        if (!map.containsKey("background_service")) map.put("background_service", false);
        if (!map.containsKey("screen_mode")) map.put("screen_mode", "normal");

        return map;
    }

}