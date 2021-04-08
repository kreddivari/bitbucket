package com.bitbucket.restapi;

import com.cdancy.bitbucket.rest.BitbucketClient;
import com.cdancy.bitbucket.rest.domain.common.RequestStatus;
import com.cdancy.bitbucket.rest.domain.repository.Repository;
import com.cdancy.bitbucket.rest.domain.system.Version;
import com.cdancy.bitbucket.rest.options.CreateRepository;

public class BitBucketCommunicator {

    public static BitbucketClient getClient(){
         BitbucketClient client = BitbucketClient.builder()
        .endPoint("https://bitbucket.org") // Optional and can be sourced from system/env. Falls back to http://127.0.0.1:7990
        //.credentials("breddivari1986:Y8PYDymACXsgM9nTzBWd") // Optional and can be sourced from system/env and can be Base64 encoded.
        .token("")
        .build();
         client.authValue();

        Version version = client.api().systemApi().version();
        return client;
    }

    public static Repository createRepo(){
        BitbucketClient client= getClient();
        CreateRepository createRepository= CreateRepository.create("testRepo",true);
        Repository repository=client.api().repositoryApi().create("testproject",createRepository);
        return repository;
    }

    public static Repository getRepo(){
        BitbucketClient client= getClient();
        Repository repository=client.api().repositoryApi().get("testproject","testrepo");
        return repository;
    }
    public static Boolean deleteRepo(){
        BitbucketClient client= getClient();
        RequestStatus deleted=client.api().repositoryApi().delete("testproject","testrepo");
        return deleted.value();
    }
}
