package com.lambdaschool.tiemendo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;

import java.util.Collection;

public abstract class AbstractController {

    <T> Collection<Resource<T>> getContents(Page<T> page, PagedResourcesAssembler<T> assembler) {
        PagedResources<Resource<T>> res = assembler.toResource(page);
        return res.getContent();
    }

    <T> HttpHeaders getHeaders(Page<T> page, PagedResourcesAssembler<T> assembler) {

        PagedResources<Resource<T>> res = assembler.toResource(page);
        HttpHeaders headers = new HttpHeaders();

        res.getLinks().forEach(link -> headers.set(link.getRel(), link.getHref()));

        var metadata = res.getMetadata();
        headers.set("total_pages", String.valueOf(metadata.getTotalPages()));
        headers.set("number", String.valueOf(metadata.getNumber()));
        headers.set("results", String.valueOf(metadata.getTotalElements()));

        return headers;
    }
}
