
package com.lutDAO;

/**
 *
 * @author manis
 */
public interface progressListener {
    
    void onProgress(int bytesSent, int totalBytes);
    void onComplete();
    void onError(String errorMessage);

}
