package com.info.zhangxiaolong.myapp.main.nav;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;

import com.info.zhangxiaolong.myapp.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by zhangxiaolong on 2018/2/20.
 */

public class NavMenuInflater {
    private static final String TAG = "MenuInflater";

    /** Menu tag name in XML. */
    private static final String XML_MENU = "menu";

    /** Item tag name in XML. */
    private static final String XML_ITEM = "item";

    private static final int NO_ID = 0;

    private static final int defaultItemId = NO_ID;

    /** Empty stack trace used to avoid log spam in re-throw exceptions. */
    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];

    private final Context mContext;

    NavMenuInflater(Context context) {
        this.mContext = context;
    }

    void inflate(int menuRes, NavMenu menu) {
        XmlResourceParser parser = null;
        try {
            parser = mContext.getResources().getXml(menuRes);
            AttributeSet attributeSet = Xml.asAttributeSet(parser);
            parseMenu(parser, attributeSet , menu);
        } catch (XmlPullParserException | IOException e) {
            throw new InflateException("Error inflating menu XML", e);
        } finally {
            if (parser != null) parser.close();
        }
    }

    /**
     * Called internally to fill the given menu. If a sub menu is seen, it will
     * call this recursively.
     */
    private void parseMenu(XmlPullParser parser, AttributeSet attributeSet , NavMenu menu)
            throws XmlPullParserException, IOException {
        // Look for the root node.
        int type;
        try {
            while ((type = parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
                // Empty
            }
            if (type != XmlPullParser.START_TAG) {
                throw new InflateException(parser.getPositionDescription()
                        + ": No start tag found!");
            }

            final String name = parser.getName();
            if(XML_MENU.equals(name)) {
                int tag = parser.next();
                while (tag != XmlPullParser.END_DOCUMENT) {
                    if(tag == XmlPullParser.START_TAG && XML_ITEM.equals(parser.getName())) {
                        //获取id, icon , title
                        TypedArray a = mContext.obtainStyledAttributes(attributeSet , R.styleable.NavMenuItem);
                        int itemId = a.getResourceId(R.styleable.NavMenuItem_navId, defaultItemId);
                        CharSequence itemTitle = a.getText(R.styleable.NavMenuItem_navTitle);
                        int itemIconResId = a.getResourceId(R.styleable.NavMenuItem_navIcon, 0);
                        menu.addMenuItem(new NavMenuItem(itemId , itemTitle , itemIconResId));
                        a.recycle();
                    }
                    tag = parser.next();
                }
            } else {
                throw new InflateException("start tag is not menu");
            }

        } catch (XmlPullParserException e) {
            final InflateException ie = new InflateException(e.getMessage(), e);
            ie.setStackTrace(EMPTY_STACK_TRACE);
            throw ie;
        } catch (Exception e) {
            final InflateException ie = new InflateException(parser.getPositionDescription()
                    + ": " + e.getMessage(), e);
            ie.setStackTrace(EMPTY_STACK_TRACE);
            throw ie;
        }
    }
}
