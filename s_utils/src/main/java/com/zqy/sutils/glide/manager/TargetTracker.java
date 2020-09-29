package com.zqy.sutils.glide.manager;


import com.zqy.sutils.glide.request.target.Target;
import com.zqy.sutils.glide.util.Util;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Holds the set of {@link Target}s currently active for a
 * {@link com.zqy.sutils.glide.RequestManager} and forwards on lifecycle events.
 */
public final class TargetTracker implements LifecycleListener {
  private final Set<Target<?>> targets =
      Collections.newSetFromMap(new WeakHashMap<Target<?>, Boolean>());

  public void track( Target<?> target) {
    targets.add(target);
  }

  public void untrack( Target<?> target) {
    targets.remove(target);
  }

  @Override
  public void onStart() {
    for (Target<?> target : Util.getSnapshot(targets)) {
      target.onStart();
    }
  }

  @Override
  public void onStop() {
    for (Target<?> target : Util.getSnapshot(targets)) {
      target.onStop();
    }
  }

  @Override
  public void onDestroy() {
    for (Target<?> target : Util.getSnapshot(targets)) {
      target.onDestroy();
    }
  }


  public List<Target<?>> getAll() {
    return Util.getSnapshot(targets);
  }

  public void clear() {
    targets.clear();
  }
}
