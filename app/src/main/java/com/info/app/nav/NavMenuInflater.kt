package com.info.app.nav

import android.content.Context
import android.content.res.XmlResourceParser
import android.util.AttributeSet
import android.util.Xml
import android.view.InflateException

import com.info.zhangxiaolong.myapp.R

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException

import java.io.IOException

/**
 * Created by zhangxiaolong on 2018/2/20.
 */

class NavMenuInflater internal constructor(private val mContext: Context) {

    internal fun inflate(menuRes: Int, menu: NavMenu) {
        var parser: XmlResourceParser? = null
        try {
            parser = mContext.resources.getXml(menuRes)
            parser?.let {
                val attributeSet = Xml.asAttributeSet(it)
                parseMenu(it, attributeSet, menu)
            }
        } catch (e: XmlPullParserException) {
            throw InflateException("Error inflating menu XML", e)
        } catch (e: IOException) {
            throw InflateException("Error inflating menu XML", e)
        } finally {
            parser?.close()
        }
    }

    /**
     * Called internally to fill the given menu. If a sub menu is seen, it will
     * call this recursively.
     */
    @Throws(XmlPullParserException::class, IOException::class)
    private fun parseMenu(parser: XmlPullParser, attributeSet: AttributeSet, menu: NavMenu) {
        // Look for the root node.
        var type: Int
        try {
            do {
                type = parser.next()
            } while (type != XmlPullParser.START_TAG && type != XmlPullParser.END_DOCUMENT)

            if (type != XmlPullParser.START_TAG) {
                throw InflateException(parser.positionDescription + ": No start tag found!")
            }

            val name = parser.name
            if (XML_MENU == name) {
                var tag = parser.next()
                while (tag != XmlPullParser.END_DOCUMENT) {
                    if (tag == XmlPullParser.START_TAG && XML_ITEM == parser.name) {
                        //获取id, icon , title
                        val a = mContext.obtainStyledAttributes(attributeSet, R.styleable.NavMenuItem)
                        val itemId = a.getResourceId(R.styleable.NavMenuItem_navId, defaultItemId)
                        val itemTitle = a.getText(R.styleable.NavMenuItem_navTitle)
                        val itemIconResId = a.getResourceId(R.styleable.NavMenuItem_navIcon, 0)
                        menu.addMenuItem(NavMenuItem(itemId, itemTitle, itemIconResId))
                        a.recycle()
                    }
                    tag = parser.next()
                }
            } else {
                throw InflateException("start tag is not menu")
            }

        } catch (e: XmlPullParserException) {
            val ie = InflateException(e.message, e)
            ie.stackTrace = EMPTY_STACK_TRACE
            throw ie
        } catch (e: Exception) {
            val ie = InflateException(parser.positionDescription
                    + ": " + e.message, e)
            ie.stackTrace = EMPTY_STACK_TRACE
            throw ie
        }

    }

    companion object {
        private val TAG = "MenuInflater"

        /** Menu tag name in XML.  */
        private val XML_MENU = "menu"

        /** Item tag name in XML.  */
        private val XML_ITEM = "item"

        private val NO_ID = 0

        private val defaultItemId = NO_ID

        /** Empty stack trace used to avoid log spam in re-throw exceptions.  */
        private val EMPTY_STACK_TRACE = arrayOfNulls<StackTraceElement>(0)
    }
}
