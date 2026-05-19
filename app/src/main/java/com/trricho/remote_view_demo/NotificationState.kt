package com.trricho.remote_view_demo

object NotificationState {
    const val NOTIFICATION_ID = 1001

    var progress: Int = 45
        private set

    var isCompleted: Boolean = false
        private set

    fun bumpProgress(by: Int = 15) {
        if (isCompleted) return
        progress = (progress + by).coerceAtMost(100)
        if (progress >= 100) {
            isCompleted = true
        }
    }

    fun markDone() {
        progress = 100
        isCompleted = true
    }

    fun snooze() {
        progress = (progress - 10).coerceAtLeast(0)
        isCompleted = false
    }

    fun reset() {
        progress = 45
        isCompleted = false
    }
}
