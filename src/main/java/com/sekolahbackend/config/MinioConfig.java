/*
 * Copyright Jordan LEFEBURE © 2019.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.sekolahbackend.config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.NoResponseException;

@Configuration
@ConditionalOnClass(MinioClient.class)
public class MinioConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(MinioConfig.class);

	@Value("${spring.minio.url}")
	private String url;

	@Value("${spring.minio.access-key}")
	private String accessKey;

	@Value("${spring.minio.secret-key}")
	private String secretKey;

	@Bean
	public MinioClient minioClient() throws InvalidEndpointException, InvalidPortException, IOException,
			InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,
			NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException,
			InvalidResponseException, MinioException {
		MinioClient minioClient = null;
		try {
			minioClient = new MinioClient(url, accessKey, secretKey, false);
		} catch (InvalidEndpointException | InvalidPortException e) {
			LOGGER.error("Error while connecting to Minio", e);
			throw e;
		}
		return minioClient;
	}

}
