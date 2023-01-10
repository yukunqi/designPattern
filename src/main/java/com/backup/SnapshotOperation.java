package com.backup;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 22:51
 **/
public interface SnapshotOperation {
    /**
     * create
     * @return
     */
    Snapshot createSnapShot();

    /**
     * revert data to a version
     * @param version
     * @return
     */
    Object revert(String version);
}
