
package com.ky.aws.services;

import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

/**
 * PresignedUrlServiceImpl
 * 
 * @author kishora
 *
 */
@Service
public class PresignedUrlServiceImpl implements PresignedUrlService
{

    @Value("${aws.region}")
    String awsRegion;

    @Value("${aws.access_key}")
    String accessKey;

    @Value("${aws.secret}")
    String secret;

    @Value("${aws.bucket}")
    String bucket;

    @Value("${aws.expiry_time_in_millis}")
    long expiryInMillis;

    @Override
    public String getPresignedUrl(final String objectKey)
    {
        final AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(awsRegion)
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secret))).build();
        final Date expiration = new Date(new Date().getTime() + expiryInMillis);
        final GeneratePresignedUrlRequest generatePresignedUrlRequest =
            new GeneratePresignedUrlRequest(bucket, objectKey).withMethod(HttpMethod.GET).withExpiration(expiration);
        final URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

}
