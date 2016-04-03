package com.benny.library.kbinding.adapterview.bindings.utils

import android.support.v7.widget.RecyclerView
import com.benny.library.autoadapter.listener.AdapterPagingCompleteListener
import com.benny.library.autoadapter.listener.AdapterPagingListener
import com.benny.library.kbinding.adapterview.bindings.PAGING_LISTENER
import com.benny.library.kbinding.adapterview.bindings.setPagingListener
import rx.Observable
import rx.Subscriber
import rx.android.MainThreadSubscription
import java.util.*

/**
 * Created by benny on 12/26/15.
 */

@Suppress("UNCHECKED_CAST")
class RecyclerViewPagingOnSubscribe(val view: RecyclerView) : Observable.OnSubscribe<Pair<Int, Any?>> {
    override fun call(subscriber: Subscriber<in Pair<Int, Any?>>) {
        val pagingListener = object : AdapterPagingListener<Any?> {
            override fun onLoadPage(receiver: AdapterPagingCompleteListener, previous: Any?, position: Int) {
                if (subscriber.isUnsubscribed) return
                subscriber.onNext(Pair(position, previous));
            }
        }

        view.setPagingListener(pagingListener)
        subscriber.add(object : MainThreadSubscription() {
            override fun onUnsubscribe() {
                (view.tag as HashMap<String, Any?>).put(PAGING_LISTENER, null)
            }
        });
    }
}