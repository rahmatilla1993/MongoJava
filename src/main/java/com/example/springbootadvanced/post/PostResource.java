package com.example.springbootadvanced.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PostResource {

    @Value("${posts.url}")
    private String baseurl;

    public List<Post> findAll() {
        Traverson traverson = new Traverson(URI.create(baseurl), MediaTypes.HAL_JSON);
        var entityModels = traverson.follow("posts")
                .toObject(new ParameterizedTypeReference<CollectionModel<EntityModel<Post>>>() {
                });
        if (entityModels == null) {
            return Collections.emptyList();
        }
        Collection<EntityModel<Post>> entityModelCollection = entityModels.getContent();
        return entityModelCollection.stream()
                .map(EntityModel::getContent)
                .toList();
    }

    public Page<Post> findAll(int page, int size) {
        URI uri = URI.create(String.format("%spaged?page=%d&size=%d", baseurl, page, size));
        Traverson traverson = new Traverson(uri, MediaTypes.HAL_JSON);
        var pagedModel = traverson.follow("self")
                .toObject(new ParameterizedTypeReference<PagedModel<EntityModel<Post>>>() {
                });
        if (pagedModel == null) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 0), 0);
        }
        Pageable pageable = PageRequest.of(page, size);
        PagedModel.PageMetadata metadata = pagedModel.getMetadata();
        Collection<EntityModel<Post>> entityModelCollection = pagedModel.getContent();
        List<Post> posts = entityModelCollection.stream()
                .map(EntityModel::getContent)
                .toList();
        return new PageImpl<>(posts, pageable, Objects.requireNonNull(metadata).getTotalElements());
    }

    public Post getPost(int id) {
        Traverson traverson = new Traverson(URI.create(String.format("%s%d", baseurl, id)), MediaTypes.HAL_JSON);
        EntityModel<Post> entityModel = traverson.follow("self")
                .toObject(new ParameterizedTypeReference<>() {
                });
        if (entityModel == null) {
            return null;
        }
        entityModel.getLinks().forEach(link -> log.info(String.valueOf(link)));
        return entityModel.getContent();
    }
}
