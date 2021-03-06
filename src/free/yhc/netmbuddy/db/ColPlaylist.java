/*****************************************************************************
 *    Copyright (C) 2012, 2013 Younghyung Cho. <yhcting77@gmail.com>
 *
 *    This file is part of YTMPlayer.
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as
 *    published by the Free Software Foundation either version 3 of the
 *    License, or (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License
 *    (<http://www.gnu.org/licenses/lgpl.html>) for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package free.yhc.netmbuddy.db;

import static free.yhc.netmbuddy.utils.Utils.eAssert;
import android.content.ContentValues;
import android.provider.BaseColumns;

public enum ColPlaylist implements DB.Col {
    TITLE           ("title",           "text",     null,   "not null"),
    // DESCRIPTION : Not used yet - reserved for future use.
    DESCRIPTION     ("description",     "text",     null,   "not null"),
    THUMBNAIL       ("thumbnail",       "blob",     null,   "not null"),

    // # of videos in this playlist.
    SIZE            ("size",            "integer",  null,   "not null"),

    // --------------------------------------------------------------------
    // newly added at DB version 2
    // --------------------------------------------------------------------
    // Youtube video id for thumbnail.
    THUMBNAIL_YTVID ("thumbnail_vid",   "text",     "\"\"", ""),
    // Reserved fields for future use
    // ENUM name can be changed without affecting DB
    RESERVED0       ("reserved0",       "text",     "\"\"", ""),
    RESERVED1       ("reserved1",       "text",     "\"\"", ""),
    RESERVED2       ("reserved2",       "integer",  "0",    ""),
    RESERVED3       ("reserved3",       "integer",  "0",    ""),
    RESERVED4       ("reserved4",       "blob",     "\"\"", ""),


    ID              (BaseColumns._ID,   "integer",  null,   "primary key autoincrement");

    private final String _mName;
    private final String _mType;
    private final String _mConstraint;
    private final String _mDefault;

    static ContentValues
    createContentValuesForInsert(String title) {
        eAssert(null != title);
        ContentValues cvs = new ContentValues();
        cvs.put(TITLE.getName(), title);
        cvs.put(DESCRIPTION.getName(), "");
        cvs.put(THUMBNAIL.getName(), new byte[0]);
        cvs.put(THUMBNAIL_YTVID.getName(), "");
        cvs.put(SIZE.getName(), 0);

        // --------------------------------------------------------------------
        // newly added at DB version 2 (Those have default values.);
        // So, we don't need to describe values explicitly here.
        // --------------------------------------------------------------------
        return cvs;
    }

    ColPlaylist(String name, String type, String defaultv, String constraint) {
        _mName = name;
        _mType = type;
        _mConstraint = constraint;
        _mDefault = defaultv;
    }
    @Override
    public String getName() { return _mName; }
    @Override
    public String getType() { return _mType; }
    @Override
    public String getConstraint() { return _mConstraint; }
    @Override
    public String getDefault() { return _mDefault; }
}
