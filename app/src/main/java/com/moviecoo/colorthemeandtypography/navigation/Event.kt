package com.moviecoo.colorthemeandtypography.navigation

/**
 * ## Event Wrapper for One-Time Events 🚨
 *
 * Used as a wrapper for data that is only intended to be consumed once, such as navigation
 * events, Snackbar messages, or Toast notifications. This prevents the event from being
 * processed multiple times, especially upon configuration changes (like screen rotation).
 *
 * @param T The type of content wrapped by the Event.
 * @property content The raw data/content of the event (e.g., a navigation route or message string).
 */
open class Event<out T>(private val content: T) {

    /**
     * Tracks whether the content has already been retrieved and handled by an observer.
     * It is set to `true` after the first call to [getContentIfNotHandled].
     */
    var hasBeenHandled = false
        private set // Prevents external modification of the handled state.

    /**
     * Retrieves the content, and if it hasn't been handled yet, marks it as handled.
     *
     * @return The [content] if it has not been handled; otherwise, returns `null`.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            // Content has already been processed by a previous observer.
            null
        } else {
            // Content is retrieved for the first time; mark it as handled.
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content without marking it as handled.
     *
     * This is useful for testing or for cases where you need to check the event content
     * without triggering the one-time consumption mechanism.
     *
     * @return The raw [content] regardless of whether it has been handled.
     */
    fun peekContent(): T = content
}