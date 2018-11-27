
package com.ky.aws.services;

import org.springframework.stereotype.Service;

/**
 * PresignedUrlService
 * 
 * @author kishora
 *
 */
@Service
public interface PresignedUrlService
{
    /**
     * Generate Presigned Url
     * 
     * @param objectKey
     * @return String
     */
    String getPresignedUrl(String objectKey);
}
