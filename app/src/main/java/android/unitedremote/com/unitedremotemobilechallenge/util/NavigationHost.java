package android.unitedremote.com.unitedremotemobilechallenge.util;

import android.support.v4.app.Fragment;

/**
 * A host (typically an {@code Activity}} that can display fragments and knows how to respond to
 * navigation events.
 */
public interface NavigationHost {
    /**
     * Trigger a navigation to the specified fragment, optionally adding a transaction to the back
     * stack to make this navigation reversible.
     * @param fragment to navigate to
     * @param addToBackstack boolean to know if we should add the fragment to back stack or not
     */
    void navigateTo(Fragment fragment, boolean addToBackstack);
}