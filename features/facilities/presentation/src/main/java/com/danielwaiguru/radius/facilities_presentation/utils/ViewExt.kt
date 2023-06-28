package com.danielwaiguru.radius.facilities_presentation.utils

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.danielwaiguru.radius.facilities_presentation.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    destructive: Boolean = false,
    onAction: (() -> Unit)? = null,
    onDismiss: () -> Unit = {},
) {
    Snackbar.make(requireView(), message, duration).apply {
        if (destructive) {
            setBackgroundTint(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.holo_red_light
                )
            )
        }
        if (onAction != null) {
            setAction(getString(R.string.retry)) {
                onAction()
            }
        }
        addCallback(
            object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_ACTION) {
                        onDismiss()
                    } else super.onDismissed(transientBottomBar, event)
                }
            }
        )
    }.show()
}