package org.solvinden.bitehack2020.backend.controller;

import org.solvinden.bitehack2020.backend.dto.TagInfo;
import org.solvinden.bitehack2020.backend.model.Tag;
import org.solvinden.bitehack2020.backend.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsRepository tagsRepository;

    @Autowired
    public TagsController(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TagInfo> getTags() {
        List<Tag> tags = tagsRepository.findAll();
        return tags.stream().map(t -> new TagInfo(t.name)).collect(Collectors.toList());
    }
}
