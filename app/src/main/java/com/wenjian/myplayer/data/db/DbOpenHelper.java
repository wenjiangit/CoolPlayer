package com.wenjian.myplayer.data.db;

import android.content.Context;

import com.wenjian.myplayer.Constants;
import com.wenjian.myplayer.data.network.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Description: DbOpenHelper
 * Date: 2018/1/20
 *
 * @author jian.wen@ubtrobot.com
 */

public class DbOpenHelper extends DaoMaster.OpenHelper {
    public DbOpenHelper(Context context) {
        super(context, Constants.Db.NAME);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

    }
}
